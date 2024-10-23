package calculator.util.converter;

import java.util.List;

public interface NumberConvertible<T extends Number> {

    List<T> convert(final List<String> splitByRegex);

}
