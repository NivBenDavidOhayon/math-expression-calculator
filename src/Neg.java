// Niv Ben - David Ohayone 213394364
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * The Neg class represents a negation operation on an expression and
 * provides methods for evaluation, assignment, differentiation, and
 * simplification of the negation expression.
 */
public class Neg extends UnaryExpression implements Expression {


    /**
     * Instantiates a new Neg.
     *
     * @param expression the expression
     */
    public Neg(Expression expression){
        super(expression);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getExpression().assign(var, expression));
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }
    @Override
    public String toString() {
        return "(-" + super.getExpression().toString() + ")";
    }


    @Override
    public double evaluate() throws Exception {
        Map<String, Double> emptyAssignment = new TreeMap<>();
        return evaluate(emptyAssignment);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -1 * super.getExpression().evaluate(assignment);
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
        return new Neg(super.getExpression().differentiate(var));
    }


    /**
     * return a simplified version of the current expression.
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(-1 * super.getExpression().evaluate());
        } catch (Exception except) {
            boolean flag = false; // just to fill the catch - do nothing !
        }
        return new Neg(super.getExpression().simplify());
    }


}