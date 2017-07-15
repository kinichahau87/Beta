package Value;

import java.util.List;

import jutil.AppError;

public class DivFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply(List<Value> args, Environment env) throws AppError {
		
		Number result = new Number(1);
		result = (Number) args.get(0);
		
		
		for(int i = 1; i < args.size(); i++) {
			if (! (args.get(i) instanceof Number)) {
				throw new AppError("Inputs to add must be numbers");
			}
			Number num = (Number)args.get(i);
			if (num.toString().equals("0.0"))
				throw new AppError(" cannot divide by 0");
			else
				result = result.div(num);
			
		}//end of for
		
		return result;
		
	}//end of apply

}
