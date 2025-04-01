package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class MST2_priorityQ {

    static class Vertex implements Comparable<Vertex> {
        int no, weight;
        public Vertex(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(in.readLine());
        int[][] adjMatrix = new int[V][V]; // 인접행렬
        boolean[] visited = new boolean[V]; // 트리정점
        int[] minEdge = new int[V]; // 트리에 속한 타정점들과 자신과의 최소간선비용

        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(in.readLine()," ");
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }// 인접행렬 입력
        //step0 : minEdge 배열 최대값으로 초기화 후 임의의 시작정점이 트리에 속하는 시작정점(0정점)이 되게 만들기
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<>(); //비트리정점중 최소간선빙용의 정점을 찾기위한 목적

        int result = 0, cnt = 0; // mst 비용
        minEdge[0] = 0; // 루틴에 의해 정점이 선택되도록 처리
        pq.offer(new Vertex(0, minEdge[0]));

        while (!pq.isEmpty()) {
            // step1 : 비트리정점중 트리에 속할 가장 유리한(간선의 비용이 적게드는) 정점 찾기
            Vertex minVertex = pq.poll();

            result += minVertex.weight; // 트리 가중치의 합에 더하기
            visited[minVertex.no] = true; //트리에 속한 정점으로 만들기

            // step2 : 새롭게 확장된 트리 정점과 나머지 비트리 정점간의 최소간선비용 비교 후 업데이트
            for (int i = 0; i < V; i++) {
                if(!visited[i] //비트리 정점 확인
                        && adjMatrix[minVertex.no][i] != 0  // 인접여부 확인
                        && minEdge[i] > adjMatrix[minVertex.no][i]) // 최소간선 비용 확인
                {
                    minEdge[i] = adjMatrix[minVertex.no][i];
                    pq.offer(new Vertex(i, minEdge[i]));
                }
            }
        }
        System.out.println(cnt==V?result:-1);
    }

}










