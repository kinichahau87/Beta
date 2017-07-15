package Value;

import java.util.List;

import jutil.AppError;

public class OrFun implements Abstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Value apply(List<Value> args, Environment env) throws AppError {
		Boolean result = new Boolean(false);
		try{
			//if (args instanceof Boolean)
				//result = (Boolean)args.get(0);
			
			for(int i = 0; i < args.size(); i++) {
				if (! (args.get(i) instanceof Boolean)) {
					throw new AppError("Inputs to add must be Boolean");
				}
				Boolean num = (Boolean)args.get(i);
				result = result.or(num);
			
			}//end of for
		}
		catch (Exception e){
			System.out.println("Arguments in function are empty");
		}
	
		
		
		return result;
	}

}
