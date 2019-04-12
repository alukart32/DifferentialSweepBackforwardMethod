package filestream;

import java.io.*;

public class FileWrite {
    String space;
    // pathname of output files
    private String filepath;

    public FileWrite(String filepath) {
        this.filepath = filepath;
    }

    public FileWrite(String space, String filepath) {
        this.space = space;
        this.filepath = filepath;
    }

    PrintWriter pw = new PrintWriter(System.out, true);

    public void write(double ...tmp){
        File fout = new File(filepath);
        try(PrintWriter file = new PrintWriter( new FileWriter(filepath, true))) {

            //String template = "%1$." + Integer.toString(precision) + "f";

            if (tmp.length == 3) {
                file.println();
                file.print("x: ");
                file.print(tmp[0]);
                file.print("| y: ");
                file.print(tmp[1]);
                file.print("| v: ");
                file.print(tmp[2]);


            } else if (tmp.length < 0 || tmp.length > 4) {
                file.print("Лесом");
            } else {
                file.println();
                file.print(tmp[0]);
                file.print("  |  ");

                file.print(tmp[1]);
                file.print("  |  ");

                file.print(tmp[2]);

                if (tmp.length == 4) {
                    file.print("  |  ");
                    file.print(tmp[3]);
                }
            }

            file.println();
            file.print("----------------------------------------------------------------------------------------------------");
        }catch (IOException exp){
            pw.println("Error!!!");
            pw.println("File writing handling problem!");
            exp.printStackTrace();
        }
    }

    public void write(double tmp){
        File fout = new File(filepath);
        try(BufferedWriter file = new BufferedWriter(new FileWriter(fout, true))){
            file.append(Double.toString(tmp));
            file.flush();
            file.newLine();
        }catch (IOException exp){
            pw.println("Error!!!");
            pw.println("File writing handling problem!");
            exp.printStackTrace();
        }
    }

    public void write(String str){
        File fout = new File(filepath);
        try(BufferedWriter file = new BufferedWriter(new FileWriter(fout, true))){
            file.append(str);
            file.flush();
            file.newLine();
        }catch (IOException exp){
            pw.println("Error!!!");
            pw.println("File writing handling problem!");
            exp.printStackTrace();
        }
    }

    public void cleanFile(){
        try(FileWriter fileWriter = new FileWriter(filepath)){
            fileWriter.write("");
        }catch (IOException exp){
            pw.println(exp.getStackTrace());
        }
    }

    public void changePath(String newPath){
        filepath = newPath;
    }

    public void setSpace(String space){
        this.space = space;
    }
}
