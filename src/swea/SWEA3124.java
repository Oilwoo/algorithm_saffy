package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Edge implements Comparable<Edge> {
    int from, to, weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}


public class SWEA3124 {
    static int T, V, E;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            V = Integer.parseInt(input[0]);
            E = Integer.parseInt(input[1]);

            List<Edge> egdeList = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                input = br.readLine().split(" ");
                egdeList.add(new Edge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
            }
            Collections.sort(egdeList);
            make();
            long ans = 0;
            int count = 0;
            for(Edge e : egdeList) {
                if(union(e.from, e.to)) {
                    ans += e.weight;
                    if(++count == V - 1) break;
                }
            }
            bw.write("#" + t + " " + ans);
            bw.newLine();
        }
        bw.flush();

    }

    static void make() {
        parents = new int[V+1];
        for (int i = 1; i <= V; i++) {
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
