package boj;

import java.io.*;

public class BOJ5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();

        String check = "I";
        for (int i = 1; i <= N; i++) {
            check += "OI";
        }

        int count = 0;
        for (int i = 0; i < M - (2*N); i++) {
            String range = str.substring(i, i + (2*N) + 1);
            if(check.equals(range)) count++;
        }
        System.out.println(count);
    }
}
