import java.util.HashMap;
class Output implements Stmt {
	Expr expr;
	
 @Override
	public void parse() {
		Parser.expectedToken(Core.OUT);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		expr = new Expr();
		expr.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
 @Override
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("out(");
		expr.print();
		System.out.println(");");
	}

 @Override
	public void execute(Scanner scanner,HashMap<String, Memory.Value> memory) {
		System.out.println(expr.execute());
		
	}
}