package org.zerock.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.WeatherApiDTO;
import org.zerock.mapper.WeatherApiMapper;

@Service
public class WeatherApiService {

	private final WeatherApiMapper weatherApiMapper;

	private static final Logger logger = LoggerFactory.getLogger(WeatherApiService.class);

	// Constructor
	public WeatherApiService(WeatherApiMapper weatherApiMapper) {
		this.weatherApiMapper = weatherApiMapper;
	}

	// 외부 API 데이터를 DB에 저장하는 메소드
	@Transactional
	public void saveWeatherData() {
		try {
			// API URL을 호출하여 데이터 가져오기
			String csvResponse = getWeatherDataFromApi();
			if (csvResponse.isEmpty()) {
				logger.error("Received empty response from API");
				return;
			}
			System.out.println("csvResponse : " + csvResponse);

			// CSV 데이터를 DB에 직접 삽입하는 메소드 호출
			// insertDataDirectly(csvResponse);
			parseAndSaveWeatherData(csvResponse);
		} catch (Exception e) {
			logger.error("Error occurred while saving weather data", e);
			throw new RuntimeException("Error occurred while saving weather data", e);
		}
	}

	public void parseAndSaveWeatherData(String rawData) {
		// 줄 단위로 데이터를 분리
		String[] lines = rawData.split("\n");

		List<WeatherApiDTO> weatherDataList = new ArrayList<>();

		// 데이터를 파싱해서 DTO에 매핑
		   // 데이터를 파싱해서 DTO에 매핑
        for (String line : lines) {
            line = line.trim();
            System.out.println(line);

            // #으로 시작되는 행은 skip
            if (line.startsWith("#") || line.isEmpty()) {
                continue;
            }

			String[] fields = line.split("\\s+"); // 공백으로 구분
			
			// 각 필드에서 공백 제거 (여기서 공백을 제거해야 함)
	        for (int i = 0; i < fields.length; i++) {
	            fields[i] = fields[i].trim(); // 각 필드에 대해 공백 제거
	        }
			
			if (fields.length < 33)
				continue; // 데이터가 부족한 경우 skip

			WeatherApiDTO dto = new WeatherApiDTO();
			dto.setDate(fields[0]);
			dto.setStationId(Integer.parseInt(fields[1]));
			dto.setWindSpeed(new BigDecimal(fields[2]));
			dto.setWindDirection(Integer.parseInt(fields[3]));
			dto.setWindMax(new BigDecimal(fields[4]));
			dto.setMaxTemperature(new BigDecimal(fields[5]));
			dto.setMaxWindSpeed(new BigDecimal(fields[6]));
			dto.setMaxWindDirection(Integer.parseInt(fields[7]));
			dto.setMaxTempTime(fields[8]);
			dto.setMinTemperature(new BigDecimal(fields[9]));
			dto.setMinWindSpeed(new BigDecimal(fields[10]));
			dto.setMinWindDirection(Integer.parseInt(fields[11]));
			dto.setMinTempTime(fields[12]);
			dto.setAvgTemperature(new BigDecimal(fields[13]));
			dto.setAvgWindSpeed(new BigDecimal(fields[14]));
			dto.setTotalPrecipitation(new BigDecimal(fields[15]));
			dto.setEvaporationSpeed(new BigDecimal(fields[16]));
			dto.setTotalSunshine(new BigDecimal(fields[17]));
			dto.setPressure(new BigDecimal(fields[18]));
			dto.setHumidity(new BigDecimal(fields[19]));
			dto.setDewPoint(new BigDecimal(fields[20]));
			dto.setVisibility(new BigDecimal(fields[21]));
			dto.setRainDuration(new BigDecimal(fields[22]));
			dto.setSnowDepth(new BigDecimal(fields[23]));
			dto.setSnowDuration(new BigDecimal(fields[24]));

			weatherDataList.add(dto);
		}

		// DB에 저장
		for (WeatherApiDTO weatherData : weatherDataList) {
			weatherApiMapper.insertWeatherData(weatherData);
		}
	}

	// 외부 API에서 CSV 데이터를 가져오는 메서드
	private String getWeatherDataFromApi() throws Exception {
	    URL url = new URL("https://apihub.kma.go.kr/api/typ01/url/kma_sfcdd3.php?tm1=20241227&tm2=20250113&stn=108&authKey=fQbyCZmIT5-G8gmZiN-f7A");
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Content-Type", "application/json");

	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    StringBuilder response = new StringBuilder();
	    String inputLine;

	    while ((inputLine = in.readLine()) != null) {
	        response.append(inputLine).append("\n");  // 줄바꿈 포함하여 추가
	    }
	    in.close();

	    // 응답 데이터에서 실제 CSV 부분만 추출
	    String csvResponse = response.toString();

	    int startIndex = csvResponse.indexOf("#START7777") + "#START7777".length();
	    int endIndex = csvResponse.indexOf("#7777END");
	    if (startIndex == -1 || endIndex == -1) {
	        logger.error("Invalid response format from API");
	        return ""; // 잘못된 응답 포맷 처리
	    }
	    return csvResponse.substring(startIndex, endIndex).trim(); // 실제 CSV 데이터 추출
	}
}
