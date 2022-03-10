package api;

import io.restassured.response.Response;
import utils.ConfigLoader;

import java.time.Instant;
import java.util.HashMap;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing token..");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSecond = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSecond - 300);
            } else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ABORT!!! Failed to get token");
        }
        return access_token;
    }


    private static Response renewToken() {
        HashMap<String, String> fromParams = new HashMap<>();
        fromParams.put("client_id", ConfigLoader.getInstance().getClientId());
        fromParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        fromParams.put("client_token", ConfigLoader.getInstance().getClientToken());
        fromParams.put("client_type", ConfigLoader.getInstance().getClientType());

        Response response = RestResourceMethod.postAccount("", "");
        if (response.statusCode() != 200) {
            throw new RuntimeException("Abort!!! Renew Token Failed");
        }
        return response;
    }
}