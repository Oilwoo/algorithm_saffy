package swea;

import java.util.*;
import java.io.*;

class Pos {
    int x;
    int y;

    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Pos p = (Pos) obj;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public String toString() {
        return "Pos [x=" + x + ", y=" + y + "]";
    }

}

public class SWEA2105 {

    private static int T, answer;
    private static int N;
    private static int[] dx = {-1, -1, 1, 1};
    private static int[] dy = {-1, 1, 1, -1};
    private static int[][] map;

    private static boolean[] desserts;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            desserts = new boolean[101];

            map = new int[N + 1][N + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = -1;
            visited = new boolean[N + 1][N + 1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Pos cur = new Pos(i, j);
                    dfs(cur, cur, 0, false, 2, 0);
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    private static void dfs(Pos start, Pos cur, int depth, boolean clockwise, int dir, int turn) {
        // 처음 뎁스,
        if (depth > 0 && cur.equals(start)) {
            if (turn == 4)
                answer = Math.max(answer, depth);
            return;
        }

        int dessert = map[cur.x][cur.y];
        visited[cur.x][cur.y] = desserts[dessert] = true;

        // 직진
        int nx = cur.x + dx[dir], ny = cur.y + dy[dir];
        // 진입 조건
        // 1. map 안에 들어오는지
        // 2. 방문했던 점이 아닌지
        // 3. 내가 먹었던 디저트가 아닌지
        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            int ndessert = map[nx][ny];
            if (!visited[nx][ny] && !desserts[ndessert]) {
                dfs(start, new Pos(nx, ny), depth + 1, clockwise, dir, turn);
            }
        }

        // 현재 회전 방향으로 회전
        int ndir = changeDir(clockwise, dir);
        nx = cur.x + dx[ndir];
        ny = cur.y + dy[ndir];
        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            int ndessert = map[nx][ny];
            if ((!visited[nx][ny] && !desserts[ndessert]) || start.equals(new Pos(nx, ny))) {
                dfs(start, new Pos(nx, ny), depth + 1, clockwise, ndir, turn + 1);
            }
        }

        visited[cur.x][cur.y] = desserts[dessert] = false;
    }

    private static int changeDir(boolean clockwise, int dir) {
        if (clockwise) {
            return (dir + 1) % 4;
        } else {
            return (dir - 1 + 4) % 4;
        }
    }
}
