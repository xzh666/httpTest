package pu.hui.httpTest.util;

import java.util.Random;

public class RandomUtil {

	/**
	 * ���ָ����Сֵmin�����ֵmax��Χ�ڵ�������������ָ�����ֵmax����
	 * 
	 * @param min
	 *            �����������Сֵ��Χ
	 * @param max
	 *            ������������ֵ��Χ
	 * @return int ��ɵ����������
	 */
	public static int getRandomNumber(int min, int max) {
		int number = (int) (Math.random() * (max - min) + min);
		return number;
	}

	/**
	 * ���ָ����Χ�ڵ�������������ָ����Χ����
	 * 
	 * @param extent
	 *            ��������ķ�Χ
	 * 
	 * @return int ��ɵ����������
	 */
	public static int getExtentRandomNumber(int extent) {
		int number = (int) (Math.random() * extent);
		return number;
	}

	/**
	 * ���N��ָ����Χ�ڵĲ��ظ��������������ָ����Χ����
	 * 
	 * @param extent
	 *            ��������ķ�Χ
	 * @param number
	 *            ��������ĸ���
	 * 
	 * @return int[] ��ɵĲ��ظ����������������
	 */
	public static int[] getExtentRandomNumbers(int extent, int number) {
		int[] rs = new int[number];
		for (int i = 0; i < number; i++) {
			int temp = getExtentRandomNumber(extent);
			boolean isHave = false;
			// �ж��Ƿ���ڸ������
			for (int j = 0; j < rs.length; j++) {
				if (rs[j] == temp) {
					isHave = true;
					break;
				}
			}
			if (isHave == true) {
				i--;
				continue;
			}
			rs[i] = temp;
		}
		return rs;
	}

	/**
	 * generate specified length string with numbers.
	 * 
	 * @param lengthOfNumber
	 *            the length of the number string to be created.
	 */
	public static String getRndNumByLen(int lengthOfNumber) {
		int i, count = 0;

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();

		while (count < lengthOfNumber) {
			i = Math.abs(rnd.nextInt(9));
			if (i == 0 && count == 0) {
			} else {
				randomStr.append(String.valueOf(i));
				count++;
			}
		}
		return randomStr.toString();
	}

	/**
	 * generate specified length string with chars.
	 * 
	 * @param lengthOfString
	 *            the length of the string to be created.
	 */
	public static String getRndStrByLen(int lengthOfString) {
		int i, count = 0;
		final String chars = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();

		while (count < lengthOfString) {
			i = Math.abs(rnd.nextInt(35) % charArr.length);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString();
	}

	public static void main(String[] args) {
		System.out.println("RandomNumberUtil.getRandomNumber:"
				+ RandomUtil.getRandomNumber(1000000, 9999999));
		System.out.println("RandomNumberUtil.getExtentRandomNumber:"
				+ RandomUtil.getExtentRandomNumber(9999999));
		System.out.println("RandomNumberUtil.getExtentRandomNumbers:"
				+ RandomUtil.getExtentRandomNumbers(9999999, 1)[0]);
		System.out.println("RandomNumberUtil.getRndNumByLen:"
				+ RandomUtil.getRndNumByLen(20));
		System.out.println("RandomNumberUtil.getRndStrByLen:"
				+ RandomUtil.getRndStrByLen(20));
	}
}