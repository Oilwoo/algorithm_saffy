package boj;

import java.io.*;
import java.util.*;

public class BOJ32251 {
    static int N, Q;
    static List<Integer>[] adj;
    static long[] fruit;
    // ▶ 자식만 골라내기 위한 parent 배열
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        // 1) 무방향 트리 구성
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // 2) fruit를 long으로 변경
        fruit = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            fruit[i] = Long.parseLong(st.nextToken());
        }

        // 3) 루트(1) 기준으로 parent[] 계산 (BFS)
        parent = new int[N+1];
        Arrays.fill(parent, -1);
        parent[1] = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(1);
        while (!dq.isEmpty()) {
            int u = dq.poll();
            for (int v : adj[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    dq.offer(v);
                }
            }
        }

        // 4) 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int u = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                pour(u, w);
            } else {
                int u = Integer.parseInt(st.nextToken());
                sb.append(fruit[u]).append('\n');
            }
        }
        System.out.print(sb);
    }

    // ▶ 이제 물은 무조건 “parent[v] == u” 인 v(=자식) 으로만 흘러갑니다.
    static void pour(int start, long water) {
        Deque<long[]> dq = new ArrayDeque<>();
        dq.offer(new long[]{start, water});

        while (!dq.isEmpty()) {
            long[] cur = dq.poll();
            int u = (int) cur[0];
            long w = cur[1];

            // 1) 흡수
            long absorb = Math.min(w, fruit[u]);
            fruit[u] += absorb;
            long left = w - absorb;
            if (left <= 0) continue;

            // 2) 실제 자식 수만 세기
            int cnt = 0;
            for (int v : adj[u]) {
                if (parent[v] == u) cnt++;
            }
            if (cnt == 0) continue;

            // 3) 분배량
            long per = left / cnt;
            if (per <= 0) continue;

            // 4) 자식에게만 재귀(큐)에 넣기
            for (int v : adj[u]) {
                if (parent[v] == u) {
                    dq.offer(new long[]{v, per});
                }
            }
        }
    }
}
