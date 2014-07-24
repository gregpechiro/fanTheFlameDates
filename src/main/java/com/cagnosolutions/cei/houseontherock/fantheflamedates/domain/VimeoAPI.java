package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VimeoAPI {

	private String accessToken = "42d31cdffcfb6f820c33687faedda08f";

	public VimeoAPI() {
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	private HttpURLConnection makeApiCall(String method, String url, String... param) throws Exception {
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		con.setRequestMethod(method);
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Authorization", "Bearer " + this.accessToken);
		con.setRequestProperty("Accept", "application/vnd.vimeo.*+json;version=3.2");
		if (param.length > 0) {
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(param[0]);
			wr.flush();
			wr.close();
		}
		con.getResponseCode();
		return con;
	}

	private Map makePatchCall(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPatch patch = new HttpPatch(url);
		patch.addHeader("User-Agent", "Mozilla/5.0");
		patch.addHeader("Authorization", "Bearer "+ this.accessToken);
		patch.addHeader("Accept", "application/vnd.vimeo.*+json;version=3.2");
		List<NameValuePair> urlParameters = new ArrayList<>();
		for (String paramKey : params.keySet()) {
			urlParameters.add(new BasicNameValuePair(paramKey, params.get(paramKey)));
		}
		patch.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(patch);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(response.getEntity().getContent(), Map.class);
	}

	public Map getInfo(String url) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(makeApiCall("GET", url).getInputStream(), Map.class);
	}

	public Map postInfo(String url, String params) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(makeApiCall("POST", url, params).getInputStream(),Map.class);
	}

	public void editVideo(VimeoVideo video) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("name", video.getName());
		map.put("description", video.getDescription());
		map.put("privacy.view", "anybody");
		map.put("review_link", "true");
		makePatchCall("https://api.vimeo.com/" + video.getVideoUri(), map);
	}

	public void addTags(List<String> tags, String videoUrl) throws Exception {
		for (String tag : tags) {
			makeApiCall("PUT", "https://api.vimeo.com" + videoUrl + "/tags/" + tag);
		}
	}

	public void addEmbedPreset(String preset, String videoUrl) throws Exception {
		makeApiCall("PUT", "https://api.vimeo.com" + videoUrl + "/presets/" + preset);
	}

	public void deleteVideo(String videoUri) throws Exception {
		makeApiCall("DELETE", "https://api.vimeo.com" + videoUri);
	}
}
