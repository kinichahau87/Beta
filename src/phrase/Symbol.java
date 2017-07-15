package phrase;

import java.lang.reflect.Type;
import java.util.List;

import jutil.AppError;
import jutil.Lex;

import Value.Environment;
import Value.Value;

public class Symbol extends Expression {
	
	private String name = "";
	
	 public static final String NUMBER = "\\d*\\.?\\d+";
	   public static final String OPERATOR = "\\+|\\*|-|/|=|<|>|%";
	   public static final String IDENTIFIER = "[a-zA-Z][a-zA-Z0-9]*";
	   public static final String PUNCTUATION =     
	                                    ";|\\(|\\.|\\)|\\]|\\[|\\}|\\{";
	    public static final String SYMBOL =
	        Lex.join(IDENTIFIER, OPERATOR);
	   
	
	public static boolean isNext(List<String> tokens) throws AppError{
		if (tokens.size() == 0 || tokens == null)
			return false;
		try{
			Lex.scan(SYMBOL, tokens.get(0));
		}//end of try
		catch (Exception e){
			return false;
		}
		
		return true;
	}
	
	public static Phrase parse(List<String> tokens) throws AppError{
		
		
		
		if (tokens.size() == 0 || tokens == null)
			throw new AppError("Phrase is empty");
		Symbol result = new Symbol();
		
		result.name = tokens.remove(0);
				
		return result;		
	}
	
	public String toString(){
		return name;
	}
	
	public Value eval(Environment env) throws AppError {
		
			
		if (env.get(name) == null){
			throw new AppError(name + " Does not Exist");
		}
		
		return env.get(name);
	}

	
	public Type getType(Environment env) throws AppError {
		
		return null;
	}

}
