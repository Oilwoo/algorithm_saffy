package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Atom {
    int x, y, d, e;

    Atom(int x, int y, int d, int e) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.e = e;
    }
}

public class SWEA5648 {
    // 방향 벡터: 상, 하, 좌, 우 (입력 좌표에 2를 곱했으므로, 0.5 대신 1씩 이동)
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    // 좌표 범위 (입력값 * 2): 원래 -1000 ~ 1000 → -2000 ~ 2000
    static final int OFFSET = 2000; // 음수 좌표 보정
    static final int GRID_SIZE = 4001; // (-2000 부터 2000)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            List<Atom> atoms = new ArrayList<>();

            // 원자 입력 (좌표를 정수로 스케일링)
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                atoms.add(new Atom(x * 2, y * 2, d, e));
            }

            int result = 0;
            // 시뮬레이션: 남은 원자 목록이 없을 때까지 반복
            while (!atoms.isEmpty()) {
                // 좌표별 원자 그룹을 저장할 맵 (정수 키 사용)
                Map<Integer, List<Atom>> collisionMap = new HashMap<>();

                // 각 원자 이동 처리
                for (Atom a : atoms) {
                    a.x += dx[a.d];
                    a.y += dy[a.d];
                    // 경계 밖으로 벗어난 원자는 다음 단계에 포함하지 않음
                    if (a.x < -OFFSET || a.x > OFFSET || a.y < -OFFSET || a.y > OFFSET)
                        continue;
                    // key 계산: (a.x + OFFSET) * GRID_SIZE + (a.y + OFFSET)
                    int key = (a.x + OFFSET) * GRID_SIZE + (a.y + OFFSET);
                    collisionMap.computeIfAbsent(key, k -> new ArrayList<>()).add(a);
                }

                // 충돌에 의한 에너지 소멸 처리 및 살아남은 원자 갱신
                List<Atom> survivors = new ArrayList<>();
                for (List<Atom> group : collisionMap.values()) {
                    if (group.size() >= 2) { // 두 개 이상의 원자가 충돌하면 모두 소멸
                        for (Atom a : group) {
                            result += a.e;
                        }
                    } else { // 충돌이 없으면 다음 단계에 살아남은 원자로 추가
                        survivors.add(group.get(0));
                    }
                }
                atoms = survivors;
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
