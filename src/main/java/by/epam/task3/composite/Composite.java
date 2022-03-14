package by.epam.task3.composite;

import java.util.List;

public class Composite {
    private CompositeType type;
    private String string;
    private List<Composite> children;

    public Composite(CompositeType type) {
        this.type = type;
    }

    public Composite(CompositeType type, String string) {
        this(type);
        this.string = string;
    }

    public CompositeType getType() {
        return type;
    }

    public void setType(CompositeType type) {
        this.type = type;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<Composite> getChildren() {
        return children;
    }

    public void setChildren(List<Composite> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composite composite = (Composite) o;
        return type == composite.type &&
                string == null ? composite.string == null : string.equals(composite.string) &&
                children == null ? composite.children == null : children.equals(composite.children);
    }

    @Override
    public int hashCode() {
        int number = 31;
        int hash = 1;
        hash = number * hash + (type == null ? 0 : type.hashCode());
        hash = number * hash + (string == null ? 0 : string.hashCode());
        hash = number * hash + (children == null ? 0 : children.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Composite{")
                .append("type=").append(type)
                .append(", string='").append(string).append('\'')
                .append(", children=").append(children)
                .append('}')
                .toString();
    }
}
