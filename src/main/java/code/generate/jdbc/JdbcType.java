package code.generate.jdbc;

public enum JdbcType {
    BIGINT("bigint", "Integer", null),

    INT("int", "Integer", null),

    SMALLINT("smallint", "Integer", null),

    INTEGER("integer", "Integer", null),

    BIT("bit", "Boolean", null),

    TINYINT("tinyint", "Integer", null),

    BYTE("binary", "Byte[]", null),

    CHAR("char", "String", null),

    VARCHAR("varchar", "String", null),

    LONGTEXT("longtext", "String", null),

    TEXT("text", "String", null),

    DATE("date", "Date", "java.sql.Date"),
    
    TIME("time", "Date", "java.sql.Date"),

    YEAR("year", "Date", "java.sql.Date"),

    DATETIME("datetime", "Date", "java.sql.Date"),

    TIMESTAMP("timestamp", "Timestamp", "java.sql.Timestamp"),

    BIGDECIMAL("decimal", "BigDecimal", "java.math.BigDecimal"),

    FLOAT("float", "Float", null),

    DOUBLE("double", "Double", null),

    TINYBLOB("tinyblob", "Byte[]", null),

    BLOB("blob", "Byte[]", null),

    MEDIUMBLOB("mediumblob", "Byte[]", null),

    LONGBLOB("longblob", "Byte[]", null),
    ;

    private String fieldType;
    private String javaType;
    private String javaTypePak;

    JdbcType(String fieldType, String javaType, String javaTypePak) {
        this.fieldType = fieldType;
        this.javaType = javaType;
        this.javaTypePak = javaTypePak;
    }

    public static JdbcType getByFieldType(String fieldType) {

        for (JdbcType jdbcType : JdbcType.values()) {
            if (fieldType.equals(jdbcType.fieldType)) {
                return jdbcType;
            }
        }

        return null;
    }

    public static JdbcType getByJavaType(String javaType) {

        for (JdbcType jdbcType : JdbcType.values()) {
            if (javaType.equals(jdbcType.getJavaType())) {
                return jdbcType;
            }
        }

        return null;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getJavaTypePak() {
        return javaTypePak;
    }
}
