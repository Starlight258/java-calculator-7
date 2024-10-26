package calculator.util.regex;

import static java.util.regex.Pattern.quote;

import java.util.Objects;

public class Regex {

    private static final String OR = "|";

    private final StringBuilder value;

    public Regex(final String value) {
        this.value = new StringBuilder(value);
    }

    public void add(final String other) {
        value.append(quote(other));
    }

    public void addContinuously(final String other) {
        value.append(OR).append(quote(other));
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Regex other = (Regex) o;
        return this.value().equals(other.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value());
    }

}
