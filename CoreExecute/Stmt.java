
import java.util.*;
interface Stmt {
	void parse();
	void print(int indent);
	void execute(Scanner scanner, HashMap<String, Memory.Value> memory);
}