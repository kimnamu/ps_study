// 가장 긴 증가하는 부분 수열 2
// https://www.acmicpc.net/problem/12015
// 힌트
// 1. "가장 긴 증가하는 부분 수열"을 위한 "특별한 수열"을 만들어 준다. 
//    이 "특별한 수열"은 n번째 값이 n번 증가하는데 사용될 수 중 가장 작은 수를 입력한다.
//    그러면 이 특별한 수열의 길이가 "가장 긴 증가하는 부분 수열"의 길이가 된다.
// 2. 수열의 순서가 바뀌면 안되기 때문에 입력받는 수를 바로바로 "특별한 수열"값을 갱신해 준다.
//   2.1 새로 입력된 수가 "특별한 수열"의 마지막 수 보다 크다면, 특별한 수열 뒤에 값을 추가해주면된다. 이때 "특별한 수열"이 길어지는 만큼 답도 커진다.
//   2.2 새로 입력된 수가 "특별한 수열"의 마지막 수 보다 작다면, 새로 입력된 수보다 크거나 같은 값의 위치에 새롭게 갱신해준다.
// 3. 이를 구현하기 위해 모든 수가 1번째 수가 될수 있도록 0번째 수는 모든 수보다 작은 0을 넣어주고 시작한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer> v;
	
	static int bs(int temp)
	{
	    int l = 0;
	    int r = v.size()-1;
	    int m = (l + r) / 2;
	    while (l <= r)
	    {
	        m = (l + r) / 2;
	        if (v.get(m) < temp)
	        {
	            l = m + 1;
	        }
	        else
	        {
	            r = m - 1;
	        }
	    }
	    return l;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		v = new ArrayList<>();
		v.add(0);
		int temp, cnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			temp = Integer.parseInt(st.nextToken());
			
			if (temp > v.get(v.size()-1)) {
				v.add(temp);
				cnt += 1;
			} else {
				int loc = bs(temp);
				v.set(loc, temp);
			}
		}
		
		System.out.println(cnt);
	}

}
