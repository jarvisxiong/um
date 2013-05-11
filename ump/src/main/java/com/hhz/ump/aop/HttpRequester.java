/**
 * 
 */
package com.hhz.ump.aop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * @author huangjian
 *         2011-5-27
 */
public class HttpRequester {
	private Logger logger = Logger.getLogger(getClass());
	private String defaultContentEncoding;

	public HttpRequester() {
		this.defaultContentEncoding = Charset.defaultCharset().name();
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString) throws IOException {
		return this.send(urlString, "GET", null, null);
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params) throws IOException {
		return this.send(urlString, "GET", params, null);
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @param propertys
	 *            请求属性
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params, Map<String, String> propertys)
			throws IOException {
		return this.send(urlString, "GET", params, propertys);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString) throws IOException {
		return this.send(urlString, "POST", null, null);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params) throws IOException {
		return this.send(urlString, "POST", params, null);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @param propertys
	 *            请求属性
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params, Map<String, String> propertys)
			throws IOException {
		return this.send(urlString, "POST", params, propertys);
	}

	/**
	 * 发送HTTP请求
	 * 
	 * @param urlString
	 * @return 响映对象
	 * @throws IOException
	 */
	private HttpRespons send(String urlString, String method, Map<String, String> parameters,
			Map<String, String> propertys) throws IOException {
		HttpURLConnection urlConnection = null;

		if (method.equalsIgnoreCase("GET") && parameters != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : parameters.keySet()) {
				if (i == 0) {
					param.append("?");
				} else {
					param.append("&");
				}
				param.append(key).append("=").append(parameters.get(key));
				i++;
			}
			urlString += param;
		}
		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(method);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setRequestProperty("Accept", "*/*");
		urlConnection.setRequestProperty("Accept-Language", "zh-CN");
		urlConnection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
		urlConnection.setRequestProperty("Connection", "Keep-Alive");
		urlConnection.setRequestProperty("Cache-Control", "no-cache");
		if (propertys != null) {
			for (String key : propertys.keySet()) {
				urlConnection.addRequestProperty(key, propertys.get(key));
			}
		}

		if (method.equalsIgnoreCase("POST") && parameters != null) {
			StringBuffer param = new StringBuffer();
			for (String key : parameters.keySet()) {
				param.append("&");
				param.append(key).append("=").append(parameters.get(key));
			}
			urlConnection.getOutputStream().write(param.toString().getBytes());
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		return this.makeContent(urlString, urlConnection);
	}

	public HttpRespons sendTile(String urlString, String title, String subTitle, int count) {
		try{
		HttpURLConnection urlConnection = null;

		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setRequestProperty("Content-Type", "text/xml");
		urlConnection.addRequestProperty("X-WindowsPhone-Target", "token");
		urlConnection.addRequestProperty("X-NotificationClass", "1");
		StringBuffer sbMsg=new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sbMsg.append("<wp:Notification xmlns:wp=\"WPNotification\"><wp:Tile>");
		sbMsg.append("<wp:BackgroundImage>").append("/Background.png").append("</wp:BackgroundImage>");
		sbMsg.append("<wp:Count>").append(count).append("</wp:Count>");
		sbMsg.append("<wp:Title>").append(title).append("</wp:Title>");
		
//		sbMsg.append("<wp:BackBackgroundImage>").append("/Pdlogo.png").append("</wp:BackBackgroundImage>");
//		sbMsg.append("<wp:BackTitle>").append(title).append("</wp:BackTitle>");
//		sbMsg.append("<wp:BackContent>").append(count==0?"无记录!":subTitle).append("</wp:BackContent>");
		
		sbMsg.append("</wp:Tile></wp:Notification>");
		int contentLength = sbMsg.toString().getBytes("UTF-8").length;
		urlConnection.setRequestProperty("Content-Length", String.valueOf(contentLength));
		urlConnection.getOutputStream().write(sbMsg.toString().getBytes("UTF-8"), 0, contentLength);
		urlConnection.getOutputStream().flush();
		urlConnection.getOutputStream().close();

		return this.makeContent(urlString, urlConnection);
		}catch(Exception e){
			logger.warn("推送手机网批错误(sendTile):"+ e.getMessage());
		}
		return null;
	}

	public HttpRespons sendToast(String urlString, String title, String subTitle){
		try{
		HttpURLConnection urlConnection = null;

		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setRequestProperty("Content-Type", "text/xml");
		urlConnection.addRequestProperty("X-WindowsPhone-Target", "toast");
		urlConnection.addRequestProperty("X-NotificationClass", "2");
		
		StringBuffer sbMsg=new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sbMsg.append("<wp:Notification xmlns:wp=\"WPNotification\"><wp:Toast>");
		sbMsg.append("<wp:Text1>").append(title).append("</wp:Text1>");
		sbMsg.append("<wp:Text2>").append(subTitle).append("</wp:Text2>");
		sbMsg.append("<wp:Param>").append("/MainPage.xaml?param=Toast").append("</wp:Param>");
		sbMsg.append("</wp:Toast></wp:Notification>");
		
		int contentLength = sbMsg.toString().getBytes("UTF-8").length;
		urlConnection.setRequestProperty("Content-Length", String.valueOf(contentLength));
		urlConnection.getOutputStream().write(sbMsg.toString().getBytes("UTF-8"), 0, contentLength);
		urlConnection.getOutputStream().flush();
		urlConnection.getOutputStream().close();

		return this.makeContent(urlString, urlConnection);
		}catch(Exception e){
			logger.warn("推送手机网批错误(sendToast):"+ e.getMessage());
		}
		return null;
	}

	public void sendSimple(String urlString) {
		try {
			HttpURLConnection urlConnection = null;

			URL url = new URL(urlString);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			this.makeContent(urlString, urlConnection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	/**
	 * 得到响应对象
	 * 
	 * @param urlConnection
	 * @return 响应对象
	 * @throws IOException
	 */
	private HttpRespons makeContent(String urlString, HttpURLConnection urlConnection) throws IOException {
		HttpRespons httpResponser = new HttpRespons();
		try {
			String ecod = urlConnection.getContentEncoding();
			if (ecod == null) {
				ecod = this.defaultContentEncoding;
			}
			// System.out.println("当前编码:" + ecod);
			InputStream in = urlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, ecod));
			httpResponser.contentCollection = new Vector<String>();
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				httpResponser.contentCollection.add(line);
				temp.append(line.toString()).append("\r\n");
				line = bufferedReader.readLine();
			}
			bufferedReader.close();

			httpResponser.urlString = urlString;

			httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
			httpResponser.file = urlConnection.getURL().getFile();
			httpResponser.host = urlConnection.getURL().getHost();
			httpResponser.path = urlConnection.getURL().getPath();
			httpResponser.port = urlConnection.getURL().getPort();
			httpResponser.protocol = urlConnection.getURL().getProtocol();
			httpResponser.query = urlConnection.getURL().getQuery();
			httpResponser.ref = urlConnection.getURL().getRef();
			httpResponser.userInfo = urlConnection.getURL().getUserInfo();

			httpResponser.content = temp.toString();
			httpResponser.contentEncoding = ecod;
			httpResponser.code = urlConnection.getResponseCode();
			httpResponser.message = urlConnection.getResponseMessage();
			httpResponser.contentType = urlConnection.getContentType();
			httpResponser.method = urlConnection.getRequestMethod();
			httpResponser.connectTimeout = urlConnection.getConnectTimeout();
			httpResponser.readTimeout = urlConnection.getReadTimeout();

			return httpResponser;
		} catch (IOException e) {
			throw e;
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
	}

	/**
	 * 默认的响应字符集
	 */
	public String getDefaultContentEncoding() {
		return this.defaultContentEncoding;
	}

	/**
	 * 设置默认的响应字符集
	 */
	public void setDefaultContentEncoding(String defaultContentEncoding) {
		this.defaultContentEncoding = defaultContentEncoding;
	}
}
