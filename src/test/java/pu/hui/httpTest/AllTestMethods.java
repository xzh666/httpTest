package pu.hui.httpTest;

import org.apache.http.impl.client.DefaultHttpClient;

import net.sf.json.JSONObject;

public class AllTestMethods {
	//private static Logger logger = LoggerFactory.getLogger(KeyWordsAction.class);
	//public static ObjectMap objectMap = new ObjectMap(Constants.PATH_CONFIGURATIONFILE);
	static DefaultHttpClient httpClient = new DefaultHttpClient();
	
	public static boolean customerCustomerLogin(String requestUrl, String jsonObjectParam,String asertString) throws Exception {
		System.out.println("login  -------- ");
		
		String[] keyValue = asertString.split("=");
		
		WrapedHttpClient wrapedHttpClient = new WrapedHttpClient(httpClient);		
		WrapedHttpResponse response = wrapedHttpClient.sendPostRequest(requestUrl,jsonObjectParam);
		TestSuiteByExcel.responseBodyString = response.getBody();
		int stateLine = response.getStatusCode();
		if(stateLine==200){
			String responseValue = response.getJsonReponse().getString(keyValue[0]);
			if(keyValue[1].equals(responseValue)){
				return true;
			}		
		}	
		return false;
	}
	
	public static boolean getRegions(String requestUrl, String jsonObjectParam,String asertString) throws Exception {
		System.out.println("getRegions ---------");
		
		WrapedHttpClient wrapedHttpClient = new WrapedHttpClient(httpClient);		
		WrapedHttpResponse response = wrapedHttpClient.sendPostRequest(requestUrl,jsonObjectParam);
		return true;
	}
	
}
