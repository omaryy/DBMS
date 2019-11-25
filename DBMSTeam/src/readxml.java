	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.DocumentBuilder;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import org.w3c.dom.Node;
	import org.w3c.dom.Element;
	import java.io.File;
public class readxml {



	  public static  void readfromxmlfile(String path,int a[]) {

	    try {

		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
				
	

		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
		NodeList nList = doc.getElementsByTagName("row");
				
		System.out.println("----------------------------");
if(a.length==0) {
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList list1=nNode.getChildNodes();
				for(int i=0;i<list1.getLength();i++)
				{
					Node n=list1.item(i);
					System.out.println("column"+(i+1)+" "+n.getTextContent());
				}
		}
		}
}
else {
	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			NodeList list1=nNode.getChildNodes();
			for(int i=0,j=0;i<a.length&&j<list1.getLength();i++,j++)
			{
				Node n=list1.item(a[i]);
				System.out.println("column"+(a[i])+" "+n.getTextContent());
			}
	}
	}
	
}
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

	}


