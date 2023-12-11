import java.util.ArrayList;
import java.util.LinkedList;

public class Molly6 {
//find prime partitions, adding primes together to find every number

	public static LinkedList<int[]> findPrimePartition(int num, boolean withOne, boolean print) {
		ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(num);
		if (withOne) {
			primesList.add(0, 1);
		}
		return findPrimePartitionCont(print, primesList.get(primesList.size()-1), withOne, primesList, new ArrayList<Integer>(primesList), new LinkedList<int[]>());
	}
	
	public static LinkedList<int[]> findPrimePartitionCont(boolean print, int num, boolean withOne, ArrayList<Integer> primesList, ArrayList<Integer> numCheckList, LinkedList<int[]> partitionsList) {
		while (numCheckList.get(numCheckList.size() - 2) * 2 > num) {
			numCheckList.remove(numCheckList.get(numCheckList.size() - 1));
		}
		for (int i = 0; i < numCheckList.size(); i++) {
			if (numCheckList.contains(num - numCheckList.get(i) * 2)) {
				partitionsList.add(new int[] {num, numCheckList.get(i), numCheckList.get(i), num-2*numCheckList.get(i)});
				if (print) {
					System.out.println(partitionsList.get(partitionsList.size()-1)[0] + "=" + partitionsList.get(partitionsList.size()-1)[1] + "+" + partitionsList.get(partitionsList.size()-1)[2] + "+" + partitionsList.get(partitionsList.size()-1)[3]);
				}
			}
		}
		primesList.remove(primesList.get(primesList.size() - 1));
		if ((withOne && primesList.size() > 1) || (!withOne && primesList.size() > 2)) {
			if (print) {
				System.out.println("------------");
			}
			findPrimePartitionCont(print, primesList.get(primesList.size()-1), withOne, primesList, numCheckList, partitionsList);
		}
		return partitionsList;
	}

	public static void main(String[] args) {
		int num = 100;
		boolean withOne = false;
		boolean print = true;
		findPrimePartition(num, withOne, print);
		System.out.println("done");
	}
}
