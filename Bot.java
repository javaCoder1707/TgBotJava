package com.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.parser.ParserRU;
import ru.parser.ResultRU;
import uk.parser.ParserUK;
import uk.parser.ResultUK;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bot extends TelegramLongPollingBot {
 private Set<User> users = new HashSet<>();
 private DateFormat hour = new SimpleDateFormat("HH");
 private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

  //Russian
   private List<ResultRU> allNewsResultsRU = ParserRU.receiveAllNewsResults();
   private List<ResultRU> policyNewsResultsRU = ParserRU.receivePolicyNewsResults();
   private List<ResultRU> mobileNewsResultsRU = ParserRU.receiveMobileNewsResults();
   private List<ResultRU> carsNewsResultsRU = ParserRU.receiveCarsNewsResults();
   private List<ResultRU> sportNewsResultsRU = ParserRU.receiveSportNewsResults();
   private List<ResultRU> artNewsResultsRU = ParserRU.receiveArtNewsResults();

   //English
    private List<ResultUK> allNewsResultsUK = ParserUK.receiveAllNewsResults();
    private List<ResultUK> resultsNewsOfUK = ParserUK.receiveResultsNewsOfUK();
    private List<ResultUK> worldNewsResultsUK = ParserUK.receiveWorldNewsResults();
    private List<ResultUK> politicNewsResultsUK = ParserUK.receivePoliticNewsResults();
    private List<ResultUK> motorsNewsResultsUK = ParserUK.receiveMotorsNewsResults();
    private List<ResultUK> sportNewsResultsUK = ParserUK.receiveSportNewsResults();

    public Bot() throws IOException {
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException | IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Russian
    private void sendAllNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
            allNewsResultsRU.forEach(x -> sendMsg(message, x.toString()));

            System.out.println("ALL_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
            writer.write("ALL_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPolicyNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(policyNewsResultsRU.size() == 4){
                policyNewsResultsRU.forEach(x -> sendMsg(message, x.toString()));
                policyNewsResultsRU = ParserRU.receivePolicyNewsResults();

                return;
            }

            for (int i = 0; i <= 4; i++) {
                sendMsg(message, policyNewsResultsRU.get(i).toString());
                policyNewsResultsRU.remove(policyNewsResultsRU.get(i));
                Thread.sleep(1000);
            }

            System.out.println("POLICY_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("POLICY_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMobileNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(mobileNewsResultsRU.size() == 4){
                mobileNewsResultsRU.forEach(x -> sendMsg(message, x.toString()));
                mobileNewsResultsRU = ParserRU.receiveMobileNewsResults();

                return;
            }

            for (int i = 0; i <= 4; i++) {
                sendMsg(message, mobileNewsResultsRU.get(i).toString());
                mobileNewsResultsRU.remove(mobileNewsResultsRU.get(i));
                Thread.sleep(1000);
            }

            System.out.println("MOBILE_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("MOBILE_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void sendCarsNewsRU(Message message) {
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(carsNewsResultsRU.size() == 4){
                carsNewsResultsRU.forEach(x -> sendMsg(message, x.toString()));
                carsNewsResultsRU = ParserRU.receiveCarsNewsResults(); 

                return;
            }

            for (int i = 0; i <= 4; i++) {
                sendMsg(message, carsNewsResultsRU.get(i).toString());
                carsNewsResultsRU.remove(carsNewsResultsRU.get(i));
                Thread.sleep(1000);
            }

            System.out.println("CAR_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("CAR_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void sendSportNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(sportNewsResultsRU.size() == 4){
                sportNewsResultsRU.forEach(x -> sendMsg(message, x.toString()));
                sportNewsResultsRU = ParserRU.receiveSportNewsResults();

                return;
            }

            for (int i = 0; i <= 4; i++) {
                sendMsg(message, sportNewsResultsRU.get(i).toString());
                sportNewsResultsRU.remove(sportNewsResultsRU.get(i));
                Thread.sleep(1000);
            }

            System.out.println("SPORT_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("SPORT_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void sendArtNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(artNewsResultsRU.size() == 4){
                artNewsResultsRU.forEach(x -> sendMsg(message, x.toString()));
                artNewsResultsRU = ParserRU.receiveArtNewsResults();
                                        
               return;
            }

            for (int i = 0; i <= 4; i++) {
                sendMsg(message, artNewsResultsRU.get(i).toString());
                artNewsResultsRU.remove(artNewsResultsRU.get(i));
                Thread.sleep(1000);
            }

            System.out.println("ART_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("ART_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void helpRU(Message message){
        System.out.println("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
            writer.write("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //English
    private void sendAllNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
           allNewsResultsUK.forEach(x -> sendMsg(message, x.toString()));

            System.out.println("ALL_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
            writer.write("ALL_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendNewsOfUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
           
            if(resultsNewsOfUK.size() == 0) resultsNewsOfUK = ParserUK.receiveResultsNewsOfUK();
               
            for(int i = 0; i < 4; i++){
                sendMsg(message, resultsNewsOfUK.get(i).toString());
                resultsNewsOfUK.remove(resultsNewsOfUK.get(i));
                Thread.sleep(1000);
            }

            System.out.println("NEWS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("NEWS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void sendWorldNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(worldNewsResultsUK.size() == 0) worldNewsResultsUK = ParserUK.receiveWorldNewsResults();

            for(int i = 0; i < 4; i++){
                sendMsg(message, worldNewsResultsUK.get(i).toString());
                worldNewsResultsUK.remove(worldNewsResultsUK.get(i));
                Thread.sleep(1000);
            }

            System.out.println("WORLD_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("WORLD_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendPoliticNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(politicNewsResultsUK.size() == 0) politicNewsResultsUK = ParserUK.receivePoliticNewsResults();

            for(int i = 0; i < 4; i++){
                sendMsg(message, politicNewsResultsUK.get(i).toString());
                politicNewsResultsUK.remove(politicNewsResultsUK.get(i));
                Thread.sleep(1000);
            }

            System.out.println("POLITIC_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("POLITIC_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMotorsNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(motorsNewsResultsUK.size() == 0) motorsNewsResultsUK = ParserUK.receiveMotorsNewsResults();

            for(int i = 0; i < 4; i++){
                sendMsg(message, motorsNewsResultsUK.get(i).toString());
                motorsNewsResultsUK.remove(motorsNewsResultsUK.get(i));
                Thread.sleep(1000);
            }

            System.out.println("MOTORS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("MOTORS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendSportNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(sportNewsResultsUK.size() == 0) sportNewsResultsUK = ParserUK.receiveSportNewsResults();

            for(int i = 0; i < 4; i++){
                sendMsg(message,  sportNewsResultsUK.get(i).toString());
                sportNewsResultsUK.remove(sportNewsResultsUK.get(i));
                Thread.sleep(1000);
            }

            System.out.println("SPORT_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("SPORT_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void helpUK(Message message){
        System.out.println("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
            writer.write("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String txt = message.getText();
        sendMsg(message,"Attention! \n" +
        "Technical work in progress, please try again in 30 minutes." +
                "\n" + "\n" +
                "Внимание! \n" +
                "Ведутся технические работы, повторите попытку через 30 минут.");

        switch (txt) {
            case "/start":
                sendMsg(message, "Chose a language \n /Russian or /English");
                break;

           //Russian
            case "/Russian":
            case "Привет":
            case "привет":
                sendMsg(message,
                        "Привет\uD83D\uDC4B  чтобы узнать все новости на данный момент введите команду /newsAllR" +
                                "\n" + "\n" +
                                "Чтобы узнать новости на тему политики \n \uD83C\uDFDB введите команду /policyR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему электроники \n \uD83D\uDCF1 введите команду /mobileR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему авто\n \uD83D\uDE98 введите команду /carR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему спорта \n ⚽️ введите команду /sportR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему искусства \n \uD83C\uDFA8 введите команду /artR\n" +
                                "\n" +
                                "Если возникли неполадки введите команду - /helpR");
                break;

            case "/newsAllR":
                 sendAllNewsRU(message);
                 break;

            case "/policyR":
                sendPolicyNewsRU(message);
                 break;

            case "/mobileR":
                sendMobileNewsRU(message);
                break;

            case "/carR":
               sendCarsNewsRU(message);
                break;

            case "/sportR":
                sendSportNewsRU(message);
                break;

            case "/artR":
               sendArtNewsRU(message);
                break;

            case "/helpR":
                sendMsg(message,  "Приносим извинение за неполадки, программа находиться в стадии разработки " +
                        "\n" + "\n" +
                        "Если вы нашли неполадки или хотите расширить функциональность программы пишите на почту javacoder1707@gmail.com");
                helpRU(message);
                break;



            //English
            case "/English":
            case "hi":
            case "Hello":
            case "Hi":
            case "hello":
                sendMsg(message,
                        "Hi\uD83D\uDC4B to get the all news, enter the command \n /newsAll" +
                                "\n" + "\n" +
                                "To get news on the topic of UK \uD83C\uDDEC\uD83C\uDDE7 enter the command /newsUK \n" +
                                "\n" +
                                "To get news on the topic of world \uD83C\uDF0D enter the command /newsWorld \n" +
                                "\n" +
                                "To get news about politic \uD83C\uDFDB enter the command /politic \n" +
                                "\n" +
                                "To get news about cars \uD83D\uDE98 enter the command /motors \n" +
                                "\n" +
                                "To get news about the sport ⚽ enter the command /sport \n" +
                                "\n" +
                                "If you have problems enter the command\n - /help");
                break;

            case "/newsAll":
               sendAllNewsUK(message);
                break;

            case "/newsUK":
               sendNewsOfUK(message);
                break;

            case "/newsWorld":
              sendWorldNewsUK(message);
                break;

            case "/politic":
               sendPoliticNewsUK(message);
                break;

            case "/motors":
               sendMotorsNewsUK(message);
                break;

            case "/sport":
                sendSportNewsUK(message);
                break;

            case "/help":
                sendMsg(message,
                        "We apologize for the problems, the program is under development" +
                                "\n" + "\n" +
                                "If you find a problem or want to expand the functionality of the program, write to javacoder1707@gmail.com");
                helpUK(message);
                break;

            default:
                sendMsg(message, "This command does not exist!!!");
                sendMsg(message, "Enter /start");
                break;
        }
        users.add(message.getFrom());

        if(hour.format(new Date()).equals("23")){
            try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                writer.write("\n" + users.size() + " people used JavaNewsUKBot at " + format.format(new Date()) + " \n \n");
                users.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "JavaNewsUKBot";
    }

    @Override
    public String getBotToken() {
        return "1237517329:AAHOKBf9a3XulgmsR2pBF-GHmnuv_4hkgCA";
    }
}
