package ssafy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class DFS1 {
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
        dfs(0, new boolean[V]);
    }

    private static void dfs(int current, boolean[] visited) {
        visited[current] = true;
        System.out.print((char) (current+65));
        for (int i = 0; i < V; i++) {
            if(adjMatrix[current][i] != 1 || visited[i]) continue; // 인접하지 않은 정점이거나 방문된 상태면 skip
            dfs(i, visited);
        }
    }
}
