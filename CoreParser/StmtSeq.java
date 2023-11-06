import java.util.ArrayList;

class StmtSeq {
    String value = "";
    Scanner s;
    Stmt st;
    StmtSeq ss;
    public StmtSeq(Scanner scanner){
        s=scanner;
    }
    String parse(Scanner s,ArrayList<String> integer,ArrayList<String> array) {
        ArrayList<String> in = new ArrayList<String>();
        ArrayList<String> ar = new ArrayList<String>();
        in.addAll(integer);
        ar.addAll(array);
        st = new Stmt();
        value =value + st.parse(s,in,ar);
        if (s.currentToken() == Core.ID || s.currentToken() == Core.IF || s.currentToken() == Core.WHILE || s.currentToken() == Core.OUT ||
            s.currentToken() == Core.IN || s.currentToken() == Core.INTEGER || s.currentToken() == Core.ARRAY) {
            ss = new StmtSeq(s);
            value = value+ "\n" + ss.parse(s,in,ar);
        }
        return value;
    }

    void print(){
       System.out.println(value);
    }
}