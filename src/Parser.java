/**
 * Created by kirillkudaev on 4/10/16.
 */
import java.util.ArrayList;
import java.util.Map;

public class Parser {

    // Declare variables.
    private ArrayList<Token> tokens;
    private int index = 0;
    private double expressionValue;
    public static SymbolTable myTable = new SymbolTable();  // To stores values of the corresponding variables (IDs).

    // Constructor.
    public Parser(ArrayList<Token> t) throws ExpressionLogicException {
        tokens = t;
        parse();
    }

    private void  parse() throws ExpressionLogicException {
        expressionValue = expression().getValue();
    }

    private ExpressionNode expression() throws ExpressionLogicException { return restExpression(term()); }

    private ExpressionNode restExpression(ExpressionNode eNode) throws ExpressionLogicException {

        if (index < tokens.size()) {
            if (tokens.get(index).getTokenType() == Token.TokenType.T_PLUS) {
                // If '+' token.

                return restExpression(new AddNode(tokens.get(index++).getValue(), eNode, term()));

            } else if (tokens.get(index).getTokenType() == Token.TokenType.T_MINUS) {
                // If '-' (minus) token.

                return restExpression(new SubtractNode(tokens.get(index++).getValue(), eNode, term()));
            } else if (tokens.get(index).getTokenType() == Token.TokenType.T_EQUALS) {
                // If '=' token.

                index = 0;
                return assignment();
            }
        }

        // Otherwise return node that it got.
        return eNode;
    }

    private ExpressionNode term() throws ExpressionLogicException {

        return restTerm(factor());
    }

    private ExpressionNode restTerm(ExpressionNode eNode) throws ExpressionLogicException {

        if (index < tokens.size()) {
            if (tokens.get(index).getTokenType() == Token.TokenType.T_MULT) {
                // If '*' token.

                return restTerm(new MulNode(tokens.get(index++).getValue(), eNode, factor()));

            } else if (tokens.get(index).getTokenType() == Token.TokenType.T_DIV) {
                // If '/' token.

                return restTerm(new DivNode(tokens.get(index++).getValue(), eNode, factor()));

            } else if (tokens.get(index).getTokenType() == Token.TokenType.T_MOD_DIV) {
                // If '%' token.

                return restTerm(new ModDivNode(tokens.get(index++).getValue(), eNode, factor()));
            }
        }

        // Otherwise return node that it got.
        return eNode;
    }

    private ExpressionNode factor() throws ExpressionLogicException {

        if (index < tokens.size()) {
            if (tokens.get(index).getTokenType() == Token.TokenType.T_DOUBLE) {
                // If double token.

                return new DoubleNode(tokens.get(index++).getValue());

            } else if (tokens.get(index).getTokenType() == Token.TokenType.T_ID) {
                // If variable (ID) token.


                if (myTable.hasSymbol(tokens.get(index).getLexeme())) {
                    return new DoubleNode(myTable.findSymbol(tokens.get(index++).getLexeme()));
                }

                return new IDNode(tokens.get(index++).getValue());

            } else if (tokens.get(index).getTokenType() == Token.TokenType.T_LEFT_PAREN) {
                // If '(' token.

                index++;    // Consumes left parenthesis token.
                ParenNode tempParenNode = new ParenNode(expression());
                index++;    // Consumes right parenthesis token.
                return tempParenNode;

            } else if (tokens.get(index).getTokenType() == Token.TokenType.T_MINUS) {
                // If '-' (unary minus) token.

                index++; // Skips the token.
                return new DoubleNode(-factor().getValue());

            } else {
                throw new ExpressionLogicException("Expression logic error: Parse failed on "
                        + tokens.get(index).getLexeme());
            }
        } else {
            throw new ExpressionLogicException("Expression logic error: Parse failed");
        }
    }


    private ExpressionNode assignment() throws ExpressionLogicException{

        String varName = tokens.get(0).getLexeme();
        tokens.remove(0);       // Removes ID from token list.
        tokens.remove(0);       // Removes '=' sign from token list.
        ExpressionNode valueNode = expression();
        myTable.addSymbol(varName, valueNode.getValue());   // Adds variable-value pair to the table.
        return valueNode;
    }

    public double getExpressionValue() { return expressionValue; }
}
