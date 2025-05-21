
public class Application implements Expression {

    public Expression left;
    public Expression right;

    public Application(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public Application copy() {
        return new Application(left.copy(), right.copy());
    }

    public Expression insert(Variable var, Expression exp) {

        Expression newLeft = left.insert(var, exp);
        Expression newRight = right.insert(var, exp);
        
        return new Application(newLeft, newRight);
    }

    public boolean equals(Expression o) {
        return (o instanceof Application x && (this.left.equals(x.left) && this.right.equals(x.right))) ? true : false;
    }

    public String toString() {
        return "(" + left + " " + right + ")"; 
    }

}