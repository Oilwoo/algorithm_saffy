package swea;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SWEA3289_ilwoo {
    static int T, N, M;
    static int parents[];
    static List<Integer> ans;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            bw.write("#" + t + " ");
            N = sc.nextInt();
            M = sc.nextInt();
            // 초기화
            make(); //집합 만들기 1~N까지
            ans = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                int action = sc.nextInt();
                int a = sc.nextInt();
                int b = sc.nextInt();
                if (action == 0) { //집합 합치기
                    union(a, b);
                } else if (action == 1) { //집합인지 확인하기
                    bw.write(String.valueOf(check(a, b)));
                }
            }
            bw.newLine();
        }
        bw.flush();
    }

    static void make() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i; //루트노드(부모)를 자신으로
        }
    }

    static int find(int a) { //루트노드 찾기
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false; //루트가 같으면 이미 동일한 집합

        parents[bRoot] = aRoot;
        return true;
    }

    static int check(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return 1; //루트가 같으면 이미 동일한 집합
        return 0;
    }
}
