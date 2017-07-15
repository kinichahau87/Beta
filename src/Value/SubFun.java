package Value;

import java.util.List;

import jutil.AppError;

public class SubFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply(List<Value> args, Environment env) throws AppError {
		Number result = new Number(0);
		try{
			result = (Number)args.get(0);
			for(int i = 1; i < args.size(); i++) {
				if (! (args.get(i) instanceof Number)) {
					throw new AppError("Inputs to add must be numbers");
				}
				Number num = (Number)args.get(i);
				result = result.sub(num);
			}//end of for
		}
		catch( Exception e){
			System.out.println("Must have two arguments");
		}
			
		
		return result;
	}

}
