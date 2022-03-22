package by.epam.task3.composite;

public enum TextComponentType {
    LETTER,
    PUNCTUATION,
    WORD,
    LEXEME("", " "),
    SENTENCE,
    PARAGRAPH("\t", "\n"),
    TEXT;

    private String prefix = "";
    private String postfix = "";

    TextComponentType(){
    }

    TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
