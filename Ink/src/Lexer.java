import java.util.ArrayList;

public class Lexer {
	
	public Lexer() {
		super();
	}

	public ArrayList<String> lex(String code) {
		
		char[] toklist = code.toCharArray();
		ArrayList<String> tokens = new ArrayList<String>();
		String toks = "";
		boolean isPara = false;
		boolean isQuot = false;
		boolean isMath = false;
		boolean isBracket = false;
		boolean isComment = false;
		boolean isArr = false;
		int numBracket = 0;
		
		for (int i=0; i<toklist.length; i++) {
			char tok = toklist[i];
			if (tok == '(' && !isComment) {
				isPara = true;
			} else if (tok == ')' && !isComment) {
				isPara = false;
			} else if (tok == '"' || tok == '\'' && !isComment) {
				if (isQuot == false) { isQuot = true; }
				else { isQuot = false; }
			} else if (tok == '[' && !isComment) {
				if (toks != "") { tokens.add(toks); toks="";}
				isArr = true;
			} else if (tok == ']' && !isComment) {
				isArr = false;
			}
			if (tok == '{' && !isComment) {
				if (!isBracket)
					if (toks != "") { tokens.add(toks); toks = ""; }
				isBracket = true;
				numBracket += 1;
			} else if (tok == '}' && !isComment) {
				numBracket -= 1;
			} else if (tok == '/' && toklist[i+1] == '/') {
				isComment = true;
			} else if (tok == '~') {
				isComment = false;
			}
			
			
			if (isComment) {}
			else if (isBracket == true || isArr == true) {
				toks += tok;
			} else if ((tok == '=')) { 
				if (toklist[i+1]=='=' || toklist[i-1]=='=' || toklist[i+1]=='!' || toklist[i-1]=='!') {
					toks += tok;
				} else if (toks == "") {
					tokens.add(String.valueOf(tok));
					toks = "";
				} else {
					tokens.add(toks);
					tokens.add(String.valueOf(tok));
					toks = "";
				}
			} else if (tok == '~') {
				isMath = false;
				if (toks != "") { tokens.add(toks); toks = ""; }
				tokens.add("<EOL>");
			} else if ((tok == '(')) {
				boolean allPara = true;
				for (int k=0; i<toks.toCharArray().length; k++) {
					if (toks.toCharArray()[k] != '(') {
						allPara = false;
					}
				} if (toks != "" && allPara == false) {
					tokens.add(toks);
					toks = "";
					toks += tok;
				} else if (toks.replaceAll("\t", "").compareTo("print") == 0 || toks.replaceAll("\t", "").compareTo("println") == 0) {
					tokens.add(toks);
					toks = "";
					toks += tok;
				} else {
					if (toks != "") { tokens.add(toks); toks="";}
					toks += tok;
				}
			} else if ((tok != ' ') || (isPara == true) || (isQuot == true) || (isMath == true)) {
				toks += tok;
			} else {	
				if (toks != "") { tokens.add(toks); }
				toks = "";
			}
			
			if (tok == '}' && numBracket<1) {
				isBracket = false;
				tokens.add(toks);
				toks="";
			}
		}
		return tokens;
	}
}
