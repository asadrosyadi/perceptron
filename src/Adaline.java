package Adaline;

import java.util.Scanner;
import java.text.DecimalFormat;
public class Adaline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double x1 []={0,1,2,2,4,-2,-1,-2,-1,1};
        double x2 []={4,4,3,2,1,4,3,2,1,-2};
        double t  []={1,1,1,1,1,-1,-1,-1,-1,-1};
        double y_in []={0,0,0,0,0,0,0,0,0,0};
        double y_out []={0,0,0,0,0,0,0,0,0,0};
        String kondisi []= {"","","","","","","","","",""};
        double w1 =0.2;
        double w2 =0.4;
        double wb =0.3;
        double alpa = 0;
        double teta = 0;
        boolean tanda = true;
        int epoch=1;
        DecimalFormat df = new DecimalFormat("#.##");
        
        double x1_uji []={2,1,3,2,-1,1,-2,-3,2,3};
        double x2_uji []={2,3,1,1,5,-1,3,3,-3,-4};
        double t_uji  []={1,1,1,1,1,-1,-1,-1,-1,-1};
        double y_in_uji []={0,0,0,0,0,0,0,0,0,0};
        double y_out_uji []={0,0,0,0,0,0,0,0,0,0};
        
       
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < x1.length; i++) {
            System.out.println("|\t"+x1[i]+"\t|\t"+x2[i]+"\t|\t"+t[i]+"\t|");
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nilai alpha = ");
        alpa = input.nextDouble();
        
        System.out.println("-----Data Proses-----");
        while(tanda){
            System.out.println("Epoch ke - "+epoch);
            System.out.println("|x1\t|x2\t|y_in\t|kondisi|");
            double w1s=0;
            double w2s=0;
            double wbs=0;
            for (int i = 0; i < y_in.length; i++) {
                y_in[i] = wb + (w1*x1[i]) + (w2*x2[i]);

                w1s = w1;
                w2s = w2;

                w1=w1+alpa*x1[i]*(t[i]-y_in[i]);
                w2=w2+alpa*x2[i]*(t[i]-y_in[i]);
                wb=wb+alpa*t[i];
                    
                if (((w1-w1s) > (w2-w2s) || (w1-w1s)<0 )||((w2-w2s) > (w1-w1s) || (w2-w2s)<0 )) {
                    kondisi[i]="Benar";
                }else{
                    kondisi[i]="Salah";
                }
                    
                System.out.println("|"+x1[i]+"\t|"+x2[i]+"\t|"+df.format(y_in[i])+"\t|"+kondisi[i]+"\t|");
            }
            double error=0;
            for (int i = 0; i < x1.length; i++) {
                error = error+Math.pow(t[i]-y_out[i], 2);
                //System.out.print(error);
            }
            System.out.println("Error nya adalah : "+ error / x1.length);
            
            if(kondisi[0].equals("Benar")&&kondisi[1].equals("Benar")&&kondisi[2].equals("Benar")&&kondisi[3].equals("Benar")&&kondisi[4].equals("Benar")&&kondisi[5].equals("Benar")&&kondisi[6].equals("Benar")&&kondisi[7].equals("Benar")&&kondisi[8].equals("Benar")&&kondisi[9].equals("Benar")) {
                tanda = false;
            }
            epoch=epoch+1;
            
        }
        System.out.print("Nilai bobot x1\t= "+w1);
        System.out.print("\tNilai bobot x2\t= "+w2);
        System.out.println("\tNilai bobot bias = "+wb);
        
        System.out.println("------Pengujian Bobot-------");
        System.out.println("Data Pelatihan :");
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        for (int i = 0; i < x1.length; i++) {
            System.out.println("|\t"+x1[i]+"\t|\t"+x2[i]+"\t|\t"+t[i]+"\t|");
        }
        System.out.println("Data Pengujian :");
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        for (int i = 0; i < x1.length; i++) {
            System.out.println("|\t"+x1_uji[i]+"\t|\t"+x2_uji[i]+"\t|\t"+t_uji[i]+"\t|");
        }
//      
        System.out.println("-----------------------------Hasil Pengujian-----------------------------");
        System.out.println("|x1\t|x2\t|w1\t|w2\t|wb\t|y_in\t|y_out\t|target\t|kondisi|");
            for (int i = 0; i < y_in_uji.length; i++) {
                y_in_uji[i] = wb + (w1*x1_uji[i]) + (w2*x2_uji[i]);
                if(y_in_uji[i]<0){
                    y_out_uji[i]=-1;
                }else{
                    y_out_uji[i]=1;
                }
                if (y_out_uji[i]!=t_uji[i]) {
//                    w1=w1+alpa*x1n_uji[i]*t[i];
//                    w2=w2+alpa*x2n_uji[i]*t[i];
//                    wb=wb+alpa*t_uji[i];
                    kondisi[i]="Salah";
                }else{
                    kondisi[i]="Benar";
                }
                System.out.println("|"+x1_uji[i]+"\t|"+x2_uji[i]+"\t|"+df.format(w1)+"\t|"+df.format(w2)+"\t|"+df.format(wb)+"\t|"+df.format(y_in_uji[i])+"\t|"+y_out_uji[i]+"\t|"+t_uji[i]+"\t|"+kondisi[i]+"\t|");
            }
    }

    public static double getMaxValue(double[] array) {
        double maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }
    public static double getMinValue(double[] array) {
        double minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }
}
