package boj;

import java.io.*;

public class BOJ2167 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[][] matrix = new int[N+1][M+1];
        // 2차원 배열 만들기
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                matrix[i][j] = Integer.parseInt(input[j-1]);
            }
        }
        //부분합 배열 만들기
        int[][] prefix_matrix = new int[N+1][M+1];
        //가로로 부분합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefix_matrix[i][j] =  prefix_matrix[i][j-1] + matrix[i][j];
            }
        }
        //세로로 부분합
        for (int j = 1; j <= M; j++) {
            for (int i = 1; i <= N; i++) {
                prefix_matrix[i][j] =  prefix_matrix[i-1][j] + prefix_matrix[i][j];
            }
        }

        // 합을 구할 부분의 개수 K
        int K = Integer.parseInt(br.readLine());
        for (int index = 0; index < K; index++) {
            input = br.readLine().split(" ");
            //i, j, x, y
            int i = Integer.parseInt(input[0]);
            int j = Integer.parseInt(input[1]);
            int x = Integer.parseInt(input[2]);
            int y = Integer.parseInt(input[3]);

            int result  = prefix_matrix[x][y] - prefix_matrix[i-1][y] - prefix_matrix[x][j-1] + prefix_matrix[i-1][j-1];
            System.out.println(result);
        }


    }
}
