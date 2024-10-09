package com.henry.leetcodetracker.util;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class HttpAuthUtils {

    // Data Fields
    private HttpClient client;

    // Constructor

    public HttpAuthUtils() {}

    public HttpAuthUtils(HttpClient client) {
        this.setClient(HttpClient.newHttpClient());
    }

    // Getters and setters
    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public String fetchCsrfToken(String loginUrl) throws IOException, InterruptedException {

        // Create and send request to login url.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(loginUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Extract the CSRF token from the "set-cookie" headers.
        Optional<String> csrfToken = response.headers()
                .firstValue("set-cookie")
                .map(header -> HttpCookie.parse(header).stream()
                        .filter(cookie -> "csrftoken".equals(cookie.getName()))
                        .findFirst()
                        .orElse(new HttpCookie("csrftoken", ""))
                        .getValue());

        return csrfToken.orElseThrow(() -> new IOException("Failed to fetch CSRF token"));
    }

    public String performLogin(String loginUrl,
                               String username,
                               String password,
                               String csrfToken) throws IOException, InterruptedException {

        String loginForm = "login="
                + username
                + "&password="
                + password
                + "&csrfmiddlewaretoken="
                + csrfToken;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(loginUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Referer", loginUrl)
                .header("Cookie", "csrftoken=" + csrfToken)
                .POST(HttpRequest.BodyPublishers.ofString(loginForm))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<String> cookies = response.headers().allValues("set-cookie");

        return cookies.stream()
                .filter(cookie -> cookie.startsWith("LEETCODE_SESSION"))
                .map(cookie -> HttpCookie.parse(cookie).get(0).getValue())
                .findFirst()
                .orElseThrow(() -> new IOException("Failed to retrieve LEETCODE_SESSION"));
    }
}
