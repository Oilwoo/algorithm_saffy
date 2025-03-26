package boj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ15683 {
    static int N, M;
    static int[][] matrix;
    static int[] dx = {-1, 0, 1, 0}; //상 우 하 좌
    static int[] dy = {0, 1, 0, -1}; //상 우 하 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        matrix = new int[N][M];
        List<Integer[]> cameras = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
                if (matrix[i][j] != 0 && matrix[i][j] != 6) { //카메라 위치 기록
                    Integer[] c = new Integer[3];
                    c[0] = matrix[i][j]; //카메라종류
                    c[1] = i; // x 좌표
                    c[2] = j; // y 좌표
                    cameras.add(c);
                }
            }
        }
        // 카메라 위치를 기반으로 맵 업데이트

        for (Integer[] c : cameras) {
            int type = c[0];
            int x = c[1];
            int y = c[2];
        }
    }

    static void check(int t, int d, int x, int y) {
        if(t == 1) {
            int nx = x;
            int ny = y;
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d];
                ny = ny + dy[d];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
        } else if (t == 2 && d < 2) { // 두 방향만 체크 하면되니까
            // d = 0 이면 상하를 체크해야하니까 d 0, 2 쳌
            // d = 1 이면 좌우를 체크해야하니까 d 1, 3 쳌
            int nx = x;
            int ny = y;
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d];
                ny = ny + dy[d];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d+1];
                ny = ny + dy[d+1];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
        } else if (t == 3) {
            int nx = x;
            int ny = y;
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d];
                ny = ny + dy[d];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
            // 한바퀴 다 돌았으면 다시 위로
            if(d == 3) d = -1;
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d+1];
                ny = ny + dy[d+1];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
        } else if (t == 4) {
            int nx = x;
            int ny = y;
            // 좌
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                int temp = d;
                if(d == 0) temp = 4;
                nx = nx + dx[temp-1];
                ny = ny + dy[temp-1];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d];
                ny = ny + dy[d];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
            // 한바퀴 다 돌았으면 다시 위로
            if(d == 3) d = -1;
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d+1];
                ny = ny + dy[d+1];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
        } else if (t == 5 && d == 0) {
            int nx = x;
            int ny = y;
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d];
                ny = ny + dy[d];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d+1];
                ny = ny + dy[d+1];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d+2];
                ny = ny + dy[d+2];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
            while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                nx = nx + dx[d+3];
                ny = ny + dy[d+3];
                if(matrix[nx][ny] == 6) break; //벽이면 멈춤
                matrix[nx][ny] = -1;
            }
        }
    }
}
