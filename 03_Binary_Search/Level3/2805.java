// 나무 자르기
// https://www.acmicpc.net/problem/2805
// 힌트
// 1. Binary Search를 통해 적정 자르는 나무의 범위를 좁힌다.
//    자르는 나무높이의 범위는 [0, 가장 큰 나무] 이다.
// 2. 대소관계에 유의하자.
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int v_max;
	static int tree[];
	
	static boolean solve(int k) {
		long total = 0;
		for (int i = 0; i < N; i++) {
			total += Math.max(0,  tree[i] - k);
		}
		
		if (total >= M) {
			return true;
		} else {
			return false;
		}
	}
	
	static int bs() {
		int l = 0;
		int r = v_max;
		int m = 0;
		
		while (l <= r) {
			m = (l + r) / 2;
			
			if (solve(m)) {
				 l = m + 1;
			} else {
				r = m - 1;
			}	
		}
		
		return r;
	}
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		tree = new int[N];
		v_max = 0;
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			v_max = Math.max(v_max, tree[i]);
		}
		
		int answer = bs();
		
		System.out.println(answer);
		
		
	}
}

