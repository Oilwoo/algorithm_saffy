package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SWEA5644 {
    static int T, M, A, ax, ay, bx, by, ans;
    static int[] aOrder, bOrder;
    static int[][] bc;
    static int[] dy = {0, -1, 0, 1, 0}; //상우하좌
    static int[] dx = {0, 0, 1, 0, -1}; //상우하좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]);
            A = Integer.parseInt(input[1]);

            //사용자 이동 정보
            aOrder = new int[M];
            bOrder = new int[M];
            input = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                aOrder[i] = Integer.parseInt(input[i]);
            }
            input = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                bOrder[i] = Integer.parseInt(input[i]);
            }

            // BC(AP) 정보
            bc = new int[A][4]; //x, y, c, p
            for (int i = 0; i < A; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < 4; j++) {
                    bc[i][j] = Integer.parseInt(input[j]);
                }
            }

            // 정보 초기화
            ax = 1; ay = 1; bx = 10; by = 10;
            ans = 0; //총 충전량

            // 첫 위치에서 충전
            ans += getMaxCharge();
            // 사용자 이동 시작
            for (int i = 0; i < M; i++) {
                move(i);
                ans += getMaxCharge();
            }
            System.out.println("#" + t + " " + ans);
        }
    }

    static void move(int i) {
        ax += dx[aOrder[i]];
        ay += dy[aOrder[i]];
        bx += dx[bOrder[i]];
        by += dy[bOrder[i]];
    }

    static int getMaxCharge() {
        List<Integer> aBC = new ArrayList<>();
        List<Integer> bBC = new ArrayList<>();

        for (int i = 0; i < A; i++) {
            if (canCharge(ax, ay, bc[i])) aBC.add(i);
            if (canCharge(bx, by, bc[i])) bBC.add(i);
        }

        int maxCharge = 0;

        if (aBC.isEmpty() && bBC.isEmpty()) return 0;

        for (int a : aBC) {
            for (int b : bBC) {
                int charge;
                if (a == b) charge = bc[a][3] / 2 + bc[a][3] / 2; // 같은 충전기 사용 시 반반 충전
                else charge = bc[a][3] + bc[b][3]; // 다른 충전기 선택 가능

                maxCharge = Math.max(maxCharge, charge);
            }
        }

        // 한 명만 충전 가능할 때의 최대값 갱신
        if (!aBC.isEmpty()) {
            for (int a : aBC) {
                maxCharge = Math.max(maxCharge, bc[a][3]);
            }
        }
        if (!bBC.isEmpty()) {
            for (int b : bBC) {
                maxCharge = Math.max(maxCharge, bc[b][3]);
            }
        }

        return maxCharge;
    }

    static boolean canCharge(int x, int y, int[] bc) {
        //사용자 좌표에서 배터리 좌표까지의 거리가 bc[2]=c 커버리지 안에 있으면 true
        return Math.abs(x - bc[0]) + Math.abs(y - bc[1]) <= bc[2];
    }
}
