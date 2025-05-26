package com.mycompany.app;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Main\\Dev\\ST\\chromedriver-win64\\chromedriver.exe");
        
        Task2.run();

        Task3.run();
    }
}
