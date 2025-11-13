// Niv Ben - David Ohayone 213394364
import java.util.List;
import java.util.Map;

/**
 * The BinaryExpression class is an abstract class for binary expressions
 * with fields for the left and right argument expressions. It implements
 * evaluation and variable retrieval methods.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression ex1;
    private Expression ex2;

    /**
     * Constructor.
     *
     * @param ex1 left argument expression.
     * @param ex2 right argument expression.
     */
    public BinaryExpression(Expression ex1, Expression ex2) {
        //restart the x and y values
        this.ex1 = ex1;
        this.ex2 = ex2;
    }
    /**
     * return zero
     *
     * @param assignment map value
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }
    /**
     * return zero
     *
     */
    @Override
    public double evaluate() throws Exception {
        return 0;
    }

    /**
     * @return list of the variables in the left and the right arguments.
     */
    public List<String> getVariables() {

        List<String> list1 = getExpression1().getVariables();
        List<String> list2 = getExpression2().getVariables();

        for (int i = 0; i < list2.size(); i++) {
            if (!(list1.contains(list2.get(i)))) {
                list1.add(list2.get(i));
            }
        }
        return list1;
    }


    /**
     * Gets expression 1.
     *
     * @return the left argument of the expression.
     */
    protected Expression getExpression1() {
        return this.ex1;
    }

    /**
     * Gets expression 2.
     *
     * @return the right argument of the expression.
     */
    protected Expression getExpression2() {
        return this.ex2;
    }


    /**
     * the function checks if the expression is equal to the value.
     *
     * @param ex    expression object
     * @param value double number
     * @return true if equals else false
     */
    public boolean areEqual(Expression ex, double value) {
        return ex.toString().equals(Double.toString(value));
    }

}
