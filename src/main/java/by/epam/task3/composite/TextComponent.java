package by.epam.task3.composite;

import java.util.List;

public interface TextComponent {
    List<TextComponent> getChildren();

    TextComponentType getType();
}