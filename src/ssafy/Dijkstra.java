package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    static class Node{
        int vertex, weight;
        Node next;
        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }

    static class Vertex implements Comparable<Vertex>{
        int no, minDistance;

        public Vertex(int no, int minDistance) {
            super();
            this.no = no;
            this.minDistance = minDistance;
        }

        @Override
        public int compareTo(Vertex o) {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 최단경로의 값(정점수 -1 만큼의 간선의 가중치의 합) 은 int를 넘지 않는다.
        final int INF = Integer.MAX_VALUE;

        Node[] edges = new Node[v];
        int[] minDistance = new int[v];
        boolean[] visited = new boolean[v];

        // 인접리스트 만들기
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[from] = new Node(to, weight, edges[from]);
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Arrays.fill(minDistance, INF);
        minDistance[start] = 0;
        pq.offer(new Vertex(start, minDistance[start]));

        for (int i = 0; i < v; i++) {
            // 미방문 정점중 출발지에서 가장 가까운 정점 찾

            Vertex stopOver = pq.poll();

            visited[stopOver.no] = true;
            if(stopOver.no == end) break;

            for (Node temp = edges[stopOver.no]; temp != null; temp = temp.next) {
                if(visited[temp.vertex] && minDistance[temp.vertex] > stopOver.minDistance + temp.weight) {

                }
            }
        }

        System.out.println(minDistance[end] == INF ? -1 : minDistance[end]);

    }
}
