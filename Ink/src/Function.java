import java.util.ArrayList;

import javax.script.ScriptException;

public class Function {
	
	private String name;
	private ArrayList<Variable> parameters;
	private String content;
	private Dictionary dic;
	private Object value;
	
	public Function(String name, String param, String content) {
		super();
		this.name = name;
		this.parameters = getParameterVars(param.substring(1, param.length()-1)+"~");
		this.content = content;
		System.out.println(toString());
	}
	
	public void setParam(String param) {
		String parameter = param.substring(1, param.length()-2)+"~";
		ArrayList<String> newParams = new ArrayList<String>();
		char[] toklist = parameter.toCharArray();
		String toks = "";
		for (char tok : toklist) {
			if (tok == ',' || tok == '~') {
				if (toks != "") {
					newParams.add(toks.replaceAll(" ", ""));
					toks = "";
				}
			} else {
				toks += tok;
			}
		}
		if (newParams.size() == parameters.size()) {
			for (int i=0; i<parameters.size(); i++) {
				parameters.get(i).setValue(newParams.get(i));
			}
		}
	}

	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void callFunc(String content) throws ScriptException {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (Variable var : parameters) {
			dic.vars.add(var);
			index.add(dic.vars.size());
		}
		String code = content.substring(1, content.length()-1);
		if (code.contains("return")) {
			code += "<"+name+">~";
		}
		new Parser(new Lexer().lex(code));
		for (Integer num : index) {
			dic.vars.remove(num);
		}
	}
	
	//Parse parameter list
	private ArrayList<Variable> getParameterVars(String value) {
		ArrayList<Variable> params = new ArrayList<Variable>();
		char[] toklist = value.toCharArray();
		String toks = "";
		for (char tok : toklist) {
			if (tok == ',' || tok == '~') {
				if (toks != "") {
					if (dic.isName(toks) && !dic.isVariable(toks)) {
						params.add(new Variable(toks.replaceAll(" ", ""), null));
						toks = "";
					}
				}
			} else {
				toks += tok;
			}
		}
		return params;
	}
	
	public String toString() {
		return "Function: Name=" + name + " | Parameters=" + parameters.get(0).getValue() + " | Value=" + value;
	}
}
