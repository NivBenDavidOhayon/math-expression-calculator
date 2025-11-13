// Niv Ben - David Ohayone 213394364
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Cos class is a concrete implementation of the UnaryExpression class
 * representing the cosine function. It provides methods for assignment,
 * variable retrieval, evaluation, differentiation, and simplification of
 * the expression.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param expression expression.
     */
    public Cos(Expression expression) {
        super(expression);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.getExpression().assign(var, expression));
    }

    /**
     * @return list of the varibles that are in the Cos.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {
        return "cos(" + super.getExpression().toString() + ")";
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> emptyAssignment = new TreeMap<>();
        return evaluate(emptyAssignment);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double value = super.getExpression().evaluate(assignment);
        return Math.cos(Math.toRadians(value));
    }

    /**
     * the method Performs a mathematical derivation operation for the expression
     *
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating
     * the current expression
     * relative to variable `var`.
     */
    @Override
    public Expression differentiate(String var) {
        return new Neg(new Mult(new Sin(super.getExpression()),
                super.getExpression().differentiate(var)));
    }

    /**
     * return a simplified version of the current expression.
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            //simplify the expression
            return new Cos(super.getExpression().simplify());
        }
    }


}
