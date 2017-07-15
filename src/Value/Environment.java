package Value;

public class Environment extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final Value value = null;
	Environment ce;
	Environment de;
	
	public Environment(){
		
		ce = de = null;
		
		put("+",  new AddFun());
		put("*", new MulFun());
		put("-", new SubFun());
		put("and", new AndFun());
		put("or", new OrFun());
		put("not", new NotFun());
		put("<", new lessThanFun());
		put(">", new greatThanFun());
		put("=", new equalsFun());
		put("/", new DivFun());
		
		
		
		
		
		
	}
	
	
	public Environment getCe(){
		return ce;
	}
	
	public void setDe(Environment other){
		
		de = other;
	}

}
