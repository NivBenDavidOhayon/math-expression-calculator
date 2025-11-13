// Niv Ben - David Ohayone 213394364
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Div class represents a division operation between two expressions.
 * It provides methods to evaluate, assign values to variables, differentiate,
 * and simplify the division expression.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param ex1 left argument expression.
     * @param ex2 right argument expression.
     */
    public Div(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    public String toString() {
        return ("(" + getExpression1().toString() + " / "
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
                return new Div(assigned1, assigned2);
            }
        }
        return null;
    }

    /**
     * return list of the varibles that are in the division.
     *
     * @return list of the varibles that are in the division.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Expression firstEx = super.getExpression1();
        Expression secondEx = super.getExpression2();

        double leftValue = firstEx.evaluate(assignment);
        double rightValue = secondEx.evaluate(assignment);
        if (super.getExpression2().toString().equals("0.0")) {
            throw new RuntimeException("Undefined expression");
        }

        if (rightValue == 0.0) {
            throw new Exception("Division by zero is not allowed.");
        } else {
            return leftValue / rightValue;
        }
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> emptyAssignment = new TreeMap<>();
        return evaluate(emptyAssignment);
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(new Minus(new Mult(super.getExpression1().differentiate(var),
                super.getExpression2()),
                new Mult(super.getExpression1(),
                        super.getExpression2().differentiate(var))),
                new Pow(super.getExpression2(),
                        new Num(2)));

    }
    /**
     * the method simplifies the expressions
     * @return Returns the expression after simplify.
     */
    @Override
    public Expression simplify() {
        Expression firstEx = super.getExpression1().simplify();
        Expression secondEx = super.getExpression2().simplify();

        //if the secondEx and the firstEx are equals
        if (firstEx.toString().equals(secondEx.toString())) {
            return new Num(1);
        }
        //if the firstEx is 0
        if (areEqual(firstEx, 0.0)) {
            return new Num(0);
        }
        //if the secondEx is 1
        if (areEqual(secondEx, 1.0)) {
            return firstEx;
        }
        //if the secondEx is -1
        if (areEqual(secondEx, -1.0)) {
            return new Neg(firstEx);
        }
        return simplifyDiv(firstEx, secondEx);
    }
    /**
     * the method simplifies the expressions.
     * @param expression1 - expression1 object
     * @param expression2 - expression2 object
     * @return Returns the expression after simplify.
     */
    private Expression simplifyDiv(Expression expression1, Expression expression2) {
        Div DivExpression = new Div(expression1, expression2);
        boolean hasVariables = !DivExpression.getVariables().isEmpty();
        //if there are variables in the expression
        if (!hasVariables) {
            try {
                return new Num(DivExpression.evaluate());
            } catch (Exception e) {
                return DivExpression;
            }
        }
        return DivExpression;
    }


}