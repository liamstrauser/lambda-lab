
public interface Expression {

    public Expression copy();

    public Expression insert(Variable var, Expression exp);

}