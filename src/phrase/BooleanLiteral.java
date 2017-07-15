package phrase;

import java.lang.reflect.Type;
import java.util.List;

import Value.Environment;
import Value.Value;
import Value.Boolean;

import jutil.AppError;
import jutil.Lex;


public class BooleanLiteral extends Literal {
    private Boolean value;
    public static boolean isNext(List<String> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            return false;
        }
        String token = tokens.get(0);
        try {
            Lex.scan("true | false", token);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    public static Phrase parse(List<String> tokens) throws AppError {
        if (!isNext(tokens)) {
            throw new AppError("Next token not a boolean");
        }
        BooleanLiteral result = new BooleanLiteral();
        String token = tokens.remove(0);
        boolean val= new java.lang.Boolean(token);
        
        result.value = new Boolean(val);
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

