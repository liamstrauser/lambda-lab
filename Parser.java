
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;


public class Parser {
	
	public ArrayList<String> tokens;
	

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

	public Expression parseApp (int start, int length) {
		String current = tokens.get(length-1);

		if (current.equals(")")) {
			Stack parenStack = new Stack();
			int idx = length-2;
			int insideParen;
			while (parenStack.size() > 0 && idx > 0) {
				if (tokens.get(idx).equals(")")){
					insideParen = idx;
					parenStack.push(")");
					
				}
				else if (tokens.get(idx).equals("(")) {
					if (parenStack.size() == 0) {
						break;
					}
					else {
						parenStack.pop();
					}
				}

			}
			
		}
		
		
		if (start <= 0) {
			return new Variable(tokens.get(start));
		}

		return new Application(parseApp(start, length-1), new Variable(tokens.get(length-1)));
	}

	// public Expression parseHelper(int start, int end) {
		
	// 	if (tokens.get(0).equals("\\")) {
	// 		return null; // IDK but this will handle function
	// 	}
		
	// 	// we are just looking at one thing so it
	// 	if (start == end) {
	// 		return new Variable(tokens.get(start));
	// 	}

	// 	return parseApp(start, end);
	// }
	

	public Expression parse() throws ParseException {
		// if (tokens.size() == 0) return null;

		preParse();

		// Expression exp = parseHelper(0, tokens.size()-1);

		if (tokens.size() == 1) {
			Variable var = new Variable(tokens.get(0));
			return var;
		}
		else if (tokens.size() == 2) {
			Application app  = new Application(new Variable(tokens.get(0)), new Variable(tokens.get(1)));
			return app;
		}
		else if (tokens.size() > 2) {
			Application app = (Application) parseApp(0, tokens.size());
			return app;
		}


		// This is nonsense code, just to show you how to thrown an Exception.
		// To throw it, type "error" at the console
		if ("apple".equals("error")) {
			throw new ParseException("User typed \"Error\" as the input!", 0);
		}
		
		return new Variable("");
	}
}