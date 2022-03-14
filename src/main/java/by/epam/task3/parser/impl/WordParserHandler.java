package by.epam.task3.parser.impl;


import by.epam.task3.composite.Composite;
import by.epam.task3.composite.CompositeType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParserHandler extends ParserHandler {
    private static final String WORD_PUNCTUATION_REGEX = "[0-9a-zA-Zа-яА-ЯёЁ']+|[\\p{Punct}\\u2026]";
    private static final String WORD_REGEX = "[0-9a-zA-Zа-яА-ЯёЁ']+";

    private static final Pattern WORD_PUNCTUATION_PATTERN = Pattern.compile(WORD_PUNCTUATION_REGEX);
    private static final Pattern WORD_PATTERN = Pattern.compile(WORD_REGEX);

    public WordParserHandler() {
       // this.nextHandler = new LetterParser();
    }

    @Override
    public void parse(Composite component, String text) {
        List<Composite> words = new ArrayList<>();

        Matcher matcher = WORD_PUNCTUATION_PATTERN.matcher(text);
        while (matcher.find()) {
            String wordText = matcher.group();

            Matcher wordMather = WORD_PATTERN.matcher(wordText);
            if (wordMather.matches()) {
                Composite word = new Composite(CompositeType.WORD);
                word.setString(wordText);
                words.add(word);
            } else {
                Composite punctuation = new Composite(CompositeType.PUNCTUATION);
                punctuation.setString(Character.toString(wordText.charAt(0)));
                words.add(punctuation);
            }
        }
        component.setChildren(words);
    }
}
