package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ9205 {
    static int T, N, x, y, targetX, targetY;
    static int[][] store;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            // 편의점 갯수
            N = Integer.parseInt(br.readLine());
            store = new int[N][2];
            String[] input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                //x,y 좌표
                store[i][0] = Integer.parseInt(input[0]);
                store[i][1] = Integer.parseInt(input[1]);
            }
            input = br.readLine().split(" ");
            // 목적지
            targetX = Integer.parseInt(input[0]);
            targetY = Integer.parseInt(input[1]);

            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{x, y});
            boolean[] visited = new boolean[N];
            boolean sadflag = true;
            while (!q.isEmpty()) {
                int[] nArr = q.poll();
                int nx = nArr[0];
                int ny = nArr[1];

                // 목적지 먼저 체크
                if (manhaton(nx, ny, targetX, targetY) <= 1000) {
                    System.out.println("happy");
                    sadflag = false;
                    break;
                }
                // 편의점 체크
                for (int i = 0; i < N; i++) {
                    int cx = store[i][0];
                    int cy = store[i][1];

                    if (manhaton(nx, ny, cx, cy) <= 1000 && !visited[i]) {
                        q.offer(new int[] {cx, cy});
                        visited[i] = true;
                    }
                }
            }
            if(sadflag) System.out.println("sad");
        }
    }

    static int manhaton(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
