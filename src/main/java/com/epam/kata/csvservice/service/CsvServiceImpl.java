package com.epam.kata.csvservice.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.kata.csv_processor.client.CsvProcessorClient;
import com.epam.kata.csv_processor.client.ProcessingClient;
import com.epam.kata.csv_processor.exceptions.CsvProcessorException;
import com.epam.kata.csv_processor.models.CsvClientFileRequest;
import com.epam.kata.csv_processor.models.CsvFileObject;
import com.epam.kata.csv_processor.service.CsvFileLoadStrategy;
import com.epam.kata.csv_processor.service.LoadLocalFile;
import com.epam.kata.csv_processor.service.Separator;
import com.epam.kata.csvservice.models.CsvFile;

@Service
public class CsvServiceImpl implements CsvService {

	@Override
	public void loadCsvFile(CsvFile csvFile) throws CsvProcessorException {
		ProcessingClient csvProcessingClient = new CsvProcessorClient();
		CsvFileLoadStrategy csvFileLoadStrategy = new LoadLocalFile();
		csvFileLoadStrategy.setCustomOperation(null);
		csvFileLoadStrategy.setSeparation(Separator.COMMA);
		csvProcessingClient.loadCsvFile(csvFileLoadStrategy, createCsvFileObjectForClient(csvFile));

	}

	private CsvClientFileRequest createCsvFileObjectForClient(CsvFile csvFile) {
		return new CsvClientFileRequest.CsvClientFileRequestBuilder(csvFile.getFileUrl()).build();

	}

}
