Name: Jason Fong Shen Yik
 
Name of other files:
Memory.java = A memory class that stores all the global and local variables. It allows the execute method to collect the variables easily.

Special features:
I combined a lot of methods in Id.java. Java classes that calls Id.java only calls the specific method.

Interpreter Design:
For global variables, I used a HashMap to store all the global variables. For local variables, I use a stack of HashMaps to store the variables. To keep the local variables within an "If" or "While", I would have the stack to push a HashMap, so that the local variables are within the scope. The local variables are then popped after the Stmt.java finish executing.

Test:
I had a tough time understanding id := array id. I placed multiple System.out.println() in many of the java files to keep track of where it went wrong specifically. 


