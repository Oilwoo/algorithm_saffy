package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3307 {
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            int[] lis = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            for (int i = 0; i < N; i++) {
                lis[i] = 1;
                for (int j = 0; j < i; j++) {
                    if(arr[j] < arr[i] && lis[i] < lis[j]+1)
                        lis[i] = lis[j]+1;
                }
                max = Math.max(max, lis[i]);
            }
            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
