import java.util.ArrayList;

class Words{
    private ArrayList<String> words;

    public Words(){
	words = new ArrayList<String>();
	words.add("");
	words.add("あ");
	words.add("い");
	words.add("う");
	words.add("え");
	words.add("お");
	words.add("か");
	words.add("き");
	words.add("く");
	words.add("け");
	words.add("こ");
	words.add("さ");
	words.add("し");
	words.add("す");
	words.add("せ");
	words.add("そ");
	words.add("た");
	words.add("ち");
	words.add("つ");
	words.add("て");
	words.add("と");
	words.add("な");
	words.add("に");
	words.add("ぬ");
	words.add("ね");
	words.add("の");
	words.add("は");
	words.add("ひ");
	words.add("ふ");
	words.add("へ");
	words.add("ほ");
	words.add("ま");
	words.add("み");
	words.add("む");
	words.add("め");
	words.add("も");
	words.add("や");
	words.add("ゆ");
	words.add("よ");
	words.add("わ");
	words.add("を");
	words.add("ん");
	words.add("が");
	words.add("ぎ");
	words.add("ぐ");
	words.add("げ");
	words.add("ご");
	words.add("ざ");
	words.add("じ");
	words.add("ず");
	words.add("ぜ");
	words.add("ぞ");
	words.add("だ");
	words.add("ぢ");
	words.add("づ");
	words.add("で");
	words.add("ど");
	words.add("ば");
	words.add("び");
	words.add("ぶ");
	words.add("べ");
	words.add("ぼ");
	words.add("ぱ");
	words.add("ぴ");
	words.add("ぷ");
	words.add("ぺ");
	words.add("ぽ");
	words.add("a");
	words.add("b");
	words.add("c");
	words.add("d");
	words.add("e");
	words.add("f");
	words.add("g");
	words.add("h");
	words.add("i");
	words.add("j");
	words.add("k");
	words.add("l");
	words.add("m");
	words.add("n");
	words.add("o");
	words.add("p");
	words.add("q");
	words.add("r");
	words.add("s");
	words.add("t");
	words.add("u");
	words.add("v");
	words.add("w");
	words.add("x");
	words.add("y");
	words.add("z");
	words.add("1");
	words.add("2");
	words.add("3");
	words.add("4");
	words.add("5");
	words.add("6");
	words.add("7");
	words.add("8");
	words.add("9");
    }

    public String getWord(int index){
	if(words.get(index).equals(""))
	    return words.get(index);
	else
	    return "+" + words.get(index);
    }

    public int length(){
	return words.size();
    }
}
