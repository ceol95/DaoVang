/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daovang;

import java.util.Scanner;

/**
 *
 * @author ceol1
 */
/**
 * 
 * W là mô hình bản đồ được dựng
 * "B" là ô cận hố
 * "S" là ô cận quái
 * "P" là ô chứa hố
 * "G" là ô chứa vàng
 * "W" là ô chứa quái
 */
class Environment {

    Scanner scr = new Scanner(System.in);
    //char w[][]=new char[5][5];
    int np;     //Số hố
    int wp, gp; // vị trí quái,vị trí vàng
    int pos[]; // vị trí hố
    int b_pos[] = new int[20];
    int s_pos[] = new int[20];
    int n = 4 ; // cấp ma trận ~ số ô 1 chiều
    int width = n+1;

//Khoi tao bien
    void accept(String w[][]) {
        for (int i = 0; i < 20; ++i) {
            b_pos[i] = -1;
            s_pos[i] = -1;
        }

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                w[i][j] = "";
            }
        }
        /**
          * vẽ bản đồ màn hinh
          */
        int count = 1;
        System.out.println("\n\n********* Wumpus World Problem *********\n-by Aditya Mandhare.\n");

        System.out.println("The positions are as follows.");
        for (int i = 1; i <= n; ++i) {
            System.out.println("\n-----------------------------------------------------------------");
            System.out.print("|\t");
            for (int j = 1; j <= n; ++j) {
                System.out.print((count++) + "\t|\t");
            }
        }
        System.out.println("\n-----------------------------------------------------------------");
        /**
         * Nhập liệu
         */
        System.out.println("\nAgent start position: 13");
        w[n][1] = "A";
        System.out.println("\nEnter the number of pits.");
        np = scr.nextInt();// Nhập số hố
        pos = new int[np];// Khởi tạo mảng vị trí hố
        System.out.println("Positions of pit, gold and wumpus should not overlap.");
        System.out.println("Enter the position of pits.");
        for (int i = 0; i < np; ++i) {//tạo mảng vị trí hố
            pos[i] = scr.nextInt();
            show_sense(pos[i], 1, w);//đưa các dấu hiệu ở ô liền kề vào ma trận w
        }
        System.out.println("Enter the position of wumpus.");
        wp = scr.nextInt();// nhập vị trí của quái
        show_sense(wp, 2, w);////đưa các dấu hiệu ở ô liền kề vào ma trận w

        System.out.println("Enter the position of gold.");
        gp = scr.nextInt();//nhập vị trí của vàng

        insert(w);// đưa vị trí quái, hố, vàng vào ma trận w
    }
//Khoi tao ma tran
    void insert(String w[][]) {
        int temp = 0;
        int count = 0;
        //flag1 cho biết đã tính ô vàng chưa
        //flag2 cho biết đã thêm ô quái chưa
        int flag1 = 0, flag2 = 0;
        
        for (int i = 0; i < np; ++i) {
            temp = pos[i];//temp bằng số ô chứa hố
            count = 0;// biến đếm số ô hiện tại
            for (int j = 1; j <= n; ++j) {
                for (int k = 1; k <= n; ++k) {
                    ++count;
                    if (count == temp) {
                        w[j][k] += "P";// hố
                    } else if (count == gp && flag1 == 0) {
                        w[j][k] += "G";//vàng
                        flag1 = 1;
                    } else if (count == wp && flag2 == 0) {
                        w[j][k] += "W";//quái
                        flag2 = 1;
                    }
                }
            }
        }

        display(w);
    }
    /**
     * Đưa vị trí đối tượng vào trong mảng
     * @param a vị trí đối tượng
     * @param b loại đối tượng 1 là hố 2 là quái
     * @param w mảng ma trận
     */
    void show_sense(int a, int b, String w[][]) {
        int t1, t2, t3, t4;
        t1 = a - 1;//ô trái liền kề
        t2 = a + 1;//ô phải liền kề
        t3 = a + n;//ô dưới liền kề
        t4 = a - n;//ô trên liền kề

        if (a == 5 || a == 9) {
            t1 = 0;
        }
        if (a == 8 || a == 12) {
            t2 = 0;
        }
        if (a == 4) {
            t2 = 0;
        }
        if (a == 13) {
            t1 = 0;
        }

        if (t3 > 16) {
            t3 = 0;
        }
        if (t4 < 0) {
            t4 = 0;
        }

//int temp[]=new int[4];
        if (b == 1) {//hố
            b_pos[0] = t1;
            b_pos[1] = t2;
            b_pos[2] = t3;
            b_pos[3] = t4;
        } else if (b == 2) {//quái
            s_pos[0] = t1;
            s_pos[1] = t2;
            s_pos[2] = t3;
            s_pos[3] = t4;
        }

        int temp1, count;

        for (int i = 0; i < 4; ++i) {
            if (b == 1) {
                temp1 = b_pos[i];
            } else {
                temp1 = s_pos[i];
            }
            count = 0;
            for (int j = 1; j <= n; ++j) {
                for (int k = 1; k <= n; ++k) {
                    ++count;
                    if (count == temp1 && b == 1 && !w[j][k].contains("B")) {
                        w[j][k] += "B";// cận hố
                    } else if (count == temp1 && b == 2 && !w[j][k].contains("S")) {
                        w[j][k] += "S";// cận quái
                    }
                }
            }
        }

//display(w);
    }
/**
 * 
 * @param w 
 * Hiển thị lại ma trận w khi đã thêm xong các đối tượng bản đồ
 */
    void display(String w[][]) {
        System.out.println("\nThe environment for problem is as follows.\n");
        for (int i = 1; i <= n; ++i) {
            System.out.println("\n-----------------------------------------------------------------");
            System.out.print("|\t");
            for (int j = 1; j <= n; ++j) {
                System.out.print(w[i][j] + "\t|\t");
            }
        }
        System.out.println("\n-----------------------------------------------------------------");
    }

}
