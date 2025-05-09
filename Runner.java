public class Runner {

    public String redex = "";

    public static Expression run(Expression exp){
 
        

        if (isRedex(exp)) {
            System.out.println("isRedex(exp): "+ isRedex(exp));
            return reduce(exp);
        }
        return exp;
    }

    public static Expression reduce(Expression exp) {
        
        if (exp instanceof Application) {
            Application app = (Application) exp;

            if (app.left instanceof Function) {
                Function func = (Function) app.left;

                // function assets
                Variable redexVar = func.var;
                Expression redexExp = func.exp;

                // unknown thing on the right -- but since the left of the app is a function you must insert whateever it is
                Expression redexInsert = app.right;

                // now need to put some function that inserts something into a function
                // ex. insert(func, redexInsert) 
                // I think that maybe we do this in function bc what if its STILL a function after we reduce?
            }
            // if the thing on the left is NOT an application?? probbaly just reduce both sides
            else {

            }
        }

        // its just a variable or has already been reduced
        return exp;
    }


    public static boolean isRedex(Expression exp) {

        String ex = exp.toString();

        if (ex.length() < 6) {
            return false;
        }
        
        for (int i  = 0; i < ex.length(); i++) {
            if (ex.charAt(i) == '(' && ex.substring(i+1, i+2).equals("λ")) {
                int opens = 1;
                i += 2;
                while (opens > -1) {
                    if (ex.charAt(i) == ('(')){
                        opens ++;
                    }
                    else if (ex.charAt(i) == ')') {
                        opens --;
                        if (opens == 0) {
                            return (++i <= ex.length()-1) ? true : false;
                        }
                    }
                    else if (opens == 0) {
                        return (++i <= ex.length()-1) ? true : false;
                    }
                    i++;
                }
               
                
            }
        }
        
        return false;
    }
} 

