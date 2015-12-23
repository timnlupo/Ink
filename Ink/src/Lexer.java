import java.util.ArrayList;

public class Lexer {
	
	static Input input = new Input("test.ink");
	private static char[] symbols = {'(', ')', '{', '}', '=', '+', '-', '*', '/'};
	
	public static void main (String[] args) {
		initialLex(input.readFile());
	}
	
	private static ArrayList<Character> initialLex (String input) {

		char[] toklist = input.toCharArray();
		ArrayList<Character> toks = new ArrayList<Character>();
		
		for (char tok : toklist) {
			if (tok != ' ') {
				toks.add(tok);
			} else {
				if (toks.isEmpty() == false) { lex(toks); }
				toks.clear();
			}
		}
		return toks;
	}
	
	public static ArrayList<Object> lex (ArrayList<Character> toks) {
		
		ArrayList<Object> tokens = new ArrayList<Object>();
		
		//lex separate array lists to one full one for parsing
		System.out.println(toks);
		
		return tokens;
	}
}
