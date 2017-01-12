# RestCalculator

Requirements:
Create the web application in Java which meets the following criteria:

1) The web application should offer an REST-like API interface (HTTP/JSON) for simple arithmetic calculations.

2) The API offers an operation which accepts a list of numbers, does some calculations and returns calculation result.

Calculation result is always a single number.

It should be defined in configuration how the calculations are done.

It is up to you to define how the configuration looks like.


For example, if the inputs are `X1` and `X2`, the calculation could be `X1 + 5*X2/X1`.

Implementation:

Important: no MAVEN nor Spring were used.

1. Clone/download sources. 
2. Start main method in Application.java - it launches local HttpServer on http://localhost:8081.
3. PORT_NUMBER = 8081 can be changed in Application.java.
4. To perform calculation: specify your operation + specify your parameters. Example: http://localhost:8081/x1+x2?x1=10&x2=4
5. Simple arithmetic calculations like add (+), substract(-), multiply(*) and divide(/) are supported.
6. All variables(parameters) MUST BE NAMED with x. Like: x1, x2, x3...There is no limitation on parameters number. But please do not forget to specify all of them in your operation.
7. It does matter if you use . or , for separation of integral and fractional parts.
8. Result will return float number, but be aware that some inaccuracies can happen due to nature of float type. If you mix alphabetic letters with numbers - letters will be removed.
9. If operation requires 3 parameters there MUST BE three parameters.
10. Not a number and Infinity can be obtained as result.
11. Empty value for parameter will be processed as error and your operation won't give any result.
