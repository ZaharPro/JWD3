package by.epam.task3.composite;

import java.util.List;

public class TextComponent {
    private TextComponentType type;
    private List<TextComponent> children;

    public TextComponent(TextComponentType type) {
        this.type = type;
    }

    public TextComponentType getType() {
        return type;
    }

    public void setType(TextComponentType type) {
        this.type = type;
    }

    public List<TextComponent> getChildren() {
        return children;
    }

    public void setChildren(List<TextComponent> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComponent that = (TextComponent) o;
        return type == that.type &&
                children == null ? that.children == null : children.equals(that.children);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + (type == null ? 0 : type.hashCode());
        hash = hash * 31 + (children == null ? 0 : children.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("TextComposite{")
                .append("type=").append(type)
                .append(", children=").append(children)
                .append('}')
                .toString();
    }
}
