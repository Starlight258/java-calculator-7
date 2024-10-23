package calculator.domain.delimiter;

import static calculator.domain.delimiter.DelimiterPattern.CUSTOM_DELIMITER;

import calculator.util.regex.Regex;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Delimiters {

    private static final String CONTAINING_ALL_START_REGEX = "^.*(";
    private static final String CONTAINING_ALL_END_REGEX = ").*";
    private static final String NON_MATCH = "(?!)";

    private final List<Delimiter> delimiters;

    public Delimiters(final List<Delimiter> delimiters) {
        this.delimiters = delimiters;
    }

    public Delimiters add(final Delimiter delimiter) {
        validate(delimiter);

        return new Delimiters(concat(delimiter));
    }

    public Regex makeRegex() {
        Regex regex = new Regex(CUSTOM_DELIMITER.regex());
        for (Delimiter delimiter : delimiters) {
            regex.addContinuously(delimiter.delimiter());
        }

        return regex;
    }

    private void validate(final Delimiter delimiter) {
        checkIfContainsOther(delimiter);
    }

    private void checkIfContainsOther(final Delimiter newDelimiter) {
        Regex existingDelimitersRegex = makeExistingRegex();
        Regex checkContainmentRegex = makeContainmentRegex(existingDelimitersRegex);

        if (newDelimiter.matches(checkContainmentRegex)) {
            throw new IllegalArgumentException("구분자를 중복 선언하거나, 내부에 다른 구분자를 포함할 수 없습니다.");
        }
    }

    private Regex makeExistingRegex() {
        Regex existingDelimitersRegex = new Regex(NON_MATCH);
        for (Delimiter existingDelimiter : delimiters) {
            existingDelimitersRegex.addContinuously(existingDelimiter.delimiter());
        }

        return existingDelimitersRegex;
    }

    private Regex makeContainmentRegex(final Regex existingDelimitersRegex) {
        return new Regex(
                CONTAINING_ALL_START_REGEX + existingDelimitersRegex.value() + CONTAINING_ALL_END_REGEX);
    }

    private List<Delimiter> concat(final Delimiter delimiter) {

        return Stream.concat(delimiters.stream(), Stream.of(new Delimiter(delimiter.delimiter())))
                .toList();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Delimiters that = (Delimiters) o;
        return delimiters.equals(that.delimiters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delimiters);
    }

}
