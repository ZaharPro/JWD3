package by.epam.task3.composite;

public class Symbol extends TextComponent {
    private char value;

    public Symbol(TextComponentType type) {
        super(type);
    }

    public Symbol(TextComponentType type, char value) {
        super(type);
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Symbol symbol = (Symbol) o;
        return value == symbol.value;
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + value;
    }
}
