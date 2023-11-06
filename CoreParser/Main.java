import java.io.IOException;
class Main {
	public static void main(String[] args) throws IOException {
		// Initialize the scanner with the input file
		Scanner S = new Scanner(args[0]);
		Procedure P = new Procedure(S);
		P.parse(S);
		P.print();
		// Print the token stream
	}
}