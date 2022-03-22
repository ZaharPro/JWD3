package by.epam.task3.parser;

import by.epam.task3.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParserHandler {
    private static final Logger logger = LogManager.getLogger();

    protected AbstractParserHandler nextHandler = DefaultParserHandler.getInstance();

    public AbstractParserHandler() {
    }

    public abstract void parse(TextComponent composite, String text);

    private static class DefaultParserHandler extends AbstractParserHandler {
        private static final DefaultParserHandler INSTANCE = new DefaultParserHandler();

        public static DefaultParserHandler getInstance() {
            return INSTANCE;
        }

        private DefaultParserHandler() {

        }

        @Override
        public void parse(TextComponent composite, String text) {
            logger.info("End of parsers chain");
        }
    }
}
