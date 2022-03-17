package by.epam.task3.parser.impl;

import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserHandler extends ParserHandler {
    private static final String SENTENCE_REGEX = "([A-Z]|[А-ЯЁ]).+?([.!?\\u2026])(\\s|$)";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGEX);

    public SentenceParserHandler() {
        nextHandler = new LexemeParserHandler();
    }

    public void parse(TextComponent paragraph, String text) {
        List<TextComponent> sentences = new ArrayList<>();
        Matcher matcher = SENTENCE_PATTERN.matcher(text);
        while (matcher.find()) {
            String sentenceText = matcher.group();
            TextComponent sentence = new TextComponent(TextComponentType.SENTENCE);
            sentences.add(sentence);
            nextHandler.parse(sentence, sentenceText);
        }
        paragraph.setChildren(sentences);
    }
}
