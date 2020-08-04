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

    //Russian news variables
    private List<ResultRU> allNewsResultsRU = ParserRU.receiveAllNewsResults();
    private List<ResultRU> policyNewsResultsRU = ParserRU.receivePolicyNewsResults();
    private List<ResultRU> mobileNewsResultsRU = ParserRU.receiveMobileNewsResults();
    private List<ResultRU> carsNewsResultsRU = ParserRU.receiveCarsNewsResults();
    private List<ResultRU> sportNewsResultsRU = ParserRU.receiveSportNewsResults();
    private List<ResultRU> artNewsResultsRU = ParserRU.receiveArtNewsResults();

    //English news variables
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
    
    private void sendMessage(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
            Thread.sleep(1000);
        } catch (TelegramApiException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Russian news sending methods
    private void sendAllNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
            allNewsResultsRU.forEach(allNewsResult -> sendMessage(message, allNewsResult.toString()));
            allNewsResultsRU = ParserRU.receiveAllNewsResults();

            System.out.println("ALL_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
            writer.write("ALL_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPolicyNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(policyNewsResultsRU.size() == 5){
                policyNewsResultsRU.forEach(policyNewsResult -> sendMessage(message, policyNewsResult.toString()));
                policyNewsResultsRU = ParserRU.receivePolicyNewsResults();

                return;
            }

            for (int i = 0; i < 5; i++) {
                sendMessage(message, policyNewsResultsRU.get(i).toString());
                policyNewsResultsRU.remove(policyNewsResultsRU.get(i));
            }

            System.out.println("POLICY_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("POLICY_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMobileNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(mobileNewsResultsRU.size() == 5){
                mobileNewsResultsRU.forEach(mobileNewsResult -> sendMessage(message, mobileNewsResult.toString()) );
                mobileNewsResultsRU = ParserRU.receiveMobileNewsResults();

                return;
            }

            for (int i = 0; i < 5; i++) {
                sendMessage(message, mobileNewsResultsRU.get(i).toString());
                mobileNewsResultsRU.remove(mobileNewsResultsRU.get(i));
            }

            System.out.println("MOBILE_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("MOBILE_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void sendCarsNewsRU(Message message) {
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(carsNewsResultsRU.size() == 5){
                carsNewsResultsRU.forEach(carsNewsResult -> sendMessage(message, carsNewsResult.toString()));
                carsNewsResultsRU = ParserRU.receiveCarsNewsResults();

                return;
            }

            for (int i = 0; i < 5; i++) {
                sendMessage(message, carsNewsResultsRU.get(i).toString());
                carsNewsResultsRU.remove(carsNewsResultsRU.get(i));
            }

            System.out.println("CAR_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("CAR_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException  e){
            e.printStackTrace();
        }
    }

    private void sendSportNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(sportNewsResultsRU.size() == 5){
                sportNewsResultsRU.forEach(sportNewsResult -> sendMessage(message, sportNewsResult.toString()));
                sportNewsResultsRU = ParserRU.receiveSportNewsResults();

                return;
            }

            for (int i = 0; i < 5; i++) {
                sendMessage(message, sportNewsResultsRU.get(i).toString());
                sportNewsResultsRU.remove(sportNewsResultsRU.get(i));
            }

            System.out.println("SPORT_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("SPORT_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void sendArtNewsRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            Collections.shuffle(artNewsResultsRU);

            if(artNewsResultsRU.size() == 5){
                artNewsResultsRU.forEach(artNewsResult -> sendMessage(message, artNewsResult.toString()));
                artNewsResultsRU = ParserRU.receiveArtNewsResults();

                return;
            }

            for (int i = 0; i < 5; i++) {
                sendMessage(message, artNewsResultsRU.get(i).toString());
                artNewsResultsRU.remove(artNewsResultsRU.get(i));
            }

            System.out.println("ART_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("ART_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void helpRU(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
            
            System.out.println("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
            
            writer.write("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //English news sending  methods 
    private void sendAllNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
           allNewsResultsUK.forEach(allNewsResult -> sendMessage(message, allNewsResult.toString()));
           allNewsResultsUK = ParserUK.receiveAllNewsResults();

            System.out.println("ALL_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
            writer.write("ALL_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendNewsOfUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(resultsNewsOfUK.size() == 4){
                resultsNewsOfUK.forEach(resultNewsOfUK -> sendMessage(message, resultNewsOfUK.toString()));
                resultsNewsOfUK = ParserUK.receiveResultsNewsOfUK();

                return;
            }

            for(int i = 0; i < 4; i++){
                sendMessage(message, resultsNewsOfUK.get(i).toString());
                resultsNewsOfUK.remove(resultsNewsOfUK.get(i));
            }

            System.out.println("NEWS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("NEWS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void sendWorldNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(worldNewsResultsUK.size() == 4){
                worldNewsResultsUK.forEach(worldNewsResult -> sendMessage(message, worldNewsResult.toString()));
                worldNewsResultsUK = ParserUK.receiveWorldNewsResults();

                return;
            }

            for(int i = 0; i < 4; i++){
                sendMessage(message, worldNewsResultsUK.get(i).toString());
                worldNewsResultsUK.remove(worldNewsResultsUK.get(i));
            }

            System.out.println("WORLD_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("WORLD_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPoliticNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(politicNewsResultsUK.size() == 4){
                politicNewsResultsUK.forEach(politicNewsResult -> sendMessage(message, politicNewsResult.toString()));
                politicNewsResultsUK = ParserUK.receivePoliticNewsResults();

                return;
            }

            for(int i = 0; i < 4; i++){
                sendMessage(message, politicNewsResultsUK.get(i).toString());
                politicNewsResultsUK.remove(politicNewsResultsUK.get(i));
            }

            System.out.println("POLITIC_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("POLITIC_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMotorsNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(motorsNewsResultsUK.size() == 4){
                motorsNewsResultsUK.forEach(motorsNewsResult -> sendMessage(message, motorsNewsResult.toString()));
                motorsNewsResultsUK = ParserUK.receiveMotorsNewsResults();

                return;
            }

            for(int i = 0; i < 4; i++){
                sendMessage(message, motorsNewsResultsUK.get(i).toString());
                motorsNewsResultsUK.remove(motorsNewsResultsUK.get(i));
            }

            System.out.println("MOTORS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("MOTORS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendSportNewsUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {

            if(sportNewsResultsUK.size() == 4){
                sportNewsResultsUK.forEach(sportNewsResult -> sendMessage(message, sportNewsResult.toString()));
                sportNewsResultsUK = ParserUK.receiveSportNewsResults();

                return;
            }

            for(int i = 0; i < 4; i++){
                sendMessage(message,  sportNewsResultsUK.get(i).toString());
                sportNewsResultsUK.remove(sportNewsResultsUK.get(i));
            }

            System.out.println("SPORT_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write("SPORT_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void helpUK(Message message){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
            
            System.out.println("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
            
            writer.write("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String txt = message.getText();
        switch (txt) {
            case "/start":
                sendMessage(message, "Choose language \n /Russian (Привет) or /English (Hi, Hello)");
                break;

            //Russian commands
            case "/Russian":
            case "Привет":
            case "привет":
                sendMessage(message,
                        "Привет\uD83D\uDC4B  чтобы узнать все новости на данный момент введите команду /newsAllR" +
                                "\n" + "\n" +
                                "Чтобы узнать новости на тему политики \n \uD83C\uDFDB введите команду /policyR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему технологий \n \uD83D\uDCF1 введите команду /mobileR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему авто \n \uD83D\uDE98 введите команду /autoR\n" +
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

            case "/autoR":
                sendCarsNewsRU(message);
                break;

            case "/sportR":
                sendSportNewsRU(message);
                break;

            case "/artR":
                sendArtNewsRU(message);
                break;

            case "/helpR":
                sendMessage(message,  "Приносим извинение за неполадки, программа находиться в стадии разработки " +
                        "\n" + "\n" +
                        "Если вы нашли неполадки или хотите расширить функциональность программы пишите на почту javacoder1707@gmail.com");
                helpRU(message);
                break;



            //English commands
            case "/English":
            case "hi":
            case "Hello":
            case "Hi":
            case "hello":
                sendMessage(message,
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
                sendMessage(message,
                        "We apologize for the problems, the program is under development" +
                                "\n" + "\n" +
                                "If you find a problem or want to expand the functionality of the program, write to javacoder1707@gmail.com");
                helpUK(message);
                break;

            default:
                sendMessage(message, "This command does not exist!!!\n Нет такой команды!!!");
                sendMessage(message, "Enter /start  \n Введите /start");
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
        return "TOKEN";
    }
}
