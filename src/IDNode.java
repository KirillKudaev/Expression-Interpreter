/**
 * Created by kirillkudaev on 4/10/16.
 */
public class IDNode extends ExpressionNode {

    // Constructor.
    public IDNode(double d) { super(d, null, null); }

    @Override
    public double getValue() { return 0; }
}
