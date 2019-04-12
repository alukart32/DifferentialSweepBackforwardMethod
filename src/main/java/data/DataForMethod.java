package data;

import funcs.Func;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataForMethod {
    private double
            A, B,
            // коэф. граничных условий
            alpha, beta;
            // кол-во точек в сетке
    int     N;

    // false - слево |  направо
    // true - справо | налево
    boolean diffDirect;

    // alpha' = функция
    Func[] func;

    public DataForMethod(double a, double b, double alpha, double beta, int n, boolean diffDirect, Func[] func) {
        A = a;
        B = b;
        this.alpha = alpha;
        this.beta = beta;
        N = n;
        this.diffDirect = diffDirect;
        this.func = func;
    }
}
