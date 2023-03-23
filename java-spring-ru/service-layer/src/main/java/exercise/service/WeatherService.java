package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.WeatherdDto;
import exercise.model.Weather;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;
    @Autowired
    ObjectMapper objectMapper;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public List<Weather> getWeather(String name) throws JsonProcessingException {
        List<City> cities = cityRepository.findByNameStartingWithIgnoreCase(name);
        return getWeathers(cities);
    }

    public List<Weather> getAllWeather() throws JsonProcessingException {
        List<City> cities = cityRepository.findAllByNameNotNullOrderByName();
        return getWeathers(cities);
    }

    private List<Weather> getWeathers(List<City> cities) throws JsonProcessingException {
        if (cities.isEmpty()) {
            throw new CityNotFoundException("City not found");
        }
        List<Weather> result = new ArrayList<>();
        for (City city1 : cities) {
            result.add(objectMapper.readValue(client.get("http://weather/api/v2/cities/" + city1.getName()), Weather.class));
        }
        return result;
    }

    public List<WeatherdDto> WeatherToWeatherDto(List<Weather> weathers) {
        List<WeatherdDto> result = new ArrayList<>();
        for (Weather weather : weathers) {
            result.add(new WeatherdDto(weather.getTemperature(), weather.getName()));
        }
        return result;
    }
    // END
}
