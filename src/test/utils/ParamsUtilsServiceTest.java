package test.utils;

import main.java.utils.ParamsUtilsService;
import org.junit.Test;

import javax.naming.NameNotFoundException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by elena on 1/12/17.
 */

public class ParamsUtilsServiceTest {

    @Test
    public void splitParametersStringMethodShouldReturnArrayOfParametersWhenParametersStringIsCorrectlyFormed() {
        String[] expectedParameters = {"x1=100", "x2=200"};
        String[] obtainedParameters = ParamsUtilsService.splitParametersString("x1=100&x2=200");
        assertEquals("Wrong number of parameters obtained.", 2, obtainedParameters.length);
        assertInLoop(expectedParameters, obtainedParameters);
    }

    @Test
    public void splitParametersStringMethodShouldReturnEmptyArrayOfParametersWhenParametersStringIsEmpty() {
        String[] obtainedParameters = ParamsUtilsService.splitParametersString("");
        assertEquals("Wrong number of parameters obtained.", 0, obtainedParameters.length);
    }

    @Test
    public void splitParametersStringMethodShouldReturnEmptyArrayOfParametersWhenParametersStringIsNull() {
        String[] obtainedParameters = ParamsUtilsService.splitParametersString("");
        assertEquals("Wrong number of parameters obtained.", 0, obtainedParameters.length);
    }

    @Test
    public void splitParametersStringMethodShouldReturnArrayOfParametersWithOneElementWhenParametersStringHasOnlyOneParameter() {
        String[] expectedParameters = {"x1=100"};
        String[] obtainedParameters = ParamsUtilsService.splitParametersString("x1=100");
        assertEquals("Wrong number of parameters obtained.", 1, obtainedParameters.length);
        assertInLoop(expectedParameters, obtainedParameters);
    }

    @Test
    public void getParamValueMethodShouldReturnValueWhenParameterContainsNameEqualsAndValue() throws Exception {
        assertEquals("Wrong value extracted.", 100f, ParamsUtilsService.getParamValue("x1=100"));
    }

    @Test(expected = NameNotFoundException.class)
    public void getParamValueMethodShouldThrowExceptionWhenParameterEmpty() throws Exception {
        ParamsUtilsService.getParamValue("");
    }

    @Test(expected = NameNotFoundException.class)
    public void getParamValueMethodShouldThrowExceptionWhenParameterNull() throws Exception {
        ParamsUtilsService.getParamValue(null);
    }

    @Test(expected = NameNotFoundException.class)
    public void getParamValueMethodShouldThrowExceptionWhenParameterDoesNotContainEqualsSign() throws Exception {
        ParamsUtilsService.getParamValue("x1100");
    }

    @Test
    public void getParamValueMethodShouldReturnValueWhenParameterContainsNameWhichStartsOnX() throws Exception {
        assertEquals("Wrong value extracted.", 100f, ParamsUtilsService.getParamValue("x1=100"));
    }

    @Test
    public void getParamValueMethodShouldReturnValueWhenParameterContainsCommaInValue() throws Exception {
        assertEquals("Wrong value extracted.", 100.2f, ParamsUtilsService.getParamValue("x1=100,2"));
    }

    @Test
    public void getParamValueMethodShouldReturnValueWhenParameterContainsPointInValue() throws Exception {
        assertEquals("Wrong value extracted.", 100.2f, ParamsUtilsService.getParamValue("x1=100.2"));
    }

    @Test
    public void getParamValueMethodShouldReturnFloatValueWhenParameterContainsWronglyAddedCharactersInValue() throws Exception {
        assertEquals("Wrong value extracted.", 100.2f, ParamsUtilsService.getParamValue("x1=100.2afafa"));
    }

    @Test(expected = NameNotFoundException.class)
    public void getParamValueMethodShouldThrowExceptionWhenParameterDoesNotContainNameWhichStartsOnX() throws Exception {
        ParamsUtilsService.getParamValue("a1=100");
    }

    @Test(expected = NumberFormatException.class)
    public void getParamValueMethodShouldThrowExceptionWhenParameterCantBeParsedToFloat() throws Exception {
        ParamsUtilsService.getParamValue("x1=100afvb");
    }

    private void assertInLoop(String[] expectedParameters, String[] obtainedParameters) {
        for (int i = 0; i < expectedParameters.length; i++) {
            assertEquals(expectedParameters[i], obtainedParameters[i]);
        }
    }
}
