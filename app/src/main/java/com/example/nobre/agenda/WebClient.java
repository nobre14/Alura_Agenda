package com.example.nobre.agenda;

import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class WebClient {
    public String post(String json){
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);// POST

            PrintStream outPut = new PrintStream(connection.getOutputStream());
            outPut.println(json);

            connection.connect(); // estabelece conexão com o servidor
            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next();
            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
