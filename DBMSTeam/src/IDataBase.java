
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class IDataBase implements Database {
int counter=1;
readxml re=new readxml();
WriteXml wr=new WriteXml();
	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		String s="DataBase\\" ;
		//convert the string (database name) into list of words again
		StringBuilder strb=new StringBuilder(databaseName);
		ArrayList< String> list=new ArrayList<String>();
		while(strb.length()!=0) {
			list.add(strb.substring(0, strb.indexOf(" ")));
			strb.delete(0, strb.indexOf(" ")+1);
		}
	
		boolean b= list.get(0).contains(".xml");
		
		if(b==true && dropIfExists==false) {      //create table
			//getting the name of table from the path
			StringBuilder sb=new StringBuilder(list.get(0));
			while(true) {
				sb.deleteCharAt(0);
				if(sb.charAt(0)=='\\') {
					sb.deleteCharAt(0);
					break;
				}
			}
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
return wr.createxmlfile(list, sb, s);

		}
		else if(b==false && dropIfExists==false) {   //create database
			s=s+list.get(0);
			File file=new File(s);
			if(file.mkdir()==true) {
				return "completed successfully!";
			}else {
				return databaseName+" is used!";
			}
		}
		else if(b==true && dropIfExists==true) {     //drop table
			
		}else {										  //drop database
			
		}
		
		
         
		return null;
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		
Parser om=new Parser();
ArrayList<String> querylist=om.regexchecker ("[A-Za-z]{1,}.{1}", query);

Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher(query);
int i=0;
int a[]=new  int[100];
while(m.find()) {
	a[i]=Integer.parseInt(m.group());
	i++;
}


String databasename=querylist.get(querylist.size()-1);
String path="DataBase\\"+databasename+"\\"+querylist.get(querylist.size()-2)+".xml";
String finalone="";
for(int j=0;j<path.length();j++)
{

	if(path.charAt(j)==' ')
	{
		j++;
	}
	finalone=finalone+path.charAt(j);
}


re.readfromxmlfile(finalone,a);


		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
	
				
		String s="DataBase\\" ;
		StringBuilder strb=new StringBuilder(query);
		ArrayList< String> list=new ArrayList<String>();
		while(strb.length()!=0) {
			list.add(strb.substring(0, strb.indexOf(" ")));
			strb.delete(0, strb.indexOf(" ")+1);
		}
		StringBuilder sb=new StringBuilder(list.get(0));
		boolean b= list.get(0).contains(".xml");
		
		if(b==true ) {      //create table
			//getting the name of table from the path
		
			while(true) {
				sb.deleteCharAt(0);
				if(sb.charAt(0)=='\\') {
					sb.deleteCharAt(0);
					break;
				}
			}
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);


		}
		s=s+list.get(0);
	
ArrayList<String> values=new ArrayList<String>();
for(int i=0;i<list.size();i++)
{
	if(list.get(i).compareTo("values")==0)
	{
		for(int j=i+1;j<list.size();j++)
		{
			
			values.add(list.get(j));
		}
	}
}

try {

	return wr.addNodeToXML(values,sb, s);
} catch (SAXException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

		
		return 0;
    
}
	public void printselected(Object arr[][])
	{
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[i].length;j++)	
			{
				System.out.println(arr[i][j]+" ");
				
			}
			System.out.println();
		}
		
	}
	
	}

