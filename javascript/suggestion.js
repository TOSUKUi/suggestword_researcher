function mySuggestion() {

    
 

    var searches = "マリオ"
    var myxml = "";
    var suggest;
    var sn = new SuggestNode(searches);
    var root = new SuggestNode(searches);
    root = makeSuggestionTree(sn,data);



}

function getSuggest(word,data){
    var suggest = new Array();
    var k = 0;
    for(var i = 0;i < data.length;i++){

	var url = 'http://www.google.com/complete/search?hl=ja&output=toolbar&q=' + word +" "+ data[i];
	myxml = UrlFetchApp.fetch(url).getContentText();
	var myDoc = XmlService.parse(myxml);
	var root = myDoc.getRootElement();

	var children = root.getChildren('CompleteSuggestion');
	if(children.length < 1) continue;

	for(j=0;j<children.length;j++){
	    var suggestion_node = children[j].getChild('suggestion');
	    suggest = suggestion_node.getAttribute('data').getValue();
	    k = k + 1;

	}
    }
        /*for(var j = 0;j < suggest.length;j++){
    var a = getSuggest(suggest[j],data,suggests);
    suggests += a;
    }*/

    suggest = deleteOverlap(suggest);


    return suggest;

}

function SuggestNode(suggest){
    this.suggest = suggest;
    this.childSuggest = new Array();


}

function searchSuggestTree(root,suggests){


}




function makeSuggestionTree(sn,data){
    var children = getSuggest(sn.suggest,data);
    for(var i = 0;i < children.length; i++){
	var sns = new SuggestNode(children[i]);
	sn.childSuggest[i] = sns;
	makeSuggestionTree(sns,data);
    }
    return sn;
}








function deleteOverlap(a){
    var list = new Array();
    var k = 1;
    var flag = 0;
    list[0] = a[0];
    for(var i = 1;i < a.length; i++){
	for(var j = 0;j < list.length; j++){
	    if (a[i] == list[j]){
		flag = 1;
		break;
	    }
	    if (flag == 1)  break;
	}
	if(flag == 0){
	    list[k] = a[i];
	    k = k + 1;

	}
	flag = 0;
    }

    return list;

}
