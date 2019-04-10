package filestream.parser;

public class StringParser {
    private int getAmountOfElementOfStr(String str) {
        int count = 0;

        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == ' ')
                count++;
            i++;
        }
        return count;
    }

    public double[] getData(String str, boolean line)throws NumberFormatException{
        int amount = getAmountOfElementOfStr(str);
        double[] arr = new double[amount];

        StringBuffer tmp = new StringBuffer("");

        if(!line) {
            // A B alpha beta N
            if (amount != (5)) {
                throw new NumberFormatException();
            }

            int i = 0;
            int j = 0;
            while (i < str.length() && j < amount) {
                if (str.charAt(i) != ' ')
                    tmp.append(str.charAt(i));
                else {
                    arr[j] = Double.parseDouble(tmp.toString());
                    j++;
                    tmp.delete(0, tmp.length());
                }
                i++;
            }
        }
        else {
            String d = new String();

            int i = 0;
            int j = 0;
            while (i < str.length() && j < amount) {
                if (str.charAt(i) != '|')
                    tmp.append(str.charAt(i));
                else {
                    int k = 0;
                    while (tmp.charAt(k) != ':')
                        k++;

                    d = tmp.substring(k+2, tmp.length());

                    arr[j] = Double.parseDouble(d);
                    j++;
                    tmp.delete(0, tmp.length());
                }
                i++;
            }
        }
        return arr;
    }
}
