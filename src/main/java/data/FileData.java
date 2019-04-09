package data;

import exception.ApiException;
import filestream.FileRead;
import lombok.Data;

@Data
public class FileData {
    private double
                    A, B,
                    // коэф. граничных условий
                    alpha, beta,
                    // кол-во точек в сетке
                    N,
                    h;

    // false - слево |  направо
    // true - справо | налево
    boolean direction = false;

    public void setData(){
        FileRead fileRead = new FileRead("E:\\Programming\\Курс_3\\Numeric_Methods\\lab3\\src\\main\\resources\\input.txt");
        double[] arr = fileRead.readData();

        if(arr == null)
            throw new ApiException("Invalid input. No data!!!");

        A = arr[0];
        B = arr[1];
        alpha = arr[2];
        beta = arr[3];

        if(Math.pow(alpha, 2) + Math.pow(beta, 2) < 1)
            throw new ApiException("Invalid alpha or beta!!!");

        N = arr[4];

        h = Math.abs((B-A)/N);
    }
}
