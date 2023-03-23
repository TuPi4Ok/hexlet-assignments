package exercise.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    String name;
    int temperature;
    String cloudy;
    int wind;
    int humidity;
}
