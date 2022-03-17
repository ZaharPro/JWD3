package by.epam.task3.service.impl;

import by.epam.task3.composite.Composite;
import by.epam.task3.composite.CompositeType;
import by.epam.task3.exception.CustomTextException;
import by.epam.task3.service.TextService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String VOWEL_REGEX = "(?ui)[aeiouyуеыаоэяиюё]";
    private static final String CONSONANT_REGEX = "(?ui)[a-zа-я&&[^aeiouyуеыаоэяию]]";

    private static final Pattern VOWEL_PATTERN = Pattern.compile(VOWEL_REGEX);
    private static final Pattern CONSONANT_PATTERN = Pattern.compile(CONSONANT_REGEX);

    @Override
    public List<Composite> sortParagraphBySentenceCount(Composite text) {
        return text.getChildren().stream()
                .sorted(Comparator.comparingInt(o -> o.getChildren().size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Composite> findSentenceWithLongestWord(Composite text) throws CustomTextException {
        OptionalInt longestWordLength = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(CompositeType.WORD))
                .mapToInt(w -> w.getChildren().size())
                .max();

        int maxLength =  longestWordLength.orElseThrow(() -> new CustomTextException("invalid value"));

        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream()
                        .flatMap(l -> l.getChildren().stream())
                        .filter(w -> w.getType().equals(CompositeType.WORD))
                        .filter(w -> w.getChildren().size() == maxLength))
                .collect(Collectors.toList());
    }

    @Override
    public List<Composite> deleteSentencesWithWordsLessThan(Composite text, int countWord) {
        List<Composite> paragraphList = text.getChildren();
        List<Composite> sentenceList;

        for (Composite paragraph : paragraphList) {
            sentenceList = paragraph.getChildren();
            for (Composite sentence : sentenceList) {
                int countOfWords = 0;
                for (Composite lexeme : sentence.getChildren()) {
                    for (Composite word : lexeme.getChildren()) {
                        if (word.getType().equals(CompositeType.WORD)) {
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

    @Override
    public Map<String, Integer> findCountOfWords(Composite text) {
        Map<String, Integer> sameWords = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(CompositeType.WORD))
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, Integer::sum));
        sameWords.values().removeIf(i -> i == 1);
        return sameWords;
    }

    @Override
    public long countVowelsInText(Composite text) {
        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(CompositeType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .map(Object::toString)
                .filter(let -> VOWEL_PATTERN.matcher(let).matches())
                .count();
    }

    @Override
    public long countConsonantsInText(Composite text) {
        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(CompositeType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .map(Object::toString)
                .filter(let -> CONSONANT_PATTERN.matcher(let).matches())
                .count();
    }
}
