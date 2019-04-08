package diffSweep;

import data.FileData;
import lombok.Data;
import rungeKutt.RungeMethod;

@Data
public class DiffSweep {

    private FileData data;
    private RungeMethod rungeMethod;

    private double gammaOne;
    private double gammaTwo;
    private double gammaThree;

    private double alpaInA;
    private double betaInA;

    public DiffSweep(FileData data, RungeMethod rungeMethod) {
        this.data = data;
        this.rungeMethod = rungeMethod;
        setConfigData();
    }

    private void setConfigData(){
        gammaOne = 0;
        gammaTwo = 5;
    }

    private double p(double x){
        return 1;
    }

    private double q(double x){
        return 1;
    }

    private void sweep(){

    }

    public void diffSweep(){
        sweep();
    }
}
