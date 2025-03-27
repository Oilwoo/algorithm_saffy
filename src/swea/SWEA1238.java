package swea;

import java.io.*;
import java.util.*;

public class SWEA1238 {
    static int T, N, S, ans;
    static List<Integer> visited;
    static Map<Integer, List<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = 10;

        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            S = Integer.parseInt(input[1]);

            visited = new ArrayList<>();
            map = new HashMap<Integer, List<Integer>>();

            // 간선 정보 입력받기
            input = br.readLine().split(" ");
            for (int i = 0; i < N; i+=2) {
                int s = Integer.parseInt(input[i]);
                int e = Integer.parseInt(input[i+1]);
                // 이미 존재하면 인접리스트에 추가
                if (map.containsKey(s)) {
                    map.get(s).add(e);
                } else { // 새로 넣는 숫자는
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(e);
                    map.put(s, list);
                }
            }
            //bfs 수행 마지막으로 연락받은 사람 찾기
            bfs();
            bw.write("#" + t + " " + ans);
            bw.newLine();
        }

        bw.flush();
    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[101];

        queue.offer(S); //시작
        visited[S] = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); //연락 레벨 연락 단계에서 처리해야할 수
            int maxAtLevel = 0;

            // 한 레벨의 노드를 모두 처리하기
            for(int i = 0; i < levelSize; i++) {
                int cur = queue.poll();
                // 해당 레벨에서 최댓값 갱신
                maxAtLevel = Math.max(maxAtLevel, cur);

                // 현재 노드와 연결된 모든 노드를 방문처리
                if(map.containsKey(cur)) {
                    for(int next : map.get(cur)) {
                        if(!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                }
            }
            //해당 단계에서 가장 높은 숫자 -> 마지막 으로 연락받은 사람들중 가장 높은 수
            ans = maxAtLevel;
        }
    }
}
