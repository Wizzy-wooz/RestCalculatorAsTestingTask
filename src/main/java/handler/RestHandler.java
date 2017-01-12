package main.java.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.utils.ParamsUtilsService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by elena on 1/12/17.
 * There is no need to cover this Class with tests
 * due to usage of external/JDK internal solutions which have been already tested
 */
public class RestHandler implements HttpHandler {

    public static final String URL_SEPARATOR = "/";
    public static final String PARAMETER_NAME = "x";
    public static final String ENGINE_NAME = "JavaScript";
    public static final String SHOW_WARNING_ON_PAGE = "Failed to perform calculation. " +
            "Please, use the following equation pattern: equation?x1=value&x2=value. Example: x1+x2?x1=2&x2=3.";
    public static final int RESPONSE_CODE_OK = 200;
    public static final int CONTENT_LENGTH = 0;

    private static Logger logger = Logger.getLogger(RestHandler.class.getName());

    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        String equation = path.substring(path.indexOf(URL_SEPARATOR) + URL_SEPARATOR.length());

        //to evaluate a math expression given in string form JDK built-in Javascript engine is used
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        Map<String, Object> vars = new HashMap<>();
        String result;

        try {
            String parameters[] = ParamsUtilsService.splitParametersString(httpExchange.getRequestURI().getQuery());
            for (int i = 0; i <= parameters.length - 1; i++) {
                vars.put(PARAMETER_NAME + (i + 1), ParamsUtilsService.getParamValue(parameters[i]));
            }
            result = String.valueOf(engine.eval(equation, new SimpleBindings(vars)));
        } catch (Exception e) {
            result = SHOW_WARNING_ON_PAGE;
            logger.warning(e.getCause().getMessage());
        }

        sendResponse(httpExchange, result.equals("NaN") ? "Obtained result is not a number." : result);
    }

    private void sendResponse(HttpExchange httpExchange, String result) {
        try {
            httpExchange.sendResponseHeaders(RESPONSE_CODE_OK, CONTENT_LENGTH);
            try (BufferedOutputStream out = new BufferedOutputStream(httpExchange.getResponseBody())) {
                out.write(result.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

}
