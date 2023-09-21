package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake1() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertThat(App.take(numbers1, 2).size()).isEqualTo(2);
        assertThat(App.take(numbers1, 2)).contains(1, 2);
        // END
    }

    @Test
    void testTake2() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(7, 3, 10));
        assertThat(App.take(numbers1, 8).size()).isEqualTo(3);
        assertThat(App.take(numbers1, 8)).contains(7, 3, 10);
        // END
    }
}
