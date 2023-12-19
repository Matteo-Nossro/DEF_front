package com.example.MSCE.DFVProject;

import com.example.MSCE.DFVProject.service.CsvDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DfvProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfvProjectApplication.class, args);
		CsvDataService reader = new CsvDataService();
		String filePath = "full.csv";
		reader.readCsvData(filePath);
	}

}
