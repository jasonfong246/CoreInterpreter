
class Call implements Stmt{
    Id id;
    Parameters p;
    @Override
    public void parse(){
        Parser.scanner.nextToken();
        id = new Id();
		id.parse();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		p = new Parameters();
		p.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();
    }
    
    @Override
    public void print(int indent){
        
    }
    @Override
    public void execute(){
        if(Memory.function.containsKey(id.getId())){
            Memory.callFunction(id.getId(),p);
        }else{
            System.out.println("Error: Function not declared");
            System.exit(0);
        }
    }
}
