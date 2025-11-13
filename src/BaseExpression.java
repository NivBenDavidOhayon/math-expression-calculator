// Niv Ben - David Ohayone 213394364
import java.util.*;

/**
 * The BaseExpression class is an abstract class that implements the
 * Expression interface, providing common functionality for expression classes,
 * including evaluation and variable access. It also includes a convenience
 * method for evaluating expressions with an empty assignment and a getter
 * method for accessing the list of variables.
 */
public abstract class BaseExpression implements Expression{

    /**
     * A convenience method.
     *
     * @return `evaluate(assignment)` method above, but uses an empty assignment.
     * @throws Exception - exception didn't secsseded to do evalute
     */
    @Override
    public double evaluate() throws Exception {
        return evaluate(new HashMap<String, Double>());
    }

}
