import java.util.*;
class Decl implements Stmt {
	DeclInteger declInt;
	DeclArray declArr;
	
 @Override
	public void parse() {
		if (Parser.scanner.currentToken() == Core.INTEGER) {
			declInt = new DeclInteger();
			declInt.parse();
		} else {
			declArr = new DeclArray();
			declArr.parse();
		}
	}
	
 @Override
	public void print(int indent) {
		if (declInt != null) {
			declInt.print(indent);
		} else {
			declArr.print(indent);
		}
	}

	 @Override
	public void execute(Scanner scanner, HashMap<String, Memory.Value> memory) {
		if (declInt != null) {
			declInt.execute(memory);
		} else {
			declArr.execute(memory);
		}
	}

}