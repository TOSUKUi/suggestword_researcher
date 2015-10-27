import java.io.*;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import java.nio.charset.MalformedInputException;
import java.net.MalformedURLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import java.util.zip.GZIPInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *@author TOSUKUi
 *
 */

class XmlController{
    
     
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    /**
     *五十音及びアルファベットなどサジェストの接尾語
     */
    Words words;
   
    public XmlController() throws ParserConfigurationException{
	factory = DocumentBuilderFactory.newInstance();	
	builder = factory.newDocumentBuilder();
	factory.setValidating(true);
	words = new Words();

    }


    
    
    

    public void getSuggest(String word) throws SAXException,MalformedURLException,UnsupportedEncodingException,IOException{
	
	for(int i = 0;i < words.length();i++){
	    

	    URL url = new URL("http://www.google.com/complete/search?hl=ja&output=toolbar&ie=utf-8&oe=utf-8&q=" + "\"" +word +'+'+this.words.getWord(i) +"\"");
	    
	    // 接続オブジェクト
	    HttpURLConnection urlConn = null;
	    try{
		urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setRequestMethod("GET");
		
		
	     }catch(IOException e){
		e.printStackTrace();
		System.out.println(e + "がコネクトプロセスで検出された");
	     }
	    urlConn.connect();
	   
	    
	    
	    Document doc = null;
	    System.out.println(urlConn.getInputStream());
	    try {
		doc = builder.parse(urlConn.getInputStream());
	    }catch(IOException e){
		e.printStackTrace();
		System.out.println(e + "がドキュメント生成プロセスで検出された");
	    } catch (SAXException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	    }
	    Element root = doc.getDocumentElement();
	    System.out.println("ルート要素名：" + root.getTagName());
	    NodeList nodeList = root.getElementsByTagName("CompleteSuggestion");
	    System.out.println("検索ワード="+ word + "+" + this.words.getWord(i) );
	    for (int j = 0; j < nodeList.getLength(); j++) {
		Element element = (Element)nodeList.item(j);
		Element childElement = (Element)element.getElementsByTagName("suggestion").item(0);
		String suggest = childElement.getAttribute("data");
	
	    }
	    
	    
		    
	}
	
	

    }

    

}
