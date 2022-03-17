package by.epam.task3.parser;

import by.epam.task3.composite.Composite;
import by.epam.task3.exception.CustomTextException;

public interface CompositeParser {
    Composite parse(String string);

    Composite parseFromFile(String path) throws CustomTextException;
}
