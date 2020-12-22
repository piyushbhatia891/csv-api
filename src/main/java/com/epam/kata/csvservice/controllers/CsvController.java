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
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (FileNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteContentFromCSVFileForAName(@RequestBody @Validated CsvFile csvFile) {
		try {
			boolean isDeleted= csvService.deleteRowByName(csvFile);
			if(isDeleted) {
				return ResponseEntity.ok("deleted");
			} else
				return ResponseEntity.ok("Not Deleted");
		} catch (FileNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@GetMapping("/emptyValue")
	public boolean getNullOrEmptyValueColumnNames(@RequestParam("fileUrl") String fileUrl,
			@RequestParam("isLocal") boolean isLocal) {
		try {
			if (csvService.getColumnNamesHavingNullOrEmptyValues(fileUrl, isLocal))
				return true;
			else 
				return false;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	
	
	

}
