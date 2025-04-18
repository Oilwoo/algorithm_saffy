package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SWEA2117 {
    static int N, M, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            List<Integer> homeX = new ArrayList<>();
            List<Integer> homeY = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    int pos = Integer.parseInt(input[j]);
                    if (pos == 1) {
                        homeX.add(i);
                        homeY.add(j);
                    }
                }
            }

            int ans = 0;
            int Kmax = 2 * (N-1) + 1;
            for (int k = 1; k <= Kmax; k++) {
                int pay = (k * k + (k - 1) * (k - 1));
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int count = 0;
                        //포함되는 범위에 있는 집의 갯수
                        for (int l = 0; l < homeX.size(); l++) {
                            if (Math.abs(homeX.get(l) - i) + Math.abs(homeY.get(l) - j) < k) {
                                count++;
                            }
                        }
                        int total = (count * M) - pay; // 수익 - 소요비용
                        if (total >= 0) { //손해를 안보는 경우에서 최대니까
                            ans = Math.max(ans, count);
                        }
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
