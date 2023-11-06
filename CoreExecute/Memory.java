import java.util.*;
class Memory{

    static class Value {
        Core type;
        int intValue;
        int[] arrayValue;
    }

    static HashMap<String, Value> global = new HashMap<>();
    static Stack<HashMap<String, Value>> local = new Stack<>();
    // Use this flag to keep track of when we finish the DeclSeq
    static boolean dsFlag = true;
    static void store(String var, int value) {
        // Look up based on var, change the intValue to the value passed in
        if (local.size() > 0) {
            local.peek().get(var).intValue = value;
        } 
        if(global.size()>0){
            global.get(var).intValue = value;
        }
    }
}
