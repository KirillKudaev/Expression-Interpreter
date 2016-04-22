/**
 * Created by kirillkudaev on 4/10/16.
 */
public abstract class ExpressionNode {

    private double value;
    public ExpressionNode leftNode;
    public ExpressionNode rightNode;

    // Constructor.
    public ExpressionNode(double val, ExpressionNode left, ExpressionNode right) {
        value = val;
        leftNode = left;
        rightNode = right;
    }

    public double getValue() {
        return value;
    }
}
