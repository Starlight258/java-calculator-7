package calculator;

import calculator.domain.adder.Addable;
import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.DelimiterExtractor;
import calculator.domain.delimiter.Delimiters;
import calculator.io.ConsoleInputHandler;
import calculator.io.ConsoleOutputHandler;
import calculator.util.converter.NumberConvertible;
import calculator.util.splitter.Splitter;
import java.util.List;

public class StringCalculator {

    private final ConsoleInputHandler consoleInputHandler;
    private final ConsoleOutputHandler consoleOutputHandler;
    private final DelimiterExtractor delimiterExtractor;
    private final Splitter splitter;
    private final NumberConvertible numberConvertible;
    private final Addable addable;

    public StringCalculator(ConsoleInputHandler consoleInputHandler, ConsoleOutputHandler consoleOutputHandler,
                            DelimiterExtractor delimiterExtractor, Splitter splitter,
                            NumberConvertible numberConvertible, Addable addable) {
        this.consoleInputHandler = consoleInputHandler;
        this.consoleOutputHandler = consoleOutputHandler;
        this.delimiterExtractor = delimiterExtractor;
        this.splitter = splitter;
        this.numberConvertible = numberConvertible;
        this.addable = addable;
    }

    public Delimiters initialize() {

        return new Delimiters(List.of(new Delimiter(","), new Delimiter(":")));
    }

    public void run(final Delimiters defaultDelimiters) {
        // 0. 입력한다.
        String input = consoleInputHandler.getUserInput();

        // 1.커스텀 구분자를 추출한다.
        Delimiters delimiters = delimiterExtractor.extractDelimitersFrom(input, defaultDelimiters);

        //2. 구분자를 기준으로 문자열을 추출한다.
        List<String> splitInput = splitter.splitByDelimiters(input, delimiters);

        //3. 추출한 문자열을 숫자로 변환한다.
        List numbers = numberConvertible.convertStringToNumber(splitInput);

        // 4. 구한 숫자들을 더한 값을 출력한다.
        consoleOutputHandler.printResult(addable.addNumbers(numbers));
    }

}
