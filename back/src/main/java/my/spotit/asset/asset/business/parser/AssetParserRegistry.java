package my.spotit.asset.asset.business.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;


@Component("assetParserRegistry")
public class AssetParserRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(AssetParserRegistry.class);

    private Map<String, AssetParser> handlers = new HashMap<>();

    @Autowired
    private List<AssetParser> parsers;

    @PostConstruct
    public void initParsers() {
        for (AssetParser parser : parsers) {
            LOG.info("Initiating utility bill parser : {}", parser.getParserName());
            handlers.put(parser.getParserName(), parser);
        }
    }

    public void process(File file) throws IOException, ParseException, AssetParserNotFoundException {
        AssetParser parser = getHandler("STANDARD");
        if (parser != null) parser.parse(file);
        else throw new AssetParserNotFoundException();
    }

    private AssetParser getHandler(String parserName) {
        return handlers.get(parserName);
    }
}
