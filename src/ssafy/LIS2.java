package ssafy;

import java.util.Arrays;
import java.util.Scanner;

public class LIS2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] C = new int[N]; //자신을 끝으로 하는 증가 부분수열의 최장 길이

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            //뒷항의 값은 앞항 영향을 끼치지 않음
            //그래서 입력 받으면서 DP해도된다
        }

        int size = 0;
        for (int i = 0; i < N; i++) {
            // i원소가 최소값으로 끝에 설 수 있는 위치 찾기
            int pos = Arrays.binarySearch(C, 0, size, arr[i]); //0 ~ size 범위에서 arr[i] 숫자를 찾음
            if(pos >= 0) return; //찾는 값이 있다는 얘기는 중복 값이 있다는 의미

            int temp = Math.abs(pos) - 1;
            C[temp] = arr[i];

            if(temp == size) ++size; //맨 뒤에 추가된 상황이므로 사이즈 증가
        }
    }
}
