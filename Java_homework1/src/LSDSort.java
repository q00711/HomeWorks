import java.util.Arrays;
import java.util.Random;

public class LSDSort {

	private static final int MAX = 1000000;

	public static void main(String[] args) {

		int[] data = generate();
		long t1, t2;
		int[] data2=(int[])(data.clone()); //copy
		

//		System.out.println(data.length-data2.length);
		long start = System.currentTimeMillis();
		Arrays.sort(data);
		long stop = System.currentTimeMillis();
		t1=stop - start;
		System.out.println("Arrays.sort = " + t1);
		
		start = System.currentTimeMillis();
		MyLSDSort(data2);  //should take another data
		stop = System.currentTimeMillis();
		t2=stop - start;
		System.out.println("LSDSort = " + t2);
		System.out.println("Difference = " + (t1-t2));
//		for (int i=0; i<50; i++) {
//			System.out.println(data[i]);
//			System.out.println(data2[i]);
//		}
	}

	private static int[] generate() {
		int[] data = new int[MAX];

		Random random = new Random();

		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(MAX); // 0 - 999999
		}

		return data;
	}
	
	private static int[] MyLSDSort(int[] data) {	
		int R = 256;
		int[] tmpData = new int[MAX];
		int mask = R - 1;
		int n1 = 32;
		int n2 = 4;
		int n3 = n1/n2;
		
		
		for (int iter = 0; iter < n2; ++iter) {
			
			int[] count = new int[R+1];
			
			for (int i = 0; i < MAX; ++i) {
				int byteInd = (( data[i] >> n3*iter ) & mask );
				count[byteInd+1]++;
			}
			
			for (int r = 0; r < R; ++r)
				count[r+1] += count[r];
			
			for (int i = 0; i < MAX; ++i) {
				int byteInd = (( data[i] >> n3*iter ) & mask );
				tmpData[count[byteInd]++] = data[i];
			}
			
			for (int i= 0; i < MAX; ++i)
				data[i] = tmpData[i];
		}

		return data;
	}	
	
}
