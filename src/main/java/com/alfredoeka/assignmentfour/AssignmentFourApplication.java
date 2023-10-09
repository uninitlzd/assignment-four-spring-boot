package com.alfredoeka.assignmentfour;

import com.alfredoeka.assignmentfour.repository.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AssignmentFourApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentFourApplication.class, args);
	}

}
