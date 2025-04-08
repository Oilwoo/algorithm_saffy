package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3282 {
    static int T, N, K;
    static int[] itemsV, itemsC;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            itemsV = new int[N+1];
            itemsC = new int[N+1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                itemsV[i] = Integer.parseInt(st.nextToken());
                itemsC[i] = Integer.parseInt(st.nextToken());
            }

            //dp 테이블
            dp = new int[N+1][K+1];

            for (int i = 1; i <= N; i++) {
                int v = itemsV[i]; // 부피
                int c = itemsC[i]; // 가치
                for (int j = 0; j <= K; j++) {
                    // j = 가방에 들어갈 수 있는 부피
                    //현재 가방에 들어갈 수 있는 부피보다 물건의 부피가 크면
                    // i-1 행의 값을 넣어준다.
                    if(j < v) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        // i-1의 최적해 랑 현재 물건의 부피를 뺀 나머지 값의 가치를 비교해서 더 큰 애를 업뎃
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-v] + c);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(dp[N][K]).append("\n");
        }
        System.out.println(sb);
    }
}
