package test.epam.task3.reader;

import by.epam.task3.exception.CustomTextException;
import by.epam.task3.reader.TextFileReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomTextImplTest {
    private TextFileReader reader;

    @BeforeClass
    private void setUp(){
        reader = new TextFileReader();
    }

    @DataProvider(name = "reader")
    private Object[][] initData(){
        return new Object[][]{
                {"src\\test\\resources\\testData.txt", "It has survived - not only (five) centuries, but also the leap into electronic." +
                        " It is a long established fact that a reader will be distracted by the readable content of a page. "}
        };
    }

    @Test(dataProvider = "reader")
    public void testFileReader(String filePath, String expected) throws CustomTextException {
        String actual = reader.readTextFromFile(filePath);
        Assert.assertEquals(actual, expected);
    }
}
