package com.nexus.nexusapi.web3;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.LinkedHashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PinataUploader {

    private final OkHttpClient client;
    Map<String, String> jsonContent;
    String pinataJwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mb3JtYXRpb24iOnsiaWQiOiIzNTBmMDk0MC02NGU4LTQ1YTEtYTYyOC0yYzFlODk4OGE0YTciLCJlbWFpbCI6Imp1bGlvZXVzZWJpb3ZzQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwaW5fcG9saWN5Ijp7InJlZ2lvbnMiOlt7ImlkIjoiRlJBMSIsImRlc2lyZWRSZXBsaWNhdGlvbkNvdW50IjoxfSx7ImlkIjoiTllDMSIsImRlc2lyZWRSZXBsaWNhdGlvbkNvdW50IjoxfV0sInZlcnNpb24iOjF9LCJtZmFfZW5hYmxlZCI6ZmFsc2UsInN0YXR1cyI6IkFDVElWRSJ9LCJhdXRoZW50aWNhdGlvblR5cGUiOiJzY29wZWRLZXkiLCJzY29wZWRLZXlLZXkiOiI3MzQ0ZGViZmZiYzZiODExNGYwNyIsInNjb3BlZEtleVNlY3JldCI6IjliNjcyZjBkMTcxODU3YTg0N2NlNWQxMzg1ZWJkOTEzYTY4YWNkNTc5MDQzNzI4ZmU2ZDk4NmVlZGI1MmNlOWMiLCJpYXQiOjE2OTY3ODU1Nzd9.Sr9ZlOycihM-zxB5sDAh9fkg1hlvfNLcfYZbw-iJMio";



    PinataUploader(LinkedHashMap<String, String> jsonContent) {
        this.client = new OkHttpClient();
        this.jsonContent = jsonContent;
    }

    private String uploadJsonToPinata(Map<String, String> pinataContent, String pinataJwt) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String jsonContent = "{\"pinataContent\":" + toJson(pinataContent) + "}";
        RequestBody body = RequestBody.create(jsonContent, mediaType);

        Request request = new Request.Builder()
                .url("https://api.pinata.cloud/pinning/pinJSONToIPFS")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer " + pinataJwt)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                System.out.println("Failed to upload JSON file!");
                System.out.println("Status code: " + response.code());
                return null;
            }
        }
    }

    private String toJson(Map<String, String> map) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        // Remove the trailing comma if there are entries
        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("}");
        return json.toString();
    }

    private String getIpfsHash(String json) {
        // get the ipfs hash from the JSON response
         // Create a regular expression pattern to match the IPFS hash.
         Pattern pattern = Pattern.compile("\"IpfsHash\":\"([A-Za-z0-9]+)\"");

         // Match the regular expression pattern against the JSON string.
         Matcher matcher = pattern.matcher(json);
 
         // If the pattern matches, get the IPFS hash from the match.
         if (matcher.find()) {
             String ipfsHash = matcher.group(1);

                // Return the IPFS hash.
                return ipfsHash;
            } else {
                // If the pattern does not match, return null.
                return null;
            }
    

    }

    public String request() {
        try {
            String response = uploadJsonToPinata(jsonContent, pinataJwt);
            String ipfsHash = getIpfsHash(response);
            return ipfsHash;
        } catch (IOException e) {
            System.out.println("Failed to upload JSON file!");
            e.printStackTrace();
            return null;
        }
    }

    
}