package client;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import engine.Engine;


public class Client{
	
	private static String pFilePath;
	private static String pInputType;
	private static String pAlias;
	private static List<List<String>> inputSpec = new ArrayList<List<String>>();;
	private static Scanner scanner;
	private static List<String> prefixes = null;
	private static Engine engine ;
	private static List<Engine> engineList;
	
	public static void main(String[] args) throws FileNotFoundException, DocumentException {

		engineList = new ArrayList<Engine>();
		Client client = new Client();
		scanner = new Scanner(System.in);
		String addMore ;
		while(true){
			
			client.askInputeFile();
			
			engine = new Engine(pFilePath, pInputType , pAlias);
			client.askForRules();
			
			if(pInputType.equals("RAW")) {
				engine.registerInputRuleSetForPlainFiles(inputSpec);

			}else if(pInputType.equals("ANNOTATED")) {
				client.addPrefixes();
				engine.registerInputRuleSetForAnnotatedFiles(inputSpec , prefixes);
			}
			engine.loadFileAndCharacterizeBlocks();
			client.askForReport();
			client.askForExportToAnotherForm();
			engineList.add(engine);
			
			while(true) {
				System.out.println("Do you want input another file? Try with yes or no.");
				addMore = scanner.nextLine().strip().toUpperCase();
				if(addMore.equals("NO")) {
					client.editedFiles() ;
					return;
				} 
				else if(addMore.equals("YES")) break;
				System.out.println("I didnt get the answer.");
			}
			
		}		
	}
	
	//ask the user to add Prefixes if the fileType is ANNOTATED
	private void addPrefixes() {

		prefixes = new ArrayList<String>();
		String prefixe;
		String addMore = "YES";
		while(true) {
			if(addMore.equals("YES")) {
				System.out.println("Add prefixe ");
				prefixe = scanner.nextLine();
				prefixes.add(prefixe);
				System.out.println("Do you want add more prefixes? Try with yes or no.");
				addMore = scanner.nextLine().strip().toUpperCase();
			}else if(addMore.equals("NO")) return;
			else{
				System.out.println("I didnt get the answer. Try only with yes or no...");
				addMore = scanner.nextLine().strip().toUpperCase();;
			}
		}
		
	}
	//ask the user for the input file details (path , alias , type)
	public void askInputeFile() {
	    scanner = new Scanner(System.in);
	    Client.pFilePath = askForPath();
	    Client.pAlias = askForAlias();
	    Client.pInputType = askForFileType();
	}
	
	public String askForPath() {
	    System.out.println("enter file path"); 
	    return scanner.nextLine();
	}
	
	public String askForAlias() {
	    System.out.println("enter name "); 
	    return scanner.nextLine();
	}
	
	public String askForFileType() {
	    System.out.println("eneter file type");
	    String fileType = scanner.nextLine().strip().toUpperCase();
	    while(true) {
	    	if(fileType.equals("RAW")) return  fileType;
	    		
	    	
	    	else if(fileType.equals("ANNOTATED")) return  fileType; 

	    	else{
	    		System.out.println("invalid file type. Try again ");
	    		fileType = scanner.nextLine().strip().toUpperCase();
	    	}	
	    }   
	}
	
	//ask the user to add Rules that wants to apply to the text
	 public void askForRules(){
		 
		    String addMore = "YES";
		    while(true) {
		      if(addMore.equals("YES")) {
		    	  addRule();
		    	  System.out.println("do you want to add more rules? Type yes or no");
		    	  addMore = scanner.nextLine().strip().toUpperCase();
		      }
		      else if(addMore.equals("NO")) return;
		      else {
					System.out.println("I didnt get the answer. Try only with yes or no...");
					addMore = scanner.nextLine().strip().toUpperCase();;
		      }
		      
		    }
	 }
	  public static void addRule() {

		    List<String> rule = new ArrayList<String>();
		    String inputRule;
		    String [] inputRuleSplit = null;
		    
		    System.out.println("Add a rule ");
		    inputRule = scanner.nextLine();
		    inputRuleSplit = inputRule.split(" ");
		    inputSpec.add(rule);
			for(String l: inputRuleSplit){
				rule.add(l);
			}
		    
	}
	 public void askForReport() {
		 String report;
		 System.out.println("Do you want a report? answer yes or no.");
		 
		 while(true) {
			 report = scanner.nextLine().strip().toUpperCase();;
			 if(report.equals("YES")){
				 List<String> Report = new ArrayList<String>();
				 Report = engine.reportWithStats();
				 for(String r:Report) {
					 System.out.println(r);
				 }
				 break;
			 }
			 else if(report.equals("NO")) {
				 System.out.println("No report for this file.");
				 break;
			 }
			 else System.out.println("I didn't' get the answer. Try again with yes or no.");
		 }
	 }
	 public void askForExportToAnotherForm() throws FileNotFoundException, DocumentException {
		 String export;
		 String outputFileName;
		 System.out.println("Do you want to export the file to another form? Try with yes or no.");
		 
		 while(true) {
			 export = scanner.nextLine().strip().toUpperCase();;
			 if(export.equals("YES")) {
		        while(true){
		            System.out.println("Do you want to export this file to pdf or markdown? ");
		            export = scanner.nextLine().strip().toUpperCase();;
		            if(export.equals("PDF")) {
		              System.out.println("Enter output file name ");
		              outputFileName = scanner.nextLine();
		              engine.exportPdf( outputFileName);
		              return;
		            }
		            if(export.equals("MARKDOWN")) {
		              System.out.println("Enter output file name ");
		              outputFileName = scanner.nextLine().strip().toUpperCase();;
		              engine.exportMarkDown( outputFileName);
		              return;
		            }else System.out.println("I didn't' get the answer. Try again with pdf or markdown.");
		          }
			 }
			 else if(export.equals("NO")) {
				 System.out.println("No export to another form for this file.");
				 break;	 
			 }else System.out.println("I didn't' get the answer. Try again with yes or no.");	 
		 }
			 
	 }
	 public void editedFiles() {
		 System.out.println("Τhese files were edited ");
		 for(Engine engine: engineList) {
			 System.out.println(engine.getFilePath());
		 }
	 }
	 
}
	 
	 
	 