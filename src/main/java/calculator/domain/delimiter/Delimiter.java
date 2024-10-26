package calculator.domain.delimiter;

import calculator.util.regex.Regex;
import java.util.Objects;

public class Delimiter {

    private static final String POSITIVE_NUMBER_REGEX = "^(\\+?)\\d*$";
    private final String value;

    public Delimiter(final String value) {
        validate(value);
        this.value = value;
    }

    public boolean matches(final Regex regex) {
        return value.matches(regex.value());
    }

    private void validate(final String delimiter) {
        checkIfOnlyLetters(delimiter);
    }

    private void checkIfOnlyLetters(final String delimiter) {
        if (delimiter.matches(POSITIVE_NUMBER_REGEX)) {
            throw new IllegalArgumentException("구분자는 숫자로만 이루어질 수 없습니다. 문자를 포함하세요.");
        }
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Delimiter) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
