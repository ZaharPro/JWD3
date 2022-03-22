package by.epam.task3._main;

import by.epam.task3.composite.TextComponent;
import by.epam.task3.composite.TextComponentType;
import by.epam.task3.composite.TextComposite;
import by.epam.task3.exception.CustomTextException;
import by.epam.task3.parser.ParagraphParser;
import by.epam.task3.reader.TextFileReader;
import by.epam.task3.service.TextService;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "src\\main\\resources\\data.txt";

        TextFileReader fileReader = new TextFileReader();

        String text;
        try {
            text = fileReader.readTextFromFile(filePath);

            TextComponent textComponent = new TextComposite(TextComponentType.TEXT);
            ParagraphParser parser = new ParagraphParser();
            System.out.println("parser phase");

            parser.parse(textComponent, text);

            System.out.println("is parsed");

            System.out.println(textComponent.toString());
            System.out.println(1<<3);
            System.out.println(~6&9|(3&4));
            System.out.println(5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1));
            System.out.println((~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78);
            System.out.println((7^5|1&2<<(2|5>>2&71))|1200);

            TextService textService = new TextService();

            System.out.println(textService.countConsonantsInText(textComponent));
            System.out.println(textService.countVowelsInText(textComponent));

            Map<String, Integer> map = textService.findCountOfWords(textComponent);

            for (Map.Entry entry : map.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ";--- Value: "
                        + entry.getValue());
            }

            List<TextComponent> resultList = textService.sortParagraphBySentenceCount(textComponent);

            System.out.println(resultList.toString());

            resultList = textService.deleteSentencesWithWordsLessThan(textComponent, 31);

            System.out.println(resultList.toString());

            resultList = textService.findSentenceWithLongestWord(textComponent);

            System.out.println(resultList.toString());
        } catch (CustomTextException e) {
            e.printStackTrace();
        }

    }
}
