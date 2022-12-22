package edu.ynu.arduino;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class ArduinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArduinoApplication.class, args);
		log.info("logger class: {}", log.getClass());
	}


//	@GetMapping("/hello")
//	public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
//		return String.format("hello %s", name);
//	}

}
