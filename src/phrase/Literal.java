package phrase;
import java.util.List;

import jutil.AppError;


public abstract class Literal extends Expression {
	
	public static BooleanLiteral boolLit;
	public static boolean isNext(List<String> tokens) {
		   return BooleanLiteral.isNext(tokens) || 
		          NumericalLiteral.isNext(tokens)|| 
		          Funcall.isNext(tokens);
		}


	
	public static Phrase parse(List<String> tokens) throws AppError{
		
		if (BooleanLiteral.isNext(tokens)) return BooleanLiteral.parse(tokens);
		if (NumericalLiteral.isNext(tokens)) return NumericalLiteral.parse(tokens);
		if (Funcall.isNext(tokens)) return Funcall.parse(tokens);
		throw new AppError("Unrecognized phrase in Phrase.parse//Error in Literal");
			
		
	}//end of phrase

	
	
		

}
