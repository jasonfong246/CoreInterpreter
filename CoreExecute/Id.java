import java.util.*;
class Id {
	String identifier;
	void parse() {
		Parser.expectedToken(Core.ID);
		identifier = Parser.scanner.getId();
		Parser.scanner.nextToken();
	}
	
	void print() {
		System.out.print(identifier);
	}

	void storeVar(HashMap<String, Memory.Value> temp){
			Memory.Value val = new Memory.Value();
			val.type = Core.ID;
			val.intValue = 0;
			val.arrayValue = new int[0];
		if(Memory.dsFlag==false){
			Memory.local.peek().put(identifier, val);
		}else{
			Memory.global.put(identifier, val);
		}

		
	}

	void storeArr(HashMap<String, Memory.Value> temp){
		Memory.Value val = new Memory.Value();
		val.type = Core.ARRAY;
		val.intValue = 0;
		val.arrayValue = new int[0];
		if(Memory.dsFlag==false){
			temp.put(identifier, val);
			Memory.local.peek().put(identifier, val);
		}else{
			Memory.global.put(identifier, val);
		}

	}

	void inputVar(Scanner scanner){
		// Scan from args[1] and increase the index of identifier intValue by 1
		// If the identifier is not in the global memory, print an error message
		if(Memory.global.containsKey(identifier)){
			Memory.Value val = Memory.global.get(identifier);
			
			val.intValue = scanner.getConst();
			scanner.nextToken();
			if(scanner.currentToken() == Core.EOS){
				System.out.println("Error is not having enough values to read");
				System.exit(0);
			}
			Memory.global.put(identifier, val);
		}
		else if(Memory.local.peek().containsKey(identifier)){
			Memory.Value val = Memory.local.peek().get(identifier);
			val.intValue = scanner.getConst();
			if(scanner.currentToken() == Core.EOS){
				System.out.println("Error is not having enough values to read");
				System.exit(0);
			}
			scanner.nextToken();
			Memory.local.peek().put(identifier, val);
		}
		
	}
	int getIdValue(){
		int value=0;
		if(Memory.global.containsKey(identifier)){
			value = Memory.global.get(identifier).intValue;
		}else if(Memory.local.peek().containsKey(identifier)){
			value = Memory.local.peek().get(identifier).intValue;
			
		}
		return value;
	}

	int getArrValue(int index){
		int value=0;
		if(Memory.global.containsKey(identifier)){
			value = Memory.global.get(identifier).arrayValue[index];
		}else if(Memory.local.peek().containsKey(identifier)){
			value = Memory.local.peek().get(identifier).arrayValue[index];
		}
		return value;
	}

	void execute(){
		if(Memory.global.containsKey(identifier)){
			System.out.println(Memory.global.get(identifier).intValue);
		}else if(Memory.local.peek().containsKey(identifier)){
			System.out.println(Memory.local.peek().get(identifier).intValue);
		}
	}
}