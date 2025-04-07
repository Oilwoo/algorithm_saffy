package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA1249 {
    static int T, N;
    static int[][] map, visited;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }
            bfs();
            sb.append("#").append(t).append(" ").append(visited[N-1][N-1]).append("\n");
        }
        System.out.println(sb);
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0,0});
        visited[0][0] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if(visited[nx][ny] > visited[x][y] + map[nx][ny]) {
                        visited[nx][ny] = visited[x][y] + map[nx][ny];
                        q.offer(new int[] {nx,ny});
                    }
                }
            }

        }
    }
}

