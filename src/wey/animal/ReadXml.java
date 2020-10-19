package wey.animal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.XMLFormatter;

import com.sun.org.apache.xerces.internal.parsers.XMLParser;

public class ReadXml {

	public static void main(String[] args) {
		try {
			InputStream is = new URL("https://asms.coa.gov.tw/Asms/api/ViewNowAnimal?pageSize=3&currentPage=1&sortDirection=DESC&sortFields=AcceptDate").openStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//			XMLParser parser = XMLFormatter.
			System.out.println(isr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
