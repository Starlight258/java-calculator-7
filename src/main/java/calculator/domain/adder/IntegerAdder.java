package calculator.domain.adder;

import java.util.List;

public class IntegerAdder implements Addable<Integer> {

    private static final Integer MAX_VALUE = Integer.MAX_VALUE;
    private static final Integer MIN_VALUE = Integer.MIN_VALUE;

    @Override
    public Integer add(final List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum = addWithOverflowCheck(sum, number);
        }

        return sum;
    }

    private int addWithOverflowCheck(final int sum, final int added) {
        if (added > 0 && sum > MAX_VALUE - added) {
            throw new IllegalStateException("오버플로우가 발생했습니다.");
        }

        if (added < 0 && sum < MIN_VALUE - added) {
            throw new IllegalStateException("오버플로우가 발생했습니다.");
        }

        return sum + added;
    }

}
