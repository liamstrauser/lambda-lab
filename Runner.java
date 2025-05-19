public class Runner {


    public static Expression run(Expression exp){
 
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

                // function assets
                Variable ogVar = func.var;
                Expression redexExp = func.exp;

                // unknown thing on the right -- but since the left of the app is a function you must insert whateever it is
                Expression redexInsert = app.right;

                // now need to put some function that inserts something into a function
                // ex. insert(func, redexInsert) 
                // I think that maybe we do this in function bc what if its STILL a function after we reduce?
                
                return func.insert(redexInsert, ogVar, redexExp); 
            }

            // if the thing on the left is NOT an function?? reduce both sides, but first do the left
            else {
                Expression leftRed = reduce(app.left);
                Expression rightRed = reduce(app.right);

                if (leftRed != null) {
                    app.left = leftRed;
                    return app;
                }
                

                if (rightRed != null) {
                    app.right = rightRed;
                    return app;
                }
            }
        }

        // still have to reduce the expression embedded within that function
        else if (exp instanceof Function) {

            Function func = (Function) exp;
            Expression red = reduce(func.exp);

            if (red != null) {
                func.exp = red;
                return func;
            }
        }

        // its just a variable or has already been reduced as much as it can
        return null;
    }

    public static Expression deepCopy(Expression exp) {
        if (exp instanceof Variable) {
            return new Variable(exp.toString());
        } 
        else if (exp instanceof Application) {
            Application app = (Application) exp;
            return new Application(deepCopy(app.left), deepCopy(app.right));
        } 
        else if (exp instanceof Function) {
            Function func = (Function) exp;
            return new Function(new Variable(func.var.toString()), deepCopy(func.exp));
        }
    return null;
}
} 