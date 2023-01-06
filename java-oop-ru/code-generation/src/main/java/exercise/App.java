package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) {
        String json = car.serialize();
        try {
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path path) {
        String json = null;
        try {
            json = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Car.unserialize(json);
    }
}
// END
