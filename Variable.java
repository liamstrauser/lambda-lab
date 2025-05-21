
public class Variable implements Expression {
	public String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	public Variable copy() {
		return new Variable(name);
	}

	public Expression insert(Variable var, Expression exp) {
		if (this.equals(var)) {
			return exp.copy();
		}
		return this;
	}

	public boolean equals(Expression o) {
		return (o instanceof Variable x && this.name.equals(x.name)) ? true : false;
	}
}