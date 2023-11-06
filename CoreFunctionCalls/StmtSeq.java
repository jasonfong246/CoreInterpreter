
class StmtSeq {
	Stmt stmt;
	StmtSeq ss;

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
		} else if(Parser.scanner.currentToken() == Core.BEGIN){
			stmt = new Call();
		} else {
			System.out.println("There's no body for the statement");
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
	
	void execute() {
		stmt.execute();
		if (ss != null) {
			ss.execute();
		}
	}

	void functionStmtSeq(){
		
	}
}