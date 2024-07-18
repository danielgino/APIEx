package org.taskapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
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

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static CloseableHttpClient client= HttpClients.createDefault();

    public static void main(String[] args) {

        menu();

    }


    public static void menu(){
        boolean on=true;
        Scanner scanner=new Scanner(System.in);
        while (on) {
            System.out.println("""
                    What you wanna do?:
                    1.Register
                    2.Get Task
                    3.Add Tasks
                    4.Set Task Done
                    5.Exit
                    """);

            int choice=new Scanner(System.in).nextInt();
             String id=null;
             String task=null;
            switch (choice){
                case 1-> {
                    System.out.println("Enter ID to Register: ");
                     id = scanner.nextLine();
                    register(id);
                }
                case 2->{
                    System.out.println("Enter ID to get tasks: ");
                    id = scanner.nextLine();
                    getTask(id);
                }
                case 3->{
                    System.out.println("Enter ID : ");
                    id = scanner.nextLine();
                    System.out.println("Type your Task: ");
                     task = scanner.nextLine();
                    addTask(id, task);

                }
                case 4->{
                    System.out.println("Enter ID: ");
                    id=scanner.nextLine();
                    System.out.println("Type your Task: ");
                     task = scanner.nextLine();
                     setTaskDone(id,task);
                }
                case 5->{
                    on=false;
                }
            }
        }
    }
    public static void register(String id){
        try {
            URI uri=new URIBuilder("https://app.seker.live/fm1/register")
                    .setParameter("id",id)
                    .build();
            HttpPost post=new HttpPost(uri);
            CloseableHttpResponse response=client.execute(post);
            String consoleResponse= EntityUtils.toString(response.getEntity());
            System.out.println(consoleResponse);
            Response responseObject=new ObjectMapper().readValue(consoleResponse, Response.class);
            if (responseObject.isSuccess()){
                System.out.println("Registered Successfully!");
            }
            errorCodeCheck(responseObject,id);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getTask(String id){
        try {
            URI uri=new URIBuilder("https://app.seker.live/fm1/get-tasks")
                    .setParameter("id",id)
                    .build();
            HttpGet request=new HttpGet(uri);
            CloseableHttpResponse response = client.execute(request);
            String consoleResponse = EntityUtils.toString(response.getEntity());
            System.out.println(consoleResponse);
            Response responseObject=new ObjectMapper().readValue(consoleResponse,Response.class);
            //System.out.println(responseObject.getTasks().toString());
            System.out.println("Done Task: ");
            for (int i = 0; i <responseObject.getTasks().size() ; i++) {
                if (responseObject.getTasks().get(i).isDone()){
                    System.out.println(responseObject.getTasks().get(i).toString());
                }
            }
            System.out.println("UnDone Task: ");
            for (int i = 0; i <responseObject.getTasks().size() ; i++) {
                if (!responseObject.getTasks().get(i).isDone()){
                    System.out.println(responseObject.getTasks().get(i).toString());
                }
            }
            errorCodeCheck(responseObject,id);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addTask(String id, String taskText){
        try {
            URI uri=new URIBuilder("https://app.seker.live/fm1/add-task")
                    .setParameter("id",id)
                    .setParameter("text",taskText)
                    .build();
            HttpPost post=new HttpPost(uri);
            CloseableHttpResponse response=client.execute(post);
            String consoleResponse=EntityUtils.toString(response.getEntity());
           Response responseObject=new ObjectMapper().readValue(consoleResponse,Response.class);
            errorCodeCheck(responseObject,id);
           if (responseObject.isSuccess()){
               System.out.println("Task added Successfully!");
           }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setTaskDone(String id, String taskText){
        try {
            URI uri=new URIBuilder("https://app.seker.live/fm1/set-task-done")
                    .setParameter("id",id)
                    .setParameter("text",taskText)
                    .build();
            HttpPost post = new HttpPost(uri);
            CloseableHttpResponse response=client.execute(post);
            String consoleResponse=EntityUtils.toString(response.getEntity());
            System.out.println(consoleResponse);
            Response responseObject=new ObjectMapper().readValue(consoleResponse,Response.class);
            if (responseObject.isSuccess()){
                System.out.println("Task set to done Successfully!");
            }
            errorCodeCheck(responseObject,id);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void errorCodeCheck(Response response,String id){
        switch (response.getErrorCode()){
            case 1000-> System.out.println("ID was not sent to the server");
            case 1001-> System.out.println("The ID:" +id+" is not registered yet!");
            case 1002-> System.out.println("You didnt enter a ID!");
            case 1003-> System.out.println("This ID Already Taken!");
            case 1004-> System.out.println("The task was not sent to the server!");
            case 1005-> System.out.println("This task already open!");
            case 1006-> System.out.println("This task not exists for this user..");
            case 1007-> System.out.println("This task already marked as done!");




        }
    }
}