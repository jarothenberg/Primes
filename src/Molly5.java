import java.util.ArrayList;
import java.util.LinkedList;

public class Molly5 {
//find prime partitions, adding primes together to find every number

	public static LinkedList<int[]> findPrimePartition(int num, ArrayList<Integer> primesList, ArrayList<Integer> numCheckList) {
		// System.out.println(primesList.size());
		while (numCheckList.get(numCheckList.size() - 2) * 2 > num) {
			numCheckList.remove(numCheckList.get(numCheckList.size() - 1));
		}
		LinkedList<int[]> partitionsList = new LinkedList<int[]>();
		int[] partition = new int[4];
		for (int i = 0; i < numCheckList.size(); i++) {
			if (numCheckList.contains(num - numCheckList.get(i) * 2)) {
				partition[0] = num;
				partition[1] = numCheckList.get(i);
				partition[2] = numCheckList.get(i);
				partition[3] = num - 2 * numCheckList.get(i);
				partitionsList.add(partition);
//				System.out.println(partition[0] + "=" + partition[1] + "+" + partition[2] + "+" + partition[3]);
			}
		}
		primesList.remove(primesList.get(primesList.size() - 1));
		return partitionsList;
	}

	public static void main(String[] args) {
		int num = 50000;
		boolean withOne = true;
		int first = 1;
		ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(num);
		if (withOne) {
			primesList.add(0, 1);
			first = 0;
		}
		ArrayList<Integer> numCheckList = new ArrayList<Integer>(primesList);// list half size numbers to check i against
		for (int i = primesList.size() - 1; i > first; i--) {
//			System.out.println("------------");
			findPrimePartition(primesList.get(i), primesList, numCheckList);
		}
		System.out.println("done");
	}
}
