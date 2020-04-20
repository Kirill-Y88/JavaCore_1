package Lesson5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static  float[] arr = new float[size];
    static float[] a1 = new float[h];
    static float[] a2 = new float[h];

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        method_1(now);
        now = System.currentTimeMillis();
        method_2(now);
    }

    private static void method_1(long time) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        System.out.println("Метод 1 .Время заполнения массива единицами=" + ( System.currentTimeMillis() - time));
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Метод 1 .Время заполнения массива результатом выражения=" + ( System.currentTimeMillis() - time));
        }

    private static void method_2(long time) {
        MyThread thr1;
        MyThread thr2;
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        System.out.println("Метод 2 .Время заполнения массива единицами=" + ( System.currentTimeMillis() - time));
        System.arraycopy(arr,0, a1, 0, h);
        System.arraycopy(arr,h, a2, 0, h);
        System.out.println("Метод 2 .Время разбивки на 2 массива=" + ( System.currentTimeMillis() - time));

        thr1 = new MyThread(1);
        thr1.start();
        thr2 = new MyThread(2);
        thr2.start();

try{
    thr1.join();
    System.out.println("Метод 2 .Время просчета 1 массива массива=" + ( System.currentTimeMillis() - time));
    thr2.join();
    System.out.println("Метод 2 .Время просчета 2 массива массива=" + ( System.currentTimeMillis() - time));
} catch (InterruptedException e) {
    System.out.println(" прерывание основного потока ");
}
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Метод 2 .Время склейки массива результатом выражения=" + ( System.currentTimeMillis() - time));
        }

     static void arr_a1(){
        for (int i = 0; i <h ; i++) {
            a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
     static void arr_a2(){
        for (int i = 0; i <h ; i++) {
            a2[i] = (float)(a2[i] * Math.sin(0.2f + (h+i) / 5) * Math.cos(0.2f + (h+i) / 5) * Math.cos(0.4f + (h+i) / 2));
        }
    }

}

class MyThread extends Thread{
    int k;
    public MyThread(int k) {
        this.k = k;
    }
    @Override
    public void run() {
       if(k ==1)  Main.arr_a1();
       if(k ==2)  Main.arr_a2();
        System.out.println("ран Вася ран");
    }
}


