package rungeKutt;

import data.DataForMethod;

public class RungeMethodPhiPsi extends RungeMethod {

    private double
            // y для phi
            yPhi,
            // y для psi
            yPsi,
            // gamma1;
            gamma;

    public RungeMethodPhiPsi() {
    }

    public RungeMethodPhiPsi(DataForMethod dataForMethod, double g) {
        super(dataForMethod);
        this.gamma = g;
        yPhi = - alpha/(beta*funcs[0].func(B, 0));
        yPsi =  (gamma/beta);
    }

    public double[] solve() {
        // phi(A), psi(A)
        double[] points = new double[2];

        for (int i = 0; i <= N ; i++) {
            yPhi = formulaPhi(x + i*h, yPhi, h);
            yPsi = formulaPsi(x + i*h, yPsi, yPhi, h);
        }

        points[0]= yPhi;
        points[1]= yPsi;

        return points;
    }

    /**
     * funcs[0] = p(x, yInA)
     * funcs[1] = q(x, yInA)
     */
    private double fPhi(double x, double y) {
        return 1/funcs[0].func(x, y) - funcs[1].func(x, y)*y*y;
    }

    private double formulaPhi(double x, double y, double h){
        double k1 = h * fPhi(x, y);
        double k2 = h * fPhi(x + 0.5 * h, y + 0.5 * k1);
        double result = y + k2;
        return result;
    }

    /**
     * funcs[0] = p(x, yInA)
     * funcs[2] = f(x, yInA)
     */
    private double fPsi(double x, double y, double phi){
        return funcs[2].func(x, y)*phi - phi*funcs[1].func(x, y)*y;
    }

    private double formulaPsi(double x, double y, double phi, double h){
        double k1 = h * fPsi(x, y, phi);
        double k2 = h * fPsi(x + 0.5 * h, y + 0.5 * k1, phi);
        double result = y + k2;
        return result;
    }
}

