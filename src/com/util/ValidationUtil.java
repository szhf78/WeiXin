package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 
 * 微信请求校验工具类
 * 
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 * 
 */
public class ValidationUtil {

	private static String token = "";

	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {

		// 1. 将token、timestamp、nonce三个参数进行字典序排序
		String[] str = new String[] { token, timestamp, nonce };

		Arrays.sort(str);

		// 2. 将三个参数字符串拼接成一个字符串
		StringBuilder buff = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			buff.append(str[i]);
		}

		// 进行sha1加密
		MessageDigest md = null;
		String result = "";
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] data = md.digest(buff.toString().getBytes());
			
			//将字节数据转换成字符串
			result=bytesToStr(data);
			
			System.out.println("result加密后的字符串为："+result);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result!=null?(result.equals(signature.toUpperCase())):false;
	}

	/**
	 * 将字节数据转换成为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String bytesToStr(byte[] byteArray){
		String strDigest="";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest+=byteToHexStr(byteArray[i]);
		}
		
		return strDigest;
	}

	/**
	 * 将一个字节转换成为十六进制字符串
	 * 
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] temp1 = new char[2];

		temp1[0] = Digit[(mByte >>> 4) & 0X0F];
		temp1[1] = Digit[mByte & 0X0F];
		
		String str=new String(temp1);
		
		return str;
	}

}
