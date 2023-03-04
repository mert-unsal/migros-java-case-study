package com.migros.casestudy;

import com.munsal.kafkaconfiguration.annotation.EnableKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKafka
@SpringBootApplication
public class CaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApplication.class, args);
	}

}
