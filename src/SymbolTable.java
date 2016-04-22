import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Created by kirillkudaev on 4/18/16.
 */
public class SymbolTable {

    private  Map<String, Double> varValues = new LinkedHashMap<>();

    public void addSymbol(String var, double val) {
        varValues.put(var, val);
    }

    public boolean hasSymbol(String var) {
        return varValues.containsKey(var);
    }

    public double findSymbol(String var) {
        return varValues.get(var);
    }
}
