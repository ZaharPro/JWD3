package by.epam.task3.parser;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.composite.TextComposite;
import by.epam.task3.composite.TextComponentType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphParser extends AbstractParserHandler {
    public static final String PARAGRAPH_DELIMITER = "\\n";

    public ParagraphParser() {
        this.nextHandler = new SentenceParser();
    }

    @Override
    public void parse(TextComponent composite, String text) {
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            composite.getChildren().add(paragraphComponent);
            nextHandler.parse(paragraphComponent, paragraph);
        }
    }
}