package com.freemyip.nopersonalinfo.discord.webhook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

class DiscordRequestTest {

    @Test
    void send() throws IOException, InterruptedException {
        String testWebhook = "https://discord.com/api/webhooks/845867392361824296/OCUguDsyf-SXjZCg6WZ0J9CTc4Lcc12PWI9mQXj0g3ikjwE4L8UBKLqFBjjcfMk9xvtK";
        DiscordRequest request = new DiscordRequest(URI.create(testWebhook),"Constructor content.");
        HttpResponse<String> res = request.send();
        Assertions.assertTrue(res.statusCode() >= 200 && res.statusCode() < 300);
        for(String s : List.of("In content setter.","î¨èå¼ë·¯â¦¥ë£¯î¢»ê®é¢îè","<@586790522157531136>")) {
            request.setContent(s);
            res = request.send();
            Assertions.assertTrue(res.statusCode() >= 200 && res.statusCode() < 300);
        }
    }
}