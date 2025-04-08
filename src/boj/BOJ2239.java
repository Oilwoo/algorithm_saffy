package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2239 {
    // 스도쿠 보드를 저장할 2차원 배열
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        // BufferedReader를 이용하여 입력을 받습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';  // 각 문자를 숫자로 변환
            }
        }

        // (0,0)부터 시작해서 스도쿠 퍼즐을 백트래킹으로 해결합니다.
        solve(0, 0);
    }

    // solve 메서드: 현재 (row, col)부터 해답을 채워나가는 재귀 함수
    static void solve(int row, int col) {
        // 행 번호가 9이면, 모든 행을 처리한 것이므로 해답이 완성됨.
        if (row == 9) {
            printBoard();
            // 해답이 완성되었으므로, 프로그램을 바로 종료합니다.
            System.exit(0);
        }

        // 한 행의 끝(열 번호가 9)이면, 다음 행의 0번 열부터 진행
        if (col == 9) {
            solve(row + 1, 0);
            return;
        }

        // 현재 칸이 이미 채워진 경우(0이 아니라면) 다음 칸으로 진행
        if (board[row][col] != 0) {
            solve(row, col + 1);
            return;
        }

        // 빈 칸인 경우, 1부터 9까지 숫자를 시도
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) { // 해당 숫자를 놓을 수 있는지 검증
                board[row][col] = num;    // 가능하면 숫자를 배치하고
                solve(row, col + 1);      // 다음 칸으로 진행

                // (해답을 찾은 후 바로 System.exit(0)하므로,
                // 여기에 도달하면 아직 해답이 완성되지 않은 경우입니다.)
            }
        }

        // 가능한 숫자들을 모두 시도했는데 해답으로 이어지지 않으면, 현재 칸을 다시 0으로 초기화(백트래킹)
        board[row][col] = 0;
    }

    // isValid 메서드: board[row][col]에 num을 넣어도 되는지 확인
    static boolean isValid(int row, int col, int num) {
        // 같은 행에 이미 num이 사용되었는지 확인
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) return false;
        }

        // 같은 열에 이미 num이 사용되었는지 확인
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) return false;
        }

        // 3×3 박스 내에서 num이 사용되었는지 확인
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }

    // printBoard 메서드: 해답을 출력하는 함수
    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}
