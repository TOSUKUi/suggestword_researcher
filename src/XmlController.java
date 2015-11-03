import java.io.*;
import java.lang.Thread;
import java.lang.Process;
      
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;
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
    
    HashSet<String> suggest;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    int numQuery;
    int flag;
    int stepToEnd;
    /**
     *五十音及びアルファベットなどサジェストの接尾語
     */
    Words words;
   
    public XmlController() throws ParserConfigurationException{
	factory = DocumentBuilderFactory.newInstance();	
	builder = factory.newDocumentBuilder();
	factory.setValidating(true);
	words = new Words();
	suggest = new HashSet<String>(400);
	flag = 0;
	stepToEnd=0;
    }
    
    private void makeSuggestionTree(String suggestion,int deeply,Double progress) throws SAXException,MalformedURLException,UnsupportedEncodingException,IOException{
	this.suggest.add(suggestion);
	deeply++;
	
	int i = 0;
	System.out.print("\r                                                                                                                                   ");
	System.out.print("\r"+"進捗="+progress+"%"+ "深さ = "+ deeply+"親サジェスト :" + suggestion);
	

	HashSet<String> childrenSuggests = new HashSet<String>(getSuggest(suggestion));
	
	if(childrenSuggests.isEmpty())
	    return;
	
	
	Iterator<String> it = childrenSuggests.iterator();
	while(it.hasNext()){
	    i++;
	    if(deeply == 1)
		progress = Double.valueOf(i)/Double.valueOf(childrenSuggests.size()) * 100;
	    String child = new String(it.next());
	    if(!this.suggest.contains(child))		
		makeSuggestionTree(child,deeply,progress);	    
	    }
	
    }
    
	
    
    public HashSet<String> getSuggestion(String word) throws SAXException,MalformedURLException,UnsupportedEncodingException,IOException{
	int deeply = 0;
	Double progress = 0.0;
	makeSuggestionTree(word,deeply,progress);
	return this.suggest;
    }



    private HashSet<String> getSuggest(String word) throws SAXException,MalformedURLException,UnsupportedEncodingException,IOException{
	
	HashSet<String> suggestions = new HashSet<String>();
	
	
	for(int i = 0;i < words.length();i++){
	    

	    URL url = new URL("http://www.google.com/complete/search?hl=ja&output=toolbar&ie=utf-8&oe=utf-8&q="  + word + this.words.getWord(i));
	    
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

	    try {
		doc = builder.parse(urlConn.getInputStream());
	    }catch(IOException e){
		//e.printStackTrace();
		
		Process runtimeProcess;
		int processCompleted = -1;
		try {
		    runtimeProcess = Runtime.getRuntime().exec(new String[]{"networksetup","-disconnectpppoeservice","SRASVPN (L2TP) 2"});
		    processCompleted = runtimeProcess.waitFor();
		    BufferedReader br = new BufferedReader(new InputStreamReader(runtimeProcess.getErrorStream()));
		    String line;
		    while ((line = br.readLine()) != null) {
			System.out.print(line);
		    }
		} catch (IOException e1) {
		    e1.printStackTrace();
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}
		processCompleted = -1;
		try {
		    runtimeProcess = Runtime.getRuntime().exec(new String[]{"networksetup","-connectpppoeservice","SRASVPN (L2TP) 2"});
		    System.out.print("\r"+"networksetup -connectpppoeservice \"SRASVPN (L2TP) 2\"");
		    processCompleted = runtimeProcess.waitFor();
		    BufferedReader br = new BufferedReader(new InputStreamReader(runtimeProcess.getErrorStream()));
		    String line;
		    while ((line = br.readLine()) != null) {
			System.out.print(line);
		    }
		    Thread.sleep(3000);
		} catch (IOException e1) {
		    e1.printStackTrace();
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}
		

		

		return getSuggest(word);
	    } catch (SAXException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();		
	    }
	
	    
	    Element root = doc.getDocumentElement();
	    
	    NodeList nodeList = root.getElementsByTagName("CompleteSuggestion");
	    
	    for (int j = 0; j < nodeList.getLength(); j++) {

		Element element = (Element)nodeList.item(j);
		Element childElement = (Element)element.getElementsByTagName("suggestion").item(0);
		suggestions.add(childElement.getAttribute("data").replace(" ","+"));
		
	    }
	    if(i == 0)
		if(suggestions.size() < 9)
		    return suggestions;
	    
	  	    
	}
	
	return suggestions;
	
	
    }

    String input() throws IOException{
	BufferedReader input =
	    new BufferedReader (new InputStreamReader (System.in));
	String str = input.readLine( );
	return str;
    }
    
	    



}



