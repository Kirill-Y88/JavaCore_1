package Lesson2;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;

public class MainLesson2 {


    public static void main(String[] args) {
        String string = "10 3 1 \n2 3 2 2\n5 6 7 1\n300 3 1 0";

        try {
            System.out.println("Половина суммы чисел массива = " + arrayMethod(string));
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            System.out.println(" В введенной матрице присутствует нечисловой символ");
        } catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
            //System.out.println(e.toString());
            System.out.println(" размер матрицы не квадратный");
        }

        //не понял как обработать ситуацию с одновременно двумя сработавшими исключениями.
        /*
        try {

            try {
                System.out.println("Половина суммы чисел массива = " + arrayMethod(string));
            }
            catch (NumberFormatException e){
                System.out.println(" В введенной матрице присутствует нечисловой символ");
            }

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(" размер матрицы не квадратный");
        }

*/
    }

    public static int arrayMethod(String s) throws NumberMistake, ArrayMistake{
        int halfSumm = 0;
        String [] string0 = s.split("\n");
        String [] arrstring;
        String [][] str = new String[string0.length][string0.length];
        for (int i = 0; i < string0.length; i++){
            arrstring  = string0[i].split(" ");
            for (int j = 0; j < string0.length; j++){
                str[i][j] = arrstring[j];
                halfSumm += Integer.parseInt(str[i][j]);
            }
        }
        return halfSumm / 2;
    }
}

class ArrayMistake extends ArrayIndexOutOfBoundsException {
}

class NumberMistake extends NumberFormatException {
}
