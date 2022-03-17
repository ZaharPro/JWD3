package by.epam.task3.parser.impl;

import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComponent;

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

    public void parse(TextComponent textResult, String text) {
        List<TextComponent> paragraphs = Arrays.stream(PARAGRAPH_DELIMITER_PATTERN.split(text))
                .map(paragraphText -> {
                    TextComponent paragraph = new TextComponent(TextComponentType.PARAGRAPH);
                    nextHandler.parse(paragraph, paragraphText);
                    return paragraph;
                })
                .collect(Collectors.toList());
        textResult.setChildren(paragraphs);
    }
}
