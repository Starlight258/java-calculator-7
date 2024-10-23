package calculator.domain.delimiter;

import static calculator.domain.delimiter.DelimiterPattern.CUSTOM_DELIMITER;

import java.util.regex.Matcher;

public class DelimiterExtractor {

    public Delimiters extractFrom(final String input, final Delimiters delimiters) {
        Matcher matcher = CUSTOM_DELIMITER.pattern().matcher(input);
        Delimiters totalDelimiters = delimiters;
        while (matcher.find()) {
            totalDelimiters = totalDelimiters.add(new Delimiter(matcher.group(1)));
        }

        return totalDelimiters;
    }

}
