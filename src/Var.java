// Niv Ben - David Ohayone 213394364

import java.util.*;

/**
 * The Var class represents a variable in an expression. It stores the name
 * of the variable and provides methods for evaluation, assignment,
 * differentiation, and simplification of the variable expression.
 */
public class Var implements Expression {
    private String var;

    /**
     * Instantiates a new Var.
     *
     * @param var the var
     */
    public Var(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return this.var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.var)) {
            double value = assignment.get(this.var);
            return value;
        } else {
            String errorMessage = "The variable '" + this.var
                    + "' does not exist in the expression.";
            throw new Exception(errorMessage);
        }

    }

    @Override
    public double evaluate() throws Exception {
        // Create an empty assignment
        Map<String, Double> assignment = new TreeMap<>();

        // Call the evaluate(assignment) method with the empty assignment
        double result = evaluate(assignment);

        return result;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.compareTo(var) == 0) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public List<String> getVariables() {
        List varibles = new ArrayList();
        varibles.add(this.var);
        return varibles;
    }

    @Override
    public Expression differentiate(String var) {
        if (this.var.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
