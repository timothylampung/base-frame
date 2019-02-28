package my.spotit;


import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author canang technologies
 */
public class SchemaExporter {

    private static final String OUTPUT_FILE = "./data/src/test/resources/ddl/CREATE.sql";


    public static void main(String[] args) throws FileNotFoundException {
        HibernateExporterUtil exporter = new HibernateExporterUtil(
                "org.hibernate.dialect.PostgreSQL82Dialect",
                "my.spotit.asset", "my.spotit.asset.dashboard");
        exporter.setGenerateDropQueries(false);
        exporter.export(new File(OUTPUT_FILE));
    }
}
