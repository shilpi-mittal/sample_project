package utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class HttpClient {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";

//    public static final String HOST = "localhost";
//    public static final int PORT = 9091;
    public static final String PROTOCOL = "http";

    private HttpClientBuilder httpClient = HttpClientBuilder.create();
    private BasicHttpContext httpContext;

    public ResponseEntity SendJSON(String json, String url, String commMethod, String username, String password, boolean headersRequired) {
//        HttpHost targetHost = new HttpHost(HOST, PORT, PROTOCOL);
//        AuthScope localhost = new AuthScope(HOST, PORT);

//        CredentialsProvider provider = new BasicCredentialsProvider();
//        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
//        provider.setCredentials(AuthScope.ANY, credentials);
//        httpClient.setDefaultCredentialsProvider(provider);

//        AuthCache authCache = new BasicAuthCache();
//        authCache.put(targetHost, new BasicScheme());

//        httpContext.setAttribute(AUTH_CACHE, authCache);

        try {
            return handleRequest(commMethod, json, url, headersRequired);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResponseEntity handleRequest(String commMethod, String json, String url, boolean headersRequired) throws IOException {
        HttpResponse response;
        HttpEntity entity;
        BufferedReader rd;
        String sCurrentLine;
        String sCompleteResponse = "";

        HttpRequestBase httpRequest = getHttpRequest(commMethod, url);

        if (headersRequired)
            httpRequest=prepareRequestHeaderAndEntity(commMethod, json, httpRequest, true);
        else
            httpRequest=prepareRequestHeaderAndEntity(commMethod, json, httpRequest, false);

        response = httpClient.build().execute(httpRequest, httpContext);
        entity = response.getEntity();

        rd = new BufferedReader(new InputStreamReader(entity.getContent()));

        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatus(response.getStatusLine().getStatusCode());

        while ((sCurrentLine = rd.readLine()) != null) {
            sCompleteResponse = sCompleteResponse + (sCurrentLine);
        }
        responseEntity.setResponse(sCompleteResponse);
        EntityUtils.consume(entity);

        return responseEntity;
    }

    private HttpRequestBase getHttpRequest(String commMethod, String url) {
        switch (commMethod) {
            case GET:
                return new HttpGet(url);
            case POST:
                return new HttpPost(url);
            case PUT:
                return new HttpPut(url);
        }
        return null;
    }

    private HttpRequestBase prepareRequestHeaderAndEntity(String commMethod, String json, HttpRequestBase httpRequest, boolean headerRequired)
            throws UnsupportedEncodingException {
        if (!commMethod.equals(GET)) {

            httpRequest.setHeader(new BasicHeader("Content-Type", "application/json; charset=UTF-8"));
            ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(new StringEntity(json));

            if (!headerRequired) {
                Header[] headers = httpRequest.getAllHeaders();
                for (Header h : headers) {
                    httpRequest.removeHeader(h);
                }
            }
        }
        return httpRequest;
    }

    public void createContext() {
        this.httpClient.build();
        CookieStore cookieStore = new BasicCookieStore();
        httpContext = new BasicHttpContext();
        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    }
}