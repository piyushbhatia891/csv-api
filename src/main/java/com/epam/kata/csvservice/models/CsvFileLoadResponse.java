package com.epam.kata.csvservice.models;

import java.util.List;

public class CsvFileLoadResponse {

	private List<String> columnNames;

	public List<String> getFileId() {
		return columnNames;
	}

	private CsvFileLoadResponse(CsvFileLoadResponseBuilder builder) {
		this.columnNames = builder.columnNames;
	}

	public static class CsvFileLoadResponseBuilder {
		private List<String> columnNames;

		public CsvFileLoadResponseBuilder(List<String> columnNames) {
			this.columnNames = columnNames;
		}

		public CsvFileLoadResponse build() {
			return new CsvFileLoadResponse(this);
		}
	}

}
