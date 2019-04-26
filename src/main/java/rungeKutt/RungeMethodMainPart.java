package rungeKutt;

import data.DataForMethod;
import filestream.FileWrite;
import funcs.Func;

public class RungeMethodMainPart extends RungeMethod {
    FileWrite fileWrite = new FileWrite("E:\\Programming\\Курс_3\\Numeric_Methods\\lab3\\src\\main\\resources\\output.txt");
    private double
                    // начальные условия
                    // v`
            v,
                    // y`
                    y;

    // производная p
    Func pDiff = (x,y) -> 1;


    public RungeMethodMainPart() {
    }

    public RungeMethodMainPart(DataForMethod dataForMethod, double v, double y) {
        super(dataForMethod);
        this.v = v;
        this.y = y;
        fileWrite.cleanFile();
    }

    private double f1(double x, double y, double v){
        return v;
    }

    private double f2(double x, double y, double v){
        double p = funcs[0].func(x, y);
        double pD = pDiff.func(x,y);
        double q = funcs[1].func(x, y);
        double f = funcs[2].func(x, y);
        return -(pD/p)*v + (q/p)*y + (f/p);
    }

    private double[] formula(double x, double y, double v, double h){
        double k1 = h * f1(x, y, v);
        double l1 = h * f2(x, y, v);
        double k2 = h * f1(x + 0.5 * h, y + 0.5 * k1, v + 0.5 * l1);
        double l2 = h * f2(x + 0.5 * h, y + 0.5 * k1, v + 0.5 * l1);

        double resultY = y + k2;
        double resultV = v + l2;

        double[] points = new double[]{0, resultY, resultV, 0, 0};
        return points;
    }

    public void solve(){
        // points[0] - x
        // points[1] - y
        // points[2] - v
        // points[3] - delta y
        // points[4] - delta v
        double[] points = new double[5];
        double yd = 0;
        double vd = 0;

        double xk = x;
        for (int i = 0; i < N+1 ; i++) {
            fileWrite.write(points);

            xk = x + i*h;
            points = formula(xk, y, v, h);

            points[0] = xk+h;
            v = points[2];
            y = points[1];

            yd = Math.abs(funcs[3].func((xk+h), y) - y);
            points[3] = yd;

            vd = Math.abs(funcs[4].func((xk+h), v) - v);
            points[4] = vd;
               /* yd = Math.abs(funcs[3].func((x + N*h), y) - y);
                points[3] = yd;

                vd = Math.abs(funcs[4].func((x + N*h), v) - v);
                points[4] = vd;*/

        }
    }
}
