package by.epam.task3.parser;

import by.epam.task3.composite.Composite;
import by.vlad.jwd_task3.exception.CustomException;

public interface CompositeParser {
    Composite parse(String string);

    Composite parseFromFile(String path) throws CustomException;
}
