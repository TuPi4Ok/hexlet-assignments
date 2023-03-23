package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.dto.WeatherdDto;
import exercise.model.City;
import exercise.model.Weather;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping("/cities/{id}")
    public Weather getCity(@PathVariable(name = "id") long id) throws JsonProcessingException {
        City result = cityRepository.findCityById(id);
        if (result == null) {
            throw new CityNotFoundException("City not found");
        }
        return weatherService.getWeather(result.getName()).stream().findFirst().get();
    }

    @GetMapping("/search")
    public List<WeatherdDto> getCities(@RequestParam(name = "name", required = false) String name) throws JsonProcessingException {
        if (name == null) {
            return weatherService.WeatherToWeatherDto(weatherService.getAllWeather());
        } else {
            return weatherService.WeatherToWeatherDto(weatherService.getWeather(name));
        }
    }
    // END
}

