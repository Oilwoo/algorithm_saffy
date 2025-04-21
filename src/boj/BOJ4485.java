package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4485 {
    static int N;
    static int[][] matrix, visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            matrix = new int[N][N];
            visited = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{0, 0, matrix[0][0]});
            visited[0][0] = matrix[0][0];

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (visited[nx][ny] > matrix[nx][ny] + visited[x][y]) {
                            visited[nx][ny] = matrix[nx][ny] + visited[x][y];
                            q.offer(new int[]{nx, ny, matrix[nx][ny]});
                        }
                    }
                }
            }

            System.out.println("Problem " + t + ": " + visited[N - 1][N - 1]);
            t += 1;

        }


    }
}
