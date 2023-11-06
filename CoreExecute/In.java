import java.util.HashMap;
class Input implements Stmt {
	Id id;
	Memory mem;
 @Override
	public void parse() {
		Parser.expectedToken(Core.IN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
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
		System.out.print("in(");
		id.print();
		System.out.println(");");
	}

 @Override
	public void execute(Scanner scanner,HashMap<String, Memory.Value> memory) {
		id.inputVar(scanner);
	}
}