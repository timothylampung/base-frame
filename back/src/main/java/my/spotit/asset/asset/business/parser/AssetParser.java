package my.spotit.asset.asset.business.parser;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.persistence.EntityManager;

import my.spotit.asset.asset.domain.dao.DexAssetCodeDao;
import my.spotit.asset.asset.domain.dao.DexAssetDao;
import my.spotit.asset.asset.domain.dao.DexLocationDao;
import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;

/**
 * @author : alif.razak@canang.com.my
 * @since : 3/9/2019 1:18 AM
 */
public abstract class AssetParser {

    abstract public void parse(File file) throws IOException, ParseException;

    abstract public String getParserName();
}
