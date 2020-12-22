package com.epam.kata.csvservice.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.kata.csv_processor.client.CsvProcessorClient;
import com.epam.kata.csv_processor.models.CsvClientFileRequest;
import com.epam.kata.csv_processor.models.CsvFileObject;
import com.epam.kata.csvservice.exceptions.FilePathNotFoundException;
import com.epam.kata.csvservice.models.CsvFile;

@Service
public class CsvServiceImpl implements CsvService {

	private CsvFileLoadStrategy csvFileLoadStrategy;

	@Autowired
	private CsvProcessorClient csvProcessingClient;

	@Override
	public List<CsvFileObject> loadCsvFile(CsvFile csvFile) throws FileNotFoundException {
		return csvProcessingClient.loadCsvFile(createCsvFileObjectForClient(csvFile));

	}

	@Override
	public boolean getColumnNamesHavingNullOrEmptyValues(String fileUrl, boolean isLocal) throws FileNotFoundException {
		CsvFile file = new CsvFile();
		file.setFileUrl(fileUrl);
		file.setLocal(isLocal);
		return csvProcessingClient.getColumnNamesHavingNullOrEmptyValues(createCsvFileObjectForClient(file));
	}

	private CsvClientFileRequest createCsvFileObjectForClient(CsvFile csvFile) {
		CsvClientFileRequest fileObject = new CsvClientFileRequest();
		fileObject.setFileUrl(csvFile.getFileUrl());
		fileObject.setLocal(csvFile.isLocal());
		return fileObject;
	}

}
