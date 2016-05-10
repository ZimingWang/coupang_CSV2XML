package coupang.csv2xml.solution.noTools;

public class Execution {

	public static void main(String argv[]){
		
		CSVToXMLConverter CXConverter = new CSVToXMLConverter();
		CXConverter.convertFile("test1.csv", "result1.xml", ",");
	}
	
	
}
