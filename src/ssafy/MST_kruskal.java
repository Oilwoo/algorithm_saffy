package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST_kruskal {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
            // 오름차순으로 하고싶으면 앞(자신)에서 뒤(다른)를 뺀다
            // 반대로 내림차순은 뒤(다른)에서 앞(자신)을 뺀다
        }
    }

    static Edge[] edgeList;
    static int[] parents;
    static int V, E;

    static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i; // 자신을 부모노드로
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]); //패스 압축
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;

        // 큰 수를 가진 집합에 병합
        // 랜덤 요소 부여
        if(aRoot > bRoot) parents[bRoot] = aRoot;
        else parents[aRoot] = bRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList);
        make(); //독립 트리 생성
        int result = 0, count = 0;
        for (Edge e : edgeList) {
            if (union(e.from, e.to)) {
                result += e.weight;
                if(++count == V - 1) {
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
