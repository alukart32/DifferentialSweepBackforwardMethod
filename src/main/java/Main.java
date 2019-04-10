import data.FileData;
import diffSweep.DiffSweep;

public class Main {
    public static void main(String ...args){
        FileData data = new FileData();
        DiffSweep diffSweep = new DiffSweep(data);
        diffSweep.diffSweep();
    }
}
