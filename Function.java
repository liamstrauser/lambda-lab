
public class Function implements Expression {

    public Variable var;
    public Expression exp;
    
    public Function(Variable var, Expression exp){
        this.var = var;
        this.exp = exp;
    }

    public Expression insert(Expression redexInsert, Variable ogVar, Expression redexExp){

        if (redexExp instanceof Variable) {
            Variable var = (Variable) redexExp;

            if (var.toString().equals(ogVar.toString())) {
                return Runner.deepCopy(redexInsert);
            }
            
            else {
                return var;
            }
        }

        else if (redexExp instanceof Application){
            Application app = (Application) redexExp;
            Expression left = insert(redexInsert, ogVar, app.left);
            Expression right = insert(redexInsert, ogVar, app.right);

            return new Application (left, right);
        }
        else if (redexExp instanceof Function) {
            Function func = (Function) redexExp;

            if (func.var.toString().equals(ogVar.toString())) {
                return func;
            }

            Expression newExp = insert(redexInsert, ogVar, func.exp);
            return new Function(func.var, newExp);
        }
        
        return redexExp;
    }

    public String toString() {
        return "(λ" + var + "." + exp + ")";
    }
}