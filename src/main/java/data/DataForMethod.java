package data;

import funcs.Func;

@lombok.Data
public class DataForMethod {
    private double
            A, B,
            // коэф. граничных условий
            alpha, beta,
            // кол-во точек в сетке
            N;

    // false - слево |  направо
    // true - справо | налево
    boolean direction = false;

    // решение основной задачи Коши с выводом в файл
    // или проежуточная задача
    boolean stage;

    // alpha' = функция
    Func[] func;

    public DataForMethod(double A, double B, double alpha, double beta, double n, boolean direction, boolean stage, Func[] func) {
        A = A;
        B = B;
        this.alpha = alpha;
        this.beta = beta;
        N = n;
        this.direction = direction;
        this.stage = stage;
        this.func = func;
    }
}
