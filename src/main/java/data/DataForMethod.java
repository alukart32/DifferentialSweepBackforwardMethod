package data;

import funcs.Func;

@lombok.Data
public class DataForMethod {
    private double
            A, B,
            // коэф. граничных условий
            alpha, beta;
            // кол-во точек в сетке
    int     N;

    // false - слево |  направо
    // true - справо | налево
    boolean diffDirect = false;

    // alpha' = функция
    Func[] func;

    public DataForMethod(double a, double b, double alpha, double beta, int n, boolean diffDirect, Func[] func) {
        A = a;
        B = b;
        this.alpha = alpha;
        this.beta = beta;
        N = n;
        this.diffDirect = this.diffDirect;
        this.func = func;
    }
}
