package boj;

import java.util.Scanner;

public class BOJ11659 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] numbers = new int[N+1];
        int[] sumPrefix = new int[N+1];

        int total = 0;
        for (int i = 1; i <= N; i++) {
            numbers[i] = sc.nextInt();
            sumPrefix[i] = numbers[i] + total;
            total += numbers[i];
        }

        for (int i = 1; i <= M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();

            System.out.println(sumPrefix[e] - sumPrefix[s-1]);
        }
    }
}
