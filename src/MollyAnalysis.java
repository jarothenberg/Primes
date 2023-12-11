import java.util.ArrayList;
import java.util.LinkedList;

public class MollyAnalysis {
//find prime partitions, adding primes together to find every number
	
	public static void numRepeatsInPartitions(LinkedList<int[]> test) {
		ArrayList<Integer> huh = new ArrayList<Integer>();
		for (int[] ia : test) {
			for (int ib : ia) {
				huh.add(ib);
			}
		}
		huh.sort(null);
		huh.add(0,0);
		huh.add(huh.size(),0);
		int curCount = 0;
		int curNum;
		int preNum = 0;
		for (int i : huh) {
			curNum = i;
			if (curNum == preNum) {
				curCount++;
			}
			else if (preNum != 0){
				System.out.println(preNum+":"+curCount);
				curCount = 1;
			}
			preNum = curNum;
		}
	}
	
	public static int[][] numPartitions(LinkedList<int[]> test) {
		ArrayList<Integer> huh = new ArrayList<Integer>();
		int[][] data = new int[test.getFirst()[0]][2];
		for (int[] ia : test) {
			huh.add(ia[0]);
		}
		huh.sort(null);
		huh.add(0,0);
		huh.add(huh.size(),0);
		int curCount = 0;
		int curNum;
		int preNum = 0;
		int count = 0;
		for (int i : huh) {
			curNum = i;
			if (curNum == preNum) {
				curCount++;
			}
			else if (preNum != 0){
				//System.out.println(preNum+","+curCount);
				data[count][0] = preNum;
				data[count][1] = curCount;
				count++;
				curCount = 1;
			}
			preNum = curNum;
		}
		return data;
	}

	//https://www.youtube.com/watch?v=DmfxIhmGPP4
	
	public static void main(String[] args) {
		int num = 1000;
		boolean withOne = true;
		boolean print = true;
		numPartitions(Molly6.findPrimePartition(num, withOne, print));
	}
}