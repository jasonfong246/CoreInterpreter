import java.util.ArrayList;

public class Stmt {
    String valueCheck ="";
    String value="\t";
    Stmt st;
    StmtSeq ss;
    Expr exp;
    Decl d;
    DeclSeq ds;
    Cond cd;
    String parse(Scanner s,ArrayList<String> integer,ArrayList<String> array){

        //For current token when is ID(assign)
        if(s.currentToken()==Core.ID){
            value = value+ s.getId();
            valueCheck = s.getId();
            if(integer.contains(valueCheck) || array.contains(valueCheck)){
                //Goes to :=
                s.nextToken();
                if(s.currentToken()==Core.ASSIGN){
                    value = value+  ":=";
                    s.nextToken();
                }
                if(s.currentToken()==Core.ARRAY){        
                        
                    value = value + " array ";
                    s.nextToken(); 
                    value = value + " " +s.getId();
                    s.nextToken();
                    return value;
                }
                //Goes to constant
                if(s.currentToken()==Core.NEW){
                    if(!array.contains(valueCheck)){
                        System.out.println("ERROR: ARRAY DOES NOT EXIST OR IT SHOULD BE DECLARED AS AN ARRAY" );
                        System.exit(1);
                    }
                    value = value + "new ";
                    s.nextToken();
                }
                if(s.currentToken()==Core.INTEGER){
                    value = value + "integer";
                    s.nextToken();
                }
                if(s.currentToken()==Core.LBRACE){
                    if(!array.contains(valueCheck)){
                        System.out.println("ERROR: ARRAY DOES NOT EXIST OR IT SHOULD BE DECLARED AS AN ARRAY" );
                        System.exit(1);
                    }
                    value = value + "[";
                    s.nextToken();
                }
                exp = new Expr();
                value = value + exp.parse(s,integer,array);
                if(s.currentToken()==Core.RBRACE){
                    value = value + "]";
                    s.nextToken();
                }
                if(s.currentToken()==Core.ASSIGN){
                    value = value + ":=";
                    s.nextToken();
                    exp = new Expr();
                    value = value + exp.parse(s,integer,array);
                }
                if(s.currentToken()==Core.EQUAL){
                    System.out.println("Error: Expected token should be \":=\", but it is "+ s.currentToken() );
                    System.exit(1);
                }
                if(s.currentToken()!=Core.SEMICOLON){
                    System.out.println("Error: Expected token should be \"semicolon\", but it is "+ s.currentToken() );
                    System.exit(1);
                }
                value = value + ";";
                s.nextToken();
            }else{
                System.out.println("ERROR: VARIABLE NOT DECLARED");
                System.exit(1);
            }          
            //Move to semicolon
            return value;
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        if(s.currentToken()==Core.INTEGER){
            d = new Decl();
            value = value + d.parse(s,integer,array)+"\n\t";
            if (s.currentToken() == Core.INTEGER || s.currentToken() == Core.ARRAY) {
                d = new Decl();
                value = value + d.parse(s,integer,array)+"\n";
            }
            return value;
        }

        if(s.currentToken()==Core.ARRAY){
            d = new Decl();
            value = value + d.parse(s,integer,array)+"\n\t";
            if (s.currentToken() == Core.INTEGER || s.currentToken() == Core.ARRAY) {
                d = new Decl();
                value = value + d.parse(s,integer,array)+"\n";
            }
            return value;
        }


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //For current token when is IF
        if(s.currentToken()==Core.IF){
            value = value+ "if ";
            //Goes to Condition
            s.nextToken();
            cd = new Cond();
            value = value + cd.parse(s,integer,array);
            if(s.currentToken()!=Core.THEN){
                System.out.println("Error: Expected output should be \"then\". but it is " + s.currentToken());
                System.exit(1);
            }
            s.nextToken();
            value = value + " then";

            //Statement Seq
            ss = new StmtSeq(s);
            value = value+ "\n\t" + ss.parse(s,integer,array)+"\n";

            //Check if current token is "ELSE"
            if(s.currentToken()==Core.ELSE){
                s.nextToken();
                value = value+ "\telse\n\t";
                ss = new StmtSeq(s);
                value = value + ss.parse(s,integer,array)+"\n";
            }
            value = value+ "\tend";
            //Move to the next token after "END"
            s.nextToken();
            return value;
            
            
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //CONDITION FOR WHILE
        if(s.currentToken()==Core.WHILE){
            value = value+ "while ";
            //Goes to Condition
            s.nextToken();
            cd = new Cond();
            value = value + cd.parse(s,integer,array);
            if(s.currentToken()!=Core.DO){
                System.out.println("Error: Expected output should be \"do\". but it is " + s.currentToken());
                System.exit(1);
            }
             s.nextToken();
            value = value + " do";
            //Statement Seq

            ss = new StmtSeq(s);
            value = value+ "\n\t" + ss.parse(s,integer,array)+"\n";
            value = value+ "\tend";
            if(s.currentToken()!=Core.END){
                System.out.println("Error: token should be \"end\" instead, but it is "+s.currentToken());
                System.exit(1);
            }
            //Move to the next token after "END"
            s.nextToken();
            return value;
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        if(s.currentToken()==Core.OUT){
            value =value+ "out(";
            //move to Right parenthesis
            s.nextToken();
            //move to ID
            s.nextToken();
            exp = new Expr();
            value = value +exp.parse(s,integer,array); 
            value = value + ");";
            s.nextToken();           
            s.nextToken();        
            return value;
        }

        if(s.currentToken()==Core.IN){
            value =value+ "in(";
            //move to Right parenthesis
            s.nextToken();
            //move to ID
            s.nextToken();
            value = value +s.getId()+");";
            s.nextToken();            
            s.nextToken();
            s.nextToken();
            return value;
        }

        //Take away semicolon
        return value;
    }

}
