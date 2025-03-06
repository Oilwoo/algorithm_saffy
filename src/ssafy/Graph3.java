package ssafy;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Graph3 {

    static class Node{
        int to, weight;
        Node next;
        public Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", weight=" + weight +
                    ", next=" + next +
                    '}';
        }
    }

    static Node[] adjList;
    static int V;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        int E = sc.nextInt();

        adjList = new Node[V];
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(to, weight, adjList[to]);
        }
        for (Node node : adjList) {
            System.out.println(node);
        }

        bfs(0);

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
            for (Node temp = adjList[current]; temp != null; temp = temp.next) {
                if(visited[temp.to]) continue; // 방문된 상태면 skip

                visited[temp.to] = true; // 인접 정점 방문처리
                queue.offer(temp.to); // 인접 정점 큐에 넣기
            }
        }
    }
}

/*
7
8
0 1 10
0 2 6
0 5 7
0 6 3
4 3 6
5 3 8
5 4 9
6 4 20
 */