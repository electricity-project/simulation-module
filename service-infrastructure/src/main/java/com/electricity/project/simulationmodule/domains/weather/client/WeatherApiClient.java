package com.electricity.project.simulationmodule.domains.weather.client;

import com.electricity.project.simulationmodule.domains.weather.data.Coordinates;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherApiResponseWrapper;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherResponseAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Locale;
import java.util.Objects;

@Component
public class WeatherApiClient implements WeatherClient {
    @Value("${api.weather.key}")
    private String apiKey;
    @Value("${api.weather.realtime.weather.uri}")
    private String realtimeWeatherUri;
    private final WebClient client;

    public WeatherApiClient(@Value("${api.weather.baseurl}") String baseUrl) {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofSeconds(10));
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Override
    public WeatherResponseAbstract getRealTimeWeather(@NonNull Coordinates coordinates) {
        WeatherApiResponseWrapper response = client.get()
                .uri(realtimeWeatherUri, uriBuilder -> uriBuilder
                        .queryParam("key", apiKey)
                        .queryParam("q", String.format(Locale.US, "%f,%f", coordinates.latitude(), coordinates.longitude()))
                        .queryParam("aqi", "no")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(WeatherApiResponseWrapper.class)
                .retry(3)
                .block();

        return Objects.requireNonNull(response).getCurrent();
    }
}
