public class Runner {

    public static Expression run(Expression exp){
        
        if(exp instanceof Variable){
            return exp;
            
        }
        
        else if(exp instanceof Application){
            return exp;
        }

        else if(exp instanceof Function) {
            return exp;
        }

        return exp;
    }
}