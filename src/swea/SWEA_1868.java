package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1868{
    static int T, N;
    static char[][] map;         // 지뢰 맵('*' or '.')
    static int[][] adj;          // 각 칸별 8방 지뢰 개수
    static boolean[][] visited;  // 방문(열림) 여부
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            // 1. adj 배열 채우기 (각 칸 주위 지뢰 개수 계산)
            adj = new int[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == '*') {
                        adj[r][c] = -1; // 지뢰 표시
                    } else {
                        adj[r][c] = countBomb(r, c);
                    }
                }
            }

            // 2. visited 초기화
            visited = new boolean[N][N];

            int clicks = 0;

            // 3. (1) 먼저 주변 폭탄 개수가 0인 칸들부터 DFS/BFS로 연쇄 열기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // 지뢰가 아니고, 아직 열리지 않았고, 주변 폭탄이 0인 칸이면
                    if (map[r][c] != '*' && !visited[r][c] && adj[r][c] == 0) {
                        // 한 번 클릭한다고 생각
                        clicks++;
                        openArea(r, c);
                    }
                }
            }

            // 4. (2) 여전히 열리지 않은(visited=false) 지뢰가 아닌 칸들은 각각 클릭 필요
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] != '*' && !visited[r][c]) {
                        clicks++;
                    }
                }
            }

            System.out.println("#" + t + " " + clicks);
        }
    }

    // 주변 지뢰 개수 세기
    static int countBomb(int r, int c) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (map[nr][nc] == '*') cnt++;
        }
        return cnt;
    }

    // (r,c)가 주변 폭탄이 0인 칸일 때, 인접 칸까지 연쇄적으로 열어주는 함수(DFS or BFS)
    static void openArea(int r, int c) {
        // DFS/스택/큐 중 편한 방법 사용
        // 여기서는 간단히 DFS
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r, c});
        visited[r][c] = true;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int rr = cur[0], cc = cur[1];

            // 현재 칸(rr, cc)이 주변 폭탄이 0이면 인접 칸들까지 열어야 함
            if (adj[rr][cc] == 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = rr + dr[i];
                    int nc = cc + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (!visited[nr][nc] && map[nr][nc] != '*') {
                        visited[nr][nc] = true;
                        stack.push(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}