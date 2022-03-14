package by.epam.task3.parser.impl;

import by.epam.task3.composite.Composite;
import by.epam.task3.composite.CompositeType;
import by.epam.task3.parser.CompositeParser;
import by.vlad.jwd_task3.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class CompositeParseImpl implements CompositeParser {
    private static final Logger logger = LogManager.getLogger();
    private final ParserHandler handler = new ParagraphParserHandler();

    @Override
    public Composite parseFromFile(String path) throws CustomException {
        String text;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            text = bufferedReader.lines()
                    .map(l -> l.replace("    ", ""))
                    .map(l -> l + "\n")
                    .collect(Collectors.joining());
        } catch (IOException e) {
            logger.error("IOException during read data from file", e);
            throw new CustomException("IOException during read data from file", e);
        }
        return parse(text);
    }

    @Override
    public Composite parse(String string) {
        Composite parseResult = new Composite(CompositeType.PARSE_RESULT);
        handler.parse(parseResult, string);
        return parseResult;
    }
}
