package filestream;

import filestream.parser.StringParser;

import java.io.*;

public class FileRead {
    // pathname of input files
    private String filepath;
    private boolean line;
    private PrintWriter pw = new PrintWriter(System.out, true);

    public FileRead() {
    }

    public FileRead(String filepath) {
        this.filepath = filepath;
        this.line = false;
    }

    public FileRead(String filepath, boolean line) {
        this.filepath = filepath;
        this.line = line;
    }

    public double[] readData(){
        double[] data = null;

        File file = new File(filepath);
        try(BufferedReader fin = new BufferedReader(
                new FileReader(file))
        ){
            // temp string to hold all data from file to parse into double
            StringBuffer strData = new StringBuffer("");
            // reading values from file and save them in alpha string
            String str = fin.readLine();
            while (str != null){
                strData.append(str);
                strData.append(' ');
                str = fin.readLine();
            }
            // string parsing to get these values
            StringParser stringParser = new StringParser();
            data = stringParser.getData(new String(strData), line);
            return data;
        }catch(IOException e){
            pw.println("File reading handling error!");
            e.printStackTrace();
        }
        return null;
    }
}