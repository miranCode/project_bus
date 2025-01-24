package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.WeatherApiService;

@Controller
@RequestMapping("/admin/*")
public class WeatherApiController {

    @Autowired
    private WeatherApiService weatherApiService;

    @GetMapping("/saveWeatherData")
    public String saveWeatherData() {
    	System.out.println("Controller - /admin/saveWeatherData");
        try {
            weatherApiService.saveWeatherData();
            return "/admin/bus/busUse"; // ���������� ������ ���� �� ��ȯ�� ��
        } catch (Exception e) {
            e.printStackTrace();
            return "/admin/error"; // ���� �߻� �� ��ȯ�� ��
        }
    }
}
