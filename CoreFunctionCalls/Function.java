class Function {
    Id id;
    Parameters p;
    StmtSeq ss;
    void parse(){
        Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		p = new Parameters();
		p.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.IS);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();        
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
    }
    void execute(){
		Memory.declareFunction(id.getId(), p, ss);
    }
    
}
