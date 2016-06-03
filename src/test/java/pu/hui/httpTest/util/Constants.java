package pu.hui.httpTest.util;

public class Constants {
	//测试数据相关常量设定
	public static final String PATH_EXCELFILE = "src/test/resource/paydayloan-interface-test.xlsx";
	public static final String PATH_CONFIGURATIONFILE = "src/test/resource/objectMap.properties";
	//测试数据sheet中的列号常量设定
	//第一列用0表示，测试用例序号列
	public static final int COL_TESTCASEID = 0;
	//第四列用3表示，关键字列
	public static final int COL_KEYWORDACTION = 3;
	//第五列用4表示，操作元素的定位表达式开
	public static final int COL_LOCATEEXPRESSION = 4;
	//第六列用5表示，操作值列
	public static final int COL_ACTIONVALUE = 5;
	//第七列用6表示，测试结果列
	public static final int COL_RESPONSEBODYSTRING = 6;
	public static final int COL_ASSERTSTRING = 7;
	public static final int COL_TESTSTEPTESTRESULT = 8;
	//测试集合Sheet中的列号常量设定
	public static final int COL_RUNFLAG = 2;
	//测试集合Sheet中测试结果列号常量设定，测试用例执行结果列
	public static final int COL_TESTSUITETESTRESULT = 3;
	//测试用例Sheet名称常量设定
	public static final String SHEET_TESTSTEPS = "Interface";
	//测试用例集Sheet的常量设定
	public static final String SHEET_TESTSUITE = "测试用例集合";
	
}
