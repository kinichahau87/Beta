package phrase;
import java.lang.reflect.Type;
import java.util.List;

import Value.Environment;
import Value.Value;
import Value.Number;

import jutil.AppError;
import jutil.Lex;

public class NumericalLiteral extends Literal {
	private Number value;
	public static boolean isNext(List<String> tokens) {
		if (tokens == null || tokens.isEmpty()) {
			return false;
		}
			String token = tokens.get(0);
			try {
				Lex.scan("\\d*\\.?\\d+", token);
			} catch(Exception e) {
				return false;
			}
			return true;
	}
	public static Phrase parse(List<String> tokens) {
		if (!isNext(tokens)) {
			System.out.print("Error while parsing");
		}
		NumericalLiteral result = new NumericalLiteral();
		String token = tokens.remove(0);
		double valInt = 0;
		
		
		 valInt  = new java.lang.Double(token);
		 result.value = new Number(valInt);
				
				
		return result;
	}
	
	


	
	
	public Value eval(Environment env) throws AppError {
		
		return value;
	}
	
	public Type getType(Environment env) throws AppError {
		// TODO Auto-generated method stub
		return null;
	}
}
