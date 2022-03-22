package by.epam.task3.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private final TextComponentType type;
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public List<TextComponent> getChildren() {
        return components;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        if (type != that.type) return false;
        return components.equals(that.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (type == null ? 17 : type.hashCode());
        result = prime * result + components.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(type.getPrefix());
        components.forEach(c -> string.append(c.toString()));
        string.append(type.getPostfix());
        return string.toString();
    }
}