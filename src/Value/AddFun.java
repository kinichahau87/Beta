package Value;

import java.util.List;

import jutil.AppError;




public class AddFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply (List<Value> args, Environment env)
		throws AppError {
		Number result = new Number(0);
		for(Value arg: args) {
			if (! (arg instanceof Number)) {
				throw new AppError("Inputs to add must be numbers");
			}
			Number num = (Number)arg;
			result = result.add(num);
		}
		return result;
	}

	
	

	

	
	
}
