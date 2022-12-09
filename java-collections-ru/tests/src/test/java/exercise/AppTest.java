package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        List<Integer> test1 = new ArrayList<>();

        assertThat(App.take(numbers, 0)).isEqualTo(test1);

        test1.add(1);

        assertThat(App.take(numbers, 1)).isEqualTo(test1);

        test1.add(2);
        test1.add(3);
        test1.add(4);

        assertThat(App.take(numbers, 4)).isEqualTo(test1);
        // END
    }
}
