import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;


public class functions {
	private static double num = -1;
	private static double num2 = 0;
	private static int count = 2000;

	public static double incompleteGammaRecursion(double x, int count) {
		count--;
		num += 2;
		num2 += num;
		if (count > 0) {
			// System.out.println("num1: " + num + " num2: " + num2 + " count: " + count);
			return (x + num) - (num2 / incompleteGammaRecursion(x, count));
		}
		return 0;
	}

	public static double incompleteGamma(double x) {
		return (Math.pow(Math.E, -1 * x) / incompleteGammaRecursion(x, count));
	}

	public static BigDecimal log(double e, BigDecimal x) {
		BigDecimal result = BigDecimal.ZERO;

		BigDecimal input = new BigDecimal(x.toString());
		int decimalPlaces = 100;
		int scale = input.precision() + decimalPlaces;

		int maxite = 10000;
		int ite = 0;
		BigDecimal maxError_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces + 1);
		// System.out.println("maxError_BigDecimal " + maxError_BigDecimal);
		// System.out.println("scale " + scale);

		RoundingMode a_RoundingMode = RoundingMode.UP;

		BigDecimal two_BigDecimal = new BigDecimal("2");
		BigDecimal base_BigDecimal = new BigDecimal(e);

		while (input.compareTo(base_BigDecimal) == 1) {
			result = result.add(BigDecimal.ONE);
			input = input.divide(base_BigDecimal, scale, a_RoundingMode);
		}

		BigDecimal fraction = new BigDecimal("0.5");
		input = input.multiply(input);
		BigDecimal resultplusfraction = result.add(fraction);
		while (((resultplusfraction).compareTo(result) == 1) && (input.compareTo(BigDecimal.ONE) == 1)) {
			if (input.compareTo(base_BigDecimal) == 1) {
				input = input.divide(base_BigDecimal, scale, a_RoundingMode);
				result = result.add(fraction);
			}
			input = input.multiply(input);
			fraction = fraction.divide(two_BigDecimal, scale, a_RoundingMode);
			resultplusfraction = result.add(fraction);
			if (fraction.abs().compareTo(maxError_BigDecimal) == -1) {
				break;
			}
			if (maxite == ite) {
				break;
			}
			ite++;
		}

		MathContext a_MathContext = new MathContext(((decimalPlaces - 1) + (result.precision() - result.scale())),
				RoundingMode.HALF_UP);
		BigDecimal roundedResult = result.round(a_MathContext);
		BigDecimal strippedRoundedResult = roundedResult.stripTrailingZeros();
		// return result;
		// return result.round(a_MathContext);
		return strippedRoundedResult;
	}

	public static BigDecimal logIntegral(double x) {
		int j;
		double f;
		BigDecimal d;
		f = -1 * Math.pow(10, 99);
		x = Math.log(x);
		if (x == 1) {
			d = new BigDecimal(Double.NEGATIVE_INFINITY);
			return d;
		}
		if (Math.abs(x - 10) >= 12) {
			j = (int) (5.0 + (20.0 / Math.abs(x)));
			f = x;
			f = (1.0 / (1.0 / f - 1.0 / j)) + x;
			j--;
			while (j != 0) {
				f = (1.0 / (1.0 / f - 1.0 / j)) + x;
				j--;
			}
			f = (Math.pow(Math.E, x) / f);
			d = new BigDecimal(f);
			return d;
		} else {
			if (x == 0) {
				d = new BigDecimal(f);
				return d;
			} else {
				j = (int) (10.0 + (2.0 * Math.abs(x)));
				f = 1.0 / Math.pow((j + 1.0), 2);
				f = ((f * j * x) + 1.0) / Math.pow(j, 2);
				j--;
				while (j != 0) {
					f = ((f * j * x) + 1.0) / Math.pow(j, 2);
					j--;
				}
				f = (f * x);
				BigDecimal temp1 = new BigDecimal(f);
				d = new BigDecimal(
						"0.5772156649015328606065120900824024310421593359399235988057672348848677267776646709369470632917467495146314472498070824809605040144865428362241739976449235362535003337429373377376739427925952582470949160087352039481656708532331517766115286211995015079847937450857057400299213547861466940296043254215190587755352673313992540129674205137541395491116851028079842348775872050384310939973613725530608893312676001724795378367592713515772261027349291394079843010341777177808815495706610750101619166334015227893586796549725203621287922655595366962817638879272680132431010476505963703947394957638906572967929601009015125195950922243501409349871228247949747195646976318506676129063811051824197444867836380861749455169892792301877391072945781554316005002182844096053772434203285478367015177394398700302370339518328690001558193988042707411542227819716523011073565833967348717650491941812300040654693142999297779569303100503086303418569803231083691640025892970890985486825777364288253954925873629596133298574739302373438847070370284412920166417850248733379080562754998434590761643167103146710722370021810745044418664759134803669025532458625442225345181387912434573501361297782278288148945909863846006293169471887149587525492366493520473243641097268276160877595088095126208404544477992299157248292516251278427659657083214610298214617951957959095922704208989627971255363217948873764210660607065982561990102880756125199137511678217643619057058440783573501580056077457934213144988500786415171615194565706170432450750081687052307890937046143066848179164968425491504967243121837838753564894950868454102340601622508515583867234944187880440940770106883795111307872023426395226920971608856908382511378712836820491178925944784861991185293910293099059255266917274468920443869711147174571574573203935209122316085086827558890109451681181016874975470969366671210206304827165895049327314860874940207006742590918248759621373842311442653135029230317517225722162832488381124589574386239870375766285513033143929995401853134141586212788648076110030152119657800681177737635016818389733896639868957932991456388644310370608078174489957958324579418962026049841043922507860460362527726022919682995860988339013787171422691788381952984456079160519727973604759102510995779133515791772251502549293246325028747677948421584050759929040185576459901862692677643726605711768133655908815548107470000623363725288949554636971433012007913085552639595497823023144039149740494746825947320846185246058776694882879530104063491722921858008706770690427926743284446968514971825678095841654491851457533196406331199373821573450874988325560888873528019019155089688554682592454445277281730573010806061770113637731824629246600812771621018677446849595142817901451119489342288344825307531187018609761224623176749775564124619838564014841235871772495542248201615176579940806296834242890572594739269638633838743805471319676429268372490760875073785283702304686503490512034227217436689792848629729088926789777032624623912261888765300577862743606094443603928097708133836934235508583941126709218734414512187803276150509478055466300586845563152454605315113252818891079231491311032344302450933450003076558648742229717700331784539150566940159988492916091140029486902088485381697009551566347055445221764035862939828658131238701325358800625686626926997767737730683226900916085104515002261071802554659284938949277595897540761559933782648241979506418681437881718508854080367996314239540091964388750078900000627997942809886372992591977");
				d = d.add(temp1);
				f = Math.log(Math.abs(x));
				BigDecimal temp2 = new BigDecimal(f);
				d = d.add(temp2);
				return d;
			}
		}
	}

	public static BigDecimal logIntegral2(double x) {
		BigDecimal one = new BigDecimal(
						"0.5772156649015328606065120900824024310421593359399235988057672348848677267776646709369470632917467495146314472498070824809605040144865428362241739976449235362535003337429373377376739427925952582470949160087352039481656708532331517766115286211995015079847937450857057400299213547861466940296043254215190587755352673313992540129674205137541395491116851028079842348775872050384310939973613725530608893312676001724795378367592713515772261027349291394079843010341777177808815495706610750101619166334015227893586796549725203621287922655595366962817638879272680132431010476505963703947394957638906572967929601009015125195950922243501409349871228247949747195646976318506676129063811051824197444867836380861749455169892792301877391072945781554316005002182844096053772434203285478367015177394398700302370339518328690001558193988042707411542227819716523011073565833967348717650491941812300040654693142999297779569303100503086303418569803231083691640025892970890985486825777364288253954925873629596133298574739302373438847070370284412920166417850248733379080562754998434590761643167103146710722370021810745044418664759134803669025532458625442225345181387912434573501361297782278288148945909863846006293169471887149587525492366493520473243641097268276160877595088095126208404544477992299157248292516251278427659657083214610298214617951957959095922704208989627971255363217948873764210660607065982561990102880756125199137511678217643619057058440783573501580056077457934213144988500786415171615194565706170432450750081687052307890937046143066848179164968425491504967243121837838753564894950868454102340601622508515583867234944187880440940770106883795111307872023426395226920971608856908382511378712836820491178925944784861991185293910293099059255266917274468920443869711147174571574573203935209122316085086827558890109451681181016874975470969366671210206304827165895049327314860874940207006742590918248759621373842311442653135029230317517225722162832488381124589574386239870375766285513033143929995401853134141586212788648076110030152119657800681177737635016818389733896639868957932991456388644310370608078174489957958324579418962026049841043922507860460362527726022919682995860988339013787171422691788381952984456079160519727973604759102510995779133515791772251502549293246325028747677948421584050759929040185576459901862692677643726605711768133655908815548107470000623363725288949554636971433012007913085552639595497823023144039149740494746825947320846185246058776694882879530104063491722921858008706770690427926743284446968514971825678095841654491851457533196406331199373821573450874988325560888873528019019155089688554682592454445277281730573010806061770113637731824629246600812771621018677446849595142817901451119489342288344825307531187018609761224623176749775564124619838564014841235871772495542248201615176579940806296834242890572594739269638633838743805471319676429268372490760875073785283702304686503490512034227217436689792848629729088926789777032624623912261888765300577862743606094443603928097708133836934235508583941126709218734414512187803276150509478055466300586845563152454605315113252818891079231491311032344302450933450003076558648742229717700331784539150566940159988492916091140029486902088485381697009551566347055445221764035862939828658131238701325358800625686626926997767737730683226900916085104515002261071802554659284938949277595897540761559933782648241979506418681437881718508854080367996314239540091964388750078900000627997942809886372992591977");
		BigDecimal two = new BigDecimal(Math.log(Math.log(x)));
		BigDecimal three = new BigDecimal(0);
		int infinity = 10;
		BigDecimal sum = new BigDecimal(0);
		for (int i = 1; i < infinity; i++) {
			BigDecimal sum1 = new BigDecimal(0);
			for (int j = 0; j < Math.floor((i-1)/2); j++) {
				sum1.add(new BigDecimal(1.0/(2*j+1)));
			}
			sum.add(sum1.multiply(new BigDecimal((Math.pow(-1,i-1)*Math.pow(Math.log(x),i))).divide(factorial(new BigDecimal(i)).multiply(new BigDecimal(Math.pow(2,i-1))))));
			//System.out.println(Math.pow(Math.log(x),i));
			System.out.println((factorial(i)));
		}
		three = sum.multiply(new BigDecimal(Math.sqrt(x)));
		return one.add(two).add(three);
	}

	public static BigDecimal offsetLogIntegral(double x) {
		BigDecimal part1 = logIntegral(x);
		BigDecimal part2 = new BigDecimal(
				"1.0451637801174927848445888891946131365226155781512015758329091440750132052103595301727174056263833563060208297688747466222852397852196502790210823345578454055159053003222632748284543905148534966228633054761993303044191709434064183547149667285967170172273382737143001251938367555153194471629765297800781489714877903198218851914711370699163463454614566926839570098529440194373304148532054917559042674034376055257631453647013996141578816205226945882167913779577985755793070640280590884889202573169197579928882227965987473990618098490933485735555546332087174942954442914487954063007890050924937275997379287983240107741346070174268630576135549451725696332316233096424132629787724067443606625192514864611488915201308403944647504021678784039581192115137376543643432728897169903741038439912159756812136591208796572671442327323746868742686191379953986869507362470515035178464619210468331874813866025467134576495035178662403288015619622921383812609964490733284014730018447803478261334078147254346781259318351117515731137983836639776275603363189927586228248071363739173641349434297046497593265919083100832467052946013143817302109871322816513588362179918733896021901579732917072001427631312019360693502148449084793400341644559449560653428899223152384074106443110217499559340796995942670148918792273422874132652788462151287255613042441814100498650497517193783979330801067028134651175849512409322789052945850620034275514631720358374409661248032419258590571222683579300197048250216459980486403996071644670020390830461801095177934736996395249720576365360368601453370851943883620264321774395132023524244784030708124546148958813228794990205086962331866029410462704470868113542506614038943255968180867154153330854885036881749397143274875637594944806050052686662902670165128957607617763191733691121591689428126190110039248292081428821587712442009647276652567608530683506895173555326084498612472078774789906312677366935805424016238795348356536775773448330197737713730218590974113547936183171389071842859240768956612159504708389656783068669383202537850859147680108689468555824612607440587856072245226844636004935369009893675351457410653620552959271260779944440795024163548662760124665483904793870761178208678383839587176233880492306104848615350692834415855000110387350296302072169333679422476441088635507490258345297469764223053296328570160414505170450868434433287228692564007245938046238540985360705113100302504826970996186516083580946737704661996886316562988271581103334194805458776463921460402523461979204005305787197853829167600877311708457920784620289064070838054890784520575706041698730744068265626746705262529558405682338211210914496496137774764258013414275244883570592942444243402098524010521380942939874974622958371872995216740888032109818509800211970633750977246643788034154260905754527075942950277697875666337919822492602847707693786928030875512057576874063833830419629427843918491761560576816915197546819040268854246908682633244206594390785644122734684404858807812161533385644495213016070163246761411125431821173304903268199218975240831547016334868446452524901694586652256934273205327977972211842371918242818159658348709598514099441907406289602825177279940012206085752255235210508512074212533170015556393391757783059038762108074628375921306411931859639913820566550948009535824602869148660981393353249531369488583041287639425678419080059165363172918097909787369473150145156948473708169775597985192772651542296031706960990331999746446828447430936170839543548184930784");
		return part1.subtract(part2);
	}

	public static BigDecimal riemannPrimeCountingFunction(double x, int infinity) {
		int start = 1;
		BigDecimal sum = new BigDecimal(0);
		BigDecimal p1 = new BigDecimal(0);
		BigDecimal p2 = new BigDecimal(0);
		for (int i = start; i <= infinity; i++) {
			p1 = new BigDecimal((mobiusFunction(i) / (1.0 * i)));
			p2 = logIntegral(Math.pow(x, (1.0 / i)));
			sum = sum.add(p1.multiply(p2));
		}
		return sum;
	}

	public static int sigma(int start, int end) {// start is variable on bottom of sigma
													// end is number on top of sigma
		int x = 0;
		int sum = 0;
		for (int i = start; i <= end; i++) {
			x = 0;
			x += i;
			x = x * 4 - 1;// enter operation on right of sigma here
			sum += x;
		}
		return sum;
	}

	public static int factorial(int num) {
		int product = 1;
		for (int i = num; i > 0; i--) {
			product *= i;
		}
		return product;
	}

	public static double factorial(double num) {
		int product = 1;
		for (double i = num; i > 0; i--) {
			product *= i;
		}
		return product;
	}

	public static float factorial(float num) {
		int product = 1;
		for (float i = num; i > 0; i--) {
			product *= i;
		}
		return product;
	}

	public static BigDecimal factorial(BigDecimal num) {
		BigDecimal product = new BigDecimal(1);
		for (BigDecimal i = num; i.compareTo(BigDecimal.ZERO) > 0 ; i.subtract(new BigDecimal(1))) {
			product.multiply(i);
		}
		return product;
	}

	public static int kroneckerDelta(int i, int j) {// returns 1 if i=j, 0 if i!=j
		if (i == j) {
			return 1;
		}
		return 0;
	}

	public static int[] primeFact(int a) {// returns int array of prime factors
		int count = 1;
		int count1 = -1;
		while (count <= a) {
			count *= 2;
			count1++;
		}
		int product = 1;
		int a1 = a;
		int[] aF = new int[count1];
		count = 0;
		for (int i = 0; i < aF.length; i++) {
			int num = 2;
			while (a1 % num != 0) {
				num++;
			}
			a1 /= num;
			aF[i] = num;
			product *= num;
			count++;
			if (product == a) {
				break;
			}
		}
		int[] aF1 = new int[count];
		for (int i = 0; i < aF1.length; i++) {
			aF1[i] = aF[i];
		}
		return aF1;
	}

	public static int primeOmegaFunctionOmega(int num) { // returns number of prime factors with multiplicity
		return primeFact(num).length;
	}

	public static int primeOmegaFunctionW(int num) {// returns number of distinctive prime factors
		int[] factors = primeFact(num);
		ArrayList<Integer> distinctiveFactors = new ArrayList<Integer>();
		for (int i : factors) {
			if (distinctiveFactors.contains(i) == false) {
				distinctiveFactors.add(i);
			}
		}
		return distinctiveFactors.size();
	}

	public static int liouvilleFunction(int num) {// returns answer of -1^Omega(n)
		return (int) Math.pow(-1, primeOmegaFunctionOmega(num));
	}

	public static int mobiusFunction(int num) {
		return kroneckerDelta(primeOmegaFunctionOmega(num), primeOmegaFunctionW(num)) * liouvilleFunction(num);
	}
	
	public static int primeCounterFloor(int num) {
	    int sum = 0;
	    for (int i = 1; i <= (int)(Math.log(num)/Math.log(2)); i++) {
	        int sum1 = 0;
	        for (int j = 2; j <= (int)(Math.pow(num, 1.0 / i)); j++) {
	            sum1 += (int)(Math.pow(num, 1.0 / i) / j) * mobiusFunction(j) * primeOmegaFunctionOmega(j);
	        }
	        sum += mobiusFunction(i) * sum1;
	    }
	    return -sum;
	}

	public static int primeCounterFloorOuter(int num) {
	    int sum = 0;
	    for (int i = 1; i <= (int)(Math.log(num)/Math.log(2)); i++) {
	        int sum1 = primeCounterFloorInner(num, i);
	        sum += functions.mobiusFunction(i) * sum1;
	    }
	    return -sum;
	}

    public static int primeCounterFloorInner(int num, int i) {
        int sum = 0;
        for (int j = 2; j <= (int)(Math.pow(num, 1.0 / i)); j++) {
            sum += (int)(Math.pow(num, 1.0 / i) / j) * functions.mobiusFunction(j) * functions.primeOmegaFunctionOmega(j);
        }
        return sum;
    }

	public static void primesCSV(int num) throws IOException {
        String filename = "primesTest.csv";
        String[] header = {"x","y"};
        ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(num);//15485864 is first million primes
        int[][] data = new int[primesList.size()][2];
        int count = 0;
        for (int i : primesList) {
            data[count][0] = count+1;
            data[count][1] = i;
            count++;
        }
        csv.write(header,data,filename);
    }

    public static void primeCountingFunction(int num, double stepSize) throws IOException {
        String filename = "primesTest.csv";
        String[] header = {"x","y"};
        double[][] data = new double[(int)(Math.ceil((num)/stepSize))][2];
        ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(num);
        int count = 0;
        double y = 0;
        double error = 0.00000000001;
        double iRounded = 0;
        int precision = 3;
        for (double i = 1; i < num + error; i += stepSize) {
            iRounded = Math.round(i*Math.pow(10,precision));
            iRounded /= Math.pow(10,precision);
            for (int prime : primesList) {
                if (iRounded == prime) {
                    y++;
                    break;
                }
            }
            data[count][0] = stepSize*count + 1;
            data[count][1] = y;
            count++;
        }
        csv.write(header,data,filename);
	}

    public static void primeCountingFunctionModified(int num, double stepSize) throws IOException {
        String filename = "primesTestMod.csv";
        String[] header = {"x","y"};
        double[][] data = new double[(int)(Math.ceil((num)/stepSize))][2];
        ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(num);
        int count = 0;
        double y = 0;
        double error = 0.00000000001;
        double iRounded = 0;
        int precision = 3;
        for (double i = 1; i < num + error; i += stepSize) {
            iRounded = Math.round(i*Math.pow(10,precision));
            iRounded /= Math.pow(10,precision);
            for (int prime : primesList) {
                if (Math.log(iRounded)/Math.log(prime) % 1 == 0 && iRounded >= 2) {
                    y += Math.log(prime);
                    break;
                }
            }
            data[count][0] = stepSize*count + 1;
            data[count][1] = y;
            count++;
        }
        csv.write(header,data,filename);
	}

	public static double mangoldt(int x) {
		int num = -1;
		int power = 1;
        ArrayList<Integer> primesList = PrimeFinderPrint.getPrimesList(x);
		for (int prime : primesList) {
			while (num < x) {
				num = (int)Math.pow(prime,power);
				power++;
			}
			if (num == x) {
				return Math.log(prime);
			}
			power = 1;
			num = -1;
		}
		return 0;
	}

	public static double chebyshev(double x) {
		double sum = 0;
		for (int k = 2; k <= x; k++) {
			sum += mangoldt(k);
		}
		return sum;
	}

	public static double psi0(double x) throws IOException {
		double sum = 0;
		int numZeroes = 100000;
		double gamma;
		for (int i = 0; i < numZeroes; i++) {
			gamma = data.getLineFromFile(i,"ReimannZetaZeroes.txt");
			sum += (Math.cos(gamma*Math.log(x)) + 2.0*gamma*Math.sin(gamma*Math.log(x))) / (1.0 + 4.0*Math.pow(gamma,2.0));
		}
		double num1 = 4.0*Math.pow(x,0.5)*sum;
		double num2 = 0.5*Math.log(1.0-Math.pow(x,-2.0));
		return x - num1 - num2;
	}

	public static double pi0(double x) throws IOException {
		int infinity = 100;
		int numZeroes = 2;
		double num1 = riemannPrimeCountingFunction(x, infinity).doubleValue();
		double sum1 = 0;
		double gamma;
		double sum2 = 0;
		for (int i = 1; i < infinity; i++) {
			sum1 += mobiusFunction(i) * Math.pow(Math.pow(x,0.5),1.0/i) / Math.log(x);
			sum2 = 0;	
			for (int j = 0; j < numZeroes; j++) {
				gamma = data.getLineFromFile(j,"ReimannZetaZeroes.txt");
				sum2 += Math.sin((gamma/i)*Math.log(x))/gamma;
				//this is probably not working right as it looks right with 0 zeroes but not with any more
			}
			sum1 *= 2 * sum2;
		}
		double sum3 = 0;
		for (int i = 1; i < infinity; i++) {
			sum3 += riemannPrimeCountingFunction(Math.pow(x,-2.0*i),infinity).doubleValue();
		}
		double num2 = -1.0/Math.log(x) + (1.0/Math.PI)*Math.atan(Math.PI/Math.log(x));
		return num1 - sum1 + num2;
	}


	public static void main(String[] args) throws IOException {
		// System.out.println(sigma(1, 100));
		// System.out.println(primeOmegaFunctionOmega(11099088));
		// System.out.println(primeOmegaFunctionW(11099088));
		// System.out.println(liouvilleFunction(5));
		// System.out.print(mobiusFunction(8));
		// System.out.println(logIntegral(3.0/4));
		// System.out.println(factorial(5));
		// System.out.println(-1 * incompleteGamma(Math.log(1)));
		// System.out.println(-incompleteGamma(-0.69));
		// System.out.println(cauchyPrincipleLogIntegral(count));
		// System.out.println(incompleteGamma(5));
		// System.out.println(limit0Right());
		// System.out.println(helpFindC(5));
		// System.out.println("Does not work for x > 1; x = 0.5; " + logIntegral(0.5));
		// System.out.println("NEW; x = 0.5; " + logIntegral1(0.5));
		// System.out.println("0: " + logIntegral1(0));
		// System.out.println("1: " + logIntegral1(1));
		// System.out.println("u: " +
		// System.out.println(logIntegral(0.9834));
		// System.out.println("riemannR: " + riemannPrimeCountingFunction(0.00000000001,100));
		// System.out.println("pi: " + primeCounterFloor(num));
		// System.out.println("li2(x): " + logIntegral2(100));
		// System.out.println("li(x): " + logIntegral(0.00000000001));
		// System.out.println("Mangoldt(" + num + "): " + mangoldt(num));
		System.out.println(pi0(2.0));
	}
}
