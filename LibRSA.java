import java.math.BigInteger;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class LibRSA {
    public static BigInteger[] keyNumbersGenerator () {
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(Long.BYTES, random);
        BigInteger q = BigInteger.probablePrime(Long.BYTES, random);
        while (true) {
			if (p.compareTo(q) != 0) {
                if (p.isProbablePrime(1)) {
                    if (q.isProbablePrime(1)) {
						if (p.multiply(q).compareTo(BigInteger.valueOf(30000)) > 0) {
							break;
						} else {
                            p = BigInteger.probablePrime(Long.BYTES, random);
                            q = BigInteger.probablePrime(Long.BYTES, random);
                        }
                    } else {
                        q = BigInteger.probablePrime(Long.BYTES, random);
                    }
                } else {
                    p = BigInteger.probablePrime(Long.BYTES, random);
                }
		    } else {
                q = BigInteger.probablePrime(Long.BYTES, random);
            }
        }
        
        BigInteger n = p.multiply(q);
        BigInteger v = p.subtract(BigInteger.ONE).multiply((q.subtract(BigInteger.ONE)));

        BigInteger e = minorCoPrime(v);

        int dInteger = 2;
        while (e.multiply(BigInteger.valueOf(dInteger)).mod(v).compareTo(BigInteger.ONE) != 0 ) {
            dInteger += ThreadLocalRandom.current().nextInt(1, 2);
        }
        BigInteger d = BigInteger.valueOf(dInteger);

        BigInteger[] keys= {n, e, d};
        return (keys);
    }

    public static BigInteger minorCoPrime(BigInteger coPrimeGivenNumber) {
        Random random = new Random();
        BigInteger coPrimeGeneratedNumber = BigInteger.probablePrime(Long.BYTES, random);
        while ((coPrimeGivenNumber.gcd(coPrimeGeneratedNumber).compareTo(BigInteger.valueOf(1)) != 0) && (coPrimeGivenNumber.compareTo(coPrimeGeneratedNumber) > 0)) {
            coPrimeGeneratedNumber = BigInteger.probablePrime(Long.BYTES, random);
        }
        return (coPrimeGeneratedNumber);
    }

    public static boolean isInt(String string) { 
        try {
        //da rifare con i bigInteger
          Integer.parseInt(string);
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }
    }

    public static String textEncript(String encriptedMessage, BigInteger n, BigInteger e) {
        String criptedMessage = "";
        char[] charArray = encriptedMessage.toCharArray();
        System.out.println(charArray);
        for (int i = 0; i < encriptedMessage.length() ; i++) {
            criptedMessage = criptedMessage + ((BigInteger.valueOf((int)charArray[i])).modPow(e,n));
            if (i != encriptedMessage.length()-1 ) {
                criptedMessage += ";";
            }
        }
        return(criptedMessage);
    }

    public static String textDecript(String criptedMessage, BigInteger n, BigInteger d) {
        String decriptedMessage = "";
        StringTokenizer slicedMessage = new StringTokenizer(criptedMessage, ";");

        while (slicedMessage.hasMoreElements()) {
            decriptedMessage += new String((BigInteger.valueOf(Integer.parseInt(slicedMessage.nextToken()))).modPow(d, n).toByteArray());
        }
        return(decriptedMessage);
    }
}