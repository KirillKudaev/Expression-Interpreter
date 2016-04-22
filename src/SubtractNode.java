/**
 * Created by kirillkudaev on 4/10/16.
 */
public class SubtractNode extends ExpressionNode {

    // Constructor.
    public SubtractNode(double val, ExpressionNode left, ExpressionNode right) {
        super(val, left, right);
    }

    @Override
    public double getValue() {return leftNode.getValue() - rightNode.getValue();}
}
