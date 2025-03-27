package boj;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int s, e, w;

    Edge(int s, int e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}

public class BOJ1922 {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        List<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, w));
        }
        make();

        Collections.sort(edges);

        int ans = 0;
        int count = 0;
        for(Edge e : edges) {
            if(union(e.s, e.e)) {
                ans += e.w;
                if(++count == N - 1) break; // 정점의 N-1개의 간선이 생겼으면 MST완성
            }
        }
        System.out.println(ans);
    }

    static void make() {
        parents = new int[N+1];
        for(int i = 1; i<= N; i++) {
            parents[i] = i;
        }
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        if(rootA > rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;

        return true;
    }
}
