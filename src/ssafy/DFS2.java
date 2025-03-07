package ssafy;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class DFS2 {

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

        dfs(0, new boolean[V]);

    }

    private static void dfs(int current, boolean[] visited) {
        visited[current] = true;
        System.out.print((char) (current+65));
        for (Node temp = adjList[current]; temp != null; temp = temp.next) {
            if (!visited[temp.to]) continue;
            dfs(temp.to, visited);
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