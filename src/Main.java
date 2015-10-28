import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.nio.charset.MalformedInputException;
import java.net.MalformedURLException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.HashSet;
class Main{
    

    public static HashSet<String> unique;

    public static void main(String[] args) throws SAXException,MalformedURLException,UnsupportedEncodingException,IOException{

	if(args.length < 2){
	    System.out.println("Usage: java this \"searchword\" \"outputfilename\" ");
	    return;
	}
	try{ 
	    
	    XmlController xc = new XmlController();
	    unique = xc.getSuggestion(args[0]);

	}catch(ParserConfigurationException e){
	    System.out.println("xmlmiss" + e);
	}

	int numSuggest = unique.size();
	String result = unique.toString().replace(",","\n").replace("[","").replace("]","");
	System.out.println(result);
	
	System.out.println("合計サジェスト数="+numSuggest);
	String file_name = args[1];
	fileWriter(result,file_name);
	
	return;
	
    }
    
    public static  void fileWriter(String collection,String file_name) throws IOException{
	File file = new File(file_name);
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	pw.println(collection);
	pw.close();
    }

    
}
