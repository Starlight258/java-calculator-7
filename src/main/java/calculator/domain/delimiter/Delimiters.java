package calculator.domain.delimiter;

import static calculator.domain.delimiter.DelimiterPattern.CUSTOM_DELIMITER;

import calculator.util.regex.Regex;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Delimiters {

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
        validateDuplicate(delimiter);
    }

    private void validateDuplicate(final Delimiter delimiter) {
        if (delimiters.contains(delimiter)) {
            throw new IllegalArgumentException("중복된 구분자가 존재합니다.");
        }
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
