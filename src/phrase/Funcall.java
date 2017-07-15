package phrase;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Value.Abstract;
import Value.Environment;
import Value.Value;



import jutil.AppError;
import jutil.Lex;

public class Funcall extends Expression{
	
	private String funName;

	private List<Phrase> operands = new ArrayList<Phrase>();
 
	public static boolean isNext(List<String> tokens) {
		if (tokens == null || tokens.isEmpty()) {
			return false;
		}
			String token = tokens.get(0);
			try {
				Lex.scan("\\(", token);
			} catch(Exception e) {
				
				return false;
			}
			return true;
	}
public static Phrase parse(List<String> tokens) throws AppError{
	Funcall result = new Funcall();
	if ( tokens.size() > 0){
		tokens.remove(0);
		result.funName = tokens.remove(0);
	}
	else{
		
		throw new AppError("phrase is invalid:" + tokens.toString());
	}
	
		
			
		
		try{
			while (!tokens.get(0).equals(")")){
				result.operands.add(Expression.parse(tokens));
			
			}//end of while
			
		}//end of if-else
		catch(Exception e){
			throw new AppError("Syntax Error");
		}
	
		
	
	tokens.remove(0);
	
	return result;

}

	public Value eval(Environment env) throws AppError{
		Abstract fun = null;
		if(env.get(funName) instanceof Abstract)
			 fun = (Abstract)env.get(funName);
		
		List<Value> args = new LinkedList<Value>();
		for (Phrase arg: operands){
			args.add(arg.eval(env));
		}
		//recursively convert operands to args
		return fun.apply(args,env);
	}
	
	public Type getType(Environment env) throws AppError {
		
		return null;
	}
}