package my.spotit.asset.asset.business.parser;

import com.google.common.collect.Lists;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.asset.domain.model.DexLocationImpl;

@Component
public class StandardLocationParserImpl extends LocationParser {
    private static final Logger LOG = LoggerFactory.getLogger(StandardLocationParserImpl.class);
    private static final int START_ROW_INDEX = 1;

    @Override
    public void parse(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(is);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        ArrayList<Object> errors = Lists.newArrayList();
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getRowNum() < START_ROW_INDEX) continue;

            String name = row.getCell(ColIndex.NAME).getStringCellValue();
            String address = row.getCell(ColIndex.ADDRESS).getStringCellValue();
            LOG.debug("{}) code {}, address {} description {}",
                    new Object[]{
                            row.getRowNum(),
                            name,
                            address
                    });

            DexLocation location = new DexLocationImpl();
            location.setCode("LCTN-" + System.currentTimeMillis());
            location.setName(name);
            location.setDescription(name);
            location.setAddress(address);
            locationDao.save(location, securityService.getCurrentUser());
        }
    }

    @Override
    public String getParserName() {
        return "STANDARD";
    }

    interface ColIndex {
        int NAME = 0;
        int ADDRESS = 1;
    }
}
