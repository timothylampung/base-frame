package my.spotit.asset.config.hibernate;

import org.hibernate.dialect.PostgreSQL81Dialect;
import java.sql.Types;

public class CustomPostgreSQLDialect extends PostgreSQL81Dialect {
    public CustomPostgreSQLDialect() {
        super();
        registerColumnType(Types.BLOB, "bytea");
    }
}