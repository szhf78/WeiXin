package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 
 * ΢������У�鹤����
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

		// 1. ��token��timestamp��nonce�������������ֵ�������
		String[] str = new String[] { token, timestamp, nonce };

		Arrays.sort(str);

		// 2. �����������ַ���ƴ�ӳ�һ���ַ���
		StringBuilder buff = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			buff.append(str[i]);
		}

		// ����sha1����
		MessageDigest md = null;
		String result = "";
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] data = md.digest(buff.toString().getBytes());
			
			//���ֽ�����ת�����ַ���
			result=bytesToStr(data);
			
			System.out.println("result���ܺ���ַ���Ϊ��"+result);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result!=null?(result.equals(signature.toUpperCase())):false;
	}

	/**
	 * ���ֽ�����ת����Ϊʮ�������ַ���
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
	 * ��һ���ֽ�ת����Ϊʮ�������ַ���
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
