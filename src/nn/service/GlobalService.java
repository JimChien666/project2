package nn.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;


public class GlobalService {

	public static final int RECORDS_PER_PAGE = 2;
	public static final String SYSTEM_NAME = "雅君網路購物商城";
	public static final int IMAGE_FILENAME_LENGTH = 20;
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/BookDataSQLver";
	public static final String KEY = "KittySnoopyGarfieldMicky"; // 16, 24, 32
	public static final int ORDER_AMOUNT_LIMIT = 10000;
	public String getSystemName() { // systemName  ${SYSTEM.systemName}
		return SYSTEM_NAME;
	}

	public static String getMD5Endocing(String message) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = Byte.toUnsignedInt(b);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}
	
	public static String getSHA1Endocing(String message) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = Byte.toUnsignedInt(b);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	/**
	 * 依MD5演算法將檔案file轉換為128位元(16個位元組)的資料。
	 * 
	 * @param message
	 *            : 要加密的字串
	 * @return : 128位元資料的16進位表示法所構成的字串
	 */
	public static String getMD5Endocing(File file) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] ba = new byte[1024];
			int len = 0;
			while ((len = fis.read(ba)) != -1) {
				md.update(ba, 0, len);
			}
		}
		byte[] digest = md.digest();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < digest.length; ++i) {
			byte b = digest[i];
			final int value = Byte.toUnsignedInt(b);
			buffer.append(value < 16 ? "0" : "");
			buffer.append(Integer.toHexString(value));

		}
		return buffer.toString();
	}
	
	
	public static String getSHA1Endocing(File file) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] ba = new byte[1024];
			int len = 0;
			while ((len = fis.read(ba)) != -1) {
				md.update(ba, 0, len);
			}
		}
		byte[] digest = md.digest();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < digest.length; ++i) {
			byte b = digest[i];
//			int value = (b & 0x7F) + (b < 0 ? 128 : 0);
			final int value = Byte.toUnsignedInt(b);
			buffer.append(value < 16 ? "0" : "");
			buffer.append(Integer.toHexString(value));

		}
		return buffer.toString();
	}
	//d0ea71ac4fd61c62e393d493fff442f83c4f780e
	//d0ea71ac4fd61c62e393d493fff442f83c4f780e
	// 為了測試本類別的其他方法而準備的main()方法。
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\user\\Downloads\\apache-tomcat-8.0.52-windows-x64.zip");
		//
		String t1 = getSHA1Endocing(file);
		System.out.println("SHA1:" + t1);
	}

	// 本方法調整fileName的長度小於或等於maxLength。
	// 如果fileName的長度小於或等於maxLength，直接傳回fileName
	// 否則保留最後一個句點與其後的附檔名，縮短主檔名使得fileName的總長度
	// 等於maxLength。
	public static String adjustFileName(String fileName, int maxLength) {
		int length = fileName.length();
		if (length <= maxLength) {
			return fileName;
		}
		int n = fileName.lastIndexOf(".");
		int sub = fileName.length() - n - 1;
		fileName = fileName.substring(0, maxLength - 1 - sub) + "." + fileName.substring(n + 1);
		return fileName;
	}

	public static String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	// 此方法可檢視上傳資料的每個欄位與每個檔案，
	public static void exploreParts(Collection<Part> parts, HttpServletRequest req) {
		try {
			System.out.println("=============================");
			for (Part part : parts) {
				String name = part.getName();
				String contentType = part.getContentType();
				String value = "";
				long size = part.getSize(); // 上傳資料的大小，即上傳資料的位元組數
				//InputStream is = part.getInputStream();
				if (contentType != null) { // 表示該part為檔案
					// 取出上傳檔案的檔名
					String filename = GlobalService.getFileName(part);
					// 將上傳的檔案寫入到location屬性所指定的資料夾
					if (filename != null && filename.trim().length() > 0) {
						part.write(filename);
						System.out.println(part.getClass().getName());
					}
				} else { // 表示該part為一般的欄位
					// 將上傳的欄位資料寫入到location屬性所指定的資料夾，檔名為"part_"+ name
					part.write("part_" + name);
					value = req.getParameter(name);
				}
				System.out.printf("%-50s %-15s %8d  %-20s \n", name, contentType, size, value);
			}
			System.out.println("=============================");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 本方法可對字串message(Plaintext, 明文)加密，然後將加密後的字串 (Ciphertext, 密文)傳回。
	 * 
	 * @param key
	 *            : 加密金鑰
	 * @param message
	 *            : 明文，即要加密的字串
	 * @return 加密後的
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Throwable
	 */
	

	/**
	 * 本方法可對加密之字串(Ciphertext)解密，key為當初加密時的金鑰 傳回值為解密後的字串(Plaintext)
	 * 
	 */

}