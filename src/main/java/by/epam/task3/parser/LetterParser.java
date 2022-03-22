package by.epam.task3.parser;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.Symbol;

import java.util.List;

public class LetterParser extends AbstractParserHandler {
    @Override
    public void parse(TextComponent composite, String text) {
        char[] letters = text.toCharArray();
        List<TextComponent> children = composite.getChildren();
        for (char letter : letters) {
            children.add(new Symbol(TextComponentType.LETTER, letter));
        }
    }
}
