package swea;

import java.io.*;

public class SWEA1873 {
    static int T, H, W, N, direction, x, y;
    static String[][] map;
    static String[] order;
    // 방향벡터 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String[] tank = {"^", "v", "<", ">"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            H = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            map = new String[H][W];

            for (int i = 0; i < H; i++) {
                input = br.readLine().split("");
                for (int j = 0; j < W; j++) {
                    map[i][j] = input[j];
                    if (input[j].equals("^")) {
                        direction = 0;
                        x = i;
                        y = j;
                    } else if (input[j].equals("v")) {
                        direction = 1;
                        x = i;
                        y = j;
                    } else if (input[j].equals("<")) {
                        direction = 2;
                        x = i;
                        y = j;
                    } else if (input[j].equals(">")) {
                        direction = 3;
                        x = i;
                        y = j;
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            order = new String[N];
            input = br.readLine().split("");
            for (int i = 0; i < N; i++) {
                game(input[i]);
            }


            bw.write("#"+t+" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    bw.write(map[i][j]);
                }
                bw.newLine();
            }
        }
        bw.flush();
    }

    static void game(String order) {
        if ("S".equals(order)) {
            shoot();
        } else {
            // 방향 돌리고
            if (order.equals("U")) direction = 0;
            else if (order.equals("D")) direction = 1;
            else if (order.equals("L")) direction = 2;
            else if (order.equals("R")) direction = 3;
            //이동
            move();
        }
    }

    static void shoot() {
        int sx = x + dx[direction];
        int sy = y + dy[direction];
        while (sx >= 0 && sx < H && sy >= 0 && sy < W) {
            //강철벽
            if (map[sx][sy].equals("#")) break;
            //벽돌벽
            if (map[sx][sy].equals("*")) {
                map[sx][sy] = "."; //평지로 만들고
                break;
            }
            sx = sx + dx[direction];
            sy = sy + dy[direction];
        }


        //탱크가 윗 방향을 바라볼 경우
//        if (direction == 0) {
//            // 포탄 좌표
//            int sx = x - 1;
//            int sy = y;
//            while (sx >= 0) {
//                //강철벽
//                if (map[sx][sy].equals("#")) break;
//                //벽돌벽
//                if (map[sx][sy].equals("*")) {
//                    map[sx][sy] = "."; //평지로 만들고
//                    break;
//                }
//                sx = sx - 1;
//            }
//        } else if (direction == 1) {
//            // 포탄 좌표
//            int sx = x + 1;
//            int sy = y;
//            while (sx < H) {
//                //강철벽
//                if (map[sx][sy].equals("#")) break;
//                //벽돌벽
//                if (map[sx][sy].equals("*")) {
//                    map[sx][sy] = "."; //평지로 만들고
//                    break;
//                }
//                sx = sx + 1;
//            }
//        } else if (direction == 2) {
//            // 포탄 좌표
//            int sx = x;
//            int sy = y - 1;
//            while (sy >= 0) {
//                //강철벽
//                if (map[sx][sy].equals("#")) break;
//                //벽돌벽
//                if (map[sx][sy].equals("*")) {
//                    map[sx][sy] = "."; //평지로 만들고
//                    break;
//                }
//                sy = sy - 1;
//            }
//        } else if (direction == 3) {
//            // 포탄 좌표
//            int sx = x;
//            int sy = y + 1;
//            while (sy < W) {
//                //강철벽
//                if (map[sx][sy].equals("#")) break;
//                //벽돌벽
//                if (map[sx][sy].equals("*")) {
//                    map[sx][sy] = "."; //평지로 만들고
//                    break;
//                }
//                sy = sy + 1;
//            }
//        }

    }

    static void move() {
        //일단 방향을 돌림
        map[x][y] = tank[direction];
        int nx = x + dx[direction];
        int ny = y + dy[direction];
        //갈 수 있는 곳인지 && 평지인지
        if (nx >= 0 && ny >= 0 && nx < H && ny < W && map[nx][ny].equals(".")) {
            map[nx][ny] = tank[direction]; //이동한 곳에 탱크 생성
            map[x][y] = "."; //이전 장소는 평지
            x = nx; //현재 탱크 x 좌표 업데이트
            y = ny; //현재 탱크 y 좌표 업데이트
        }
    }
}
