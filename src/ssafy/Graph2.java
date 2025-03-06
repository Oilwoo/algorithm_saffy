package ssafy;

import java.util.*;

public class Graph2 {
    static int[][] adjMatrix;
    static int V;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        int E = sc.nextInt();

        adjMatrix = new int[V][V];
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjMatrix[to][from] = adjMatrix[from][to] = 1;
        }
        for (int[] am : adjMatrix) {
            System.out.println(Arrays.toString(am));
        }

        bfs(0);
        bfs2(0);

    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[V];

        visited[start] = true;
        queue.offer(start);
        
        while(!queue.isEmpty()) {
            int current = queue.poll(); // 탐색할 대상 꺼내기
            // 탐색해서 해야할 일 처리
            System.out.print((char) (current+65));
            // 자신의 인접정점들을 다음 탐색이 가능하게 관리
            for (int i = 0; i < V; i++) {
                if(adjMatrix[current][i] != 1 || visited[i]) continue; // 인접하지 않은 정점이거나 방문된 상태면 skip
                
                visited[i] = true; // 인접 정점 방문처리
                queue.offer(i); // 인접 정점 큐에 넣기
            }
        }
    }

    // 너비도 출력
    private static void bfs2(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[V];
        Arrays.fill(visited, -1);

        visited[start] = 0;
        queue.offer(start);

        while(!queue.isEmpty()) {
            int current = queue.poll(); // 탐색할 대상 꺼내기
            // 탐색해서 해야할 일 처리
            System.out.print((char) (current+65) + ":" + visited[current]);
            // 자신의 인접정점들을 다음 탐색이 가능하게 관리
            for (int i = 0; i < V; i++) {
                if(adjMatrix[current][i] != 1 || visited[i] > -1) continue; // 인접하지 않은 정점이거나 방문된 상태면 skip

                visited[i] = visited[current]+1; // 인접 정점 방문처리
                queue.offer(i); // 인접 정점 큐에 넣기
            }
        }
    }
}
