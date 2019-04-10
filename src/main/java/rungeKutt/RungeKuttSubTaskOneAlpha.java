package rungeKutt;

import data.DataForMethod;

public class RungeKuttSubTaskOneAlpha extends RungeMethod {
    public RungeKuttSubTaskOneAlpha() {}

    public RungeKuttSubTaskOneAlpha(DataForMethod dataForMethod) {
        super(dataForMethod);
        // граничное условие в точке c (B)
        y = - (beta/alpha*funcs[0].func(B, 0));
    }

    public double[] solve() {
        double[] arr = new double[N+1];

        for (int i = 0; i <= N ; i++) {
            y = formula(x + i*h, y, h);
            arr[i] = y;
        }

        return arr;
    }

    /**
     * funcs[0] = p(x, y)
     * funcs[1] = q(x, y)
     */
    private double f(double x, double y){
        return funcs[1].func(x,y) - (y*y)/funcs[0].func(x, y);
    }

    private double formula(double x, double y, double h){
        double k1 = h * f(x, y);
        double k2 = h * f(x + 0.5 * h, y + 0.5 * k1);
        double result = y + k2;
        return result;
    }
}
