package by.epam.task3.service;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.exception.CustomTextException;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphBySentenceCount(TextComponent text);

    List<TextComponent> findSentenceWithLongestWord(TextComponent text) throws CustomTextException;

    List<TextComponent> deleteSentencesWithWordsLessThan(TextComponent text, int countWord);

    Map<String, Integer> findCountOfWords(TextComponent text);

    long countVowelsInText(TextComponent text);

    long countConsonantsInText(TextComponent text);
}