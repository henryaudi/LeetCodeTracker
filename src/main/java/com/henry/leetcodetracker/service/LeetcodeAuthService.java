package com.henry.leetcodetracker.service;

import com.henry.leetcodetracker.util.ConfigUtils;
import com.henry.leetcodetracker.util.HttpAuthUtils;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.http.HttpClient;

public class LeetcodeAuthService {

    // Data Fields
    private HttpAuthUtils httpAuthUtils;
    private String leetcodeSession;
    private String csrfToken;

    // Constructor
    public LeetcodeAuthService() {
        this.setHttpAuthUtils(new HttpAuthUtils());
    }

    public HttpAuthUtils getHttpAuthUtils() {
        return httpAuthUtils;
    }

    public void setHttpAuthUtils(HttpAuthUtils httpAuthUtils) {
        this.httpAuthUtils = httpAuthUtils;
    }

    public String getLeetcodeSession() {
        return leetcodeSession;
    }

    public void setLeetcodeSession(String leetcodeSession) {
        this.leetcodeSession = leetcodeSession;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    // Methods
    public void login(String username, String password) throws IOException, InterruptedException {
        String loginUrl = ConfigUtils.getLeetcodeLoginUrl();

        this.setCsrfToken(httpAuthUtils.fetchCsrfToken(loginUrl));

        this.setLeetcodeSession(httpAuthUtils.performLogin(loginUrl
                , username
                , password
                , this.getCsrfToken()));
    }
}
