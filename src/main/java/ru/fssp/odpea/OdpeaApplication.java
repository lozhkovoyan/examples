package ru.fssp.odpea;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.fssp.odpea")
@Slf4j
public class OdpeaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OdpeaApplication.class, args);
    }
}