package code.generate.config;

import code.generate.bean.CodeInfo;
import code.generate.bean.TableInfo;
import code.generate.freemaker.FreeMakerTemplate;
import code.generate.jdbc.DataSource;
import code.generate.jdbc.JdbcUtil;
import lombok.Data;

import java.util.List;

@Data
public class GenerateConfig {
    // 数据库配置信息
    private DataSource dataSource;
    // 生成包路径
    private String basePkg;

    private FreeMakerTemplate freeMakerTemplate;

    private String entryFtl;
    private String controllerFtl;
    private String serviceFtl;
    private String serviceImplFtl;
    private String repositoryFtl;

    public void exec() {
        if (dataSource == null) {
            System.out.println("请配置datasource");
            return;
        }

        if (basePkg == null || basePkg == "") {
            System.out.println("请配置包路径");
            return;
        }

        List<TableInfo> mateData = JdbcUtil.getMateData(dataSource);
        CodeInfo codeInfo = new CodeInfo(mateData, dataSource, basePkg);

        FreeMakerTemplate freeMakerTemplate = new FreeMakerTemplate(entryFtl, controllerFtl, serviceFtl, serviceImplFtl, repositoryFtl);
        freeMakerTemplate.generate(codeInfo);
    }
}
