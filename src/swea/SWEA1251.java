package swea;

import java.io.*;
import java.util.*;

public class SWEA1251 {

    static class Edge implements Comparable<Edge> {
        double cost;
        int x, y;

        Edge(double cost, int x, int y) {
            this.cost = cost;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, N;
    static double E;
    static int[] x, y, uf;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            solve(tc);
        }

        System.out.println(sb);
        br.close();
    }

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        x = new int[N];
        y = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            y[i] = Integer.parseInt(st.nextToken());
        }

        edges = new ArrayList<>();
        E = Double.parseDouble(br.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = getDist(y[i], x[i], y[j], x[j]);
                edges.add(new Edge(dist * dist * E, i, j));
            }
        }
    }

    private static void solve(int tc) {

        uf = new int[N];
        for (int i = 0; i < N; i++) {
            uf[i] = i;
        }
        edges.sort(Comparator.naturalOrder());

        int cnt = 0;
        double total = 0.0;
        for (Edge e : edges) {
            if (find(e.x) != find(e.y)) {
                union(e.x, e.y);
                cnt++;
                total += e.cost;
                if (cnt == N) {
                    break;
                }
            }
        }

        sb.append("#").append(tc).append(" ").append(Math.round(total)).append("\n");
    }

    private static double getDist(int y1, int x1, int y2, int x2) {
        long diffY = Math.abs(y2 - y1);
        long diffX = Math.abs(x2 - x1);
        return Math.sqrt(diffY * diffY + diffX * diffX);
    }

    private static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        if (xRoot > yRoot) {
            int tmp = xRoot;
            xRoot = yRoot;
            yRoot = tmp;
        }

        uf[yRoot] = xRoot;
    }

    private static int find(int x) {
        if (uf[x] != x) {
            uf[x] = find(uf[x]);
        }
        return uf[x];
    }

}

