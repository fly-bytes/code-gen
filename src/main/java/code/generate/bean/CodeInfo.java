package code.generate.bean;

import code.generate.jdbc.DataSource;
import code.generate.jdbc.JdbcType;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class CodeInfo {
    private List<TableInfo> tableInfo;

    private String entry;
    private String controller;
    private String service;
    private String serviceImpl;
    private String repository;

    private String basePkg;
    private String entryPkg;
    private String controllerPkg;
    private String servicePkg;
    private String serviceImplPkg;
    private String repositoryPkg;

    // 实体类需要导入的包
    private Set<String> beanPkgInfo;
    // repository需要导入的主键包
    private String repositoryFkPkg;
    private String repositoryFkType;

    public CodeInfo(List<TableInfo> mateData, DataSource dataSource, String pkg) {
        this.tableInfo = mateData;
        this.basePkg = pkg;
        initData(dataSource);

        dbType2JavaType();
    }

    private void dbType2JavaType() {
        beanPkgInfo = new HashSet<>();

        for (TableInfo info : tableInfo) {
            JdbcType jdbcType = JdbcType.getByFieldType(getJdbcType(info.getFieldType()));

            info.setFieldType(jdbcType.getJavaType());
            info.setField(lineToHump(info.getField()));

            if (jdbcType.getJavaTypePak() != null) {
                beanPkgInfo.add(jdbcType.getJavaTypePak());
            }
        }

        tableInfo.stream().filter(x -> "PRI".equals(x.getFieldKey())).findFirst().ifPresent(x -> {
            this.repositoryFkType = x.getFieldType();
            this.repositoryFkPkg = JdbcType.getByFieldType(x.getFieldType().toLowerCase()).getJavaTypePak();
        });
    }

    private String getJdbcType(String field) {
        if (field.contains("(")) {
            field = field.substring(0, field.indexOf("("));
        }
        return field;
    }

    public void initData(DataSource dataSource) {
        String tableName = dataSource.getTableName();
        String tablePrefix = dataSource.getTablePrefix();
        if (tablePrefix != null && tablePrefix != "") {
            tableName = tableName.replace(tablePrefix, "");
        }

        this.entry = firstUpper(lineToHump(tableName));
        this.controller = entry + "Controller";
        this.service = entry + "Service";
        this.serviceImpl = entry + "ServiceImpl";
        this.repository = entry + "Repository";

        this.entryPkg = basePkg + ".bean";
        this.controllerPkg = basePkg + ".controller";
        this.servicePkg = basePkg + ".service";
        this.serviceImplPkg = basePkg + ".service" + ".serviceImpl";
        this.repositoryPkg = basePkg + ".repository";
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str.toLowerCase());
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String firstUpper(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }

        return new String(chars);
    }
}
