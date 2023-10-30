package com.electricity.project.simulationmodule.domains.weather.client;

import com.electricity.project.simulationmodule.domains.weather.data.Coordinates;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherApiResponse;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherApiResponseWrapper;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherResponseAbstract;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
@Profile("dev")
@RequiredArgsConstructor
public class MockedWeatherApiClient implements WeatherClient {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public WeatherResponseAbstract getRealTimeWeather(@NonNull Coordinates coordinates) {
        String mockedResponse = """
                {
                    "location": {
                        "name": "Wroclaw",
                        "region": "",
                        "country": "Poland",
                        "lat": %.2f,
                        "lon": %.2f,
                        "tz_id": "Europe/Warsaw",
                        "localtime_epoch": 1698335510,
                        "localtime": "2023-10-26 17:51"
                    },
                    "current": {
                        "last_updated_epoch": 1698335100,
                        "last_updated": "2023-10-26 17:45",
                        "temp_c": 12.0,
                        "temp_f": 53.6,
                        "is_day": 0,
                        "condition": {
                            "text": "Partly cloudy",
                            "icon": "//cdn.weatherapi.com/weather/64x64/night/116.png",
                            "code": 1003
                        },
                        "wind_mph": 9.4,
                        "wind_kph": 20.1,
                        "wind_degree": 270,
                        "wind_dir": "W",
                        "pressure_mb": 996.0,
                        "pressure_in": 29.41,
                        "precip_mm": 0.02,
                        "precip_in": 0.0,
                        "humidity": 82,
                        "cloud": 50,
                        "feelslike_c": 11.0,
                        "feelslike_f": 51.8,
                        "vis_km": 10.0,
                        "vis_miles": 6.0,
                        "uv": 3.0,
                        "gust_mph": 11.8,
                        "gust_kph": 18.9
                    }
                }""";
        mockedResponse = String.format(Locale.US, mockedResponse, coordinates.latitude(), coordinates.longitude());
        WeatherApiResponse response = objectMapper.readValue(mockedResponse, WeatherApiResponseWrapper.class).getCurrent();
        log.info("Mocked weather response: {}", response);
        return response;
    }
}
