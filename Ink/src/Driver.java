import javax.script.ScriptException;

public class Driver {
	
	/*
	 * TODO:
	 * -Add for/while
	 * -Arrays
	 * -Add assigning return values of func to variables
	 * -Cant add multiple parathensis when printing certain things (update lexer)
	 * 
	 * BUGS:
	 * -cant call function until its declared
	 * 
	 * MAYBE:
	 * -scope of variables to inside statements
	 */
	
	public static void main (String[] args) throws ScriptException {
		Parser p = new Parser(new Lexer().lex(new Input("test.ink").readFile()));
	}
}
