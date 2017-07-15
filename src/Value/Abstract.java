package Value;


import java.util.List;

import jutil.AppError;

public interface Abstract extends Value {
	
	public Value apply(List<Value> args, Environment env) throws AppError;

}
