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

            // if the thing on the left is NOT an function?? probbaly just reduce both sides
            else {
                
            }
        }

        // its just a variable or has already been reduced
        return null;
    }


    // public static boolean isRedex(Expression exp) {

    //     String ex = exp.toString();

    //     if (ex.length() < 6) {
    //         return false;
    //     }
        
    //     for (int i  = 0; i < ex.length(); i++) {
    //         if (ex.charAt(i) == '(' && ex.substring(i+1, i+2).equals("λ")) {
    //             int opens = 1;
    //             i += 2;
    //             while (opens > -1) {
    //                 if (ex.charAt(i) == ('(')){
    //                     opens ++;
    //                 }
    //                 else if (ex.charAt(i) == ')') {
    //                     opens --;
    //                     if (opens == 0) {
    //                         return (++i <= ex.length()-1) ? true : false;
    //                     }
    //                 }
    //                 else if (opens == 0) {
    //                     return (++i <= ex.length()-1) ? true : false;
    //                 }
    //                 i++;
    //             }
               
                
    //         }
    //     }
        
    //     return false;
    // }
} 

