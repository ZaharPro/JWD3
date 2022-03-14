package by.epam.task3.parser.impl;

import by.epam.task3.composite.Composite;

public abstract class ParserHandler {
    protected ParserHandler nextHandler;

    public abstract void parse(Composite parent, String text);
}
