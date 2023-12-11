import java.util.ArrayList;

public class Molly4 {
//Based on Molly3, but make the findPrimePartition method recursive so it takes the highest prime and works down recursively

	public static ArrayList<int[]> findPrimePartition(int num, ArrayList<int[]> partitionsList, ArrayList<Integer> primesList, ArrayList<Integer> numCheckList) {
		// System.out.println(primesList.size());
		//System.out.println(numCheckList.get(numCheckList.size()-1));
		int[] partition = new int[4];
		for (int i = 0; i < primesList.size(); i++) {
			partition[0] = i;
			if (primesList.contains(num - primesList.get(i) * 2)) {
				partition[0] = num;
				partition[1] = primesList.get(i);
				partition[2] = primesList.get(i);
				partition[3] = num - 2 * primesList.get(i);
				partitionsList.add(partition);
				//System.out.println(partition[0] + "=" + partition[1] + "+" + partition[2] + "+" + partition[3]);
			}
		}
		//System.out.println("------------");
		if (((primesList.get(0) == 1) && primesList.get(primesList.size()-1) != 3) || ((primesList.get(0) == 2) && primesList.get(primesList.size()-1) != 7)) {
			num = primesList.remove(primesList.size()-1);
			while (numCheckList.get(numCheckList.size() - 2) * 2 > num) {
				numCheckList.remove(numCheckList.get(numCheckList.size() - 1));
			}
			findPrimePartition(num, partitionsList, primesList, numCheckList);
		}
		return partitionsList;
	}

	public static void main(String[] args) {
		int num = 25000;
		boolean withOne = true;
		ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(num);
		if (withOne) {
			primesList.add(0, 1);
		}
		ArrayList<Integer> numCheckList = new ArrayList<Integer>(primesList);// list half size numbers to check i against
		while (numCheckList.get(numCheckList.size() - 2) * 2 > primesList.get(primesList.size()-1)) {
			numCheckList.remove(numCheckList.get(numCheckList.size() - 1));
		}
		
		//System.out.println(primesList.size() + "\n-------");
		findPrimePartition(primesList.get(primesList.size()-1), new ArrayList<int[]>(), primesList, numCheckList);
		System.out.println("done");
	}
}
