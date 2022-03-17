package by.epam.task3.parser.impl;

import by.epam.task3.composite.TextComponent;

public abstract class ParserHandler {
    protected ParserHandler nextHandler;

    public abstract void parse(TextComponent parent, String text);
}
