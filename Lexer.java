
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class Lexer {
	
	public String comment;
	/*
	 * A lexer (or "tokenizer") converts an input into tokens that
	 * eventually need to be interpreted.
	 * 
	 * Given the input 
	 *    (\bat  .bat flies)cat  λg.joy! )
	 * you should output the ArrayList of strings
	 *    [(, \, bat, ., bat, flies, ), cat, \, g, ., joy!, )]
	 *
	 */
	public ArrayList<String> tokenize(String input) {
		ArrayList<String> tokens = new ArrayList<String>();

		ArrayList<Character> tokenChars = new ArrayList<>(Arrays.asList('(', ')', '=', '\\', '.'));
		
		
		for(int i=0; i < input.length(); i++){
			if(tokenChars.contains(input.charAt(i))){
				tokens.add(String.valueOf(input.charAt(i)));
			}
			else if(String.valueOf(input.charAt(i)).equals("λ")){
				tokens.add("\\");
			}
			else if(input.charAt(i) == ' '){
				continue;
			}
			else if (input.charAt(i) == ';'){
				comment = input.substring(i+1);
				break;
			}
			else{
				String subs = "";

				while (i < input.length() && (Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i)))) {
					subs += input.charAt(i);
					i++;
				}
				
				tokens.add(subs);
				i--;
			}
		}

		return tokens;
	}
}