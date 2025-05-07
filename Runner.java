public class Runner {

    public String redex = "";

    public static Expression run(Expression exp){
 
        if (isRedex(exp)) {
            System.out.println("isRedex(exp): "+ isRedex(exp));
            return runRedex(exp);
        }
        return exp;
    }

    public static Expression runRedex(Expression exp) {
        
        String ex = exp.toString();
        String[] splitted = ex.split(" ");

        
        
        

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

