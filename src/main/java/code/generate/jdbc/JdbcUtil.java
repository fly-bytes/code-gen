package code.generate.jdbc;

import code.generate.bean.TableInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcUtil {
    public static Connection getConnection(DataSource dataSource) {
        Connection connection = null;
        try {
            Class.forName(dataSource.getDriverName());
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUserName(), dataSource.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

    public static List<TableInfo> getMateData(DataSource dataSource) {
        return getMateData(getConnection(dataSource), dataSource.getTableName());
    }

    public static List<TableInfo> getMateData(Connection connection, String tableName) {
        List<TableInfo> infoList = new LinkedList<TableInfo>();
        String sql = "SELECT " +
                            "COLUMN_NAME AS 'field', " +
                            "COLUMN_TYPE AS 'fieldType', " +
                            "IF " +
                            "( column_key = 'PRI', 'PRI', column_key ) AS 'fieldKey', " +
                            "COLUMN_COMMENT AS 'fieldNote' " +
                      "FROM  information_schema.`COLUMNS`,(SELECT  @i := 0 ) AS it  " +
                      "   WHERE  TABLE_NAME = ?   ORDER BY  TABLE_NAME, ORDINAL_POSITION;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tableName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TableInfo info = new TableInfo();
                info.setField(rs.getString("field"));
                info.setFieldType(rs.getString("fieldType"));
                info.setFieldNote(rs.getString("fieldNote"));
                info.setFieldKey(rs.getString("fieldKey"));

                infoList.add(info);
            }

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return infoList;
    }
}
