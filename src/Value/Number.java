package Value;
public class Number implements Value, Comparable {
   private static final long serialVersionUID = 1L;
   private double value;

   public Number(double value) {
	   this.value = value;
   }
   // to be completed:
   public Number add(Number other) {
	   Number newValue = new Number(1);
	   newValue.value  = this.value + other.value;
	   return newValue;
   }
   public Number times(Number other) {
	   Number newValue = new Number(1);
	   newValue.value = this.value * other.value;
	   return newValue;
   }
   public Number sub(Number other) {
	   Number newValue = new Number(1);
	   newValue.value = this.value - other.value;
	   return newValue;
   }
   public Number div(Number other) {
	   Number newValue = new Number(1);
	   newValue.value = this.value / other.value;
	   return newValue;
   }

   public String toString() { return "" + value; }

   public boolean equals(Object other) {
	   if (other == null) return false;
	   Class c = other.getClass();
	   if (!c.equals(getClass())) return false;
	   Number b = (Number)other;
	   return b.value == value;
   }

   public int hashCode() { return toString().hashCode(); }

   public int compareTo(Object arg0) {
	Number arg = (Number)arg0;
	return (int)(value - arg.value);
	}
}
