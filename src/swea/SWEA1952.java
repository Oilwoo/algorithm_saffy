package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1952 {
    static int day, oneMonth, threeMonths, year;
    static int[] plan;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            day = Integer.parseInt(input[0]);
            oneMonth = Integer.parseInt(input[1]);
            threeMonths = Integer.parseInt(input[2]);
            year = Integer.parseInt(input[3]);

            // 이용계획
            plan = new int[13];
            input = br.readLine().split(" ");
            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(input[i-1]);
            }

            //dfs 계산
            ans = year;
            dfs(1, 0);

            System.out.println("#"+t+" "+ans);

        }
    }

    public static void dfs(int month, int pay) {
        if(month > 12) {
            if(ans > pay) {
                ans = pay;
            }
            return;
        }

        if(plan[month] > 0) {
            //1일 이용권
            dfs(month+1, pay + plan[month] * day);

            //1달 이용권
            dfs(month+1, pay + oneMonth);

            //3달 이용권
            dfs(month+3, pay + threeMonths);
        } else {
          dfs(month+1, pay);
        }

    }


}
