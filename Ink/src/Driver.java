import javax.script.ScriptException;

public class Driver {
	
	/*
	 * TODO:
	 * -Add for/while
<<<<<<< HEAD
=======
	 * -Arrays
	 * -Add returning on function
>>>>>>> parent of 54fa6ba... Added Return to Function
	 * 
	 * BUGS:
	 * -cant call function until its declared
	 * -Add assigning return values of func to variables
	 * -Cant add multiple parathensis when printing certain things (update lexer)
	 * -scope of variables to inside statements
	 * -add, remove, and get from arrays
	 * 
	 */
	
	public static void main (String[] args) throws ScriptException {
		Parser p = new Parser(new Lexer().lex(new Input("test.ink").readFile()));
	}
}
