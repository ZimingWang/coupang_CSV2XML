package coupang.csv2xml.TestExample;

import coupang.csv2xml.solution.noTools.CSVToXMLConverter;

public class TestWithNoToolSolution {

	public static void main(String argv[]){
		
		CSVToXMLConverter CXConverter = new CSVToXMLConverter();
		
		CXConverter.convertFile("testSource/csv/comaDelimiter.csv", "testSource/xml/comaDelimiter.xml", ",");
		CXConverter.convertFile("testSource/csv/escapingDoublequote.csv", "testSource/xml/escapingDoublequote.xml", ";");
		CXConverter.convertFile("testSource/csv/multiLine.csv", "testSource/xml/multiLine.xml", ";");
		CXConverter.convertFile("testSource/csv/simple.csv", "testSource/xml/simple.xml", ";");
		CXConverter.convertFile("testSource/csv/simpleMissing.csv", "testSource/xml/simpleMissing.xml", ";");
		CXConverter.convertFile("testSource/csv/simpleMixed.csv", "testSource/xml/simpleMixed.xml", ";");
		CXConverter.convertFile("testSource/csv/simpleQuote.csv", "testSource/xml/simpleQuote.xml", ";");
		CXConverter.convertFile("testSource/csv/simpleQuoteMissing.csv", "testSource/xml/simpleQuoteMissing.xml", ";");
			
		
	}
	
	
}
