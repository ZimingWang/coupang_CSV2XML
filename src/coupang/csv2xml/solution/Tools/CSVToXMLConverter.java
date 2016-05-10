package coupang.csv2xml.solution.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opencsv.CSVReader;


public class CSVToXMLConverter {

    protected DocumentBuilderFactory domFactory = null;
    protected DocumentBuilder domBuilder = null;
	
	public CSVToXMLConverter() {
        try {
            domFactory = DocumentBuilderFactory.newInstance();
            domBuilder = domFactory.newDocumentBuilder();
        } catch (FactoryConfigurationError exp) {
            System.err.println(exp.toString());
        } catch (ParserConfigurationException exp) {
            System.err.println(exp.toString());
        } catch (Exception exp) {
            System.err.println(exp.toString());
        }

    }

	public int convertFile(String csvFileName, String xmlFileName, String delimiter) {

		int rowsCount = -1;
		BufferedReader csvReader;
		try {
			Document newDoc = domBuilder.newDocument();
			// Root element
			Element rootElement = newDoc.createElement("XMLCreators");
			newDoc.appendChild(rootElement);
			// Read csv file
			csvReader = new BufferedReader(new FileReader(csvFileName));

			// ** Now using the OpenCSV **//
			CSVReader reader = new CSVReader(new FileReader("test1.csv"), delimiter.charAt(0));
			// CSVReader reader = new CSVReader(csvReader);
			String[] nextLine;
			int line = 0;
			List<String> headers = new ArrayList<String>(5);
			while ((nextLine = reader.readNext()) != null) {

				if (line == 0) { // Header row
					for (String col : nextLine) {
						headers.add(col);
					}
				} else { // Data row
					Element rowElement = newDoc.createElement("row");
					rootElement.appendChild(rowElement);

					int col = 0;
					for (String value : nextLine) {
						String header = headers.get(col);

						Element curElement = newDoc.createElement(header);
						curElement.appendChild(newDoc.createTextNode(value.trim()));
						rowElement.appendChild(curElement);

						col++;
					}
				}
				line++;
			}
			// ** End of CSV parsing**//

			FileWriter writer = null;

			try {

				writer = new FileWriter(new File(xmlFileName));

				TransformerFactory tranFactory = TransformerFactory.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
				aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				Source src = new DOMSource(newDoc);
				Result result = new StreamResult(writer);
				aTransformer.transform(src, result);
				writer.flush();

			} catch (Exception exp) {
				exp.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}

			// Output to console for testing
			// Resultt result = new StreamResult(System.out);

		} catch (IOException exp) {
			System.err.println(exp.toString());
		} catch (Exception exp) {
			System.err.println(exp.toString());
		}
		return rowsCount;
		// "XLM Document has been created" + rowsCount;
	}
	
	
}
