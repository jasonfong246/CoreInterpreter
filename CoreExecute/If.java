import java.util.*;
class If implements Stmt {
	Cond cond;
	StmtSeq ss1;
	StmtSeq ss2;
	
 @Override
	public void parse() {
		Parser.scanner.nextToken();
		cond = new Cond();;
		cond.parse();
		Parser.expectedToken(Core.THEN);
		Parser.scanner.nextToken();
		ss1 = new StmtSeq();
		ss1.parse();
		if (Parser.scanner.currentToken() == Core.ELSE) {
			Parser.scanner.nextToken();
			ss2 = new StmtSeq();
			ss2.parse();
		}
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
	}
	
 @Override
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("	");
		}
		System.out.print("if ");
		cond.print();
		System.out.println(" then");
		ss1.print(indent+1);
		if (ss2 != null) {
			for (int i=0; i<indent; i++) {
				System.out.print("	");
			}
			System.out.println("else");
			ss2.print(indent+1);
		}
		for (int i=0; i<indent; i++) {
			System.out.print("	");
		}
		System.out.println("end");
	}

 @Override
	public void execute(Scanner scanner,HashMap<String, Memory.Value> memory) {
		if (cond.execute()) {
			ss1.execute(scanner,memory);
		} else if (ss2 != null) {
			ss2.execute(scanner,memory);
		}
	}
}