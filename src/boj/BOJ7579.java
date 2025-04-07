package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {
    static int[][] apps, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        apps = new int[N + 1][2]; //0 = 소요 메모리, 1 = 발생되는 비용
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            apps[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int totalCost = 0; //앱을 모두 종료하면 사용하는 비용
        for (int i = 1; i <= N; i++) {
            apps[i][1] = Integer.parseInt(st.nextToken());
            totalCost += apps[i][1];
        }

        int ans = totalCost;
        dp = new int[N + 1][totalCost + 1];

        for (int i = 1; i <= N; i++) {
            int m = apps[i][0];
            int c = apps[i][1];
            for (int j = 0; j < totalCost; j++) { // 0 ~ 전체까지 소요하는 비용
                if (i == 0) {
                    if (j >= c) dp[i][j] = m; //첫 줄은 비교대상이 없으므로 그냥 확보할 수 있는 메모리를 넣어줌
                } else {
                    if (j >= c) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - c] + m); //냅색 비교
                    } else {
                        dp[i][j] = dp[i-1][j]; // 비용이 안된다면 그냥 전행의 최적해 넣어줌.
                    }
                }
                if(dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);

    }
}
