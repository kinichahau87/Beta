package Value;
public class Boolean implements Value {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean value;
	
	public Boolean(boolean other){
		this.value = other;
	}//end of contructor
	
	public Boolean and (Boolean other){
		 Boolean newValue = new Boolean(true);
		 newValue.value = this.value & other.value;
		return newValue;
	}//end of and
	
	public Boolean or (Boolean other){
		 Boolean newValue = new Boolean(true);
		 newValue.value = this.value | other.value;
		return newValue;
	}//end of or
	
	public Boolean not(Boolean other){
		 Boolean newValue = new Boolean(true);
		 newValue.value = !other.value;
		return newValue;
	}
	
	 public String toString() { return "" + value; }
	
	public boolean equals(Object other) {
		   if (other == null) return false;
		   Class c = other.getClass();
		   if (!c.equals(getClass())) return false;
		   Boolean b = (Boolean)other;
		   return b.value == value;
	   }

}//end of class
