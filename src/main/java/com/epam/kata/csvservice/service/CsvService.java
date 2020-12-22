package com.epam.kata.csvservice.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.epam.kata.csv_processor.models.CsvFileObject;
import com.epam.kata.csvservice.models.CsvFile;

public interface CsvService {
	List<CsvFileObject> loadCsvFile(CsvFile file)  throws FileNotFoundException ;
	void writeCsvFile(List<CsvFileObject> file)  throws FileNotFoundException ;
	boolean getColumnNamesHavingNullOrEmptyValues(String fileUrl,boolean isLocal)  throws FileNotFoundException ;

}
