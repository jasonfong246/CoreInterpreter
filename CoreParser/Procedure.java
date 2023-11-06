import java.util.ArrayList;

public class Procedure {
    Scanner s;
    String name;
    DeclSeq ds;
    StmtSeq ss;
    ArrayList<String> integer = new ArrayList<String>();
    ArrayList<String> array = new ArrayList<String>();
    public Procedure(Scanner scanner){
        s=scanner;
    }

    void parse(Scanner s) {
    if (s.currentToken() != Core.PROCEDURE) {
        System.out.println("Error: ---");
        System.exit(1);
    }
    //Goes to ID
    s.nextToken();

    if (s.currentToken() != Core.ID) {
        System.out.println("Error: ---");
        System.exit(1);
    }
    //Gets ID
    name = s.getId();
    //Goes to IS
    s.nextToken();

    if (s.currentToken() != Core.IS) {
        System.out.println("Error: ---");
        System.exit(1);
    }
    //GOES TO DeclSeq OR Begin
    s.nextToken();
    //If is DeclSeq, it will go here
    if (s.currentToken() != Core.BEGIN) {
        ds = new DeclSeq(s);
        ds.parse(s,integer,array);
    }
    if (s.currentToken() != Core.BEGIN) {
        System.out.println("Error: It should have began with BEGIN");
        System.exit(1);
    }
    //If it is not Begin, It will be Statement.
    s.nextToken();
    ss = new StmtSeq(s);
    ss.parse(s,integer,array);
    //Goes to END
    if (s.currentToken() != Core.END) {
        System.out.println("Error: ---");
        System.exit(1);
    }
    s.nextToken();
    if (s.currentToken() == Core.END) {
        System.out.println("Error: Expected output should be EoS, but it has another extra \"end\"");
        System.exit(1);
    }
    //Goes to EoS
    if (s.currentToken() != Core.EOS) {
        System.out.println("Error: ---");
        System.exit(1);
    }
    s.nextToken();
    }
    
    void print() {
        System.out.println("procedure " + name + " is ");
        if (ds != null) {
            ds.print();
        }
            System.out.println("begin");
            ss.print();
            System.out.println("end");
    }

}
