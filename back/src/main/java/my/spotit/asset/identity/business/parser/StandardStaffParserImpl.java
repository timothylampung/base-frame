package my.spotit.asset.identity.business.parser;

import com.google.common.collect.Lists;

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

import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.identity.domain.model.DexStaffImpl;

@Component
public class StandardStaffParserImpl extends StaffParser {
    private static final Logger LOG = LoggerFactory.getLogger(StandardStaffParserImpl.class);
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

            // todo
            String username = row.getCell(ColIndex.USERNAME).getStringCellValue();
            String email = row.getCell(ColIndex.EMAIL).getStringCellValue();
            String name = row.getCell(ColIndex.NAME).getStringCellValue();

            DexStaff staff = new DexStaffImpl();
            staff.setCode("AST-" + System.currentTimeMillis());
            staffDao.save(staff, securityService.getCurrentUser());
        }
    }

    @Override
    public String getParserName() {
        return "STANDARD";
    }

    interface ColIndex {
        int USERNAME = 0;
        int EMAIL = 1;
        int NAME = 2;
    }
}
