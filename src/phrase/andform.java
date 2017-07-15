package phrase;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import Value.Environment;
import Value.Value;

import jutil.AppError;
import jutil.Lex;

public class andform extends SpecialForm {
	
private List<Expression> operands = new ArrayList<Expression>();

	
	public static boolean isNext(List<String> tokens) throws AppError{
		
		if (tokens.size() == 0 || tokens == null)
			return false;
		
		try{
			Lex.scan("\\(and", tokens.get(0) + tokens.get(1));
		}
		catch (Exception e){
			return false;
		}
		
		return true;
	}
	
	public static Phrase parse (List<String> tokens) throws AppError{
		andform result = new andform();
		
		//remove (
		tokens.remove(0);
		
		//remove or
		tokens.remove(0);
		
		while (!tokens.get(0).equals(")"))
			result.operands.add((Expression)Expression.parse(tokens));
		
		
		
								
		return result;		
		
	}
	
	public Value eval(Environment env) throws AppError{
		//recursively eval exp
		
		Value value = null;
		
		for (Expression exp: operands){
			 value = exp.eval(env);
			if (value.toString().equals("false"))
				return value;
			else if (value.toString().equals("true")){
				//continue with eval
			}
			else{
				throw new AppError("Arguments must of type boolean");
			}
			
				
		}//end of for
		
		
		return value;
	}
	
	public Type getType(Environment env){
		return null;
	}

}//end of class
