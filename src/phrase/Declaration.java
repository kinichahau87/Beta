package phrase;

import java.lang.reflect.Type;

import java.util.List;

import jutil.AppError;
import jutil.Lex;
import Value.Acknowledgement;
import Value.Environment;
import Value.Value;

public class Declaration extends Phrase {
	
	private Expression body;
	private Symbol symbol;
	
	
	public static boolean isNext(List<String> tokens) throws AppError{
		if (tokens.size() == 0 || tokens == null)
			return false;
		try{
			Lex.scan("\\[",tokens.get(0));
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static Phrase parse(List<String> tokens) throws AppError{
		
		Declaration result = new Declaration();
		
		if (tokens.size() == 0 || tokens == null)
			throw new AppError("Phrase is empty");
		
		//remove [
		tokens.remove(0);
		
		if (tokens.get(0).equals("define"))
			tokens.remove(0);
		else
			throw new AppError("missing the define statement");
		
		result.symbol = (Symbol)Symbol.parse(tokens);
		result.body = (Expression)Expression.parse(tokens);
		
		
		
		//remove "]"
		tokens.remove(tokens.size() - 1);
	
												
		return result;
		
	}//end of staic

	
	public Value eval(Environment env) throws AppError {

				
		env.put(symbol.toString(), body.eval(env));
		
				
		return Acknowledgement.DONE;
			
	}

	
	public Type getType(Environment env) throws AppError {


		return null;
	}

}
