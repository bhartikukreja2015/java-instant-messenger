package jimPreferences;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class encryptionPoint {
	
	protected Cipher encCipher;
	protected Cipher decCipher;
	
	public encryptionPoint(String password) {
		byte[] salt = new byte[19];
		
		Double theSum = new Double(computeSum(password));
		
		for (int i = 0; i < 20; i++) {
			// fill in the salt hash based on the password given
			salt[i] = theSum.byteValue();
			theSum = (theSum % 10) * 3;
		}
		
		int interator = getPrime(computeSum(password));
		
		PBEKeySpec theSpec = new PBEKeySpec(password.toCharArray());
		PBEParameterSpec theParamSpec = new PBEParameterSpec(salt, interator);
		SecretKeyFactory keyFac;
		try {
			keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(theSpec);
			
			encCipher = Cipher.getInstance("PBEWithMD5AndDES");
			encCipher.init(Cipher.ENCRYPT_MODE, pbeKey, theParamSpec);
			
			decCipher = Cipher.getInstance("PBEWithMD5AndDES");
			decCipher.init(Cipher.DECRYPT_MODE, pbeKey, theParamSpec);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	protected int computeSum(String password) {
		int i = 0;
		
		for (char c : password.toCharArray()) {
			i = i + c;
		}
		
		return i;
	}
	
	protected int getPrime(int primeIndex) {
		ArrayList<Integer> thePrimes = new ArrayList<Integer>();
		
		int i = 1;
		while (thePrimes.size() != primeIndex) {
			if (isPrime(i)) { thePrimes.add(i); }
		}
		
		// the product of all consecutive primes minus 1 is a prime.
		double toReturn = 1;
		for (Integer ii : thePrimes) {
			toReturn = toReturn * ii;
		}
		
		return (int) (toReturn - 1);
		
	}
	
	private boolean isPrime(int thePrime) {
		int i = 0;
		while (i != (thePrime / 2) + 1) {
			if (thePrime % i != 0) return false;
			i++;
		}
		
		return true;
	}
	
	public String encrypt(String data) {
		try {
			return encCipher.doFinal(data.getBytes()).toString();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String decrypt(String data) {
		try {
			return decCipher.doFinal(data.getBytes()).toString();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
