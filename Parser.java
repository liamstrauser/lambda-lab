
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

	public Expression parseApp (int start, int end) {
		int parenCount = 0;
		int fullExp = end;

		int right = end;
		
		for (int i = end; i > start; i--) {
			if(tokens.get(i).equals(")")){
				if (parenCount == 0) {
					end = i;
				}
				parenCount++;
			}
			else if (tokens.get(i).equals("(")) {
				parenCount--;
				if (parenCount == 0) {
					 right = i;
					 break;
				}
			}
			else if (parenCount == 0) {
				right = i;
				break;
			}
		}
		
		
		if (right == start && end == fullExp) {
			return parseHelper(start + 1, end);
		}

		if (right == end) {
			return new Application(parseHelper(start, end), new Variable(tokens.get(end)));
		}

		return new Application(parseHelper(start, right), parseHelper(right+1, end));
	}

	public Expression parseHelper(int start, int end) {

		end--;

		if (tokens.get(0).equals("\\")) {
			return null; // IDK but this will handle function
		}
		
		// we are just looking at one thing so it
		if (start == end) {
			return new Variable(tokens.get(start));
		}

		return parseApp(start, end);
	}
	

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
			Application app = (Application) parseHelper(0, tokens.size());
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