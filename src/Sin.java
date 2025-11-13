// Niv Ben - David Ohayone 213394364

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Sin class represents the sine function applied to an expression.
 * It provides methods for evaluation, assignment, differentiation, and
 * simplification of the sine expression.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param expression argument expression.
     */
    public Sin(Expression expression) {
        super(expression);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getExpression().assign(var, expression));
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {
        return "sin(" + super.getExpression().toString() + ")";
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> emptyAssignment = new TreeMap<>();
        return evaluate(emptyAssignment);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double expressionValue = super.getExpression().evaluate(assignment);
        double radians = Math.toRadians(expressionValue);
        return Math.sin(radians);

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
        return new Mult(super.getExpression().differentiate(var),
                new Cos(super.getExpression()));
    }

    /**
     * the method simplifies the expressions.
     *
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Sin(super.getExpression().simplify());
        }

    }


}
