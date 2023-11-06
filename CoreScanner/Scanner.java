import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Scanner {
    private ArrayList<String> tokens = new ArrayList<String>();
    private BufferedReader read;
    private Core token;
    private int index=0;
    String getWord="";
    // Initialize the scanner
    Scanner(String filename) throws IOException{
        String line;
        read = new BufferedReader(new FileReader(filename));
        while ((line = read.readLine()) != null) {
            String[] words = line.split("\\s+"); // Split the line into words using whitespace as a separator
            for (String word : words) {
                int i=0;
                //Checks if the assigned word is a "ASSIGN" or not
                if(!word.isEmpty()){
                    if(word.equals(":=")){
                        tokens.add(word);
                    }else{
                        //Checks if the value is a symbol or not 
                        while(i<word.length()){
                            char symbolCheck= word.charAt(i);
                            if(String.valueOf(symbolCheck).equals("[") ||
                                String.valueOf(symbolCheck).equals("]") ||
                                String.valueOf(symbolCheck).equals("(") ||
                                String.valueOf(symbolCheck).equals(")") ||
                                String.valueOf(symbolCheck).equals(";") ||
                                String.valueOf(symbolCheck).equals("+") ||
                                String.valueOf(symbolCheck).equals("-") ||
                                String.valueOf(symbolCheck).equals("/") ||
                                String.valueOf(symbolCheck).equals("*") ||
                                String.valueOf(symbolCheck).equals("<") ||
                                String.valueOf(symbolCheck).equals("=") ||
                                String.valueOf(symbolCheck).equals(":") ||
                                String.valueOf(symbolCheck).equals(",") ){
                                    //Separates the word with the symbols and tokenize separately
                                    if(!getWord.isEmpty()){
                                        tokens.add(getWord);
                                        //Clears the string, so it can continue getting the string value
                                        getWord = "";
                                    }
                                getWord = String.valueOf(symbolCheck);
                                tokens.add(getWord);
                                getWord= "";
                            }else{
                                //Concats the string value character by character
                                getWord = getWord + symbolCheck;
                            }
                            i++;
                        }
                        //If the word isn't empty, it adds the word to the token
                        if(!getWord.isEmpty() ){
                                tokens.add(getWord);
                                getWord="";
                            }
                    }
                }
            }

        }
    }

    // Advance to the next token
    public void nextToken() {
        index++;
    }

    // Return the current token
    public Core currentToken() {
        if(index<tokens.size()){
            if(tokens.get(index).equals("procedure")){
                token = Core.PROCEDURE;
            }else if(tokens.get(index).equals("begin")){
                token = Core.BEGIN;
            }else if(tokens.get(index).equals("is")){
                token = Core.IS;
            }else if(tokens.get(index).equals("end")){
                token = Core.END;
            }else if(tokens.get(index).equals("if")){
                token = Core.IF;
            }else if(tokens.get(index).equals("else")){
                token = Core.ELSE;
            }else if(tokens.get(index).equals("in")){
                token = Core.IN;
            }else if(tokens.get(index).equals("integer")){
                token = Core.INTEGER;
            }else if(tokens.get(index).equals("return")){
                token = Core.RETURN;
            }else if(tokens.get(index).equals("do")){
                token = Core.DO;
            }else if(tokens.get(index).equals("new")){
                token = Core.NEW;
            }else if(tokens.get(index).equals("not")){
                token = Core.NOT;
            }else if(tokens.get(index).equals("and")){
                token = Core.AND;
            }else if(tokens.get(index).equals("or")){
                token = Core.OR;
            }else if(tokens.get(index).equals("out")){
                token = Core.OUT;
            }else if(tokens.get(index).equals("array")){
                token = Core.ARRAY;
            }else if(tokens.get(index).equals("then")){
                token = Core.THEN;
            }else if(tokens.get(index).equals("while")){
                token = Core.WHILE;
            }else if(tokens.get(index).equals("+")){
                token = Core.ADD;
            }else if(tokens.get(index).equals("-")){
                token = Core.SUBTRACT;
            }else if(tokens.get(index).equals("*")){
                token = Core.MULTIPLY;
            }else if(tokens.get(index).equals("/")){
                token = Core.DIVIDE;
            }else if(tokens.get(index).equals(":=")){
                token = Core.ASSIGN;
            }else if(tokens.get(index).equals("=")){
                token = Core.EQUAL;
            }else if(tokens.get(index).equals("<")){
                token = Core.LESS;
            }else if(tokens.get(index).equals("-")){
                token = Core.SUBTRACT;
            }else if(tokens.get(index).equals(":")){
                token = Core.COLON;
            }else if(tokens.get(index).equals(";")){
                token = Core.SEMICOLON;
            }else if(tokens.get(index).equals(".")){
                token = Core.PERIOD;
            }else if(tokens.get(index).equals(",")){
                token = Core.COMMA;
            }else if(tokens.get(index).equals("(")){
                token = Core.LPAREN;
            }else if(tokens.get(index).equals(")")){
                token = Core.RPAREN;
            }else if(tokens.get(index).equals("[")){
                token = Core.LBRACE;
            }else if(tokens.get(index).equals("]")){
                token = Core.RBRACE;
            }else if(tokens.get(index).matches("[0-9]+") ){
                int checkValue = Integer.parseInt(tokens.get(index));
                if(checkValue > 100003 ){
                    token = Core.ERROR;
                    System.out.println("Error is too large constant in file.");
                }else{
                    token = Core.CONST;
                }
            }else if(tokens.get(index).matches("[a-zA-Z0-9]+")){
                token = Core.ID;
            }else if(tokens.get(index)==null || tokens.get(index).equals("EOS")){
                token = Core.EOS;
            }else{
                token = Core.ERROR;
                System.out.println("Error is \""+tokens.get(index)+"\" in file.");
            }
        }else{
            token = Core.EOS;
        }
        return token;
    }

	// Return the identifier string
    public String getId() {
        String value = tokens.get(index);
        return value;
    }

	// Return the constant value
    public int getConst() {
        int value = Integer.valueOf(tokens.get(index));
        return value;

    }

}
