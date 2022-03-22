package test.epam.task3.interpreter;

import by.epam.task3.interpreter.BitInterpreter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BitInterpreterTest {
    private BitInterpreter parser;

    @BeforeClass
    private void setUp(){
        parser = BitInterpreter.getInstance();
    }

    @DataProvider(name = "polishNotationParser")
    private Object[][] initDataSet(){
        return new Object[][]{
                {},
                {}
        };
    }

    @Test(dataProvider = "polishNotationParser")
    public void testPolishNotationParser(String expected, String inputData){
        String actual = parser.execute(inputData);
        Assert.assertEquals(actual, expected);
    }
}
