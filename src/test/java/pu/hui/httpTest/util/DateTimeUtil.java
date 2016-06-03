package pu.hui.httpTest.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * ��ȡϵͳ��ǰ���ں�ʱ�䲢��ʽ��ΪyyyyMMddHHmmss������20110810155638��ʽ
	 * 
	 * @param ��
	 * @return ϵͳ��ǰ���ں�ʱ�䲢��ʽ��ΪyyyyMMddHHmmss������20110810155638��ʽ
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * ��ȡϵͳ��ǰ���ں�ʱ�䲢��ʽ��ΪyyyyMMddHHmmssSSS������20130526002728796��ʽ
	 * 
	 * @param ��
	 * @return ϵͳ��ǰ���ں�ʱ�䲢��ʽ��ΪyyyyMMddHHmmssSSS������20130526002728796��ʽ
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

	/**
	 * ��ȡϵͳ��ǰ���ڲ���ʽ��ΪyyyyMMdd������20110810��ʽ
	 * 
	 * @param ��
	 * @return ϵͳ��ǰ���ڲ���ʽ��ΪyyyyMMdd������20110810��ʽ
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	/**
	 * ��ȡϵͳ��ǰʱ�䲢��ʽ��ΪHHmmss������155638��ʽ
	 * 
	 * @param ��
	 * @return ϵͳ��ǰʱ�䲢��ʽ��ΪHHmmss������155638��ʽ
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(new Date());
	}

	/**
	 * ��ȡϵͳ��ǰʱ�䲢��ʽ��ΪHHmmssSSS������155039527��ʽ
	 * 
	 * @param ��
	 * @return ϵͳ��ǰʱ�䲢��ʽ��ΪHHmmssSSS������155039527��ʽ
	 */
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
		return sdf.format(new Date());
	}

	/**
	 * ����Զ����ʽ����ȡϵͳ��ǰʱ��
	 * 
	 * @param format
	 *            ʱ���ʽ����yyyy-MM-dd HH:mm:ss:SSS
	 * @return ����Զ����ʽ������ϵͳ��ǰʱ��
	 */
	public static String formatedTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * get specified time string in specified date format.
	 * 
	 * @param days
	 *            days after or before current date, use + and - to add.
	 * @param dateFormat
	 *            the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
	 */
	public static String addDaysByFormatter(int days, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(cal.getTime());
	}

	/**
	 * get specified time string in specified date format.
	 * 
	 * @param months
	 *            months after or before current date, use + and - to add.
	 * @param dateFormat
	 *            the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
	 */
	public static String addMonthsByFormatter(int months, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, months);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(cal.getTime());
	}

	/**
	 * get specified time string in specified date format.
	 * 
	 * @param years
	 *            years after or before current date, use + and - to add.
	 * @param dateFormat
	 *            the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
	 */
	public static String addYearsByFormatter(int years, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, years);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(cal.getTime());
	}

	/**
	 * get first day of next month in specified date format.
	 * 
	 * @param dateFormat
	 *            the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
	 */
	public static String firstDayOfNextMonth(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(cal.getTime());
	}

	/**
	 * get first day of specified month and specified year in specified date
	 * format.
	 * 
	 * @param year
	 *            the year of the date.
	 * @param month
	 *            the month of the date.
	 * @param dateFormat
	 *            the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
	 */
	public static String firstDayOfMonth(int year, int month, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(cal.getTime());
	}

	/**
	 * get first day of specified month of current year in specified date
	 * format.
	 * 
	 * @param month
	 *            the month of the date.
	 * @param dateFormat
	 *            the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
	 */
	public static String firstDayOfMonth(int month, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(cal.getTime());
	}

	/**
	 * get the system current milliseconds.
	 */
	public static String getMilSecNow() {
		return String.valueOf(System.currentTimeMillis());
	}

	public static void main(String[] args) {
		System.out.println(DateTimeUtil.getCurrentDateTime());
		System.out.println(DateTimeUtil.getDateTime());
		System.out.println(DateTimeUtil.getCurrentDate());
		System.out.println(DateTimeUtil.getCurrentTime());
		System.out.println(DateTimeUtil.getTime());
		System.out.println(DateTimeUtil.addDaysByFormatter(+1, "yyyy-MM-dd"));
		System.out.println(DateTimeUtil.addDaysByFormatter(-1, "yyyy-MM-dd"));
		System.out.println(DateTimeUtil.addMonthsByFormatter(+1, "yyyy-MM-dd"));
		System.out.println(DateTimeUtil.addMonthsByFormatter(-1, "yyyy-MM-dd"));
		System.out.println(DateTimeUtil.addYearsByFormatter(+1, "yyyy-MM-dd"));
		System.out.println(DateTimeUtil.addYearsByFormatter(-1, "yyyy-MM-dd"));
		System.out.println(System.getProperty("user.dir"));
	}
}
