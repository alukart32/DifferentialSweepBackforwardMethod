package rungeKutt;

import data.DataForMethod;

public class RungeKuttSubTaskOneBeta extends RungeMethod {
    double[] alphaX;

    double gamma;
    /**
     * Тип задачи Коши (alpha, beta); (phi, psi)
     */
    boolean type;

    public RungeKuttSubTaskOneBeta() {
    }

    public RungeKuttSubTaskOneBeta(DataForMethod dataForMethod, double gamma) {
        super(dataForMethod);
        // коэф. из граничного условия
        this.gamma = gamma;
        // начальное краевое условие
        y = - (gamma/alpha*funcs[0].func(B, 0));
    }

    public double[] solve(double[] alpha) {
        double[] betaX = new double[N+1];

        for (int i = 0; i <= N ; i++) {
            y = formula(x + i*h, y, alpha[i], h);
            betaX[i] = y;
        }
        return betaX;
    }

    /**
     * funcs[0] = p(x, yInA)
     * funcs[2] = f(x, yInA)
     */
    private double f(double x, double y, double alpha){
        return funcs[2].func(x,y) - (y*alpha)/funcs[0].func(x, y);
    }

    private double formula(double x, double y, double alpha, double h){
        double k1 = h * f(x, y, alpha);
        double k2 = h * f(x + 0.5 * h, y + 0.5 * k1, alpha);
        double result = y + k2;
        return result;
    }
}