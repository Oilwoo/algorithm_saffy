package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class KMP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int tLength = text.length;
        int pLength = pattern.length;

        int[] pi = new int[pLength]; // 부분일치 테이블(실패함수) 만들기 : 패턴에서 불일치가 발생할 경우 패턴포인터 이동 목적

        for (int i = 1, j = 0; i < pLength; i++) { // 패턴에서 패턴을 찾는 원리 이용
            // i = 패턴의 접미사, j = 패턴의 접두사
            // 길이는 2 이상이 의미가 있는 것
            // 두 포인터의 위치에서 불일치가 발생하면, 최대한 직전 위치의 정보를 활용해서 불필요한 비교를 줄임
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }

            // 현재 i 위치까지 부분문자열의 접미사가 접두사와 일치하는 패턴의 최장길이를 저장
            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            } else {
                pi[i] = 0; // 일치하는 접두사/접미사 없음
            }
        }
        //System.out.println(Arrays.toString(pi));

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0, j=0; i < tLength; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j-1];
            }

            if(text[i] == pattern[j]) {
                if (j == pLength - 1) { //일치가 발생한 위치가 패턴의 끝
                    ++cnt; // 패턴 찾았으므로 카운트를 증가
                    list.add(i-j);
                    j = pi[j];
                } else {
                    ++j;
                }

            }
        }

        //패턴 문자열이 맞는 갯수가 몇개인지
        System.out.println(cnt);
        if (cnt > 0) {
            System.out.println(list);
        }

    }
}
