import java.util.ArrayList;

public class Cond {
    Expr exp;
    String value="";
    Cond cd;
    String parse (Scanner s,ArrayList<String> integer,ArrayList<String> array){
        if(s.currentToken()==Core.NOT){
            value = value + "not "; 
            s.nextToken();
        }
        //Expression before '='' or '<'
        exp = new Expr();            
        value = value + exp.parse(s,integer,array);
        //Condition for Equal
        if(s.currentToken()==Core.EQUAL){
            s.nextToken();
            exp = new Expr();            
            value = value+ " = " + exp.parse(s,integer,array);
            
                      
        }
        //Condition for Less 
        if(s.currentToken()==Core.LESS){
            s.nextToken();
            exp = new Expr();
            value = value+ " < " + exp.parse(s,integer,array);
        }
        if(s.currentToken()==Core.OR){
            cd= new Cond();
            value = value+ " or "+ cd.parse(s,integer,array);
        }

        if(s.currentToken()==Core.AND){
            cd= new Cond();
            value = value+" and "+cd.parse(s,integer,array);
        } 
    return value;
    }
}
