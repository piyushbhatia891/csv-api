package com.epam.kata.csvservice.controllers;

import static org.mockito.Mockito.when;
import javax.annotation.security.RunAs;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.epam.kata.csv_processor.models.CsvFileObject;
import com.epam.kata.csvservice.CsvServiceApplication;
import com.epam.kata.csvservice.models.CsvFile;
import com.epam.kata.csvservice.service.CsvService;

import ch.qos.logback.core.status.Status;

@RunAs(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK,classes = {CsvServiceApplication.class})
public class CsvControllerTest {
	@MockBean
	private CsvService csvService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	CsvFile csvFile;
	List<String> columnNames=new ArrayList<String>();
	List<CsvFileObject> returnedFile=new ArrayList<CsvFileObject>();
	@BeforeAll
	public void init() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		csvFile=new CsvFile();
		csvFile.setFileUrl("D:/abc/12.csv");
		csvFile.setLocal(true);
		returnedFile.add(new CsvFileObject());
		columnNames.add("one");
	}
	
	@Test
	@DisplayName("load a csv file from local file path")
	public void loadCsvFileFromLocal() {
		when(csvService.loadCsvFile(csvFile)).thenReturn(returnedFile);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/csv/load", csvFile)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{'fileUrl':'D:/abc/12.csv','isLocal':'true' }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("load a csv file from remote file path")
	public void loadCsvFileFromRemotePath() {
		when(csvService.loadCsvFile(csvFile)).thenReturn(returnedFile);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/csv/load", csvFile)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{'fileUrl':'D:/abc/12.csv','isLocal':'false' }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("Get List of columns having null or empty Values")
	public void getNullOrEmptyCont() {
		when(csvService.getColumnNamesHavingNullOrEmptyValues(csvFile.getFileUrl(),csvFile.isLocal()))
		.thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/csv/load", csvFile)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
		

}
