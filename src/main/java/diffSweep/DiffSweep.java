package diffSweep;

import data.DataForMethod;
import data.FileData;
import funcs.Func;
import lombok.Data;
import rungeKutt.RungeKuttSubTaskOneAlpha;
import rungeKutt.RungeKuttSubTaskOneBeta;
import rungeKutt.RungeMethod;

@Data
public class DiffSweep {

    private FileData data;

    private double gammaEdgeCondOne;
    private double gammaEdgeCondTwo;
    private double gammaEdgeCondThree;

    private double alpaInA;
    private double betaInA;

    private double
                    a, b;
    /**
     * Функции в системе
      */
    private Func p;
    private Func q;
    private Func f;
    Func[] funcs;

    public DiffSweep(FileData data) {
        this.data = data;
        setConfigData();
    }

    private void setConfigData(){
        gammaEdgeCondOne = 0;
        gammaEdgeCondTwo = 5;

        a = data.getAlpha();
        b = data.getBeta();

        p = (x,y) -> x+1;
        q = (x,y) -> 1;
        f = (x,y) -> -x*x*x*x+16*x*x*x+12*x*x;

        funcs = new Func[3];
        funcs[0] = p;
        funcs[1] = q;
        funcs[2] = f;
    }

    /**
     *  Дифф. прогонка для самосопряж. задачи вида
     *
     *  (p(x)y')' - q(x)y = f(x)
     *
     *  -- гран. условия --
     *
     *  ay'(b) + by(b) = gammaEdgeCondTwo
     *  y(a) = gammaEdgeCondOne
     *
     *
     *  x in [ a,b ]
     *
     *  a, b, gamma >= 0
     **/

    /**
     * Решаем задачу Коши для переноса краевого решения из B в A
     *
     * коэф. из данной гран. условия
     * a1, b1, gamma1, a1^ + b1^2 > 0
     *
     * a'(x) = q(x) - a^2/p(x)
     * a(c) = -p(c)b1/a1
     *
     * b'(x) = f(x) - a(x)b(x)/p(x)
     * b(c) = - p(c)gamma1/a1
     *
     *
     * решаем задачи Коши одновременно
     *
     * @return
     *          [a(A), b(A)]
     */
    private double[] transferEdgeConditionAlpha(){
        int n = (int)data.getN();
        double[] alpha = new double[n];
        double[] beta = new double[n];

        DataForMethod dataForMethod = new DataForMethod(data.getA(), data.getB(),
                                                        data.getAlpha(), data.getBeta(), n,
                                                        false, funcs);
        RungeMethod method = new RungeKuttSubTaskOneAlpha(dataForMethod);
        alpha = ((RungeKuttSubTaskOneAlpha) method).solve();

        method = new RungeKuttSubTaskOneBeta(dataForMethod, gammaEdgeCondTwo);
        beta = ((RungeKuttSubTaskOneBeta) method).solve(alpha);

        double[] arr = {alpha[n-1], beta[n-1]};
        return arr;
    }

    /**
     * Решаем задачу Коши для переноса краевого решения из B в A
     *
     * @return
     *          [phi(A), psi(A)]
     */
    private double[] transferEdgeConditionBeta(){
        return null;
    }

    private void sweep(){
        // [0] = alpha(A), [1] = beta(A)
        double[] coeff;
        /**
         * Необходимо в зависимости от a != 0 или b != 0 сделать
         * перенос гран. условия (из точки B в A), решая задачу Коши (необходимо найти значение a(A), b(A))
         *
         * Если и a!=0, и b!=0, то выбираем любую задачу Коши для этого.
         */
        if(a!=0)
            coeff = transferEdgeConditionAlpha();
            else
                if(b!=0)
                   coeff = transferEdgeConditionBeta();

        /**
         * Необходимо понять а система СЛАУ
         *
         * p(A)y'(A)=alpha(A)y(A) + beta(A)
         * y(A) = gammaEdgeCondOne
         *
         * имеет ли решение?
         *
         * По правилу Краммера необходимо сделать (на листочке)
         *
         *  delta = определитель системы
         *
         * 1) delta != 0 -> единств. решение
         * 2) delta = 0 и deltaY != 0, deltaYOne != 0 -> нет решения
         * 3) delta = 0 и deltaY == deltaYOne == 0 -> 00 мн-во решений
         *
         */



        /**
         * Теперь построили задачу, которую и будем решать
         */

    }

    public void diffSweep(){
        sweep();
    }
}