package me.icywater.automod.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT {
    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "YOUR_KEY_HERE"; // API key goes here
        String model = "gpt-3.5-turbo"; // current model of chatgpt api

        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Build the request body
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", model);
            JSONObject messageObject = new JSONObject();
            messageObject.put("role", "user");
            messageObject.put("content", message);
            requestBody.put("messages", new JSONArray().put(messageObject));

            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(requestBody.toString());
            writer.flush();
            writer.close();

            // Get the response
            int responseCode = con.getResponseCode();
            if (responseCode == 429) {
                // If the response is 429, read the Retry-After header and sleep for that amount of time
                String retryAfter = con.getHeaderField("Retry-After");
                if (retryAfter != null) {
                    try {
                        int retryAfterSeconds = Integer.parseInt(retryAfter);
                        Thread.sleep(retryAfterSeconds * 1000);
                    } catch (NumberFormatException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // After sleeping, you can choose to retry the request or return an error message
                return "Rate limit exceeded, please try again later";
            } else if (responseCode != 200) {
                throw new RuntimeException("Server returned HTTP response code: " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }
}