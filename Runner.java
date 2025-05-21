import java.util.HashSet;

public class Runner {

    public static HashSet<String> freeVars = new HashSet<>();

    public static Expression run(Expression exp){
 
        freeVars.clear();
        Expression red = reduce(exp);

        while (red != null) {
            exp = red;
            red = reduce(exp);
        }

        return exp;
    }
    

    public static Expression reduce(Expression exp) {
        
        if (exp instanceof Application) {
            Application app = (Application) exp;

            if (app.left instanceof Function) {
                Function func = (Function) app.left;

                Variable ogVar = func.var;
                Expression redexExp = func.exp;
                Expression redexInsert = app.right.copy();

                findFreeVars(redexInsert);
                
                return redexExp.insert(ogVar, redexInsert); 
            }

            else {
                Expression leftRed = reduce(app.left);
                if (leftRed != null) {
                    app.left = leftRed;
                    return app;
                }

                Expression rightRed = reduce(app.right);
                if (rightRed != null) {
                    app.right = rightRed;
                    return app;
                }
            }
        }

        else if (exp instanceof Function) {

            Function func = (Function) exp;
            Expression red = reduce(func.exp);

            if (red != null) {
                func.exp = red;
                return func;
            }
        }

        return null;
    }

    public static void findFreeVars(Expression exp) {

        if (exp instanceof Variable) {
            Variable var = (Variable) exp;
            freeVars.add(var.name);
        } 
        
        else if (exp instanceof Application) {
            Application app = (Application) exp;

            findFreeVars(app.left);
            findFreeVars(app.right);
        } 
        else if (exp instanceof Function) {
            Function func = (Function) exp;
            freeVars.add(func.var.name);

            findFreeVars(func.exp);
        }
    }
} 