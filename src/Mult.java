// Niv Ben - David Ohayone 213394364

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Mult class represents a multiplication operation between two
 * expressions and provides methods for evaluation, assignment,
 * differentiation, and simplification of the multiplication expression.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param ex1 left argument expression.
     * @param ex2 right argument expression.
     */
    public Mult(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    public String toString() {
        return ("(" + getExpression1().toString() + " * "
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
                return new Mult(assigned1, assigned2);
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
        Expression left = super.getExpression1();
        Expression right = super.getExpression2();

        double leftValue = left.evaluate(assignment);
        double rightValue = right.evaluate(assignment);

        return leftValue * rightValue;
    }


    @Override
    public double evaluate() throws Exception {
        Map<String, Double> emptyAssignment = new TreeMap<>();
        return evaluate(emptyAssignment);
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
        return new Plus(new Mult(super.getExpression1().differentiate(var),
                super.getExpression2()),
                new Mult(super.getExpression1(),
                        super.getExpression2().differentiate(var)));
    }

    /**
     * return a simplified version of the current expression.
     *
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        Expression var1 = super.getExpression1().simplify();
        Expression var2 = super.getExpression2().simplify();
        // if one of the exponents is 0
        if (var1.toString().equals("0.0") || var2.toString().equals("0.0")) {
            return new Num(0);
        }
        // if ex1 is 1
        if (var1.toString().equals("1.0")) {
            return super.getExpression2().simplify();
        }
        // if ex 2 is 1
        if (var2.toString().equals("1.0")) {
            return super.getExpression1().simplify();
        }
        // if ex 1 is -1
        if (var1.toString().equals("-1.0")) {
            return new Neg(super.getExpression2().simplify());
        }
        // if ex 2 is -1
        if (var2.toString().equals("-1.0")) {
            return new Neg(super.getExpression1().simplify());
        }
        return simplifyMult(var1, var2);
    }

    /**
     * the method simplifies the expressions.
     *
     * @param expression1 - expression1 object
     * @param expression2 - expression2 object
     * @return Returns the expression after simplify.
     */
    private Expression simplifyMult(Expression expression1, Expression expression2) {
        Mult multiExpression = new Mult(expression1, expression2);
        boolean hasVariables = !multiExpression.getVariables().isEmpty();
        //if there are variables in the expression
        if (!hasVariables) {
            try {
                return new Num(multiExpression.evaluate());
            } catch (Exception e) {
                return multiExpression;
            }
        }

        return multiExpression;
    }

}

