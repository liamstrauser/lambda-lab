
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
	
	public ArrayList<String> tokens;
	public HashMap<String, Expression> defs = new HashMap<String, Expression>();
	
	
	/*
	 * Turns a set of tokens into an expression.  Comment this back in when you're ready.
	 */

	public void preParse() {
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).equals("\\") && (i == 0 || !tokens.get(i-1).equals("("))) {
				preParseHelper(i);
			}
		}
		System.out.println(tokens.toString());
	}

	public void insertParens(int start, int end) {
		tokens.add(start, "(");
		tokens.add(end + 1, ")");
	}

	public void preParseHelper(int index) {
		int start = index;
		int opens = 0;


		int i = index;
		while (i < tokens.size()) {
			String current = tokens.get(i);

			if (current.equals("(")){
				opens++;
			}
			
			else if(current.equals(")")){
				opens--;
				
				if (opens < -1){
					System.out.println("closing paren error");
				}
				else if(opens == -1){
					insertParens(start, i);
					return;
				}
			}
			i++;
		}

		if (opens == 0){
			insertParens(start, i);
		} 
		
		else if (opens > 0){
			System.out.println("Unclosed opening");
		}
		
		else {
			System.out.println("closing paren error");
		}
	}

	public Expression appHelper (int idx) {
		if (idx = tokens.size) {
			
		}
		else if () {

		}

		return new Application(new Variable(tokens.get(idx)), new Variable(tokens.get());
	}
	

	public Expression parse() throws ParseException {
		if (tokens.size() == 1) {
			Variable var = new Variable(tokens.get(0));
			return var;
		}
		else if (tokens.size() == 2) {
			Application app  = new Application(new Variable(tokens.get(0)), new Variable(tokens.get(1)));
			return app;
		}
		else if (tokens.size() > 2) {

			Application app = new Application(new Variable(tokens.get(0)), new Variable(tokens.get(1)));

			int i = 2;

			
		}
		


		// This is nonsense code, just to show you how to thrown an Exception.
		// To throw it, type "error" at the console
		
		return new Variable("");
	}
}
