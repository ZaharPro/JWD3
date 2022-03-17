package by.epam.task3.service;

import by.epam.task3.composite.Composite;
import by.epam.task3.exception.CustomTextException;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<Composite> sortParagraphBySentenceCount(Composite text);
    List<Composite> findSentenceWithLongestWord(Composite text) throws CustomTextException;
    List<Composite> deleteSentencesWithWordsLessThan(Composite text, int countWord);
    Map<String, Integer> findCountOfWords(Composite text);
    long countVowelsInText(Composite text);
    long countConsonantsInText(Composite text);
}