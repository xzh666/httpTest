package pu.hui.httpTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.testng.annotations.Test;

import pu.hui.httpTest.util.Constants;
import pu.hui.httpTest.util.ExcelUtil;
import pu.hui.httpTest.util.JsonFormatUtil;

public class TestSuiteByExcel {
	private static Logger logger = Logger.getLogger(TestSuiteByExcel.class);
	public static Method method[];
	public static String excelMethodName;
	public static String requestUrl;
	public static String jsonObjectParam;
	public static String asertString;
	public static AllTestMethods keyWordsAction;
	public static String responseBodyString;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	public static boolean testCaseResult;
	public static String testStatus;
	
	public static void main(String[] args) throws Exception{
		TestSuiteByExcel tsb = new TestSuiteByExcel();
		tsb.testTestSuite();
	}
	
	@Test
	public void testTestSuite() throws Exception {
		keyWordsAction = new AllTestMethods();
		method = keyWordsAction.getClass().getMethods();
		String excelFilePathString = Constants.PATH_EXCELFILE;
		ExcelUtil.setExcelFile(excelFilePathString);
		List<String> results = new ArrayList<String>();
		int testCasesCount = ExcelUtil.getRowCount(Constants.SHEET_TESTSUITE);
		int sucCount = 0;//成功个数
		int failCount = 0;//失败个数
		for(int testCaseNo = 1; testCaseNo <= testCasesCount; testCaseNo++){
			//读取“测试用例集合”Sheet中每行的测试用例序号1111111111111111
			testCaseID = ExcelUtil.getCellData(Constants.SHEET_TESTSUITE, testCaseNo, Constants.COL_TESTCASEID);
			//读取“测试用例集合”Sheet中每行的是否运行列中的值
			testCaseRunFlag = ExcelUtil.getCellData(Constants.SHEET_TESTSUITE, testCaseNo, Constants.COL_RUNFLAG);
			long start = System.currentTimeMillis();
			//如果是否运行列中的值为"y"，则执行测试用例中的所有步骤
			if(testCaseRunFlag.equalsIgnoreCase("y")){
				logger.debug("--------------      \"" + testCaseID + " \"开始执行----------------");
				testCaseResult = true;
				//在“登录”sheet页中，获取当前要执行测试用例的第一个步骤所有行行号
				testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.SHEET_TESTSTEPS, testCaseID, Constants.COL_TESTCASEID);
				//在“登录”sheet页中，获取当前要执行测试用例的最后一个步骤所有行行号
				testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.SHEET_TESTSTEPS, testCaseID, testStep);
				for (; testStep<testLastStep; testStep++) {
					logger.info("本次是测试的==============第" +testStep+"个接口================" );
					excelMethodName = ExcelUtil.getCellData(Constants.SHEET_TESTSTEPS, testStep, Constants.COL_KEYWORDACTION);
					logger.info("本次要测试的接口为： " + excelMethodName);
					requestUrl = ExcelUtil.getCellData(Constants.SHEET_TESTSTEPS, testStep, Constants.COL_LOCATEEXPRESSION);
					logger.info("本次要测试的接口地址为： " + requestUrl);
					jsonObjectParam = ExcelUtil.getCellData(Constants.SHEET_TESTSTEPS, testStep, Constants.COL_ACTIONVALUE);
					logger.info("本次要测试的请求参数： " + jsonObjectParam);
					asertString = ExcelUtil.getCellData(Constants.SHEET_TESTSTEPS, testStep, Constants.COL_ASSERTSTRING);
					
					execute_Actions();
					ExcelUtil.setCellData(Constants.SHEET_TESTSUITE, testCaseNo, Constants.COL_TESTSUITETESTRESULT, "失败");
					logger.info("本次测试服务端返回的结果为：" +JsonFormatUtil.formatJson(responseBodyString));
					logger.info("本次要测试要判断的字段信息： " + asertString);
					if(testCaseResult == false){
						//如果测试用例任何一个测试步骤执行失败，则测试用例集合Sheet中的当前执行测试用例的执行结果设定为"测试执行失败"
						
						logger.info("本次测试执行结果======失败" );
						//logger.info("--------------      \"" + testCaseID + " \"测试执行结束----------------");
						testStatus = "失败";
						//当前测试用例出现执行失败的步骤，则整个测试用例设定为失败状态，break语句跳出当前的for循环，继续执行下一个测试用例
						break;
					} else {
						//如果测试用例所有测试步骤执行成功，则测试用例集合Sheet中的当前执行测试用例的执行结果设定为"测试执行成功"
						ExcelUtil.setCellData(Constants.SHEET_TESTSUITE, testCaseNo, Constants.COL_TESTSUITETESTRESULT, "成功");
						logger.info("本次测试执行结果======成功" );
						testStatus = "成功";
					}
				}
//				if(testResult == false){
//					failCount++;//				}else{
//					sucCount++;
//				}
//				String passRate = (testStatus=="成功"?"100%":"0%");
//				long end = System.currentTimeMillis();
//				results.add(testCaseID+","+(end-start)/1000+"s"+","+testStatus+","+passRate);
			}
			//FileUtils.write(new File("test.html"), , "UTF-8", true);
		}
		double suc = sucCount;
		double fail = failCount;
		double avg = suc/(suc+fail)*100;
		results.add("Total,,"+(sucCount+failCount)+","+avg+"%");
		//TableUtil.appendHtml("iap自动化测试", results);
	}
	private static void execute_Actions() {
		try {
			for(int i = 0; i< method.length; i++){
				/**
				 * 使用反射的方式，找到关键字对应的测试方法，并使用value(操作值)作为测试方法的函数值进行调用
				 */
				if(method[i].getName().equals(excelMethodName)){
					boolean methodResult = (boolean) method[i].invoke(excelMethodName, requestUrl, jsonObjectParam,asertString);
					ExcelUtil.setCellData(Constants.SHEET_TESTSTEPS, testStep, Constants.COL_RESPONSEBODYSTRING, TestSuiteByExcel.responseBodyString);
					if(methodResult == false){
						testCaseResult=false;
						//如果当前测试步骤执行失败，则当前执行测试步骤的执行结果设定为"测试步骤执行失败"
						ExcelUtil.setCellData(Constants.SHEET_TESTSTEPS, testStep, Constants.COL_TESTSTEPTESTRESULT, "失败");
						//测试步骤执行失败，则直接关闭浏览器，不再执行后续的测试步骤
						//KeyWordsAction.close_brower("","");
						//当前测试用例出现执行失败的步骤，则整个测试用例设定为失败状态，break语句跳出当前的for循环，继续执行下一个测试用例
						break;
					} else {
						//如果当前测试步骤执行成功，则当前执行测试步骤的执行结果设定为"测试步骤执行成功"
						ExcelUtil.setCellData(Constants.SHEET_TESTSTEPS, testStep, Constants.COL_TESTSTEPTESTRESULT, "成功");
						break;
					}
				}
				//如果所有的方法都执行完，则直接关闭浏览器
				if (i==method.length-1) {
					//KeyWordsAction.close_brower("","");
					break;
				}
			}
		} catch (Exception e) {
			//Assert.fail("execute exception...");
			e.printStackTrace();
		}
	}
}
