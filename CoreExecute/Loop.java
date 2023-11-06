import java.util.HashMap;

class Loop implements Stmt {
	Cond cond;
	StmtSeq ss;
	
 @Override
	public void parse() {
		Parser.scanner.nextToken();
		cond = new Cond();;
		cond.parse();
		Parser.expectedToken(Core.DO);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
	}
	
 @Override
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("while ");
		cond.print();
		System.out.println(" do");
		ss.print(indent+1);
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.println("end");
	}

 @Override
	public void execute(Scanner scanner,HashMap<String, Memory.Value> memory) {
		while (cond.execute()){
			ss.execute(scanner,memory);
		}
	}
}