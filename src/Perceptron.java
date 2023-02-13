package nn3;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Perceptron {
    
    public static void main(String[] args) {
        double x1 []={0,1,2,2,4,-2,-1,-2,-1,1};
        double x2 []={4,4,3,2,1,4,3,2,1,-2};
        double x1n []={0,0,0,0,0,0,0,0,0,0};
        double x2n []={0,0,0,0,0,0,0,0,0,0};
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
        DecimalFormat df = new DecimalFormat("#.###");
        
        double x1_uji []={2,1,3,2,-1,1,-2,-3,2,3};
        double x2_uji []={2,3,1,1,5,-1,3,3,-3,-4};
        double x1n_uji []={0,0,0,0,0,0,0,0,0,0};
        double x2n_uji []={0,0,0,0,0,0,0,0,0,0};
        double t_uji  []={1,1,1,1,1,-1,-1,-1,-1,-1};
        double y_in_uji []={0,0,0,0,0,0,0,0,0,0};
        double y_out_uji []={0,0,0,0,0,0,0,0,0,0};
        
       
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < x1.length; i++) {
            System.out.println("|\t"+x1[i]+"\t|\t"+x2[i]+"\t|\t"+t[i]+"\t|");
        }
        Scanner input = new Scanner(System.in);
        System.out.print("nilai alpha = ");
        alpa = input.nextDouble();
        System.out.print("nilai teta = ");
        teta = input.nextDouble();
        System.out.println("----Normalisasi nilai input----");
        System.out.println("|x1\t|x2\t|x1Norm\t|x2Norm\t|");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < x1.length; i++) {
            double x1nor = 0;
            double x2nor = 0;
            x1nor = (x1[i]-getMinValue(x1))/(getMaxValue(x1)-getMinValue(x1));
            x2nor = (x2[i]-getMinValue(x2))/(getMaxValue(x2)-getMinValue(x2));
            System.out.println("|"+x1[i]+"\t|"+x2[i]+"\t|"+df.format(x1nor)+"\t|"+df.format(x2nor)+"\t|");
            x1n[i]=x1nor;
            x2n[i]=x2nor;
        }
        System.out.println("-----Proses Training-----");
        while(tanda){
            System.out.println("Nilai Epoch ke - "+epoch);
            System.out.println("|x1\t|x2\t|x1Norm\t|x2Norm\t|w1\t|w2\t|wb\t|y_in\t|y_out\t|target\t|kondisi\t|");
            for (int i = 0; i < y_in.length; i++) {
                y_in[i] = wb + (w1*x1n[i]) + (w2*x2n[i]);
                if(y_in[i]<teta){
                    y_out[i]=-1;
                }else{
                    y_out[i]=1;
                }
                if (y_out[i]!=t[i]) {
                    w1=w1+alpa*x1n[i]*t[i];
                    w2=w2+alpa*x2n[i]*t[i];
                    wb=wb+alpa*t[i];
                    kondisi[i]="BS";
                }else{
                    kondisi[i]="S";
                }
                System.out.println("|"+x1[i]+"\t|"+x2[i]+"\t|"+df.format(x1n[i])+"\t|"+df.format(x2n[i])+"\t|"+df.format(w1)+"\t|"+df.format(w2)+"\t|"+df.format(wb)+"\t|"+df.format(y_in[i])+"\t|"+y_out[i]+"\t|"+t[i]+"\t|"+kondisi[i]+"\t|");
            }
            double error=0;
            for (int i = 0; i < x1.length; i++) {
                error = error+Math.pow(t[i]-y_out[i], 2);
                //System.out.print(error);
            }
            System.out.println("Error adalah : "+ error / x1.length);
            
            if(kondisi[0].equals("S")&&kondisi[1].equals("S")&&kondisi[2].equals("S")&&kondisi[3].equals("S")&&kondisi[4].equals("S")&&kondisi[5].equals("S")&&kondisi[6].equals("S")&&kondisi[7].equals("S")&&kondisi[8].equals("S")&&kondisi[9].equals("S")) {
                tanda = false;
            }
            epoch=epoch+1;
            
        }
        System.out.println("Nilai bobot ideal adalah : ");
        System.out.println("w1 (bobot x1)\t= "+w1);
        System.out.println("w2 (bobot x2)\t= "+w2);
        System.out.println("w1 (bobot bias)\t= "+wb);
        
        System.out.println("Apakah lanjut ke pengujian bobot ? Y/T");
        String pilihan = "";
        pilihan = input.next();
        System.out.println("------Pengujian Bobot-------");
        System.out.println("Data Latih :");
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        for (int i = 0; i < x1.length; i++) {
            System.out.println("|\t"+x1[i]+"\t|\t"+x2[i]+"\t|\t"+t[i]+"\t|");
        }
        System.out.println("Data Uji :");
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        for (int i = 0; i < x1.length; i++) {
            System.out.println("|\t"+x1_uji[i]+"\t|\t"+x2_uji[i]+"\t|\t"+t_uji[i]+"\t|");
        }
//        System.out.println("Hasil Normalisasi :");
        for (int i = 0; i < x1_uji.length; i++) {
            double x1ujinor = 0;
            double x2ujinor = 0;
            x1ujinor = (x1_uji[i]-getMinValue(x1_uji))/(getMaxValue(x1_uji)-getMinValue(x1_uji));
            x2ujinor = (x2_uji[i]-getMinValue(x2_uji))/(getMaxValue(x2_uji)-getMinValue(x2_uji));
            //System.out.println("|"+x1_uji[i]+"\t|"+x2_uji[i]+"\t|"+df.format(x1ujinor)+"\t|"+df.format(x2ujinor)+"\t|");
            x1n_uji[i]=x1ujinor;
            x2n_uji[i]=x2ujinor;
        }
        System.out.println("Hasil Pengujian :");
        System.out.println("|x1\t|x2\t|x1Norm\t|x2Norm\t|w1\t|w2\t|wb\t|y_in\t|y_out\t|target\t|kondisi\t|");
        if (pilihan.equalsIgnoreCase("Y")) {
            for (int i = 0; i < y_in_uji.length; i++) {
                y_in_uji[i] = wb + (w1*x1n_uji[i]) + (w2*x2n_uji[i]);
                if(y_in_uji[i]<teta){
                    y_out_uji[i]=-1;
                }else{
                    y_out_uji[i]=1;
                }
                if (y_out_uji[i]!=t_uji[i]) {
//                    w1=w1+alpa*x1n_uji[i]*t[i];
//                    w2=w2+alpa*x2n_uji[i]*t[i];
//                    wb=wb+alpa*t_uji[i];
                    kondisi[i]="BS";
                }else{
                    kondisi[i]="S";
                }
                System.out.println("|"+x1_uji[i]+"\t|"+x2_uji[i]+"\t|"+df.format(x1n_uji[i])+"\t|"+df.format(x2n_uji[i])+"\t|"+df.format(w1)+"\t|"+df.format(w2)+"\t|"+df.format(wb)+"\t|"+df.format(y_in_uji[i])+"\t|"+y_out_uji[i]+"\t|"+t_uji[i]+"\t|"+kondisi[i]+"\t|");
            }
            
            double error=0;
            for (int i = 0; i < t_uji.length; i++) {
                error = error+Math.pow((t_uji[i]-y_out_uji[i]), 2);
                System.out.print(error);
            }
            System.out.println("Error nya adalah : "+ error / t_uji.length);
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
