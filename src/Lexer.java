/**
 * Created by Kirill Kudaev on 3/29/16.
 */

import java.util.ArrayList;

public class Lexer {

    // Declare variables.
    private String inputString;
    private Token currToken;
    private ArrayList<Token> tokens = new ArrayList<>();    // ArrayList of all the tokens.

    // Constructor.
    public Lexer(String str) throws ExpressionFormatException {

        inputString = str.replaceAll("\\s+",""); // Removes all whitespaces and other non visible characters such as tab.
        createTokens();
    }

    public ArrayList<Token> getTokens() {return tokens;}

    private ArrayList<Token> createTokens() throws ExpressionFormatException{

        // Iterates through each char of the string.
        for (int i = 0; i < inputString.length(); i++) {

            char currChar = inputString.charAt(i); // Stores current character.

            if (currChar == '=') {

                currToken = new Token(Token.TokenType.T_EQUALS, "=", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (currChar == '+') {

                currToken = new Token(Token.TokenType.T_PLUS, "+", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (currChar == '-') {

                currToken = new Token(Token.TokenType.T_MINUS, "-", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (currChar == '*') {

                currToken = new Token(Token.TokenType.T_MULT, "*", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (currChar == '/') {

                currToken = new Token(Token.TokenType.T_DIV, "/", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (currChar == '%') {

                currToken = new Token(Token.TokenType.T_MOD_DIV, "%", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (currChar == '(') {

                currToken = new Token(Token.TokenType.T_LEFT_PAREN, "(", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (currChar == ')') {

                currToken = new Token(Token.TokenType.T_RIGHT_PAREN, ")", 0);
                tokens.add(currToken); // Adds token to the list.

            } else if (Character.isDigit(currChar)) {

                String doub = "" + currChar; // To assembly our double from digits, adds first digit.

                // While there is next char and the char is a digit.
                while (i < inputString.length() - 1 && Character.isDigit(inputString.charAt(i+1))) {
                    doub += inputString.charAt(++i);    // Adds a digit to the end of our double.
                }

                // If second char is '.'.
                if (i < inputString.length() - 1 && inputString.charAt(i+1) == '.') {
                    doub += '.';    // Adds '.' to the end of our double.
                    i = i+2;        // To jump over '.' in our string.

                    // While next char is digit.
                    while (i < inputString.length() && Character.isDigit(inputString.charAt(i))){
                           doub += inputString.charAt(i++); // Adds a digit to the end of our double.
                    }
                    i--; // To prevent from incrementing an extra time.
                }

                double val = Double.parseDouble(doub);  // Convert to type double.
                currToken = new Token(Token.TokenType.T_DOUBLE, doub, val);
                tokens.add(currToken); // Adds token to the list.


            } else  if (Character.isLetter(currChar)) {

                String id = "" + currChar;   // To assembly our id from chars, adds first char.
                boolean hasDigit = false;

                // While next char is digit or letter.
                while (i < inputString.length() - 1 && Character.isLetterOrDigit(inputString.charAt(i+1))) {
                    if (Character.isDigit(inputString.charAt(i+1))){
                        hasDigit = true;
                    } else {
                        if (hasDigit) {
                            i++;    // To look at the next char.
                            throw new ExpressionFormatException("Expression eval error: Lex failed on '"
                                    + currChar + "'(letter after a digit for id) with String remaining: '"
                                    + inputString.substring(i, inputString.length()) + "'");
                        }
                    }
                    id += inputString.charAt(++i); // Adds a digit or a letter to the end of our id.
                }

                currToken = new Token(Token.TokenType.T_ID, id, 0);
                tokens.add(currToken); // Adds token to the list.
            } else {
                // In case something is wrong in our expression.

                throw new ExpressionFormatException("Expression eval error: Lex failed on '"+ currChar +
                            "' with String remaining: '" + inputString.substring(i, inputString.length()) + "'");

            }
        }
        return  tokens;
    }
}
