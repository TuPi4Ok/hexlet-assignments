package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Getter
@AllArgsConstructor
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Car unserialize(String json) {
        Car result;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(json, new TypeReference<Car>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    // END
}
