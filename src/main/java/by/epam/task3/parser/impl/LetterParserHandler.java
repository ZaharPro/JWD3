package by.epam.task3.parser.impl;


import by.epam.task3.composite.Symbol;
import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class LetterParserHandler extends ParserHandler {
    @Override
    public void parse(TextComponent component, String text) {
        char[] letters = text.toCharArray();
        List<TextComponent> children = new ArrayList<>(letters.length);
        for (char letter : letters) {
            children.add(new Symbol(TextComponentType.LETTER, letter));
        }
        component.setChildren(children);
    }
}
