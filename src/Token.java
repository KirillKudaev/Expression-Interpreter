/**
 * Created by Kirill Kudaev on 3/29/16.
 */

public class Token {

    // Declare variables.
    private TokenType tokenType;
    private String name;
    private double value;

    // Constructor.
    public Token(TokenType t, String s, double d) {
        tokenType = t;
        name = s;
        value = d;
    }

    // Declare token type enumerator.
    public enum TokenType {T_ID, T_DOUBLE, T_PLUS, T_MINUS, T_MULT, T_DIV, T_EQUALS, T_LEFT_PAREN, T_RIGHT_PAREN, T_MOD_DIV
    }

    public TokenType getTokenType() {return tokenType;}

    public String getLexeme() {return name;}

    public double getValue() {return value;}

    @Override
    public String toString() {return String.format("%-20s%-15s%-15.3f", tokenType, name, value);
    }
}
