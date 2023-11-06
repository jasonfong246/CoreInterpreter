Name: Jason Fong Shen Yik

Files:
Parameters.java- Takes all the parameters from a function. The execute() method returns an
ArrayList of Strings. 

Memory.java - Added a declareFunction method to declare a function and store it inside a 
HashMap<String, Function>. Added a static class Function to help store Function's parameters
and StmtSeq. Added callFunction to help call Function and pass arguments into the formal
parameters. 

Call.java - A call parser that calls the Function. Its execute method is done in the
Memory.class.

Special Features - I used an extra static int "count" to keep track of duplicate parameters.
This is because in the ArrayList, there will always be duplicate variables in each recursion
call.

How the call stack is implemented - I have a callFunction method declared in my Memory.class.
The method first get its procedure's ID to properly search for which Function to be used. 
The formal parameters and arguments are then executed to copy the variables over. I also have
a local stack that allows me to push the copied variables inside. It is then pushed into 
the main frame for StmtSeq execute. The frame pops after the execute is finished.

Known Bugs - Tracking the null pointer exception that has occured after I push the local
stack into the main frame. Fixing the duplicate parameters, because each recursion is bound
to have duplicate variables.