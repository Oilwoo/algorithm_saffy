package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class SWEA1267 {
    static int V, E;
    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = 10;

        for (int t = 1; t <= T; t++) {
            String[] Input = br.readLine().split(" ");
            V = Integer.parseInt(Input[0]);
            E = Integer.parseInt(Input[1]);

            count = new int[V + 1];
            List<Integer> answer = new ArrayList<>();
            Map<Integer, List<Integer>> adjMap = new HashMap<Integer, List<Integer>>();

            for (int i = 1; i <= V; i++) {
                adjMap.put(i, new ArrayList<>());
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjMap.get(a).add(b);
                count[b] += 1; // 진입차수
            }

            // 큐에다가 시작점 노드들을 넣어준다
            // 진입차수가 0인 노드들을 큐에 넣는다
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= V; i++) {
                if (count[i] == 0) {
                    q.add(i);
                }
            }

            while (!q.isEmpty()) {
               int n = q.poll();
               answer.add(n); //정답 순서에 기록
               // 2. 인접합 노드들의 진입차수를 -1 하고 0이면 큐에 넣기
               List<Integer> list = adjMap.get(n);
               for(int i : list) {
                   count[i] -= 1;
                   // 3. 간선 제거 후 진입차수가 0이 된 정점을 큐에 넣는다.
                   if(count[i] == 0) {
                       q.add(i);
                   }
               }
            }

            bw.write("#" + t +" ");
            for(int a : answer) {
                bw.write(String.valueOf(a)+ " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
