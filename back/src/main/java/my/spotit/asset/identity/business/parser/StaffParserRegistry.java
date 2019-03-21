package my.spotit.asset.identity.business.parser;

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


@Component("staffParserRegistry")
public class StaffParserRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(StaffParserRegistry.class);

    private Map<String, StaffParser> handlers = new HashMap<>();

    @Autowired
    private List<StaffParser> parsers;

    @PostConstruct
    public void initParsers() {
        for (StaffParser parser : parsers) {
            handlers.put(parser.getParserName(), parser);
        }
    }

    public void process(File file) throws IOException, ParseException, StaffParserNotFoundException {
        StaffParser parser = getHandler("STANDARD");
        if (parser != null) parser.parse(file);
        else throw new StaffParserNotFoundException();
    }

    private StaffParser getHandler(String parserName) {
        return handlers.get(parserName);
    }
}
