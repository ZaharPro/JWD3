package by.epam.task3.parser;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.exception.CustomTextException;

public interface CompositeParser {
    TextComponent parse(String string);

    TextComponent parseFromFile(String path) throws CustomTextException;
}
