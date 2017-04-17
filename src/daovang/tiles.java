/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daovang;

/**
 *
 * @author ceol1
 */
class tiles {

    int safe = 0;//check an toàn
    int unsafe = 0;// check không an toàn
    int wump = 0;// quái
    int pit = 0;// hố
    int gold = 0;// vàng
    int doubt_pit = 0;//nghi ngờ là hố
    int doubt_wump = 0;//nghi ngờ là quái vật
    String env;// ký tự trong ô hiện tại của ma trận w 
    int num = 0;
    int br = 0;// cờ check tường bên phải
    int bl = 0;// cờ check tường bên trái
    int bu = 0;// cờ check tường bên trên
    int bd = 0;// cờ check tường bên dưới
    int visited = 0;// đã ghé thăm chưa
    int l, r, u, d;// trái, phải, trên , dưới check xêm các vị trí liền kề này của ô hiện tại đã ghé thăm chưa
    String back = "";
    
    /**
     * 
     * @param s là ký tự chứa trong ô
     * @param v là số ô hiện tại
     */
    tiles(String s, int v) {
        env = s;
        num = v;
        l = r = u = d = 0;
        if (v == 9 || v == 5) {// 1<chia cho n dư 1 <n*(n-1 )
            bl = 1;
        }

        if (v == 8 || v == 12) {// n*i với i chạy từ 2 đến (n-1) 
            br = 1;
        }

        if (v == 1) {// ==1
            bu = 1;
            bl = 1;
        }
        if (v == 13) {// n*(n-1)+1
            bd = 1;
            bl = 1;
        }
        if (v == 4) {//n
            bu = 1;
            br = 1;
        }
        if (v == 16) {//n*n
            bd = 1;
            br = 1;
        }

    }
    /**
     *  * "B" là ô cận hố
        * "S" là ô cận quái
        * "P" là ô chứa hố
        * "G" là ô chứa vàng
        * "W" là ô chứa quái
     * @return 
     */
    int sense() {
        if (env.contains("B")) {
            return 1;
        } else if (env.contains("S")) {
            return 2;
        } else if (env.contains("G")) {
            return 3;
        }
        if (env.contains("W")) {
            return 4;
        } else {
            return 0;
        }
    }

}
