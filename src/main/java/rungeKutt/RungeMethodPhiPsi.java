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
        yPsi = - (gamma/beta);
    }

    public double[] solve() {
        // phi(A), psi(A)
        double[] points = new double[2];

        double[] phi = new double[N+1];
        phi[0] = yPhi;

        double[] psi = new double[N+1];
        psi[0] = yPsi;

        double xk = x - h;
        for (int i = 0; i < N ; i++) {
            xk+=h;
            phi[i+1] = formulaPhi(xk, phi[i], h);
            psi[i+1] = formulaPsi(xk, psi[i], phi[i], h);
        }

        points[0]= phi[N];
        points[1]= psi[N];

        return points;
    }

    /**
     * funcs[0] = p(x, yInA)
     * funcs[1] = q(x, yInA)
     */
    private double fPhi(double x, double y) {
        return (1.0/funcs[0].func(x, y)) - (y*y)*funcs[1].func(x, y);
    }

    private double formulaPhi(double x, double y, double h){
        double k1 = h * fPhi(x, y);
        double k2 = h * fPhi(x + 0.5 * h, y + 0.5 * k1);
        double result = y + k2;
        return result;
    }

    /**
     * funcs[0] = p(x, yInA)
     * funcs[1] = q(x, yInA)
     * funcs[2] = f(x, yInA)
     */
    private double fPsi(double x, double y, double phi){
        return phi*funcs[2].func(x, y) - phi*y*funcs[1].func(x, y);
    }

    private double formulaPsi(double x, double y, double phi, double h){
        double k1 = h * fPsi(x, y, phi);
        double k2 = h * fPsi(x + 0.5 * h, y + 0.5 * k1, phi);
        double result = y + k2;
        return result;
    }
}

