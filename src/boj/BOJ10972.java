package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10972 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }

        int i = N - 1;
        // i-1이 작다면 다음에 오는 순열을 출력하기 위해
        while(i >= 0) {
            if (array[i - 1] < array[i]) {
                int temp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = temp;
                // i를 기준으로 뒤에는 다 오름차순
                Arrays.sort(array, i, N);
                //다음 수열을 찾아서 종료
                break;
            } else {
                i -= 1;
                if (i == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        for (int n : array) {
            System.out.print(n + " ");
        }
    }
}
