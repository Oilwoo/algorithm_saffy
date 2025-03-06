package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SWEA2105 {
    static int T, N, max, count, total, startX, startY;
    static int[][] map;
    static List<Integer> visited_number;
    static boolean[][] visited_position;
    //방향벡터(대각선) 우상 우하 좌하 좌상
    static int[] dx = {-1, 1, 1, -1};
    static int[] dy = {1, 1, -1, -1};
    static boolean flag;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }

            max = -1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    //시작점 기록
                    flag = false;
                    startX = i;
                    startY = j;
                    count = -1;//디저트 종류 갯수 초기화
                    visited_position = new boolean[N][N];// 방문 초기화
                    visited_number = new ArrayList<Integer>();
                    visited_position[i][j] = true;
                    visited_number.add(map[i][j]); //방문한 디저트 종류
                    dfs(i, j, 1);

                    // 한번이라도 돌아왔으면
                    if(flag) max = Math.max(max, count);
                }
            }
            bw.write("#" + t + " " + max);
            bw.newLine();
        }
        bw.flush();
    }

    static void dfs(int i, int j, int cur) {
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            // dfs로 탐색하다가 본인 위치로 돌아오면
            // 여태까지 방문한 좌표 수 카페 수
            if (nx == startX && ny == startY && ) {
                flag = true;
                count = cur;
            }
            // 맵 이내에서 이동하고, 이번 탐색에서 방문한 위치도 아니고 방문한 숫자도 아니여야함
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited_position[nx][ny] && !visited_number.contains(map[nx][ny])) {
                flag = false;
                visited_position[nx][ny] = true; //위치 방문처리
                visited_number.add(map[nx][ny]); //디저트 종류 방문처리
                dfs(nx, ny, cur + 1);
            }
        }
    }
}
