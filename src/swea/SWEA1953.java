package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953 {
    static int t, n, m, r, c, l;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            //입력
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            //지도 그리기
            map = new int[n][m];
            visited = new boolean[n][m];
            for(int x = 0; x < n; x++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int y = 0; y < m; y++) {
                    map[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            queue = new ArrayDeque<>();
            queue.add(new int[] {r, c, 1});

            while(!queue.isEmpty()) {

            }

            //sb.append("#").append(i).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }

    //우 좌 하 상
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static void solve (int x, int y, int depth) {
        int type = map[x][y];
        int[] movement = null;
        switch(type) {
            case 1: //우좌하상
                movement = new int[] {0,1,2,3};
            case 2: //상하
                movement = new int[] {2,3};
            case 3: //좌우
                movement = new int[] {0,1};
            case 4: //상우
                movement = new int[] {0,4};
            case 5: //하우
                movement = new int[] {0,3};
            case 6: //하좌
                movement = new int[] {1,2};
            case 7: //상좌
                movement = new int[] {1,3};
        }
        if(movement == null) return;
        for(int i : movement) {
            int mx = x + dx[i];
            int my = y + dy[i];

            if(mx>=0 && mx < n && my >= 0 && my < m) continue;
            if ((i == 0 && map[mx][my] != 1 && map[mx][my] != 3 && map[mx][my] != 6 && map[mx][my] != 7)
                    || (i == 1 && map[mx][my] != 1 && map[mx][my] != 3 && map[mx][my] != 4 && map[mx][my] != 5)
                    || (i == 2 && map[mx][my] != 1 && map[mx][my] != 2 && map[mx][my] != 4 && map[mx][my] != 7)
                    || (i == 3 && map[mx][my] != 1 && map[mx][my] != 2 && map[mx][my] != 5 && map[mx][my] != 6)) continue;

        }

    }

}
