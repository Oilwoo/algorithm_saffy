package ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class AdjMatrixTest {
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
            int weight = sc.nextInt();
            adjMatrix[to][from] = adjMatrix[from][to] = weight;
        }
        for (int[] am : adjMatrix) {
            System.out.println(Arrays.toString(am));
        }

    }
}

//7
//        8
//        0 1 10
//        0 2 6
//        0 5 7
//        0 6 3
//        4 3 6
//        5 3 8
//        5 4 9
//        6 4 20