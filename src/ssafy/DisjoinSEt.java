package ssafy;

import java.util.Arrays;

public class DisjoinSEt {
    static int N;
    static int[] parents;

    static void make() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i; //자신의 부모를 자신으로
        }
    }

    static int find(int a) { // a가 속한 집합 찾기(집합의 대표자를 반환)
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]); // 경로 압축

    }

    static boolean union(int a, int b) { // a가 속한 집합과 b가 속한 집합을 합침.
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false; //같은 집합에 있으면 return false;

        parents[bRoot] = aRoot; //b루트노드를 a루트 노드로 변경
        return true;
    }

    public static void main(String[] args) {
        N = 5; //0,1,2,3,4 5개
        make();
        System.out.println(Arrays.toString(parents));
        System.out.println(union(0,1));
        System.out.println(Arrays.toString(parents));
        System.out.println(union(1,2));
        System.out.println(Arrays.toString(parents));
        System.out.println(union(3,4));
        System.out.println(Arrays.toString(parents));
        System.out.println(union(1,2));
    }
}
