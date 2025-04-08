package ssafy;

import java.util.Scanner;

public class LIS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] LIS = new int[N]; //자신을 끝으로 하는 증가 부분수열의 최장 길이

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            //뒷항의 값은 앞항 영향을 끼치지 않음
            //그래서 입력 받으면서 DP해도된다
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            LIS[i] = 1; //자신만 끝에 세웠을 경우의 최장 길이는 1로 초기화
            for (int j = 0; j < i; j++) { //i보다 앞에 있는 모든 대상에 대해 탐색
                if(arr[j] < arr[i] && LIS[j] < LIS[j]+1) { // 나보다 앞에 있는 j의 값이 나보다 작고 j 뒤에 i를 세우는 것이 더 최장을 만족
                    LIS[i] = LIS[j]+1;
                }
            }
            max = Math.max(max, LIS[i]);
        }

        System.out.println(max);
    }
}
