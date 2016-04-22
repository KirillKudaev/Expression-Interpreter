/**
 * Created by kirillkudaev on 4/10/16.
 */
public class ParenNode extends ExpressionNode {

    // Constructor.
    public ParenNode(ExpressionNode left) {
        super(left.getValue(), left, null);
    }

    @Override
    public double getValue() {return leftNode.getValue();}
}