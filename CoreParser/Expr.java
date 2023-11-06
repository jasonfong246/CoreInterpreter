import java.util.ArrayList;

public class Expr {
    String value="";
    Expr exp;
    String parse(Scanner s,ArrayList<String> integer,ArrayList<String> array){
            if(s.currentToken()==Core.LPAREN){
                value = value+"(";
                s.nextToken();
                exp = new Expr();
                value = value + exp.parse(s,integer,array);
                if(s.currentToken()==Core.RPAREN){
                    value = value + ")";
                    s.nextToken();
                }else{
                    System.out.println("Error: It should have right parenthesis at the end");
                    System.exit(1);
                } 
            }
            if(s.currentToken()==Core.CONST){
                value = value + s.getConst();
                s.nextToken();
            }
            if(s.currentToken()==Core.ID){
                if(integer.contains(s.getId()) || array.contains(s.getId())){
                   value =value + s.getId() ;
                    s.nextToken();
                }else{
                    System.out.println("Error: Variable is no declared within the scope");
                    System.exit(1);
                }
            }

            
            if(s.currentToken()==Core.LBRACE){
                value = value+"[";
                s.nextToken();
                exp = new Expr();
                value = value + exp.parse(s,integer,array);
                if(s.currentToken()==Core.RBRACE){
                    value = value+"]";
                    s.nextToken();
                }else{
                    System.out.println("Error: It should have right braces at the end");
                    System.exit(1);
                } 
            }
            if(s.currentToken()==Core.SUBTRACT){
                value = value + "-";
                s.nextToken();
                exp = new Expr();
                value = value + exp.parse(s,integer,array);  
            }
            if(s.currentToken()==Core.ADD){
                value = value + "+";
                s.nextToken();
                exp = new Expr();
                value = value + exp.parse(s,integer,array);  
            }
            if(s.currentToken()==Core.MULTIPLY){
                value = value + "*";
                s.nextToken();
                exp = new Expr();
                value = value + exp.parse(s,integer,array);  
            }

            if(value.length()>1){
                if(value.charAt(value.length()-1)=='+'|| value.charAt(value.length()-1)=='-'|| value.charAt(value.length()-1)=='*' ||value.charAt(value.length()-1)=='/'){
                    System.out.println("ERROR : " + value.charAt(value.length()-1) + " in the expression before ;");
                    System.exit(1);
                }
            }

        return value;
        //Takes the semicolon away
    }
}
