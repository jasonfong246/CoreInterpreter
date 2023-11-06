import java.util.HashMap;

class DeclSeq {
	Decl decl;
	DeclSeq ds;
	Memory mem;
	void parse() {
		decl = new Decl();
		decl.parse();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
	}
	
	void print(int indent) {
		decl.print(indent);
		if (ds != null) {
			ds.print(indent);
		}
	}
	void execute(Scanner scanner,HashMap<String, Memory.Value> memory){
		//Execute the declarationH
		decl.execute(scanner,memory);
		//Execute the declaration sequence
		if (ds != null) {
			ds.execute(scanner,memory);
		}
	}
}