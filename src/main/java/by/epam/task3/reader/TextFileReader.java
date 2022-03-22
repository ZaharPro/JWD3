package by.epam.task3.reader;

import by.epam.task3.exception.CustomTextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.stream.Collectors;

public class TextFileReader {
    private static final Logger logger = LogManager.getLogger();

    public String readTextFromFile(String path) throws CustomTextException {
        String text;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            text = bufferedReader.lines()
                    .map(l -> l.replace("    ", ""))
                    .map(l -> l + "\n")
                    .collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            logger.error("Cannot find this file " + path, e);
            throw new CustomTextException("Cannot find this file " + path, e);
        } catch (IOException e) {
            logger.error("IOException during read data from file", e);
            throw new CustomTextException("IOException during read data from file", e);
        }
        return text;
    }
}
