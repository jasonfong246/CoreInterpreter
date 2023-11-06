import java.util.ArrayList;

class DeclSeq {
    String value= "\t";
    Scanner s;
    Decl d;
    DeclSeq ds;

    public DeclSeq (Scanner scanner){
        s=scanner;
    }
    String parse(Scanner s,ArrayList<String> integer,ArrayList<String> array) {
        d = new Decl();
        value = value + d.parse(s,integer,array);
        if (s.currentToken() == Core.INTEGER || s.currentToken() == Core.ARRAY) {
            ds = new DeclSeq(s);
            value = value + "\n"+ds.parse(s,integer,array); 
        }
        return value;
    }

    void print(){
        System.out.println(value);
    }
}