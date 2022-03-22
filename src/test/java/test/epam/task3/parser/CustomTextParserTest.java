package test.epam.task3.parser;

import by.epam.task3.composite.Symbol;
import by.epam.task3.composite.TextComponent;
import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComposite;
import by.epam.task3.parser.ParagraphParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomTextParserTest {
    private ParagraphParser parser;

    @BeforeClass
    private void setUp(){
        parser = new ParagraphParser();
    }

    @Test
    public void testFileReader() {
        String data = "\tIt was.";

        TextComponent word1 = new TextComposite(TextComponentType.WORD);
        TextComponent word2 = new TextComposite(TextComponentType.WORD);

        TextComponent punct = new Symbol(TextComponentType.PUNCTUATION, '.');

        TextComponent lexeme1 = new TextComposite(TextComponentType.LEXEME);
        TextComponent lexeme2 = new TextComposite(TextComponentType.LEXEME);

        TextComponent sentence = new TextComposite(TextComponentType.SENTENCE);
        TextComponent paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        TextComponent expected = new TextComposite(TextComponentType.TEXT);

        word1.getChildren().add(new Symbol(TextComponentType.LETTER,'I'));
        word1.getChildren().add(new Symbol(TextComponentType.LETTER,'t'));

        word2.getChildren().add(new Symbol(TextComponentType.LETTER,'w'));
        word2.getChildren().add(new Symbol(TextComponentType.LETTER,'a'));
        word2.getChildren().add(new Symbol(TextComponentType.LETTER,'s'));

        lexeme1.getChildren().add(word1);
        lexeme2.getChildren().add(word2);
        lexeme2.getChildren().add(punct);

        sentence.getChildren().add(lexeme1);
        sentence.getChildren().add(lexeme2);

        paragraph.getChildren().add(sentence);
        expected.getChildren().add(paragraph);

        TextComponent actual = new TextComposite(TextComponentType.TEXT);
        parser.parse(actual, data);
        Assert.assertEquals(actual.toString(), expected.toString());
    }
}
