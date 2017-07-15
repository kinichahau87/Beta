
 package jutil;
/**
 * Base class for all application-specific errors.
 * @author Pearce
 */
public class AppError extends Exception {
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AppError(String g) { super(g); }
	public AppError() { this("unknown"); 
	
	}
}//end of class
