package phrase;
import java.util.List;

import jutil.AppError;
import jutil.Lex;


public abstract class Expression extends Phrase {
	
	  // Omega tokens:
	   public static final String NUMBER = "\\d*\\.?\\d+";
	   public static final String OPERATOR = "\\+|\\*|-|/|=|<|>|%";
	   public static final String IDENTIFIER = "[a-zA-Z][a-zA-Z0-9]*";
	   public static final String PUNCTUATION =     
	                                    ";|\\(|\\.|\\)|\\]|\\[|\\}|\\{";
	    public static final String SYMBOL =
	        Lex.join(IDENTIFIER, OPERATOR);
	    public static String TOKEN = SYMBOL;
	    static {
	     TOKEN = Lex.join(TOKEN, PUNCTUATION);
	     TOKEN = Lex.join(TOKEN, NUMBER);
	    }
	

	public static boolean isNext( List<String> tokens) throws AppError{
		
		if (tokens == null || tokens.isEmpty()) {
			return false;
		}
		return Literal.isNext(tokens) || Symbol.isNext(tokens) || SpecialForm.isNext(tokens);
	}//end of isNext
	
	public static Phrase parse(List<String> tokens) throws AppError{
		if (SpecialForm.isNext(tokens)) return SpecialForm.parse(tokens);
		if (Literal.isNext(tokens)) return Literal.parse(tokens);
		if (Symbol.isNext(tokens)) return Symbol.parse(tokens);
		 throw new AppError("Unrecognized phrase in Phrase.parse//Error in Expression");
			
		
	}//end of phrase
	

}
