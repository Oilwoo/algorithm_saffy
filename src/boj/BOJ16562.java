package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ16562 {
    static class Edge implements Comparable<BOJ1197.Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(BOJ1197.Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N, M, K;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        price = new int[N + 1];
        input = br.readLine().split(" ");
        for (int i = 1; i < N; i++) {
            price[i] = Integer.parseInt(input[i]);
        }

        ArrayList<Edge>[] edges = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        // 간선정보 입력받기
        for (int i = 1; i < M; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            //양방향으로 설정
            edges[a].add(new Edge(b,price[a])); // 시작점의 친구비용이 가중치랑
        }

    }
}
