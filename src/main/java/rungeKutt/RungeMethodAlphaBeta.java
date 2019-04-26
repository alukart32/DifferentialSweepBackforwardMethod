package rungeKutt;

import data.DataForMethod;

public class RungeMethodAlphaBeta extends RungeMethod {

    private double
                    // y для alpha
                    yAlpha,
                    // y для beta
                    yBeta,
                    // gamma 1;
                    gamma;

    public RungeMethodAlphaBeta() {}

    public RungeMethodAlphaBeta(DataForMethod dataForMethod, double g) {
        super(dataForMethod);
        this.gamma = g;
        this.yAlpha = -((beta*funcs[0].func(B, 0))/alpha);
        this.yBeta =  ((gamma*funcs[0].func(B, 0)/alpha));
    }

    public double[] solve() {
        // alpha(A), beta(A)
        double[] points = new double[2];
        double xk;

        double[] beta = new double[N+1];
        beta[0] = yBeta;

        double[] alpha = new double[N+1];
        alpha[0] = yAlpha;

        xk = x - h;
        for (int i = 0; i < N ; i++) {
            xk += h;
            // в точке x + h
            alpha[i+1] = formulaAlpha(xk, alpha[i], h);
            beta[i+1] = formulaBeta(xk, beta[i], alpha[i], h);
        }

        points[0]= alpha[N];
        points[1]= beta[N];

        return points;
    }

    /**
     * funcs[0] = p(x, yInA)
     * funcs[1] = q(x, yInA)
     */
    private double fA(double x, double y){
        return funcs[1].func(x,y) - ((y*y)/funcs[0].func(x, y));
    }

    private double formulaAlpha(double x, double y, double h){
        double k1 = h * fA(x, y);
        double k2 = h * fA(x + 0.5 * h, y + 0.5 * k1);
        double result = y + k2;
        return result;
    }

    /**
     * funcs[0] = p(x, yInA)
     * funcs[2] = f(x, yInA)
     */
    private double fB(double x, double y, double alpha){
        return funcs[2].func(x,y) - ((y*alpha)/funcs[0].func(x, y));
    }

    private double formulaBeta(double x, double y, double alpha, double h){
        double k1 = h * fB(x, y, alpha);
        double k2 = h * fB(x + 0.5 * h, y + 0.5 * k1, alpha);
        double result = y + k2;
        return result;
    }
}
