package pu.hui.httpTest.util;

public class StringUtil {

	/**
	 * �ж�������ַ�����Ƿ�Ϊ�ջ�����"null"�ַ�
	 * 
	 * @param args
	 *            ������ִ�
	 * 
	 * @return ������ַ�Ϊ�ջ�����"null"�ַ��򷵻�true,��֮�򷵻�false
	 */
	public static boolean validateNull(String args) {
		if (args == null || args.length() == 0 || args.equalsIgnoreCase("null")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ������Ϊ�ջ�����"null"�ַ���ַ������д��?
	 * 
	 * @param source
	 *            Դ�ַ�
	 * @param target
	 *            Ŀ���ַ�
	 * 
	 * @return ������ַ�Ϊ�ջ�����"null"�ַ��򷵻�Ŀ���ַ���֮�򷵻�Դ�ַ�
	 */
	public static String chanageNull(String source, String target) {
		if (source == null || source.length() == 0
				|| source.equalsIgnoreCase("null")) {
			return target;
		} else {
			return source;
		}
	}

	/**
	 * ����<, >,\n�������ַ�ķ�����
	 * 
	 * @param input
	 *            ��Ҫ���˵��ַ�
	 * @return ��ɹ����Ժ���ַ�
	 */
	public static final String filterStr(String str) {
		str = str.replaceAll(";", "");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("'", "");
		str = str.replaceAll("--", " ");
		str = str.replaceAll("/", "");
		str = str.replaceAll("%", "");
		str = str.replaceAll(" ", "&nbsp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("\n", "<br>");
		str = str.replaceAll("\r", "<br>");
		return str;
	}
}
