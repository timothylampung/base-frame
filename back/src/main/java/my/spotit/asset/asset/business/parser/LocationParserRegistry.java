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


@Component("utilityBillParserRegistry")
public class LocationParserRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(LocationParserRegistry.class);

    private Map<String, LocationParser> handlers = new HashMap<>();

    @Autowired
    private List<LocationParser> parsers;

    @PostConstruct
    public void initParsers() {
        for (LocationParser parser : parsers) {
            LOG.info("Initiating utility bill parser : {}", parser.getParserName());
            handlers.put(parser.getParserName(), parser);
        }
    }

    public void process(File file) throws IOException, ParseException, LocationParserNotFoundException {
        LocationParser parser = getHandler("STANDARD");
        if (parser != null) parser.parse(file);
        else throw new LocationParserNotFoundException();
    }

    private LocationParser getHandler(String parserName) {
        return handlers.get(parserName);
    }
}
