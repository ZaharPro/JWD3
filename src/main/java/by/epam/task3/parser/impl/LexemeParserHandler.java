package by.epam.task3.parser.impl;

import by.epam.task3.util.BitOperation;
import by.epam.task3.util.BitOperationImpl;
import by.epam.task3.composite.Composite;
import by.epam.task3.composite.CompositeType;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LexemeParserHandler extends ParserHandler {
    private static final String LEXEME_DELIMITER = "\\s";
    private static final String BIT_OPERATION_REGEX = "(\\d+)([\\&\\|\\^\\(\\~\\<+\\>+\\)]){2,}";//

    private static final Pattern LEXEME_DELIMITER_PATTERN = Pattern.compile(LEXEME_DELIMITER);
    Pattern pattern = Pattern.compile(BIT_OPERATION_REGEX);

    public LexemeParserHandler() {
        nextHandler = new WordParserHandler();
    }

    public void parse(Composite paragraph, String text) {
        List<Composite> lexemes = Arrays.stream(LEXEME_DELIMITER_PATTERN.split(text))
                .map(lexemeText -> {
                    Composite lexeme = new Composite(CompositeType.LEXEME);

                    Matcher matcher = pattern.matcher(lexemeText);
                    if (matcher.find()) {
                        BitOperation bitOperation = BitOperationImpl.getInstance();
                        int value = bitOperation.calculate(lexemeText);
                        lexemeText = Integer.toString(value);
                    }
                    nextHandler.parse(lexeme, lexemeText);
                    return lexeme;
                })
                .collect(Collectors.toList());
        paragraph.setChildren(lexemes);
    }
}