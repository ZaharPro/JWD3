package by.epam.task3.parser.impl;

import by.epam.task3.composite.Composite;
import by.epam.task3.composite.CompositeType;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParagraphParserHandler extends ParserHandler {
    private static final String PARAGRAPH_DELIMITER_REGEX = "\\n";
    private static final Pattern PARAGRAPH_DELIMITER_PATTERN = Pattern.compile(PARAGRAPH_DELIMITER_REGEX);

    public ParagraphParserHandler() {
        nextHandler = new SentenceParserHandler();
    }

    public void parse(Composite textResult, String text) {
        List<Composite> paragraphs = Arrays.stream(PARAGRAPH_DELIMITER_PATTERN.split(text))
                .map(paragraphText -> {
                    Composite paragraph = new Composite(CompositeType.PARAGRAPH);
                    nextHandler.parse(paragraph, paragraphText);
                    return paragraph;
                })
                .collect(Collectors.toList());
        textResult.setChildren(paragraphs);
    }
}
