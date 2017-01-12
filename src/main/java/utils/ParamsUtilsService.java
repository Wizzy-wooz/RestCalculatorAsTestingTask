package main.java.utils;

import main.java.handler.RestHandler;

import javax.naming.NameNotFoundException;

/**
 * Created by elena on 1/12/17.
 */
public class ParamsUtilsService {

    /**
     * This method helps to split parameters from URI
     */
    public static String[] splitParametersString(String parametersString) {
        if (parametersString == null || parametersString.isEmpty()) {
            return new String[0];
        }
        return parametersString.split("&");
    }

    /**
     * This method helps to get a value from parameterAndValue String plus it checks that parameter was named correctly - x
     */
    public static float getParamValue(String parameter) throws Exception {
        if (parameter == null || parameter.isEmpty() || !parameter.contains("=")) {
            throw new NameNotFoundException();
        }

        String[] pair = parameter.split("=");
        if (pair[0].toLowerCase().contains(RestHandler.PARAMETER_NAME)) {
            return Float.parseFloat(pair[1].replace(",", ".").replaceAll("[^\\d.]", ""));
        } else {
            throw new NameNotFoundException();
        }
    }
}
