import java.util.HashMap;

class Assign implements Stmt {
	// type is
	// 0 if id := <expr> assignment
	// 1 if id[<expr>] := <expr> assignment
	// 2 if "new" assignment
	// 3 if "array" assignment
	int type;
	// assignTo is the id on the LHS of assignment
	Id assignTo;
	// assignFrom is the id on RHS of "array" assignment
	Id assignFrom;
	// Two possible expressions in an assignment
	Expr index;
	Expr expr;
	Memory.Value val,temp;
 @Override
	public void parse() {
		type = 0;
		assignTo = new Id();
		assignTo.parse();
		if (Parser.scanner.currentToken() == Core.LBRACE) {
			type = 1;
			Parser.scanner.nextToken();
			index = new Expr();
			index.parse();
			Parser.expectedToken(Core.RBRACE);
			Parser.scanner.nextToken();
		}
		Parser.expectedToken(Core.ASSIGN);
		Parser.scanner.nextToken();
		if (Parser.scanner.currentToken() == Core.NEW) {
			type = 2;
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.INTEGER);
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.LBRACE);
			Parser.scanner.nextToken();
			index = new Expr();
			index.parse();
			Parser.expectedToken(Core.RBRACE);
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.ARRAY) {
			type = 3;
			Parser.scanner.nextToken();
			assignFrom = new Id();
			assignFrom.parse();
		} else {
			expr = new Expr();
			expr.parse();
		}
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
 @Override
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		assignTo.print();
		if (type == 1) {
			System.out.print("[");
			index.print();
			System.out.print("]");
		}
		System.out.print(":=");
		if (type == 0 || type == 1) {
			expr.print();
		} else if (type == 2) {
			System.out.print("new integer[");
			index.print();
			System.out.print("]");
		} else if (type == 3) {
			System.out.print("array ");
			assignFrom.print();
		}
		System.out.println(";");
	}
	
	 @Override
	public void execute(Scanner scanner,HashMap<String, Memory.Value> memory) {
		if(Memory.global.containsKey(assignTo.identifier))
			val = Memory.global.get(assignTo.identifier);
		else if(Memory.local.peek().containsKey(assignTo.identifier))
			val = Memory.local.peek().get(assignTo.identifier);
		if(type == 0 ){
			if(val.type==Core.ARRAY){
				if(val.arrayValue.length<1){
					System.out.println("ERROR: Array index not initiliazed");
					System.exit(0);
				}	
			}
			val.intValue = expr.execute();
		}else if(type == 1){
			int indexVal = index.execute();
			if(indexVal>=val.arrayValue.length){
				System.out.println("Error: Array index out of bounds");
				System.exit(0);
			}
			val.arrayValue[indexVal] = expr.execute();
		}
		else if(type == 2){
			val.arrayValue = new int[index.execute()];
		}else if(type ==3 ){
			Memory.Value val1 = new Memory.Value();
			if(Memory.global.containsKey(assignFrom.identifier)){
				val1 = Memory.global.get(assignFrom.identifier);
			}else if(Memory.local.peek().containsKey(assignFrom.identifier)){
				val1= Memory.local.peek().get(assignFrom.identifier);
			}
			val = val1; 	
			if(Memory.global.containsKey(assignFrom.identifier)){ 			
				Memory.global.put(assignFrom.identifier, val);
			}
			else if(Memory.local.peek().containsKey(assignFrom.identifier)){
				
				Memory.local.peek().put(assignFrom.identifier, val);
			}
		}
		if(Memory.global.containsKey(assignTo.identifier)){
			Memory.global.put(assignTo.identifier, val);
		}else if(Memory.local.peek().containsKey(assignTo.identifier)){
			Memory.local.peek().put(assignTo.identifier, val);
		}
	}
}