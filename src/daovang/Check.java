/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daovang;

import static daovang.wumpus.check;
import static daovang.wumpus.checkDoubt;
import static daovang.wumpus.complete;
import java.util.Scanner;

/**
 *
 * @author ceol1
 */
class Check {

    static int scream = 0;
    static int score = 0;
    static int complete = 0;
    static int n = 4;

    static boolean check(tiles t) {
        int temp = t.sense();
        if (temp == 1 || temp == 2) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param pos vị trí cần kiểm tra
     * @return kết quả nghi ngờ
     */
    static boolean checkDoubt(tiles t) {
        return (t.doubt_pit < 1 && t.doubt_wump < 1);
    }

    /**
     * @param pos vị trí cần kiểm tra
     * @return kết quả có phải hố hay quái
     */
    static boolean checkWP(tiles t) {
        return (t.pit != 1 && t.wump != 1);
    }

    /**
     * kiểm tra để tạo bước đi tiếp
     */
    static boolean checkPos(tiles t, int temp) {
        //buoc tu trai sang
        if (temp == 1) {
            return !(t.back.contains("r")
                    && (t.l != 1 || t.u != 1 || t.d != 1)
                    && check(t));
            //buoc tu phai sang
        } else if (temp == 2) {
            return !(t.back.contains("l")
                    && (t.r != 1 || t.u != 1 || t.d != 1)
                    && check(t));
            //buoc tu duoi len
        } else if (temp == 3) {
            return !(t.back.contains("u")
                    && (t.l != 1 || t.r != 1 || t.d != 1)
                    && check(t));
        }
        return true;
    }
    /**
     * 
     * @param t ô hiện tại
     * @return đã thấy vàng chưa
     */
    static boolean ThayVang(tiles t) {
        if (t.env.contains("G")) {
            complete = 1;
            System.out.println("Gold Found!!");
            return true;
        }
        return false;
    }

    /**
     * 
     * @param t mảng đường đi
     * @param pos vị trí hiện tại
     * kiểm tra cận hố
     */
    static void ConditionB(tiles t[], int pos) {
        if (t[pos].br != 1 && t[pos + 1].safe != 1) {
            t[pos + 1].doubt_pit += 1;
        }
        if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
            t[pos - 4].doubt_pit += 1;
        }
        if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
            t[pos - 1].doubt_pit += 1;
        }
        if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
            t[pos + 4].doubt_pit += 1;
        }

    }

    /**
     * 
     * @param t mảng đường đi
     * @param pos vị trí hiện tại
     * kiểm tra cận quái các ô lân cận
     */
    static void ConditionS(tiles t[], int pos) {
        if (t[pos].br != 1 && t[pos + 1].safe != 1) {
            t[pos + 1].doubt_wump += 1;
        }
        if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
            t[pos - 4].doubt_wump += 1;
        }
        if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
            t[pos - 1].doubt_wump += 1;
        }
        if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
            t[pos + 4].doubt_wump += 1;
        }
    }

    /**
     * 
     * @param t
     * @param pos vị trí ô sau khi bước lên
     * @return kiểm tra đã hoàn thành tìm vàng chưa
     */
    static boolean Condition(tiles t[], int pos) {

        int condition = t[pos].sense();// điều kiện bằng 
        if (condition == 3) {// chứa vàng
            complete = 1;// hoàng thành
            return true;
        } else if (condition == 1 && t[pos].visited == 0) {// điều kiện bằng ô cận hố và chưa đi vào
            ConditionB(t, pos);
            t[pos].safe = 1;
        } else if (condition == 2 && t[pos].visited == 0) {
            ConditionS(t, pos);
            t[pos].safe = 1;
        } else if (condition == 0) {
            t[pos].safe = 1;
        }
        t[pos].visited = 1;
        return false;
    }

    public static void main(String args[]) {
        Scanner scr = new Scanner(System.in);
        Environment e = new Environment();
        String w[][] = new String[5][5];
        e.accept(w);
        System.out.println("\n\nFinding the solution...");

        tiles t[] = new tiles[17];//ma trận bản đồ
        int c = 1;
        out://Chuẩn bị bản đồ
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j<=4; ++j) {
                if (c > 16) {
                    break out;
                }
                t[c] = new tiles(w[i][j], c);
                ++c;
            }
        }

        t[13].safe = 1;//trạng thái ô 13 
        t[13].visited = 1;//ô 13 đã được đến

        int pos = 13;
        int condition;
        int limit = 0;
        String temp1, temp2;
        do {
            ++limit;
            condition = -1;
            //Hàm check xem đã tìm thấy vàng chưa
            if (ThayVang(t[pos])) {
                break;
            }
            /*thứ tự ưu tiên đi là sang phải, trái,trên,dưới
            * Nếu xung quanh đều không thỏa mãn điều kiện là chưa đi qua, không nghi ngờ gì cả
            *
            */
            if (t[pos].br != 1 && t[pos].r != 1
                    && checkDoubt(t[pos + 1]) && checkWP(t[pos + 1])
                    && checkPos(t[pos], 1)) {
                temp1 = "l";
                t[pos].r = 1;
                ++pos;
                System.out.println("\nfront pos=" + pos);
                ++score;
                t[pos].back += temp1;

                if (Condition(t, pos)) {
                    break;
                }
            } else if (t[pos].bl != 1 && t[pos].l != 1
                    && checkDoubt(t[pos - 1]) && checkWP(t[pos - 1])
                    && checkPos(t[pos], 2)) {
                temp1 = "r";
                t[pos].l = 1;
                pos = pos - 1;
                System.out.println("\nback pos= " + pos);
                ++score;
                t[pos].back += temp1;

                if (Condition(t, pos)) {
                    break;
                }
            } else if (t[pos].bu != 1 && t[pos].u != 1 && (pos - 4) >= 1
                    && checkDoubt(t[pos - 4]) && checkWP(t[pos - 4])
                    && checkPos(t[pos], 3)) {

                temp1 = "d";

                t[pos].u = 1;
                pos = pos - 4;
                System.out.println("\nUp pos= " + pos);
                ++score;

                t[pos].back += temp1;

                if (Condition(t, pos)) {
                    break;
                }
            } else if (t[pos].bd != 1 && t[pos].d != 1 && (pos + 4) <= 16
                    && checkDoubt(t[pos + 4]) && checkWP(t[pos + 4])) {

                temp1 = "u";

                t[pos].d = 1;
                pos = pos + 4;
                System.out.println("\ndown pos= " + pos);
                ++score;

                t[pos].back += temp1;

                condition = t[pos].sense();

                if (Condition(t, pos)) {
                    break;
                }
                //buoc di lòng vòng quá 50 lần tính bằng limit
            } else if (limit > 50) {
                int temp3 = pos;//temp3 bằng vị trí hiện tại
                int flag_1 = 0, flag2 = 0, flag3 = 0, flag4 = 0;
                //cờ xem ô cuối cùng theo bên phải, trái, trên, dưới đã đi qua chưa nếu chưa thì đi đến

                System.out.println("\nCurrently at position " + temp3 + ".\nThinking....");
                
                //phải->trên->trái->dưới
                
                // lặp chừng nào ô bên phải đã qua rồi và chưa gặp phải tường
                while (t[pos].visited == 1 && t[pos].br != 1) {
                    ++pos;
                    ++score;
                }
                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].br == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                    // nếu ô hiện tại là hố/quái, hoặc ô này đã đi qua, bên phải là tường và ô này không an toàn
                    pos = temp3;//quay lại vị trí khi chưa lặp 
                    flag_1 = 1;//bật cờ 1
                }
                // nếu chưa đến thì gán bằng đến
                if (flag_1 == 0) {// chưa bật cờ 1 
                    t[pos].back += "l";//Lưu thêm và mảng bước trước của vị trí hiện tại là bên trái bước sang
                }
                
                //lặp đi lên trên giống ô đã đi đến bên phải
                while (pos + 4 >= 1 && t[pos].bu != 1 && t[pos].visited == 1) {
                    pos -= 4;
                    ++score;
                }
                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].bu == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                    pos = temp3;
                    flag3 = 1;
                }
                // nếu chưa đến thì gán bằng đến
                if (flag3 == 0) {
                    t[pos].back += "d";
                }
                    
                //lặp đi sang trái ô đã đi đến
                while (t[pos].visited == 1 && t[pos].bl != 1) {
                    --pos;
                    ++score;
                }
                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].bl == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                    pos = temp3;
                    flag2 = 1;
                }

                if (flag2 == 0) {
                    t[pos].back += "r";
                }
                
                //lặp đi xuống dưới ô đã đi đến rồi
                while (pos + 4 <= 16 && t[pos].bd != 1 && t[pos].visited == 1) {
                    pos += 4;
                    ++score;
                }
                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].bd == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                    pos = temp3;
                    flag4 = 1;
                }
                // nếu chưa đến thì gán bằng đến
                if (flag4 == 0) {
                    t[pos].back += "u";
                }

                t[pos].safe = 1;
                t[pos].visited = 1;
                System.out.println("reached at position " + pos);
                limit = 0;
            }//else if (limit>50)
            
            
            
            // gặp quái chưa bị giết 
            if (t[pos].env.contains("W") && scream != 1) {
                score += 100;// trừ 100 điểm
                scream = 1;// coi như quái chết
                t[pos].safe = 1;// gán an toàn trở lại
                System.out.println("\n\nWumpus killed >--0-->");//giết quái
                t[pos].env.replace("W", " ");//thay đổi trạng thái ô quái
                for (int l = 1; l <= 16; ++l) {//duyệt hết thay đổi trạng ô bị nghi ngờ và ô cận quái
                    t[l].doubt_wump = 0;
                    t[l].env.replace("S", " ");
                }
            }
            //gặp hố bị trừ 50
            if (t[pos].env.contains("P")) {
                score += 50;
                t[pos].pit = 1;// xác định ô hố
                System.out.println("\n\nFallen in pit of position " + pos + ".");
            }

            for (int k = 1; k <= 16; ++k) {
                if (t[k].doubt_pit == 1 && t[k].doubt_wump == 1) {
                    t[k].doubt_pit = 0;
                    t[k].doubt_wump = 0;
                    t[k].safe = 1;
                }
            }

            for (int y = 1; y <= 16; ++y) {
                if (t[y].doubt_wump > 1) {
                    t[y].wump = 1;
                    for (int h = 1; h <= 16; ++h) {
                        if (h != y) {
                            t[h].doubt_wump = 0;
                            t[h].env.replace("S", " ");
                        }
                    }

                }

            }

            for (int y = 1; y <= 16; ++y) {
                if (t[y].doubt_pit > 1) {
                    t[y].pit = 1;

                }
            }

            try {
                Thread.sleep(200);
            } catch (Exception p) {
            }

        } while (complete == 0);// lặp chừng nào chưa tìm thấy vàng

        if (complete == 1) {

            score *= -1;
            score += 1000;
        }
        System.out.println("The score of the agent till he reaches gold is " + score + ".\nNow he will return back following the best explored path.");

    }
}
