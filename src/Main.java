import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
public class Main {
    public static void main(String[] args) {
//        Expression bus = new Minus(new Pow(new Var("x"),new Var("x")),new Num(2));
//        Expression dbus = bus.differentiate("x").simplify();
//        System.out.println(dbus);
//
//        Expression bus1 = new Pow(new Pow(new Var("x"),new Var("x")),new Num(2));
//        Expression dbus1 = bus1.differentiate("x").simplify();
//        System.out.println(dbus1);
//
//
//        Expression angry2 = new Div(new Plus(new Pow(new Var("x"), new Num(2)),
//                new Var("x")), new Var("x"));
//        Expression angryDriver2 = angry2.differentiate("x").simplify();
//        System.out.println(angryDriver2 + " niv");
//
//        Expression angry3 = new Pow(new Plus(new Pow(new Var("x"), new Num(2)), new Var("x")), new Var("x"));
//        Expression angryDriver3 = angry3.differentiate("x").simplify();
//        System.out.println(angryDriver3 + " niv");
//
//        Expression angry4 = new Log(new Var("x"),new Var("x"));
//        Expression angryDriver4 = angry4.simplify();
//        System.out.println(angryDriver4 + " niv");

        int sum=0;
        for (int i=0;i<5;i++){
            sum+=(i++);
        }
        System.out.println(sum);
    }
}