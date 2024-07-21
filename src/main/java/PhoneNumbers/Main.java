package PhoneNumbers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Request;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Enter Phone Number: ");;
//        String number = scanner.nextLine();
//    checkPhoneNumber(number);
        translate();
    }

    public static void checkPhoneNumber(String number) {
        CloseableHttpClient client = HttpClients.createDefault();
        String apiKey="8dWfAVIVsoC2SQFEdDmcLjnyOuDtbQqL";
        try {
            URI uri = new URI("https://api.apilayer.com/number_verification/validate?number="+number);
            HttpGet request = new HttpGet(uri);
            request.addHeader("apikey", apiKey);
            HttpResponse response = client.execute(request);
            String consoleResponse = EntityUtils.toString(response.getEntity());
//            System.out.println(consoleResponse);
            Response responseObject=new ObjectMapper().readValue(consoleResponse, Response.class);
            System.out.println(responseObject.toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void translate(){
        CloseableHttpClient client = HttpClients.createDefault();
        String apiKey="8dWfAVIVsoC2SQFEdDmcLjnyOuDtbQqL";
        try {
            URI uri = new URI("https://api.apilayer.com/language_translation/translate?target=zh");
            HttpPost request = new HttpPost(uri);
            request.addHeader("apikey", apiKey);

            HttpResponse response = client.execute(request);
            String consoleResponse = EntityUtils.toString(response.getEntity());
            System.out.println(consoleResponse);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
