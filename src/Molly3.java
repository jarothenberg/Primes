import java.util.ArrayList;

public class Molly3 {
//find prime partitions, adding primes together to find every number

	public static ArrayList<int[]> findPrimePartition(int num, ArrayList<Integer> primesList) {
		// System.out.println(primesList.size());
		ArrayList<int[]> partitionsList = new ArrayList<int[]>();
		int[] partition = new int[4];
		for (int i = 0; i < primesList.size(); i++) {
			partition[0] = i;
			if (primesList.contains(num - primesList.get(i) * 2)) {
				partition[0] = num;
				partition[1] = primesList.get(i);
				partition[2] = primesList.get(i);
				partition[3] = num - 2 * primesList.get(i);
				partitionsList.add(partition);
				System.out.println(partition[0] + "=" + partition[1] + "+" + partition[2] + "+" + partition[3]);
			}
		}
		return partitionsList;
	}

	public static void main(String[] args) {
		int num = 50000;
		boolean withOne = true;
		ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(num);
		if (withOne) {
			primesList.add(0, 1);
		}
		ArrayList<Integer> numCheckList = new ArrayList<Integer>(primesList);// list half size numbers to check i against
		for (int i = primesList.size() - 1; i > 0; i--) {	
			while (numCheckList.get(numCheckList.size() - 2) * 2 > primesList.get(i)) {
				numCheckList.remove(numCheckList.get(numCheckList.size() - 1));
			}
			findPrimePartition(primesList.get(i), numCheckList);
			primesList.remove(primesList.get(primesList.size() - 1));
//			System.out.println("------------");
		}
		System.out.println("done");
	}
}
