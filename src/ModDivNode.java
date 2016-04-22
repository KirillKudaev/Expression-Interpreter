/**
 * Created by kirillkudaev on 4/10/16.
 */
public class ModDivNode extends ExpressionNode {

    // Constructor.
    public ModDivNode(double val, ExpressionNode left, ExpressionNode right) {
        super(val, left, right);
    }

    @Override
    public double getValue() { return leftNode.getValue() % rightNode.getValue(); }
}
