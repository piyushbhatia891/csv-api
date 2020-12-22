package com.epam.kata.csvservice.models;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class CsvFile {
	
	@NonNull
	private String fileUrl;
	@NonNull
	private boolean isLocal;
	private String separation;
	private String name;
	
	public String getSeparation() {
		return separation;
	}
	public void setSeparation(String separation) {
		this.separation = separation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public boolean isLocal() {
		return isLocal;
	}
	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
	

}
