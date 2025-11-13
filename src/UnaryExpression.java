// Niv Ben - David Ohayone 213394364

import java.util.List;

/**
 * UnaryExpression class represents a unary expression that operates on a
 * single operand, providing methods to retrieve the expression's variables
 * and access the operand.
 */
public abstract class UnaryExpression extends BaseExpression {

    private Expression expression;


    /**
     * Instantiates a new Unary expression.
     *
     * @param expression the expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    public List<String> getVariables() {
        return getExpression().getVariables();
    }

    /**
     * Gets expression.
     *
     * @return the expression
     */
    protected Expression getExpression() {
        return this.expression;
    }

}
