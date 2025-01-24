package org.zerock.dto;

import java.math.BigDecimal;

public class WeatherApiDTO {
    private String date; // YYYYMMDD 형식
    private int stationId;
    private BigDecimal windSpeed;
    private Integer windDirection;
    private BigDecimal windMax;
    private BigDecimal maxTemperature;
    private BigDecimal maxWindSpeed;
    private Integer maxWindDirection;
    private String maxTempTime; // HHMM 형식
    private BigDecimal minTemperature;
    private BigDecimal minWindSpeed;
    private Integer minWindDirection;
    private String minTempTime; // HHMM 형식
    private BigDecimal avgTemperature;
    private BigDecimal avgWindSpeed;
    private BigDecimal totalPrecipitation;
    private BigDecimal evaporationSpeed;
    private BigDecimal totalSunshine;
    private BigDecimal pressure;
    private BigDecimal humidity;
    private BigDecimal dewPoint;
    private BigDecimal visibility;
    private BigDecimal rainDuration;
    private BigDecimal snowDepth;
    private BigDecimal snowDuration;

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    public BigDecimal getWindMax() {
        return windMax;
    }

    public void setWindMax(BigDecimal windMax) {
        this.windMax = windMax;
    }

    public BigDecimal getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(BigDecimal maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public BigDecimal getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(BigDecimal maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public Integer getMaxWindDirection() {
        return maxWindDirection;
    }

    public void setMaxWindDirection(Integer maxWindDirection) {
        this.maxWindDirection = maxWindDirection;
    }

    public String getMaxTempTime() {
        return maxTempTime;
    }

    public void setMaxTempTime(String maxTempTime) {
        this.maxTempTime = maxTempTime;
    }

    public BigDecimal getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(BigDecimal minTemperature) {
        this.minTemperature = minTemperature;
    }

    public BigDecimal getMinWindSpeed() {
        return minWindSpeed;
    }

    public void setMinWindSpeed(BigDecimal minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public Integer getMinWindDirection() {
        return minWindDirection;
    }

    public void setMinWindDirection(Integer minWindDirection) {
        this.minWindDirection = minWindDirection;
    }

    public String getMinTempTime() {
        return minTempTime;
    }

    public void setMinTempTime(String minTempTime) {
        this.minTempTime = minTempTime;
    }

    public BigDecimal getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(BigDecimal avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public BigDecimal getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(BigDecimal avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public BigDecimal getTotalPrecipitation() {
        return totalPrecipitation;
    }

    public void setTotalPrecipitation(BigDecimal totalPrecipitation) {
        this.totalPrecipitation = totalPrecipitation;
    }

    public BigDecimal getEvaporationSpeed() {
        return evaporationSpeed;
    }

    public void setEvaporationSpeed(BigDecimal evaporationSpeed) {
        this.evaporationSpeed = evaporationSpeed;
    }

    public BigDecimal getTotalSunshine() {
        return totalSunshine;
    }

    public void setTotalSunshine(BigDecimal totalSunshine) {
        this.totalSunshine = totalSunshine;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(BigDecimal dewPoint) {
        this.dewPoint = dewPoint;
    }

    public BigDecimal getVisibility() {
        return visibility;
    }

    public void setVisibility(BigDecimal visibility) {
        this.visibility = visibility;
    }

    public BigDecimal getRainDuration() {
        return rainDuration;
    }

    public void setRainDuration(BigDecimal rainDuration) {
        this.rainDuration = rainDuration;
    }

    public BigDecimal getSnowDepth() {
        return snowDepth;
    }

    public void setSnowDepth(BigDecimal snowDepth) {
        this.snowDepth = snowDepth;
    }

    public BigDecimal getSnowDuration() {
        return snowDuration;
    }

    public void setSnowDuration(BigDecimal snowDuration) {
        this.snowDuration = snowDuration;
    }
}
