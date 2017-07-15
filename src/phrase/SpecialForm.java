package phrase;

import java.lang.reflect.Type;

import java.util.List;

import jutil.AppError;

import Value.Environment;
import Value.Value;

public abstract class SpecialForm extends Expression {
	
	
	
	public static boolean isNext(List<String> tokens) throws AppError{
		
		return ifForm.isNext(tokens) ||
				orForm.isNext(tokens) ||
				andform.isNext(tokens);
		
		
	}//end of isNext
	
	public static Phrase parse(List<String> tokens) throws AppError{
		if (andform.isNext(tokens)) return andform.parse(tokens);
		if (ifForm.isNext(tokens)) return ifForm.parse(tokens);
		if(orForm.isNext(tokens)) return orForm.parse(tokens);
		throw new AppError("Error with paring SpecialForm");								
				
	}

	
	public abstract Value eval(Environment env) throws AppError;

	
	public abstract Type getType(Environment env) throws AppError; 

}
