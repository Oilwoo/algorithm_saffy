package boj;

import java.util.Scanner;

public class BOJ1094 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int total = 64; // 막대기 길이의 합
        int cur = 64; // 현재 가장 짧은 막대
        int a = 1;
        if(x == 64) {
            System.out.println(a);
        } else {
            while (true) {
            // 가지고 있는 막대중 가장 짧은 것을 절반으로 자름
                cur /= 2;
                total -= cur; //절반으로 자른 막대기 1개를 버리고
                if(total < x) {
                    a++;
                    total += cur;
                } else if(total == x) break;
            }
            System.out.println(a);
        }
    }
}
