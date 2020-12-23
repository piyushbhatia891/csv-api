package com.epam.kata.csvservice.controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.kata.csv_processor.exceptions.CsvProcessorException;
import com.epam.kata.csvservice.models.CsvFile;
import com.epam.kata.csvservice.service.CsvService;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

	@Autowired
	private CsvService csvService;

	@PostMapping("/load")
	public ResponseEntity<String> loadCSVFile(@RequestBody @Validated CsvFile csvFile) {
		try {
			csvService.loadCsvFile(csvFile);
			return new ResponseEntity<>(HttpStatus.OK).ok("Thanks The File Submitted");
		} catch (CsvProcessorException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
