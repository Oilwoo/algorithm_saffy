package ssafy;

import java.util.Scanner;

public class DP_3knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 넣을 수 있는 물건갯수
        int W = sc.nextInt(); // 가방 목표

        int[] weights = new int[N+1];
        int[] profits = new int[N+1];

        for (int i = 1; i <= N; i++) {
            weights[i] = sc.nextInt();
            profits[i] = sc.nextInt();
        }
        int[][] D = new int[N+1][W+1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if(weights[i] <= w) {
                    D[i][w] = Math.max(D[i-1][W], D[i-1][w-weights[i]]+profits[i]);
                }else {
                    D[i][w] = D[i-1][w];
                }
            }
        }
        System.out.println(D[N][W]);
    }
}
