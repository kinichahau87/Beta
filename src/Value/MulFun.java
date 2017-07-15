package Value;

import java.util.List;

import jutil.AppError;



public class MulFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply (List<Value> args, Environment env)
		throws AppError {
		Number result = new Number(1);
		
		if (args.size() > 0 ){
			
		
			for(Value arg: args) {
				if (! (arg instanceof Number)) {
					throw new AppError("Inputs to add must be numbers");
				}
				Number num = (Number)arg;
				result = result.times(num);
				
			}//end of for
			
		}//end of if
		else{
			
			throw new AppError("There must be 2 arguments");
		}
		
		
		return result;
	}

	

}
