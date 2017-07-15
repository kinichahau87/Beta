package Value;

import java.util.List;

import jutil.AppError;

public class NotFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply(List<Value> args, Environment env) throws AppError {
		Boolean result = new Boolean(true);
		
			
			for(Value arg: args) {
				if (! (arg instanceof Boolean)) {
					throw new AppError("Inputs to add must be Boolean");
				}
				Boolean num = (Boolean)arg;
				result = result.not(num);
				
			
			}
	
		return result;
	}

}
