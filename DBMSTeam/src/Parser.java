import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class Parser {
	static GUI frame;
	static String currentDatabase="DataBase\\";
	public static void main(String[] args) {
		
		frame=new GUI("DBMS");
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
	
	
		frame.btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parse(frame.command.getText());
			}
		});
	
		
	}
	
	public static void parse (String query) {
		IDataBase d=new IDataBase();
		//using regex
		query=query+" ";
		ArrayList<String> list=regexchecker("[A-Za-z]{1,}.{1}", query);
		//removing the last character
		for (int i = 0; i < list.size(); i++) {
			StringBuilder sb=new StringBuilder(list.get(i));
			sb=sb.deleteCharAt(sb.length()-1);
			list.remove(i);
			list.add(i, sb.toString());
		}
	
		
		
		try {
			
			if(list.get(0).equalsIgnoreCase("create")) {           //create
				if(list.get(1).equalsIgnoreCase("database")) {                 //database
			frame.label.setText(d.createDatabase(list.get(2)+" " ,false));
				
					currentDatabase=list.get(2);
					
				}else if(list.get(1).equalsIgnoreCase("table")) {              //table
					String str=currentDatabase+"\\"+list.get(2)+".xml ";
					for (int i = 3; i < list.size(); i++) {
						str=str+list.get(i)+" ";
					}
					frame.label.setText(d.createDatabase(str ,false));
					
				}else {
					frame.label.setText("Can't be created!");
				}
			}
			
			else if(list.get(0).equalsIgnoreCase("drop")) {        //drop
				if(list.get(1).equalsIgnoreCase("database")) {
					frame.label.setText(d.createDatabase(list.get(2) ,true));
					
				}else if(list.get(1).equalsIgnoreCase("table")) {
					frame.label.setText(d.createDatabase(list.get(2)+".xml" ,true));
		
				}else {
					frame.label.setText("Can't be droped!");
				}
			}
			else if(list.get(0).equalsIgnoreCase("use")) {         //use
				frame.label.setText(d.createDatabase(list.get(1)+" " ,false));
				currentDatabase = list.get(1);
			}
			else if(list.get(0).equalsIgnoreCase("select"))
			{
				query=query+currentDatabase;
				
				d.executeQuery(query);
				
			}
			else if(list.get(0).equalsIgnoreCase("insert")){
				String str=currentDatabase+"\\"+list.get(2)+".xml ";
				for (int i = 3; i < list.size(); i++) {
					str=str+list.get(i)+" ";
				}
				d.executeUpdateQuery(str);
				
			}
			
			else if(list.get(0).equalsIgnoreCase("update"))
			{
				
			}
			else if(list.get(0).equalsIgnoreCase("delete"))
			{
				
				
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> regexchecker(String regex ,String str) {
		ArrayList<String> list=new ArrayList<String>();
		Pattern checkregex=Pattern.compile(regex);
		Matcher regexmatcher=checkregex.matcher(str);
		while(regexmatcher.find()) {
			if(regexmatcher.group().length() !=0 ) {
				list.add(regexmatcher.group());
			}
		}
		//System.out.println(list.size());
		return list;
	}

}

