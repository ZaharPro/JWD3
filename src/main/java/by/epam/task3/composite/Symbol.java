package by.epam.task3.composite;

import java.util.List;

public class Symbol implements TextComponent {
    private final TextComponentType type;
    private final char value;

    public Symbol(TextComponentType type, char value) {
        this.type = type;
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChildren() {
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return value == symbol.value &&
                type == symbol.type;
    }

    @Override
    public int hashCode() {
        return (type == null ? 0 : type.hashCode()) + value;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}
