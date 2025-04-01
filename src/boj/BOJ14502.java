package boj;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

class Virus {
    int x,y;
    public Virus(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

public class BOJ14502 {
    static int N,M, max, wall;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        matrix = new int[N][M];
        wall = 0;
        max = 0;

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
                if(matrix[i][j] == 1) wall++;
            }
        }

        makeWall(0);

        System.out.println(max);

    }

    //dfs 벽을 만들기
    static void makeWall(int depth) {
        if(depth == 3) { //벽을 3개 세우면 바이러스 감영 체크하기
            infection();
            return;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) { // 빈칸이면 벽을 세우고
                    matrix[i][j] = 1;
                    makeWall(depth + 1); //3개를 세울때까지 반복
                    matrix[i][j] = 0; //되돌리기 (백트랙?)
                }
            }
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    // bfs 기반 바이러스 감영 후 안정 영역 구하기
    static void infection() {
        int [][] copyMatrix = new int[N][M];
        //맵을 복사
        for(int i = 0; i < N; i++) {
            copyMatrix[i] = matrix[i].clone();
        }

        ArrayDeque<Virus> q = new ArrayDeque<Virus>();
        int virus = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(copyMatrix[i][j] == 2) {
                    q.offer(new Virus(i,j));
                    virus++;
                }
            }
        }

        //맵 전체 감염시키기
        while(!q.isEmpty()) {
            Virus v = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];
                if(nx >= 0 && nx < N && ny >=0 && ny < M) {
                   if(copyMatrix[nx][ny] == 0) {
                       copyMatrix[nx][ny] = 2;
                       q.add(new Virus(nx,ny));
                       virus++;
                   }
                }
            }
        }

        //안전영역 숫자 구하기
        int safeSpace = (N * M) - wall - virus - 3;
        max = Math.max(max, safeSpace);
    }
}
