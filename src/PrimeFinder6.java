public class PrimeFinder6 {

	public static int getPrimes(int end) {
		byte[] numbersBool = new byte[end + 1];
		for (int i = 2; i * i <= end; i++) {
			if (numbersBool[i] == 0) {
				for (int j = i * 2; j <= end; j += i) {
					numbersBool[j] = 1;
				}
			}
		}
		int count = -2;
		for (byte b : numbersBool) {
			if (b == 0) {
				count++;
			}
		}
		return count;
	}

	public static void main(String args[]) {
		int end = 100000000;	//the number in which the program will return the number of primes which come before and including it.
						//WARNING: do not enter any negative number
		System.out.println(getPrimes(end));//13
	}
}