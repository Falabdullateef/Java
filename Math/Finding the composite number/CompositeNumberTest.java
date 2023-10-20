import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class CompositeNumberTest {

    public static void main(String[] args) {
        long upperLimit = 10000000;
        AtomicLong count = new AtomicLong(0);

        LongStream.rangeClosed(0, upperLimit)
                .parallel()
                .forEach(n -> {
                    long result = n * n + 41 * n + 41;
                    if (isComposite(result)) {
                        count.incrementAndGet();
                    }
                });

        System.out.println("There are " + count + " composite numbers.");
    }

    public static boolean isComposite(long num) {
        if (num < 4) return false;
        if (num % 2 == 0 || num % 3 == 0) return true;
        long sqrt = (long) Math.sqrt(num);
        for (long i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return true;
        }
        return false;
    }
}