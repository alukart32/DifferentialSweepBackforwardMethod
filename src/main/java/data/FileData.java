package data;

import exception.ApiException;
import filestream.FileRead;
import lombok.Data;

@Data
public class FileData {
    private double
                    A, B,
                    // коэф. граничных условий
                    a, b,
                    // кол-во точек в сетке
                    N,
                    h;

    // false - слево |  направо
    // true - справо | налево
    boolean direction = false;

    public void setData(){
        FileRead fileRead = new FileRead();
        double[] arr = fileRead.readData();

        if(arr == null)
            throw new ApiException("Invalid input. No data!!!");

        A = arr[0];
        B = arr[1];
        a = arr[2];
        b = arr[3];

        if(Math.pow(a, 2) + Math.pow(b, 2) < 1)
            throw new ApiException("Invalid alpha or beta!!!");

        N = arr[4];

        h = Math.abs((B-A)/N);
    }
}
