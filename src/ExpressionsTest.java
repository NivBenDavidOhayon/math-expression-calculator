// Niv Ben - David Ohayone 213394364
import java.util.Map;
import java.util.TreeMap;
public class ExpressionsTest {
    public static void main(String[] args) throws Exception {
        Expression e1 = new Plus(new Plus(new Mult(new Num(2), new Var("x")),
                new Sin(new Mult(new Num(4), new Var("y")))),
                new Pow(new Var("e"), new Var("x")));
        String s1 = e1.toString();
        System.out.println(s1);
        Map<String, Double> assignment1 = new TreeMap<String, Double>();
        assignment1.put("x", 2.0);
        assignment1.put("y", 0.25);
        assignment1.put("e", 2.71);
        double value1 = e1.evaluate(assignment1);
        System.out.println(value1);
        Expression e2 = e1.differentiate("x");
        String s2 = e2.toString();
        System.out.println(s2);
        Map<String, Double> assignment2 = new TreeMap<String, Double>();
        assignment2.put("x", 2.0);
        assignment2.put("y", 0.25);
        assignment2.put("e", 2.71);
        double value2 = e2.evaluate(assignment2);
        System.out.println(value2);
        Expression e3 = e2.simplify();
        String s3 = e3.toString();
        System.out.println(s3);
    }
    }

