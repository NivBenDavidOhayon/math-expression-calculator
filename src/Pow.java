// Niv Ben - David Ohayone 213394364

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * The Pow class represents the exponentiation operation between two
 * expressions. It extends the BinaryExpression class and implements the
 * Expression interface. It provides methods for evaluation, assignment,
 * differentiation, and simplification of the exponentiation expression.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param ex1 left argument expression.
     * @param ex2 right argument expression.
     */
    public Pow(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    public String toString() {
        return ("(" + getExpression1().toString() + "^"
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
                return new Pow(assigned1, assigned2);
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

        if (super.getExpression1().toString().equals("0.0")
                && super.getExpression2().toString().equals("0.0")) {
            throw new RuntimeException("Undefined expression");
        }

        if (leftValue < 0.0 && rightValue > 0.0 && rightValue < 1.0) {
            throw new Exception("There is no power for a negative base" +
                    " with a fractional exponent.");
        } else if (leftValue == 0.0 && rightValue == 0.0) {
            throw new Exception("Indeterminate form: 0^0.");
        } else {
            return Math.pow(leftValue, rightValue);
        }
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
        return new Mult(new Pow(super.getExpression1(), super.getExpression2()),
                new Plus(new Mult(super.getExpression1().differentiate(var),
                        new Div(super.getExpression2(), super.getExpression1())),
                        new Mult(super.getExpression2().differentiate(var),
                                new Log(new Var("e"), super.getExpression1()))));

    }

    /**
     * the method simplifies the expressions.
     *
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        Expression var1 = super.getExpression1().simplify();
        Expression var2 = super.getExpression2().simplify();

        // if the first expression is 0
        if (var1.toString().equals("0.0")) {
            return new Num(0);
        }

        // if the first expression is 1 or the second expression is 0
        if (var1.toString().equals("1.0") || var2.toString().equals("0.0")) {
            return new Num(1);
        }

        // if the second expression is 0
        if (var2.toString().equals("1.0")) {
            return var1;
        }

        return simplifyPowExpression(var1, var2);
    }
    /**
     * the method simplifies the expressions.
     *
     * @param var1  - expression1 object
     * @param var2 - expression2 object
     * @return Returns the expression after simplify.
     */
    private Expression simplifyPowExpression(Expression var1, Expression var2) {
        Pow powExpression = new Pow(var1, var2);
        boolean hasVariables = !powExpression.getVariables().isEmpty();
        //if there are variables in the expression
        if (!hasVariables) {
            try {
                return new Num(powExpression.evaluate());
            } catch (Exception e) {
                return powExpression;
            }
        }

        return powExpression;
    }

}
