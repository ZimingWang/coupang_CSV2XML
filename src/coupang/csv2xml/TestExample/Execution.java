package coupang.csv2xml.TestExample;

import coupang.csv2xml.solution.Tools.CSVToXMLConverter;

public class Execution {

	public static void main(String argv[]){
		
		CSVToXMLConverter CXConverter = new CSVToXMLConverter();
		CXConverter.convertFile("test1.csv", "result1.xml", ",");
	}
	
	
}
