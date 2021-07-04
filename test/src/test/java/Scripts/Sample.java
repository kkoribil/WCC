package Scripts;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import java.io.StringWriter;
/*import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;*/


public class Sample {

	@Test
	public void test() throws Exception {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();
		
		//DocumentBuilder doc = (DocumentBuilder) db.newDocument();
		
		Element root = doc.createElement("ScheduleOrder");
		root.setAttribute("D1", "v1");
		root.setAttribute("DocumentType", "0001");
		root.setAttribute("EnterpriseCode", "PB");
		root.setAttribute("OrderNo", "123456789012");
		doc.appendChild(root);
		//toString(doc);	
	}
	/*
	 * private static void toString(Document newDoc) throws Exception{ DOMSource
	 * domSource = new DOMSource(newDoc); Transformer transformer =
	 * TransformerFactory.newInstance().newTransformer(); StringWriter sw = new
	 * StringWriter(); StreamResult sr = new StreamResult(sw);
	 * transformer.transform(domSource, sr); System.out.println(sw.toString()); }
	 */

}
