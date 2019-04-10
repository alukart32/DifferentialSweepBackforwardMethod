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

    private double
                gammaEdgeCondOne,
                gammaEdgeCondTwo,
                gammaEdgeCondThree;

    private double
                alphaInA,
                betaInA;

    private double
                alpha,
                beta;

    private double
                y,
                yDiff;

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

        alpha = data.getAlpha();
        beta = data.getBeta();

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
     *  ay'(beta) + by(beta) = gammaEdgeCondTwo
     *  y(alpha) = gammaEdgeCondOne
     *
     *
     *  x in [ alpha,beta ]
     *
     *  alpha, beta, gamma >= 0
     **/

    /**
     * Решаем задачу Коши для переноса краевого решения из B в A
     *
     * коэф. из данной гран. условия
     * a1, b1, gamma1, a1^ + b1^2 > 0
     *
     * alpha'(x) = q(x) - alpha^2/p(x)
     * alpha(c) = -p(c)b1/a1
     *
     * beta'(x) = f(x) - alpha(x)beta(x)/p(x)
     * beta(c) = - p(c)gamma1/a1
     *
     *
     * решаем задачи Коши одновременно
     *
     * @return
     *          [alpha(A), beta(A)]
     */
    private double[] transferEdgeConditionAlpha(){
        int n = (int)data.getN();
        double[] alpha = new double[n+1];
        double[] beta = new double[n+1];

        DataForMethod dataForMethod = new DataForMethod(data.getA(), data.getB(),
                                                        data.getAlpha(), data.getBeta(), n,
                                                        false, funcs);
        RungeMethod method = new RungeKuttSubTaskOneAlpha(dataForMethod);
        alpha = ((RungeKuttSubTaskOneAlpha) method).solve();

        method = new RungeKuttSubTaskOneBeta(dataForMethod, gammaEdgeCondTwo);
        beta = ((RungeKuttSubTaskOneBeta) method).solve(alpha);

        double[] arr = {alpha[n], beta[n]};
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
        double[] coeff = null;
        /**
         * Необходимо в зависимости от alpha != 0 или beta != 0 сделать
         * перенос гран. условия (из точки B в A), решая задачу Коши (необходимо найти значение alpha(A), beta(A))
         *
         * Если и alpha!=0, и beta!=0, то выбираем любую задачу Коши для этого.
         */
        if(alpha !=0)
            coeff = transferEdgeConditionAlpha();
            else
                if(beta !=0)
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
        alphaInA = coeff[0];
        betaInA = coeff[1];
        // определитель системы
        double delta = p.func(alphaInA,0);

        if(delta == 0){
            // смотрим что к чему
        }
        // иначе единственное решение
        yDiff = (betaInA - gammaEdgeCondOne*alphaInA)/delta;
        y = (p.func(alphaInA,0))/delta;

        /**
         * Теперь построили задачу, которую и будем решать
         */

    }

    public void diffSweep(){
        sweep();
    }
}