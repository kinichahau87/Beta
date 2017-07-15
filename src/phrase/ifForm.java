package phrase;

import java.lang.reflect.Type;

import java.util.List;

import Value.Environment;
import Value.Value;

import jutil.AppError;
import jutil.Lex;

public class ifForm extends SpecialForm {
	
	private Expression condition;
	private Expression consequent;
	private Expression alternative;
	
	
	public static boolean isNext(List<String> tokens) throws AppError{
		
		if (tokens.size() == 0 || tokens == null)
			return false;
		
		try{
			Lex.scan("\\(if",tokens.get(0) + tokens.get(1));
		}
		catch(Exception e){
			return false;
		}
		
		return true;
		
	}
	
	public static Phrase parse(List<String> tokens) throws AppError{
		
		ifForm result = new ifForm();
		
		//remove (
		tokens.remove(0);
		
		//remove if
		tokens.remove(0);
		
		
		result.condition = (Expression)Expression.parse(tokens);
		result.consequent = (Expression)Expression.parse(tokens);
		result.alternative = (Expression)Expression.parse(tokens);
								
		return result;		
	}

	
	public Value eval(Environment env) throws AppError {
		
		Value test = condition.eval(env);
		if (test.toString().equals("true"))
			return consequent.eval(env);
		else
			return alternative.eval(env);
		
								
	}//end of eval

	
	public Type getType(Environment env) throws AppError {
		
		return null;
	}

}//end of class ifForm
