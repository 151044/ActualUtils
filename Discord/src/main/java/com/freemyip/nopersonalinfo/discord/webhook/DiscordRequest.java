package com.freemyip.nopersonalinfo.discord.webhook;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordRequest {
    private URI uri;
    private String content;
    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;

    public DiscordRequest(URI uri, String content){
        this.uri = uri;
        this.content = content;
        request = makeRequest(uri,content);
    }

    public HttpResponse<String> send() throws IOException, InterruptedException {
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
        return res;
    }

    public void setContent(String content) {
        this.content = content;
        request = makeRequest(uri,content);
    }
    private static HttpRequest makeRequest(URI uri,String content){
        return HttpRequest.newBuilder(uri).setHeader("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString("{\n\t\"content\":\"" + content + "\"\n}")).build();
    }
}

