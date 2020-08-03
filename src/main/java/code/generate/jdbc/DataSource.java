package code.generate.jdbc;

import lombok.Data;

@Data
public class DataSource {
    private String driverName;
    private String userName;
    private String password;
    private String url;
    private String tableName;

    // 表前缀
    private String tablePrefix;
}
