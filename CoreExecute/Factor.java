class Factor {
	Id id;
	Expr index;
	int constant;
	Expr expr;
	int option;
	int enterArray;
	void parse() {
		if (Parser.scanner.currentToken() == Core.ID) {
			option =1;
			id = new Id();
			id.parse();
			if (Parser.scanner.currentToken() == Core.LBRACE) {
				option=2;
				Parser.scanner.nextToken();
				index = new Expr();
				index.parse();
				Parser.expectedToken(Core.RBRACE);
				Parser.scanner.nextToken();
				
			}
		} else if (Parser.scanner.currentToken() == Core.CONST) {
			constant = Parser.scanner.getConst();
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.LPAREN) {
			option =3;
			Parser.scanner.nextToken();
			expr = new Expr();
			expr.parse();
			Parser.expectedToken(Core.RPAREN);
			Parser.scanner.nextToken();
		} else {
			System.out.println("ERROR: Expected ID, CONST, LPAREN, or IN, recieved " + Parser.scanner.currentToken());
			System.exit(0);
		}
	}
	
	void print() {
		if (id != null) {
			id.print();
			if (index != null) {
				System.out.print("[");
				index.print();
				System.out.print("]");
			}
		} else if (expr != null) {
			System.out.print("(");
			expr.print();
			System.out.print(")");
		} else {
			System.out.print(constant);
		}
	}

	int execute(){
		int value =0;
		if(option ==1){
			value = id.getIdValue();
		}
		else if(option ==2){
			value = id.getArrValue(index.execute());
		}else if(option ==3){
			value = ( expr.execute());
		}else{
			value =constant;
		}
		return value;
	}
}