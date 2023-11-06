import java.util.ArrayList;

class Decl {
    String name="";
    String parse(Scanner s,ArrayList<String> integer,ArrayList<String> array){
        if(s.currentToken()==Core.INTEGER){
            s.nextToken();
            name = "integer " + s.getId() + ";";
            if(!integer.contains(s.getId())){
                integer.add(s.getId());
            }else{
                System.out.println("Error : Integer "+ s.getId()+" has already been declared");
                System.exit(1);
            }
            //Get the semicolon
            s.nextToken();
            //Move onto the next token after semicolon
            s.nextToken();
            return name;
        }
        if(s.currentToken()==Core.ARRAY){
            s.nextToken();
            name = "array " + s.getId() + ";";
            if(!array.contains(s.getId())){
                array.add(s.getId());
            }else{
                System.out.println("Error : Array "+ s.getId()+" has already been declared");
                System.exit(1);
            }
            //Get the semicolon
            s.nextToken();
            //Move onto the next token after semicolon
            s.nextToken();
            return name;
        }
        return name;
        //Takes the semicolon away
    }
}
