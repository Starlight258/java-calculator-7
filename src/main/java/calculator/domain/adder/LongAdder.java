package calculator.domain.adder;

import java.util.List;

public class LongAdder implements Addable<Long> {

    private static final Long MAX_VALUE = Long.MAX_VALUE;
    private static final Long MIN_VALUE = Long.MIN_VALUE;

    @Override
    public Long add(final List<Long> numbers) {
        long sum = 0;
        for (Long number : numbers) {
            sum = addWithOverflowCheck(sum, number);
        }

        return sum;
    }

    private long addWithOverflowCheck(final long sum, final long added) {
        if (added > 0 && sum > MAX_VALUE - added) {
            throw new IllegalStateException("오버플로우가 발생했습니다.");
        }

        if (added < 0 && sum < MIN_VALUE - added) {
            throw new IllegalStateException("오버플로우가 발생했습니다.");
        }

        return sum + added;
    }

}
