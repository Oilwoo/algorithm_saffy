package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {
    static int N;
    static int[] pop;
    static List<Integer>[] adj;
    static boolean[] selected;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pop = new int[N + 1];
        adj = new ArrayList[N + 1];

        // 인접리스트 초기화
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 1 ~ N 인구수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pop[i] = Integer.parseInt(st.nextToken());
        }

        // 1 ~ N 인접리스트
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 조합 배열 초기화
        selected = new boolean[N + 1];

        // 팀(당)의 크기의 갯수를 1~N까지 바꿔가며 시도 N-1을 한 이유는 한쪽이 다 차지하면 안되기떄문에
        for (int sizeA = 1; sizeA <= N - 1; sizeA++) {
            combine(1, 0, sizeA);
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }

    static void combine(int idx, int cnt, int target) {
        if (cnt == target) {
            // 두 팀의 각 선거구는 서로 연결되어있어야한다
            if (isConnected(true, target) && isConnected(false, target)) {
                //인구 합 차이 계산
                int sumA = 0, sumB = 0;
                for (int i = 1; i <= N; i++) {
                    if (selected[i]) {
                        sumA += pop[i];
                    } else {
                        sumB += pop[i];
                    }
                }
                ans = Math.min(ans, Math.abs(sumA - sumB));
            }
            return;
        }

        if (idx > N) {
            return;
        }

        selected[idx] = true;
        combine(idx + 1, cnt + 1, target);

        selected[idx] = false;
        combine(idx + 1, cnt, target);
    }

    static boolean isConnected(boolean team, int expectedCnt) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();

        // 해당 당에 속한 시작점 찾기
        for (int i = 1; i <= N; i++) {
            if (selected[i] == team) { //A = true , B = false
                q.add(i);
                visited[i] = true;
                break;
            }
        }
        if (q.isEmpty()) {
            return false;
        }

        int count = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            count++;
            for (int v : adj[u]) {
                if (!visited[v] && selected[v] == team) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        return count == expectedCnt;
    }
}
