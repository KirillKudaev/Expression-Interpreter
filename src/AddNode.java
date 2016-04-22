/**
 * Created by kirillkudaev on 4/10/16.
 */
public class AddNode extends ExpressionNode {

    // Constructor.
    public AddNode(double val, ExpressionNode left, ExpressionNode right) {
        super(val, left, right);
    }

    @Override
    public double getValue() { return leftNode.getValue() + rightNode.getValue(); }
}
