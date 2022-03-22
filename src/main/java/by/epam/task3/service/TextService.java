package by.epam.task3.service;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.composite.TextComponentType;
import by.epam.task3.exception.CustomTextException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextService {
    private static final String VOWEL_REGEX = "(?ui)[aeiouyуеыаоэяиюё]";
    private static final String CONSONANT_REGEX = "(?ui)[a-zа-я&&[^aeiouyуеыаоэяию]]";

    public List<TextComponent> sortParagraphBySentenceCount(TextComponent text) {
        return text.getChildren().stream()
                .sorted(Comparator.comparingInt(o -> o.getChildren().size()))
                .collect(Collectors.toList());
    }

    public List<TextComponent> findSentenceWithLongestWord(TextComponent text) throws CustomTextException {
        OptionalInt longestWordLength = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .mapToInt(w -> w.getChildren().size())
                .max();

        longestWordLength.orElseThrow(() -> new CustomTextException("invalid value"));
        int maxLength = longestWordLength.getAsInt();

        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream()
                        .flatMap(l -> l.getChildren().stream())
                        .filter(w -> w.getType().equals(TextComponentType.WORD))
                        .filter(w -> w.getChildren().size() == maxLength))
                .collect(Collectors.toList());
    }

    public List<TextComponent> deleteSentencesWithWordsLessThan(TextComponent text, int countWord) {
        List<TextComponent> paragraphList = text.getChildren();

        for (TextComponent paragraph : paragraphList) {
            List<TextComponent> sentenceList = paragraph.getChildren();
            for (TextComponent sentence : sentenceList) {
                int countOfWords = 0;
                for (TextComponent lexeme : sentence.getChildren()) {
                    for (TextComponent word : lexeme.getChildren()) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            countOfWords++;
                        }
                    }
                }
                if (countOfWords < countWord) {
                    paragraph.getChildren().remove(sentence);
                }
            }
        }

        return paragraphList;
    }

    public Map<String, Integer> findCountOfWords(TextComponent text) {

        Map<String, Integer> counter = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, Integer::sum));

        counter.values().removeIf(i -> i == 1);

        return counter;
    }

    public long countVowelsInText(TextComponent text) {
        Pattern pattern = Pattern.compile(VOWEL_REGEX);

        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .map(Object::toString)
                .filter(let -> pattern.matcher(let).matches())
                .count();
    }

    public long countConsonantsInText(TextComponent text) {
        Pattern pattern = Pattern.compile(CONSONANT_REGEX);

        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(TextComponentType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .map(Object::toString)
                .filter(let -> pattern.matcher(let).matches())
                .count();
    }
}
