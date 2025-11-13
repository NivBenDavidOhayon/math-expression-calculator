// Niv Ben - David Ohayone 213394364
import java.util.*;

/**
 *
 * The Num class represents a numerical value in an expression. It stores a
 * double value and provides methods for evaluation, assignment,
 * differentiation, and simplification of the numerical expression.
 */
public class Num implements Expression {
    private double num;

    /**
     * Instantiates a new Num.
     *
     * @param num the num
     */
    public Num(double num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return Double.toString(num);
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return num;
    }
    @Override
    public double evaluate() throws Exception {
        return num;
    }
    @Override
    public Expression assign(String var, Expression expression){
        return new Num(this.num);
    }
    @Override
    public List<String> getVariables() {
        return new ArrayList();
    }
    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }
    @Override
    public Expression simplify() {
        return this;
    }
}
