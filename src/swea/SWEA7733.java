package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA7733 {
    static int T, N, max;
    static int[][] map;
    static boolean[][] eat_visited, visited;
    //방향벡터 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            eat_visited = new boolean[N][N];
            map = new int[N][N];
            max = 0;
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                    max = Math.max(max, map[i][j]); //가장 큰 날짜
                }
            }

            // 제일 큰 날짜까지 요정이 치즈 먹기
            int ans = 1; //
            for (int d = 1; d <= max; d++) {
                // 치즈 먹기
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(map[i][j] == d) eat_visited[i][j] = true;// 해당 날짜의 치즈를 먹음
                    }
                }
                // 치즈 덩어리 세기
                ans = Math.max(ans, countCheeseMass());
            }
            bw.write("#"+t+" "+ans);
            bw.newLine();
        }
        bw.flush();
    }

    static int countCheeseMass() {
        int cheeseCount = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j]) continue; //이미 검사한 칸은 제외
                if(eat_visited[i][j]) continue; //이미 먹은 칸도 제외
                checkCheeseMass(i, j);
                cheeseCount += 1;
            }
        }
        return cheeseCount;
    }

    static void checkCheeseMass(int x, int y) {
        //치즈가 안먹힌 칸이면 상하좌우
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            // 맵의 내부이며, 먹히지 않고, 방문한 칸이 아닐경우
            if(nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == false && eat_visited[nx][ny] == false) {
                visited[nx][ny] = true;
                checkCheeseMass(nx, ny);
            }
        }
    }
}

