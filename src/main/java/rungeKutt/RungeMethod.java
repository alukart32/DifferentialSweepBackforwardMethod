package rungeKutt;

import data.DataForMethod;
import filestream.FileWrite;
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
            // отрезок
            A,
            B,
            // кол-во узлов
            N;

    // true = B | A
    // false = A | B
    boolean direction;

    // решение основной задачи Коши с выводом в файл
    // или проежуточная задача
    boolean stage;

    // p, q определяемые в DiffSweep
    Func[] funcs;

    public RungeMethod() {}

    public RungeMethod(DataForMethod dataForMethod) {
        this.A = dataForMethod.getA();
        this.B = dataForMethod.getB();
        this.N = dataForMethod.getN();
        this.h = (B - A)/N;
        this.direction = dataForMethod.isDirection();
        this.stage = dataForMethod.isStage();

        this.funcs = dataForMethod.getFunc();

        if (direction)
            h = -h;

        this.x = stage? A: B;

    }

    public abstract void solve();

    protected abstract double formula( double x, double y, double h);
}