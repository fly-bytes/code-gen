package code.generate.jdbc;

import lombok.Data;

@Data
public class DataSource {
    // 连接驱动名
    private String driverName;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // mysql连接地址
    private String url;
    // 表名
    private String tableName;

    // 表前缀，默认生成代码删除前缀
    private String tablePrefix;
}
