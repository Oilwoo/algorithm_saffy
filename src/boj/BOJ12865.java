package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
    static int N, K;
    static int[][] items, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        items = new int[N+1][2]; //0 = w, 1 = v
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        //dp 테이블 만들기
        dp = new int[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            int w = items[i][0];
            int v = items[i][1];
            for (int j = 1; j <= K; j++) {
                // 담을 수 있는 가방의 무게보다 물건이 무거울 경우
                if(w > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 남은 무게의 한칸 위의 최적해
                    dp[i][j] = Math.max(dp[i-1][j], v + dp[i-1][j - w]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
