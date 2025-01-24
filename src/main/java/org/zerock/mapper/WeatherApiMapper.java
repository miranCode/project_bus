package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.WeatherApiDTO;

@Mapper
public interface WeatherApiMapper {
    void insertWeatherData(WeatherApiDTO weatherData); // XML 매핑에 의존
}
