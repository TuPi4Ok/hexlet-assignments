package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void TestFile() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        assertThat(Utils.readFile("src/test/resources/file")).isEqualTo("{\"key\":\"value\"}");
        storage.set("key228", "value1337");
        assertThat(Map.of("key", "value", "key228", "value1337")).isEqualTo(storage.toMap());
        storage.unset("key228");
        assertThat(Map.of("key", "value")).isEqualTo(storage.toMap());
        assertThat(storage.get("key", "null")).isEqualTo("value");
    }
    // END
}
