class DeclSeq {
	Decl decl;
	DeclSeq ds;
	Function f;
	StmtSeq ss;
	int enter;
	void parse() {
		if(Parser.scanner.currentToken() == Core.PROCEDURE) {
			enter=0;
			f = new Function();
			f.parse();
			if (Parser.scanner.currentToken() != Core.BEGIN) {
				ds = new DeclSeq();
				ds.parse();
			}
		}else{
			enter=1;
			decl = new Decl();
			decl.parse();
			if (Parser.scanner.currentToken() != Core.BEGIN) {
				ds = new DeclSeq();
				ds.parse();
			}
		}
	}
	
	void print(int indent) {
		decl.print(indent);
		if (ds != null) {
			ds.print(indent);
		}
	}
	
	void execute() {
		if(enter==0){
			f.execute();
			if (ds != null) {
				ds.execute();
			}
		}else{
			decl.execute();
			if (ds != null) {
				ds.execute();
			}
		}
	}
}