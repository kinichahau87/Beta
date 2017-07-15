package Value;

import java.util.List;

import jutil.AppError;

public class equalsFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply(List<Value> args, Environment env) throws AppError {
		
		Value value = null;
		Number num = new Number(0);
		Boolean bol = new Boolean(false);
		
		if (args.get(0) instanceof Number) 
			num = (Number)args.get(0);
		
		if (args.get(0) instanceof Boolean) 
			 bol = (Boolean)args.get(0);
			
		
		
		if (args.get(1) instanceof Number) {
			Number arg = (Number)args.get(1);
			Boolean result = new Boolean(num.equals(arg));
			return result;
		}
			
		if (args.get(1) instanceof Boolean) {
			Boolean arg = (Boolean)args.get(1);
			Boolean result = new Boolean(bol.equals(arg));
			return result;
		}
			
	
		
		return value;		
		
		
	}

}
