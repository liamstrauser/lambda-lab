
public class Function implements Expression {

    public Variable var;
    public Expression exp;
    
    public Function(Variable var, Expression exp){
        this.var = var;
        this.exp = exp;
    }


    public String toString() {
        return "(λ" + var + "." + exp + ")";
    }
}
