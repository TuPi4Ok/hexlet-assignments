package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void testEnlargeArrayImage() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] Extended = {
                {"*","*", "*", "*", "*", "*", "*", "*"},
                {"*","*", "*", "*", "*", "*", "*", "*"},
                {"*","*", " ", " "," ", " ", "*", "*",},
                {"*","*", " ", " "," ", " ", "*", "*"},
                {"*","*", " ", " "," ", " ", "*", "*"},
                {"*","*", " ", " "," ", " ", "*", "*"},
                {"*","*", "*", "*", "*", "*", "*", "*"},
                {"*","*", "*", "*", "*", "*", "*", "*"},
        };
        image = App.enlargeArrayImage(image);
        assertThat(image).isEqualTo(Extended);
    }
}
// END
