package swea;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SWEA7465_ilwoo {
    static int T, N, M;
    static int parents[];

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
            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                union(a, b);
            }
            // 무리 카운트
            Set<Integer> ans = new HashSet<Integer>();
            for (int i = 1; i <= N; i++) {
                int root = find(i);
                ans.add(root);
            }
            bw.write(String.valueOf(ans.size()));
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
        return parents[a] = find(parents[a]); //경로 최적화
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false; //루트가 같으면 이미 동일한 집합

        parents[bRoot] = aRoot;
        return true;
    }
}

