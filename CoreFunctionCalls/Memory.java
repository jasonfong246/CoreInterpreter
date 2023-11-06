import java.util.*;

class Memory {
	//scanner is stored here as a static field so it is avaiable to the execute method for factor
	public static Scanner data;
	// Class and data structures to represent variables
	static class Value {
		Core type;
		int integerVal;
		int[] arrayVal;
		Value(Core t) {
			this.type = t;
		}
	}

	static class Function{
		Parameters params;
		StmtSeq stmtSeq;
	}
	
	public static HashMap<String, Value> global;
	public static Stack<HashMap<String, Value>> local;
	public static HashMap<String,Function> function;
	public static Stack<Stack<HashMap<String, Value>>> frame;
	
	
	// Helper methods to manage memory
	
	// This inializes the global memory structure
	// Called before executing the DeclSeq
	public static void initializeGlobal() {
		global = new HashMap<String, Value>();
	}
	
	// Initializes the local data structure
	// Called before executing the main StmtSeq
	public static void initializeLocal() {
		local = new Stack<HashMap<String, Value>>();
	}

	public static void initializeFunction() {
		function = new HashMap<String, Function>();
	}

	public static void initilizeFrame(){
		frame = new Stack<Stack<HashMap<String, Value>>>();
	}

	// Pushes a "scope" for if/loop stmts
	public static void pushScope() {
		local.push(new HashMap<String, Value>());
	}
	
	// Pops a "scope"
	public static void popScope() {
		local.pop();
	}
	public static void declareFunction(String id,Parameters param, StmtSeq stmtSeq){
		Function f = new Function();
		f.params = param;
		f.stmtSeq = stmtSeq;
		if(function.containsKey(id)){
			System.out.println("Error: Duplicate function");
			System.exit(0);
		}
		function.put(id, f);
	}
	static int count=0;
	public static void callFunction(String functionId, Parameters param){
		
		Function f = function.get(functionId);
		Parameters p = f.params;
		ArrayList<String> arguParam = param.execute();
		ArrayList<String> formalParam = p.execute();
		if(count ==0){
			if(formalParam.size()>1){
				for(int x = 0; x<formalParam.size()-1; x++){
					for(int y=x+1; y<formalParam.size(); y++){
						if (formalParam.get(x).equals(formalParam.get(y))) {
							System.out.println("Error: Duplicate parameter");
							System.exit(0);
						}
					}
				}
			}
		}
		count++;
		StmtSeq s = f.stmtSeq;
		Stack<HashMap<String, Value>> localStack = new Stack<HashMap<String,Value>>();
		HashMap<String,Value> variables = new HashMap<String,Value>();
		for (int i =0; i<arguParam.size(); i++){
			Value v = getLocalOrGlobal(arguParam.get(i));
			variables.put(formalParam.get(i), v);
		}
		localStack.push(variables);		
		frame.push(Memory.local);
		Memory.local = localStack;
		s.execute();
		Memory.local = frame.pop();
	}

	// Handles decl integer
	public static void declareInteger(String id) {
		Value v = new Value(Core.INTEGER);
		if (local != null) {
			local.peek().put(id, v);
		} else {
			global.put(id, v);
		}
	}
	
	// Handles decl array
	public static void declareArray(String id) {
		Value v = new Value(Core.ARRAY);
		if (local != null) {
			local.peek().put(id, v);
		} else {
			global.put(id, v);
		}
	}
	
	// Retrives a value from memory (integer or array at index 0
	public static int load(String id) {
		int value;
		Value v = getLocalOrGlobal(id);
		if (v.type == Core.INTEGER) {
			value = v.integerVal;
		} else {
			value = v.arrayVal[0];
		}
		return value;
	}
	
	// Retrieves a array value from the index
	public static int load(String id, int index) {
		Value v = getLocalOrGlobal(id);
		return v.arrayVal[index];
	}
	
	// Stores a value (integer or array at index 0)
	public static void store(String id, int value) {
		Value v = getLocalOrGlobal(id);
		if (v.type == Core.INTEGER) {
			v.integerVal = value;
		} else {
			v.arrayVal[0] = value;
		}
	}
	
	// Stores a value at array index
	public static void store(String id, int index, int value) {
		Value v = getLocalOrGlobal(id);
		v.arrayVal[index] = value;
	}
	
	// Handles "new array" assignment
	public static void allocate(String id, int index) {
		Value v = getLocalOrGlobal(id);
		v.arrayVal = new int[index];
	}
	
	// Handles "id := array id" assignment
	public static void alias(String lhs, String rhs) {
		Value v1 = getLocalOrGlobal(lhs);
		Value v2 = getLocalOrGlobal(rhs);
		v1.arrayVal = v2.arrayVal;
	}
	
	// Looks up value of the variables, searches local then global
	private static Value getLocalOrGlobal(String id) {
		Value result;
		if (local.size() > 0) {
			if (local.peek().containsKey(id)) {
				result = local.peek().get(id);
			} else {
				HashMap<String, Value> temp = local.pop();
				result = getLocalOrGlobal(id);
				local.push(temp);
			}
		} else {
			result = global.get(id);
		}
		return result;
	}

}