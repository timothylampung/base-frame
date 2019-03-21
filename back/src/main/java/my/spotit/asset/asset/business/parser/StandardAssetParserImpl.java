package my.spotit.asset.asset.business.parser;

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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetImpl;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.asset.domain.model.DexLocationImpl;

@Component
public class StandardAssetParserImpl extends AssetParser {
    private static final Logger LOG = LoggerFactory.getLogger(StandardAssetParserImpl.class);
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

            String description = row.getCell(ColIndex.DESCRIPTION).getStringCellValue();
            String assetCode = row.getCell(ColIndex.ASSET_CODE).getStringCellValue();
            String locationCode = row.getCell(ColIndex.LOCATION_CODE).getStringCellValue();
            String category = row.getCell(ColIndex.CATEGORY).getStringCellValue();
            double quantity = row.getCell(ColIndex.QUANTITY).getNumericCellValue();
            double cost = row.getCell(ColIndex.COST).getNumericCellValue();

            DexAsset asset = new DexAssetImpl();
            asset.setCode("AST-" + System.currentTimeMillis());
            asset.setDescription(description);
            asset.setCategory(category);
            asset.setLocation(locationDao.findByCode(locationCode));
            asset.setAssetCode(assetCodeDao.findAssetCodeByCode(assetCode));
            asset.setQuantity(BigDecimal.valueOf(quantity).longValue());
            asset.setCost(BigDecimal.valueOf(cost));
            assetDao.save(asset, securityService.getCurrentUser());
        }
    }

    @Override
    public String getParserName() {
        return "STANDARD";
    }

    interface ColIndex {
        int DESCRIPTION = 0;
        int ASSET_CODE = 1;
        int LOCATION_CODE = 2;
        int CATEGORY = 3;
        int QUANTITY = 4;
        int COST = 5;
    }
}
