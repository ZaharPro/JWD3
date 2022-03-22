package by.epam.task3.parser;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParserHandler {
    private static final String SENTENCE_REGEX = "([A-Z]|[А-ЯЁ]).+?([.!?\\u2026])(\\s|$)";

    public SentenceParser() {
        this.nextHandler = new LexemeParser();
    }

    @Override
    public void parse(TextComponent composite, String text) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            String sentence = matcher.group();
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            composite.getChildren().add(sentenceComponent);
            nextHandler.parse(sentenceComponent, sentence);
        }
    }
}
