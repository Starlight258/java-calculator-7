package calculator.util.regex;

import static java.util.regex.Pattern.quote;

import java.util.Objects;

public class Regex {

    private static final String OR = "|";

    private final StringBuilder regex;

    public Regex(final String regex) {
        this.regex = new StringBuilder(regex);
    }

    public void add(final String value) {
        regex.append(quote(value));
    }

    public void addContinuously(final String value) {
        regex.append(OR).append(quote(value));
    }

    public String value() {
        return regex.toString();
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
