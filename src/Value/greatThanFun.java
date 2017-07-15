package Value;

import java.util.List;

import jutil.AppError;

public class greatThanFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply(List<Value> args, Environment env) throws AppError {
		
		Number result = new Number(0);
		
		try{
				if (args.get(0) instanceof Number)
				result = (Number)args.get(0);
		
			
				Number num = (Number)args.get(1);
				result = result.sub(num);
			
		}
		catch (Exception e){
			System.out.println("There must be two arguments in function");
		}
		
		if(java.lang.Double.parseDouble(result.toString()) > 0)
			return new Boolean(true);
		else
			return new Boolean(false);
		
	}

}
