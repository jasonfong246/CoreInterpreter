import java.util.HashMap;

class StmtSeq {
	Stmt stmt;
	StmtSeq ss;
	Memory mem;
	void parse() {
		if (Parser.scanner.currentToken() == Core.ID) {
			stmt = new Assign();
		} else if (Parser.scanner.currentToken() == Core.OUT) {
			stmt = new Output();
		}  else if (Parser.scanner.currentToken() == Core.IN) {
			stmt = new Input();
		}  else if (Parser.scanner.currentToken() == Core.IF) {
			stmt = new If();
		} else if (Parser.scanner.currentToken() == Core.WHILE) {
			stmt = new Loop();
		}  else if (Parser.scanner.currentToken() == Core.INTEGER || Parser.scanner.currentToken() == Core.ARRAY) {
			stmt = new Decl();
		} else {
			System.out.println("ERROR: Bad start to statement: " + Parser.scanner.currentToken());
			System.exit(0);
		}
		stmt.parse();
		if (Parser.scanner.currentToken() != Core.END && Parser.scanner.currentToken() != Core.ELSE) {
			ss = new StmtSeq();
			ss.parse();
		}
	}
			
	void print(int indent) {
		stmt.print(indent);
		if (ss != null) {
			ss.print(indent);
		}
	}
	void execute(Scanner scanner, HashMap<String, Memory.Value> memory){
		Memory.local.push(memory);
		stmt.execute(scanner,memory);
		Memory.local.pop();
		if (ss != null) {
			ss.execute(scanner, memory);
		}
	}
}