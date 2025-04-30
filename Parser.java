
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
		boolean extraParen = false;

		int right = end;
		
		for (int i = end; i > start-1; i--) {
			if(tokens.get(i).equals(")")){
				if (parenCount == 0) {
					end = i;
					extraParen = true;
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
		
		
		if (right == start && end == fullExp && extraParen) {
			return parseHelper(++start, end);
		}


		if (right == end) {
			return new Application(parseHelper(start, right), new Variable(tokens.get(end)));
		}

		return new Application(parseHelper(start, right), parseHelper(++right, end));
	}

	public Expression parseHelper(int start, int end) {

		end--;

		if (tokens.get(start).equals("\\")) {
			return new Function(new Variable (tokens.get(start+1)), new Variable(tokens.get(start+3))); // IDK but this will handle function
		}

		// if (tokens.get(start).equals("(") && tokens.get(end).equals(")")) {
		// 	return parseHelper(start+1, end);
		// }
		
		// we are just looking at one thing so it
		if (start == end) {
			return new Variable(tokens.get(start));
		}
		
		return parseApp(start, end);
	}
	

	public Expression parse() throws ParseException {
		// if (tokens.size() == 0) return null;

		preParse();

		Expression exp = parseHelper(0, tokens.size());

	
		// This is nonsense code, just to show you how to thrown an Exception.
		// To throw it, type "error" at the console
		if ("apple".equals("error")) {
			throw new ParseException("User typed \"Error\" as the input!", 0);
		}
		
		return exp;
	}
}