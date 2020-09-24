package com.lmsilva.geocoding.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class GeocodeAPIService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final long BASE_DELAY_MILLIS = 100;
    private static final String BASE_PATH = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String ADDRESS_PARAM = "address";

    public static class LatitudeLongitude {
        private String latitude;
        private String longitude;

        LatitudeLongitude(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }
    }

    public static LatitudeLongitude fetchLatitudeLongitude(String address, String apiKey) {
        String encodedAddress = null;
        try {
            encodedAddress = URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder urlString = new StringBuilder()
                                        .append(BASE_PATH)
                                        .append("?")
                                        .append(ADDRESS_PARAM)
                                        .append("=")
                                        .append(encodedAddress)
                                        .append("&key=")
                                        .append(apiKey);

        LatitudeLongitude result = null;

        URL url;
        try {
            url = new URL(urlString.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        HttpsURLConnection connection;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            ctx.init(null, new TrustManager[] { new InvalidCertificateTrustManager() }, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        SSLContext.setDefault(ctx);
        connection.setDoOutput(true);
        connection.setHostnameVerifier(new InvalidCertificateHostVerifier());

        try {
            int status = connection.getResponseCode();

            if (status != HttpStatus.OK.value()) {
                System.out.println("There seems to have been an issue when fetching the latitude and longitude");
                return null;
            }

            JsonNode response = MAPPER.readTree(connection.getInputStream());

            JsonNode location = response.findValue("location");
            result = new LatitudeLongitude(location.get("lat").asText(), location.get("lng").asText());
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    public static class InvalidCertificateTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {

        }

        @Override
        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
        }
    }

    public static class InvalidCertificateHostVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String paramString, SSLSession paramSSLSession) {
            return true;
        }
    }
}
