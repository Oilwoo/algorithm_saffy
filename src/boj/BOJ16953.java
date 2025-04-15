package boj;

import java.util.Scanner;

public class BOJ16953 {
    static int A, B, ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();

        ans = 1;

        // 거꾸로 생각하는 top-down 방식을 생각해내야함
        while (A != B) {
            if (B < A) {
                System.out.println(-1);
                return;
            }
            String str = String.valueOf(B);
            if (B % 2 == 0) { // 짝수면
                B /= 2;
            } else if (str.charAt(str.length() - 1) == '1') { //마지막 글자가 1이면
                str = str.substring(0, str.length() - 1); // 마지막 1 제거
                B = Integer.parseInt(str);
            } else { // 위 두케이스가 아니면 불가능
                System.out.println(-1);
                return;
            }
            ans += 1;
        }
        System.out.println(ans);
    }
}
