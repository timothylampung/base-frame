package my.spotit;


import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author canang technologies
 */
public class SchemaExporter {
    public static void main(String[] args) throws FileNotFoundException {
        HibernateExporterUtil exporter = new HibernateExporterUtil(
                "org.hibernate.dialect.PostgreSQL82Dialect",
                "my.spotit.asset");
        exporter.setGenerateDropQueries(true);
        exporter.export(new File("CREATE.sql"));
    }
}
