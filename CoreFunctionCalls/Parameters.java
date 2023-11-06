import java.util.ArrayList;

class Parameters {
    Id id;
    Parameters p;
    ArrayList<String> param = new ArrayList<>();

    void parse(){
        id = new Id();
        id.parse();
        if(Parser.scanner.currentToken()==Core.COMMA){
            Parser.scanner.nextToken();
            p = new Parameters();
            p.parse();
        }
    }

    void print(int indent){

    }
    ArrayList<String> execute(){
        param.add(id.getId());
        if(p!=null){
            param.addAll(p.execute());        
        }
        return param;
    }
}
