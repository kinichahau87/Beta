package Value;

import java.util.List;

import jutil.AppError;

public class AndFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply(List<Value> args, Environment env) throws AppError {
		Boolean result = new Boolean(true);
		
		try{
			result = (Boolean)args.get(0);
		
			
			Boolean num = (Boolean)args.get(1);
			result = result.and(num);
			
		}//end of try
		catch (Exception e){
			System.out.println("Must have two arguments");
		}
		
		return result;
	}

}
