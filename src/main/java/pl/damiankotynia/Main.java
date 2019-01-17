package pl.damiankotynia;

import org.mariuszgromada.math.mxparser.Function;

public class Main {

    public static void main(String[] args) {
        Function qwe = new Function("f(x, y) =  sin(x) +cos(y)");
        System.out.println(qwe.calculate(1, 2));

    }
}
