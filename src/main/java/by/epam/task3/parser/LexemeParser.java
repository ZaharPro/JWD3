package by.epam.task3.parser;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComposite;
import by.epam.task3.interpreter.BitInterpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LexemeParser extends AbstractParserHandler {
    private static final String LEXEME_DELIMITER = "\\s";
    private static final String BIT_OPERATOR_REGEX = "(\\d+)([\\&\\|\\^\\(\\~\\<+\\>+\\)]){2,}";//


    public LexemeParser() {
        this.nextHandler = new WordParser();
    }

    @Override
    public void parse(TextComponent composite, String text) {
        Pattern pattern = Pattern.compile(BIT_OPERATOR_REGEX);
        String[] lexemes = text.split(LEXEME_DELIMITER);

        for (String lexeme : lexemes) {
            Matcher matcher = pattern.matcher(lexeme);
            if (matcher.find()) {
                BitInterpreter bitInterpreter = BitInterpreter.getInstance();
                lexeme = bitInterpreter.execute(lexeme);
            }

            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            composite.getChildren().add(lexemeComponent);
            nextHandler.parse(lexemeComponent, lexeme);
        }
    }
}
