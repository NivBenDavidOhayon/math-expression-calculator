// Niv Ben - David Ohayone 213394364

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * The Minus class represents a subtraction operation between two expressions
 * and provides methods for evaluation, assignment, differentiation, and
 * simplification of the subtraction expression.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param ex1 left argument expression.
     * @param ex2 right argument expression.
     */
    public Minus(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    public String toString() {
        return ("(" + getExpression1().toString() + " - "
                + getExpression2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression check1 = getExpression1();
        Expression check2 = getExpression2();

        if (check1 != null && check2 != null) {
            Expression assigned1 = check1.assign(var, expression);
            Expression assigned2 = check2.assign(var, expression);

            if (assigned1 != null && assigned2 != null) {
                return new Minus(assigned1, assigned2);
            }
        }

        return null;
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getExpression1().evaluate(assignment)
                - super.getExpression2().evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        // Create an empty assignment
        Map<String, Double> assignment = new TreeMap<>();
        // Call the evaluate(assignment) method with the empty assignment
        double result = evaluate(assignment);

        return result;
    }

    /**
     * the method Performs a mathematical derivation operation for the expression
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating
     * the current expression
     * relative to variable `var`.
     */
    @Override
    public Expression differentiate(String var) {
        return new Minus(super.getExpression1().differentiate(var),
                super.getExpression2().differentiate(var));
    }

    /**
     * the method simplifies the expressions.
     *
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        Expression firstEx = super.getExpression1().simplify();
        Expression secondEx = super.getExpression2().simplify();

        //if the first expression is 0
        if (firstEx.toString().equals("0.0")) {
            return new Neg(secondEx);
        }

        //if the second expression is 0
        if (secondEx.toString().equals("0.0")) {
            return firstEx;
        }
        // if the expressions are equals
        if (firstEx.toString().equals(secondEx.toString())) {
            return new Num(0);
        }

        return simplifyMinusExpression(firstEx, secondEx);
    }

    /**
     * the method simplifies the expressions.
     *
     * @param firstEx  - expression1 object
     * @param secondEx - expression2 object
     * @return Returns the expression after simplify.
     */
    private Expression simplifyMinusExpression(Expression firstEx, Expression secondEx) {
        Minus minusExpression = new Minus(firstEx, secondEx);
        boolean hasVariables = !minusExpression.getVariables().isEmpty();

        //if there are variables
        if (!hasVariables) {
            try {
                //return the value if it can do simplify action
                return new Num(minusExpression.evaluate());
            } catch (Exception e) {
                return minusExpression;
            }
        }

        return minusExpression;
    }


}
