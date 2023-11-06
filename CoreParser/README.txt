Name: Jason Fong Shen Yik

File Names:

Core.java->Contains all the core tokens.

Scanner.java->Scans the file appropriately and stores them into a token.

Main.java-> Main method to run the code

Procedure.java->Runs the procedure, there will be a Declaration and Statement method that prints their respective values

Decl.java->Parse all the necessary Declaration for the procedure.It will call Decl.java recursively until currentToken reaches "begin". 
There is a print method that prints the desired values.

DeclSeq.java->It is a terminal that fetches its respective Integer or Array.

Stmt.java->Parse all the necessary Statements for the procedure. It will call Stmt.java recursively until currentTOekn reaches "END". 
There is a print method that prints the desired values.

StmtSeq.java->It is a terminal class that takes all its necessary grammar and combines them into a String. It will also call Expr.java when its needed

Expr.java->It is an expression class. It is also a terminal class that takes the necessary grammer into a String.

Cond.java->It is a condition class for "IF" and "WHILE"

Special features:
I have used a method that stores my declared integers or arrays in an ArrayList. I did not use a Stack-Map to store the values. Instead,
I declared 2 ArrayList, 1 for integer, and 1 for array. If the statement has an integer that needs to be assigned, it will check whether that
int is contained in the arraylist or not.

Description of the parser:
The scanner first reads the file, and stores its respective tokens into a list. The parser then reads all the values from the list,
then prints out them out. The parser checks each current token and see whether it is on the right track or not. If it isn't it will print errors.
How I tested:
I started testing the code by using a file after another. I go in by sequence of the Correct code, then only after the Error code.