import java.util.HashMap;

class Procedure {
	String name;
	DeclSeq ds;
	StmtSeq ss;
	
	void parse() {
		Parser.expectedToken(Core.PROCEDURE);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.ID);
		name = Parser.scanner.getId();
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.IS);
		Parser.scanner.nextToken();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.EOS);
	}
	
	void print() {
		System.out.println("procedure " + name + " is");
		if (ds != null) {
			ds.print(1);
		}
		System.out.println("begin ");
		ss.print(1);
		System.out.println("end");
	}

	void execute(String file){
		//Initiliaze the memory
		Scanner scanner = new Scanner(file);
		HashMap<String, Memory.Value> memory = new HashMap<String, Memory.Value>();
		//Execute the declaration sequence
		if (ds != null) {
			ds.execute(scanner,memory);
		}
		Memory.dsFlag=false;
		ss.execute(scanner,memory);
	}
}