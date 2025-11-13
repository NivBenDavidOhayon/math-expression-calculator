// Niv Ben - David Ohayone 213394364

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Log class represents a logarithm operation with a specified base and
 * argument. It provides methods to evaluate, assign values to variables,
 * differentiate, and simplify the logarithm expression.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param ex1 left argument expression.
     * @param ex2 right argument expression.
     */
    public Log(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    public String toString() {
        return ("log(" + getExpression1().toString() + ", "
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
                return new Log(assigned1, assigned2);
            }
        }
        return null;
    }

    /**
     * @return list of the variables that in the Log.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double base = super.getExpression1().evaluate(assignment);
        double right = super.getExpression2().evaluate(assignment);

        if (base <= 0.0 || base == 1.0 || right <= 0.0) {
            throw new Exception("Cannot evaluate Log expression." +
                    " The base and the argument must be positive numbers.");
        }

        return Math.log(right) / Math.log(base);
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

        if (super.getExpression1().getVariables().contains(var)) {
            Expression temp = new Div(new Log(new Var("e"),
                    super.getExpression2()), new Log(new Var("e"),
                    super.getExpression1()));
            return temp.differentiate(var);
        } else {
            return new Div(super.getExpression2().differentiate(var),
                    new Mult(super.getExpression2(), new Log(new Var("e"),
                            super.getExpression1())));
        }
    }

    /**
     * the method simplifies the expressions.
     *
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        Expression left = super.getExpression1().simplify();
        Expression right = super.getExpression2().simplify();

        // if the expressions are equals
        if (left.toString().equals(right.toString())) {
            return new Num(1);
        }
        //if the second expression is 1
        if (areEqual(right, 1.0)) {
            return new Num(0);
        }

        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Log(left.simplify(), right.simplify());
        }
    }
}

