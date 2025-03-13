package swea;

import java.io.*;
import java.util.*;

class Microbe {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    int x, y;
    int count;
    int dir;

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

class Cell {
    int sum;      // 미생물 합
    int max;      // 최대 미생물 개수
    int dir;      // 최대 미생물 개수를 가진 군집의 방향

    public Cell(int sum, int max, int dir) {
        this.sum = sum;
        this.max = max;
        this.dir = dir;
    }
}


public class SWEA2382 {
    // 상하좌우 방향 벡터
    static int N, M, K, T;
    static List<Microbe> microbes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
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
                    // 움직이고, 약품처리
                    m.move();
                    if (m.check(N)) m.treat();
                }

                Map<String, Cell> map = new HashMap<>();
                for (Microbe m : microbes) {
                    if(m.count <= 0) continue;
                    
                    String key = m.x + "," + m.y;
                    if(!map.containsKey(key)){
                        //초기 등록
                        map.put(key, new Cell(m.count, m.count, m.dir));
                    } else {
                      //이미 같은 좌표에 미생물이 있다면  
                        Cell c = map.get(key);
                        c.sum += m.count;
                        // 더 큰 미생물 수를 가진 방향으로 갱신
                        if(m.count > c.max) {
                            c.max = m.count;
                            c.dir = m.dir;
                        }
                        map.put(key, c);
                        // 세포 갱신
                    }
                }
                microbes.clear(); // 미생물 리스트 재구성
                for(Map.Entry<String, Cell> entry : map.entrySet()){
                    Cell c = entry.getValue();
                    String[] xy = entry.getKey().split(",");
                    int xx = Integer.parseInt(xy[0]);
                    int yy = Integer.parseInt(xy[1]);
                    microbes.add(new Microbe(xx, yy, c.sum, c.dir));
                }
            }

            int total = 0;
            for (Microbe m : microbes) {
                total += m.count;
            }
            bw.write("#"+t+" "+total);
            bw.newLine();
        }
        bw.flush();
    }
}


