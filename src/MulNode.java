/**
 * Created by kirillkudaev on 4/10/16.
 */
public class MulNode extends ExpressionNode {

    // Constructor.
    public MulNode(double val, ExpressionNode left, ExpressionNode right) {
        super(val, left, right);
    }

    @Override
    public double getValue() {return leftNode.getValue() * rightNode.getValue();}
}
