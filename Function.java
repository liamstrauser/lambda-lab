// Martina Lipczyk and Liam Strauser, ATICS Period 7

public class Function implements Expression {
    
    public Variable var;
    public Expression exp;
    



    public Function(Variable var, Expression exp) {
        this.var = var;
        this.exp = exp;
    }





    public Function copy() {
        return new Function(var.copy(), exp.copy());
    }





    public Expression insert(Variable ogVar, Expression redexInsert) {
 
        if (this.var.equals(ogVar)) {
            return this;
        } 


        else {
            Function func = this.copy();
            

            if (containsFreeVar(redexInsert, this.var.name)) {
                String newVarName = newName(this.var.name);
                
                func.var = new Variable(newVarName);
                func.exp = func.exp.insert(this.var, new Variable(newVarName));
            }

            func.exp = func.exp.insert(ogVar, redexInsert);

            return func;
        }
    }
    





    public boolean containsFreeVar(Expression expr, String name) {

        if (expr instanceof Variable) {
            return ((Variable) expr).name.equals(name);

        } 
        

        else if (expr instanceof Application) {
            Application app = (Application) expr;
            return containsFreeVar(app.left, name) || containsFreeVar(app.right, name);
        } 


        else if (expr instanceof Function) {
            Function func = (Function) expr;

            if (func.var.name.equals(name)) {
                return false;
            }
            return containsFreeVar(func.exp, name);
        }


        
        return false;
    }
    




    public String newName(String og) {
        String name = og;
        int counter = 1;
        

        while (true) {
            name = og + counter;
            if (!Runner.freeVars.contains(name)) {
                break;
            }
            counter++;
        }
        

        return name;
    }





    public String toString() {
        return "(λ" + var + "." + exp + ")";
    }
}