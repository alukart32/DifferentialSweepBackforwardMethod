package rungeKutt;

import data.DataForMethod;
import funcs.Func;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RungeMethod {

    double
            x,
            y,
            // след. шаг
            h,
            // коэф. в изначальном гран. условием
            alpha,
            beta,
            // отрезок
            A,
            B;
            // кол-во узлов
    int     N;

    // true = B -> A   h = -h
    // false = A -> B  h = h
    boolean diffDirect;

    // p, q определяемые в DiffSweep
    Func[] funcs;

    public RungeMethod() {}

    public RungeMethod(DataForMethod dataForMethod) {
        this.A = dataForMethod.getA();
        this.B = dataForMethod.getB();
        this.N = dataForMethod.getN();
        this.alpha = dataForMethod.getAlpha();
        this.beta = dataForMethod.getBeta();

        this.funcs = dataForMethod.getFunc();

        this.h = diffDirect ?(B - A)/N:-(B - A)/N;
        this.x = diffDirect ? A : B;
    }

    protected double f(double x, double y){
        return 1;
    }

    protected  double formula(double x, double y, double h){return 1;}
}