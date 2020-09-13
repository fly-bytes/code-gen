package code.generate;

import code.generate.config.GenerateConfig;
import code.generate.jdbc.DataSource;

public class CodeGenEg {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUserName("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/tds?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8");
        dataSource.setTableName("td_point_info");
        dataSource.setTablePrefix("tb_");

        GenerateConfig generateConfig = new GenerateConfig();
        generateConfig.setDataSource(dataSource);
        generateConfig.setBasePkg("cn.liubingxu.discuss");
        generateConfig.exec();
    }
}
