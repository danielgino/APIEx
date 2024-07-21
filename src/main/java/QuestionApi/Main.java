package QuestionApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import static QuestionApi.finals.Finals.category;

public class Main {

    public static void main(String[] args) {
         int points=0;
    menu();

    }

    public static void menu() {
        boolean on = true;
        while (on) {
            System.out.println("""
                    Welcome to Trivia Game! Please choose category of Questions:
                    1.Movies
                    2.Vehicle
                    3.Celebrity
                    4.Animals
                    """);


            int choice = new Scanner(System.in).nextInt();
            switch (choice) {

                case 1 -> {
                    int start = 0;
                    while (start < 3) {
                        questionsGenerator(category.MOVIES);
                        start++;
                    }
                    choice=0;
                }
                case 2->{
                    int start = 0;
                    while (start < 3) {
                        questionsGenerator(category.VEHICLE);
                        start++;
                    }
                    choice = 0;
                }
                case 3->{
                    int start = 0;
                    while (start < 3) {
                        questionsGenerator(category.CELEBRITY);
                        start++;
                    }
                    choice = 0;
                }
                case 4->{
                    int start = 0;
                    while (start < 3) {
                        questionsGenerator(category.ANIMAL);
                        start++;
                    }
                    choice = 0;
                }
            }
        }
    }

    public static void questionsGenerator(int num) {
        try {
            Scanner scanner=new Scanner(System.in);
            CloseableHttpClient client= HttpClients.createDefault();
            URI uri=new URIBuilder("https://opentdb.com/api.php?amount=1&category="+num+"&difficulty=easy&type=multiple")
                    .build();
            HttpGet getRequest=new HttpGet(uri);
            HttpResponse response=client.execute(getRequest);
            String consoleResponse= EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            Response question=objectMapper.readValue(consoleResponse, Response.class);
            System.out.println(question.getResults().toString());
            String userAnswer=scanner.nextLine();
            checkIfCorrectAnswer(question,userAnswer);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int checkIfCorrectAnswer(Response question,String userAnswer) {
        int points = 0;
        if (userAnswer.equalsIgnoreCase(question.getResults().get(0).getCorrect_answer())) {
            System.out.println("Correct ! you got 1 point");
            points+=1;

        } else {
            System.out.println("Wrong");
        }
     return points;
    }


}
