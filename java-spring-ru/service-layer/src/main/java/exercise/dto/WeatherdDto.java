package exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherdDto {
    int temperature;
    String name;

    public WeatherdDto(int temperature, String name) {
        this.temperature = temperature;
        this.name = name;
    }
}
