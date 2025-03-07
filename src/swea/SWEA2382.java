package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Microbe {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int x, y;
    static int count;
    static int dir;

    public Microbe(int x, int y, int count, int dir) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.dir = dir;
    }

    public void move() {
        x += dx[dir];
        y += dy[dir];
    }

    public boolean check(int N) {
        // 경계선에 들어가면
        if (x == 0 || y == 0 || x == N - 1 || y == N - 1) {
            return true;
        }
        return false;
    }

    public void treat() {
        // 미생물 수를 반으로 int형이라 알아서 소수점 잘림
        count = count / 2;

        // 상하좌우 반대로
        if (dir == 0) dir = 1;
        else if (dir == 1) dir = 0;
        else if (dir == 2) dir = 3;
        else if (dir == 3) dir = 2;
    }


}


public class SWEA2382 {
    // 상하좌우 방향 벡터
    static int N, M, K;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Microbe> microbes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        microbes = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            microbes.add(new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
        }

        // 격리 시간
        for (int i = 0; i < M; i++) {
            // 일단 움직이기
            for (Microbe m : microbes) {
                m.move();
            }
            // 격리공간에 들어간 애들 체크
            for (Microbe m : microbes) {
                m.check(N);
            }
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    // 좌표가 겹치는 미생물들
                    List<Integer> list = new ArrayList<>();
                    for (int j = 0; j < microbes.size(); j++) {
                        if (microbes.get(j).x == x && microbes.get(j).y == y) list.add(j);
                    }
                    //겹치는 미생물이 없으면 패스
                    if (list.size() != 0) {
                        int total = 0;
                        for (Microbe m : microbes) {
                            total + =m.count
                        }

                    }
                }
            }
        }

    }

    static Microbe merge(Microbe m1, Microbe m2) {

    }
}


