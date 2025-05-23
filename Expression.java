// Martina Lipczyk and Liam Strauser, ATICS Period 7

public interface Expression {

    public Expression copy();

    public Expression insert(Variable var, Expression exp);

}