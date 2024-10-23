package calculator.domain.adder;

import java.util.List;

public interface Addable<T extends Number> {

    T add(final List<T> numbers);

}
