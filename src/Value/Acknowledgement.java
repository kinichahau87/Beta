package Value;

public class Acknowledgement implements Value {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	/**
	 * Users should not be allowed to create instances of this class as it
	 * might lead to inconsistent and confusing acknowledgements. Therefore
	 * the constructor is private.
	 *
	 * @param msg
	 */
	private Acknowledgement(String msg) {
		message = msg;
	}
	public String toString() {
		return message;
	}
	/**
	 * use for acknowledging successful executions of declarations
	 */
	public static Acknowledgement DONE = new Acknowledgement("done");
	/**
	 * use for acknowledging successful executions of commands
	 */
	public static Acknowledgement OK = new Acknowledgement("ok");

}
