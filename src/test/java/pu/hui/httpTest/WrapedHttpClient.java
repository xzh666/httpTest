package pu.hui.httpTest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import pu.hui.httpTest.util.WebConstant;


public class WrapedHttpClient {

	private DefaultHttpClient client ;
	public WrapedHttpClient(DefaultHttpClient httpClient) {
		super();
		this.client = httpClient;
	}

	private Logger logger = Logger.getLogger(WrapedHttpClient.class);

	
	public WrapedHttpResponse sendGetRequest(String url) {
		HttpGet method = new HttpGet(url);
		method.addHeader(new BasicHeader("Accept", WebConstant.WEB_ACCEPT_JSON));
		method.addHeader(new BasicHeader("ContentType",
				WebConstant.WEB_CONTENT_TYPE_JSON));	
		WrapedHttpResponse response = sendRequest(method);
		return response;
	}

	public WrapedHttpResponse sendPostRequest(String url, String body) {
		HttpPost method = new HttpPost(url);
		method.addHeader(new BasicHeader("Accept", WebConstant.WEB_ACCEPT_JSON));
		method.addHeader(new BasicHeader("ContentType",
				WebConstant.WEB_CONTENT_TYPE_JSON));
		WrapedHttpResponse response = null;
		try {
			StringEntity se = new StringEntity(body);
			se.setContentEncoding(WebConstant.CHARSET_UTF8);
			se.setContentType(WebConstant.WEB_ACCEPT_JSON);
			method.setEntity(se);
			response = sendRequest(method);
		} catch (Exception e) {
			logger.error("sendPostRequest error : ", e);
		}

		return response;
	}

	private WrapedHttpResponse sendRequest(HttpRequestBase method) {

		WrapedHttpResponse res = new WrapedHttpResponse();
		HttpEntity entity = null;

		try {
			HttpResponse HttpResponse = client.execute(method);
			res.setStatusCode(HttpResponse.getStatusLine().getStatusCode());
			res.setStatusLine(HttpResponse.getStatusLine().toString());
			entity = HttpResponse.getEntity();		
			String strReponse = EntityUtils.toString(entity, EntityUtils.getContentCharSet(entity));
			res.setBody(strReponse);
			JSONObject jsonReponse = JSONObject.fromObject(strReponse);
			res.setJsonReponse(jsonReponse);
			res.setToken(jsonReponse.getString("token"));
		} catch (Exception e) {
			logger.error("sendRequest error : ", e);
		}

		return res;

	}
}
