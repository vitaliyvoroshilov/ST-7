package com.mycompany.app;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task3 {
    public static void run()
    {
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.open-meteo.com/v1/forecast?" 
                + "latitude=56&longitude=44"
                + "&hourly=temperature_2m,"
                + "rain&current=cloud_cover"
                + "&timezone=Europe%2FMoscow"
                + "&forecast_days=1"
                + "&wind_speed_unit=ms");
            //System.out.println(webDriver.getPageSource());
            WebElement elem = webDriver.findElement(By.tagName("pre"));

            String json_str = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temperature_2m = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");
            
            FileWriter fw = new FileWriter("result/forecast.txt");
            for (int i = 0; i < time.size(); i++) {
                String str = String.format("|%-2d|%-16s|%-4s|%-4s|\n",
                    i + 1, time.get(i), temperature_2m.get(i), rain.get(i));
                System.out.println(str);
                fw.write(str);
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
/*
{
    "latitude":56.0,
    "longitude":44.0,
    "generationtime_ms":0.027298927307128906,
    "utc_offset_seconds":10800,
    "timezone":"Europe/Moscow",
    "timezone_abbreviation":"GMT+3",
    "elevation":169.0,
    "current_units":
    {
        "time":"iso8601",
        "interval":"seconds",
        "cloud_cover":"%"
    },
    "current":
    {
        "time":"2025-05-26T23:30",
        "interval":900,
        "cloud_cover":0
    },
    "hourly_units":
    {
        "time":"iso8601",
        "temperature_2m":"°C",
        "rain":"mm"
    },
    "hourly":
    {
        "time":["2025-05-26T00:00","2025-05-26T01:00","2025-05-26T02:00","2025-05-26T03:00","2025-05-26T04:00",
            "2025-05-26T05:00","2025-05-26T06:00","2025-05-26T07:00","2025-05-26T08:00","2025-05-26T09:00",
            "2025-05-26T10:00","2025-05-26T11:00","2025-05-26T12:00","2025-05-26T13:00","2025-05-26T14:00",
            "2025-05-26T15:00","2025-05-26T16:00","2025-05-26T17:00","2025-05-26T18:00","2025-05-26T19:00",
            "2025-05-26T20:00","2025-05-26T21:00","2025-05-26T22:00","2025-05-26T23:00"],
        "temperature_2m":[11.9,11.5,11.2,10.7,10.7,11.3,13.3,15.1,17.1,19.7,21.6,23.3,24.1,24.9,25.3,25.7,25.4,
            25.0,24.1,22.4,20.1,18.2,16.8,15.6],
        "rain":[0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00, 0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,
            0.00,0.00,0.00,0.00,0.00]
    }
}
*/