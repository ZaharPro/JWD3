package by.epam.task3.parser.impl;

import by.epam.task3.composite.Composite;
import by.epam.task3.composite.CompositeType;
import by.vlad.jwd_task3.composite.TextComponentType;
import by.vlad.jwd_task3.composite.TextComposite;
import by.vlad.jwd_task3.composite.TextCompositeImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SentenceParserHandler extends ParserHandler {
    private static final String SENTENCE_REGEX = "([A-Z]|[А-ЯЁ]).+?([.!?\\u2026])(\\s|$)";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGEX);

    public SentenceParserHandler() {
        nextHandler = new LexemeParserHandler();
    }

    public void parse(Composite paragraph, String text) {
        List<Composite> sentences = new ArrayList<>();
        Matcher matcher = SENTENCE_PATTERN.matcher(text);
        while (matcher.find()) {
            String sentenceText = matcher.group();
            Composite sentence = new Composite(CompositeType.SENTENCE);
            sentences.add(sentence);
            nextHandler.parse(sentence, sentenceText);
        }
        paragraph.setChildren(sentences);
    }
}
