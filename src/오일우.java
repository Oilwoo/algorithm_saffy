import java.util.*;

public class 오일우 {
    static int N;
    static int[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            String direction = sc.next();
            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = sc.nextInt();
                }
            }

            move(direction);

            System.out.println("#" + t);
            for (int[] row : board) {
                for (int num : row) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
        sc.close();
    }

    static void move(String direction) {
        switch (direction) {
            case "left":
                for (int i = 0; i < N; i++) merge(board[i]);
                break;
            case "right":
                for (int i = 0; i < N; i++) {
                    reverse(board[i]);
                    merge(board[i]);
                    reverse(board[i]);
                }
                break;
            case "up":
                transpose();
                for (int i = 0; i < N; i++) merge(board[i]);
                transpose();
                break;
            case "down":
                transpose();
                for (int i = 0; i < N; i++) {
                    reverse(board[i]);
                    merge(board[i]);
                    reverse(board[i]);
                }
                transpose();
                break;
        }
    }

    static void merge(int[] row) {
        int[] temp = new int[N];
        int index = 0;
        boolean merged = false;

        for (int i = 0; i < N; i++) {
            if (row[i] == 0) continue;
            if (index > 0 && temp[index - 1] == row[i] && !merged) {
                temp[index - 1] *= 2;
                merged = true;
            } else {
                temp[index++] = row[i];
                merged = false;
            }
        }
        System.arraycopy(temp, 0, row, 0, N);
    }

    static void reverse(int[] row) {
        for (int i = 0, j = N - 1; i < j; i++, j--) {
            int temp = row[i];
            row[i] = row[j];
            row[j] = temp;
        }
    }

    static void transpose() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
    }
}
