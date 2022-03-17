package by.epam.task3.parser.impl;

import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComponent;
import by.epam.task3.exception.CustomTextException;
import by.epam.task3.parser.CompositeParser;
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
    public TextComponent parseFromFile(String path) throws CustomTextException {
        String text;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            text = bufferedReader.lines()
                    .map(l -> l.replace("    ", ""))
                    .map(l -> l + "\n")
                    .collect(Collectors.joining());
        } catch (IOException e) {
            logger.error("IOException during read data from file", e);
            throw new CustomTextException("IOException during read data from file", e);
        }
        return parse(text);
    }

    @Override
    public TextComponent parse(String string) {
        TextComponent parseResult = new TextComponent(TextComponentType.PARSE_RESULT);
        handler.parse(parseResult, string);
        return parseResult;
    }
}
