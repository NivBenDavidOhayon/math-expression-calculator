// Niv Ben - David Ohayone 213394364
import java.util.List;
import java.util.Map;

/**
 * The Expression interface defines a set of methods for working with
 * mathematical expressions. It provides functionality to evaluate an
 * expression with variable assignments, obtain a list of variables used
 * in the expression, convert the expression to a string representation,
 * assign a new expression to a variable within the expression, differentiate
 * the expression with respect to a variable, and simplify the expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment the assignment
     * @return the double
     * @throws Exception the exception
     */

    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the double
     * @throws Exception the exception
     */

    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return the variables
     */

    List<String> getVariables();


    /**
     * Returns a nice string representation of the expression.
     *
     * @return the string
     */
    String toString();

    /**
     *  Returns a new expression in which all occurrences of the variable
     *  var are replaced with the provided expression (Does not modify the
     *  current expression).
     *
     * @param var        the var
     * @param expression the expression
     * @return the expression
     */

    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var the var
     * @return the expression
     */

    Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     *
     * @return the expression
     */

    Expression simplify();

}
