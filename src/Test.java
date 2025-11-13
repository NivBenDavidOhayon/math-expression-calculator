import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) throws Exception {

        Expression e1 = new Plus(new Var("x"), new Var("y"));
        Expression e2 = new Plus(new Num(1), new Num(2));
        String s1 = e1.toString();
        System.out.println(s1);
        Expression de11 = e1.differentiate("x");
        System.out.println("dif: " + de11);
        System.out.println(" ");
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 4.0);
        double value = e1.evaluate(assignment);
        System.out.println("The result is: " + value);
        String s2 = e2.toString();
        System.out.println(s2);
        Expression de2 = e2.differentiate("x");
        System.out.println("dif: " + de2);
        System.out.println(" ");
        List<String> vars = e1.getVariables();
        for (String v : vars) {
            System.out.println(v);
        }
        Expression e3 = e1.assign("y", e2);
        String s3 = e3.toString();
        System.out.println(s3);
        Expression de3 = e3.differentiate("x");
        System.out.println("dif: " + de3);
        System.out.println(" ");
        Expression e4 = new Minus(new Var("x"), new Var("y"));
        Expression e5 = new Minus(new Num(1), new Num(2));
        String s4 = e4.toString();
        System.out.println(s4);
        Expression de4 = e4.differentiate("x");
        System.out.println("dif: " + de4);
        System.out.println(" ");
        String s5 = e5.toString();
        System.out.println(s5);
        Expression de5 = e5.differentiate("x");
        System.out.println("dif: " + de5);
        System.out.println(" ");
        Expression e6 = e2.assign("y", e5);
        String s6 = e6.toString();
        System.out.println(s6);
        Expression de6 = e6.differentiate("x");
        System.out.println("dif: " + de6);
        System.out.println(" ");
        Expression e7 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        String s7 = e7.toString();
        System.out.println(s7);
        Expression de7 = e7.differentiate("x");
        System.out.println("dif: " + de7);
        System.out.println(" ");
        Expression e8 = new Cos(new Var("x"));
        String s8 = e8.toString();
        System.out.println(s8);
        Expression de8 = e8.differentiate("x");
        System.out.println("dif: " + de8);
        System.out.println(" ");
        Map<String, Double> ass = new TreeMap<String, Double>();
        ass.put("x", 360.0);
        double val = e8.evaluate(ass);
        System.out.println("The result is: " + val);
        Expression e9 = new Sin(
                new Pow(
                        new Mult(
                                new Plus(
                                        new Mult(new Num(2), new Var("x")),
                                        new Var("y")),
                                new Num(4)),
                        new Var("x")));
        String s9 = e9.toString();
        System.out.println(s9);
        Expression de9 = e9.differentiate("x");
        System.out.println("dif: " + de9);
        System.out.println(" ");
        Expression e10 = new Neg(new Var("x"));
        String s10 = e10.toString();
        System.out.println(s10);
        Expression de10 = e10.differentiate("x");
        System.out.println("dif: " + de10);
        Expression e11 = new Pow(new Var("x"), new Num(4));
        Expression de = e11.differentiate("x");
        System.out.println(de);
        e11 = new Log(new Var("x"), new Var("x"));
        de = e11.differentiate("x");
        System.out.println(de);


        e11 = new Mult(new Plus(new Var("x"), new Num(2d)), new Num(2d));
        String s = e11.toString();
        System.out.println(s);
        de = e11.differentiate("x");
        System.out.println(de);
        //עידן
        e5 = new Mult(new Var("x"),new Mult(new Var("x"),new Mult(new Var("x"),new Var("x"))));
        de = e5.differentiate("x");
        System.out.println(de);

        // we expect to see 4*(x^3)
// but seeing: ((x ^ 4.0) * ((1.0 * (4.0 / x)) + (0.0 * log(e, x))))
/// is also fine, as it is equivalent (we will improve it in the next part).
        System.out.println("nivvvv");
        Expression e444 = new Pow(new Var("x"), new Num(4));
        System.out.println(e444);
        Expression de11111 = (e444.differentiate("x")).simplify();
        System.out.println(de11111 + "   !!!!");

        System.out.println("nivvvv");
        Expression n = new Log(new Var("x"), new Var("x"));
        Expression dn = (n.differentiate("x")).simplify();
        System.out.println(dn + "   !!!!");

    }
}