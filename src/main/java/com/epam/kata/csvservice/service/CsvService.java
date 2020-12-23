package com.epam.kata.csvservice.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.epam.kata.csv_processor.exceptions.CsvProcessorException;
import com.epam.kata.csv_processor.models.CsvClientFileRequest;
import com.epam.kata.csv_processor.models.CsvFileObject;
import com.epam.kata.csvservice.models.CsvFile;

public interface CsvService {
	void loadCsvFile(CsvFile file)  throws CsvProcessorException ;
	
}
