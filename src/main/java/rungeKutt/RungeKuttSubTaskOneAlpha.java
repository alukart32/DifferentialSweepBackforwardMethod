package rungeKutt;

import data.DataForMethod;
import filestream.FileWrite;
import funcs.Func;

public class RungeKuttSubTaskOneAlpha extends RungeMethod {

    FileWrite fileWrite = new FileWrite("E:\\Programming\\Курс_3\\Numeric_Methods\\lab3\\src\\main\\resources\\alpha.txt");

    /**
     * Тип задачи Коши (alpha, beta); (phi, psi)
     */
    boolean type;

    public RungeKuttSubTaskOneAlpha() {
    }

    public RungeKuttSubTaskOneAlpha(DataForMethod dataForMethod, boolean type) {
        super(dataForMethod);
    }

    @Override
    public void solve() {

    }

    /**
     * funcs[0] = p(x, y)
     * funcs[1] = q(x, y)
     */
    private double f(double x, double y){
        return funcs[1].func(x,y) - (y*y)/funcs[0].func(x, y);
    }

    @Override
    protected double formula(double x, double y, double h) {
        double k1 = h * f(x, y);
        double k2 = h * f(x + 0.5 * h, y + 0.5 * k1);
        double result = y + k2;
        return result;
    }
}
