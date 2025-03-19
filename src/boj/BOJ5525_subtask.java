package boj;

import java.io.*;

public class BOJ5525_subtask {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();

        String check = "IOI";
        int count = 0;
        int ans = 0;
        int i = 0;
        while(i < M - 2) {
            //문자열 자르기
            String range = str.substring(i, i + 3);
            // IOI인지 체크
            // OI가 나오면 인덱스를 +2 해도 되니까, 속도를 빠르게 할 수 있음
            if(range.equals(check)) {
                i += 2;
                count += 1;
                //Pn 임을 체크 count == N이 같아야 IOI * N 이니까
                if(count == N) {
                    ans += 1;
                    count -= 1;
                    // 다른 Pn이 나올수도 있기 때문
                }
            } else {
                i += 1; //다음 인덱스;
                count = 0;
            }
        }
        System.out.println(ans);
    }
}
