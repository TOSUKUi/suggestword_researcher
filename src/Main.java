import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.nio.charset.MalformedInputException;
import java.net.MalformedURLException;
import java.io.UnsupportedEncodingException;
class Main{
    public static void main(String[] args) throws SAXException,MalformedURLException,UnsupportedEncodingException,IOException{
	try{
	XmlController xc = new XmlController();
	xc.getSuggest("マリオ");
	}catch(ParserConfigurationException e){
	    System.out.println("xmlmiss" + e);
	}


	return ;
	
    }



}
