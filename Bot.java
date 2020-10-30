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
import com.parser.ru.ParserRU;
import com.parser.ru.ResultRU;
import com.parser.uk.ParserUK;
import com.parser.uk.ResultUK;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    private final Set<User> USERS = new HashSet<>();
    private final DateFormat HOUR = new SimpleDateFormat("HH");
    private final DateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    //Russian news variables
    private List<ResultRU> allNewsResultsRU = ParserRU.receiveAllNewsResults();
    private final List<ResultRU> policyNewsResultsRU = ParserRU.receivePolicyNewsResults();
    private final List<ResultRU> mobileNewsResultsRU = ParserRU.receiveMobileNewsResults();
    private final List<ResultRU> autoNewsResultsRU = ParserRU.receiveAutoNewsResults();
    private final List<ResultRU> sportNewsResultsRU = ParserRU.receiveSportNewsResults();
    private final List<ResultRU> artNewsResultsRU = ParserRU.receiveArtNewsResults();

    //English news variables
    private List<ResultUK> allNewsResultsUK = ParserUK.receiveAllNewsResults();
    private final List<ResultUK> resultsNewsOfUK = ParserUK.receiveResultsNewsOfUK();
    private final List<ResultUK> worldNewsResultsUK = ParserUK.receiveWorldNewsResults();
    private final List<ResultUK> politicNewsResultsUK = ParserUK.receivePoliticNewsResults();
    private final List<ResultUK> motorsNewsResultsUK = ParserUK.receiveMotorsNewsResults();
    private final List<ResultUK> sportNewsResultsUK = ParserUK.receiveSportNewsResults();

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
    private void sendNewsRU(Message message, List<ResultRU> newsResultsRU){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
            List<ResultRU> results = new ArrayList<>(ParserRU.receiveNewsResultsByType(newsResultsRU.get(0).getType()));
            
            if(newsResultsRU.size() == 5){
                newsResultsRU.forEach(newsResult -> sendMessage(message, newsResult.toString()));
                newsResultsRU.clear();
                newsResultsRU.addAll(results);

                return;
            }

            for(int i = 0; i < 5; i++){
                sendMessage(message, newsResultsRU.get(i).toString());
                newsResultsRU.remove(newsResultsRU.get(i));
            }

            System.out.println( newsResultsRU.get(0).getType() + ": Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write(newsResultsRU.get(0).getType() + ": Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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






    //English news sending methods
    private void sendNewsUK(Message message, List<ResultUK> newsResultsUK){
        try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
            List<ResultUK> results = new ArrayList<>(ParserUK.receiveNewsResultsByType(newsResultsUK.get(0).getType()));
            
            if(newsResultsUK.size() == 4){
                newsResultsUK.forEach(newsResult -> sendMessage(message, newsResult.toString()));
                newsResultsUK.clear();
                newsResultsUK.addAll(results);
                
                return;
            }

            for(int i = 0; i < 4; i++){
                sendMessage(message, newsResultsUK.get(i).toString());
                newsResultsUK.remove(newsResultsUK.get(i));
            }

            System.out.println(newsResultsUK.get(0).getType() +  ": Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

            writer.write(newsResultsUK.get(0).getType() + ": Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


    //Help message sending method
    private void help(Message message){
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
                sendMessage(message, "Choose language \n /russian (Привет) or /english (Hi, Hello)");
                break;

           //Russian commands
            case "/russian":
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
                                "Чтобы узнать новости на тему спорта \n ⚽ введите команду /sportR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему искусства \n \uD83C\uDFA8 введите команду /artR\n" +
                                "\n" +
                                "Если возникли неполадки введите команду - /helpR");
                break;

            case "/newsAllR":
                sendAllNewsRU(message);
                break;

            case "/policyR":
                sendNewsRU(message, policyNewsResultsRU);
                break;

            case "/mobileR":
                sendNewsRU(message, mobileNewsResultsRU);
                break;

            case "/autoR":
                sendNewsRU(message, autoNewsResultsRU);
                break;

            case "/sportR":
                sendNewsRU(message, sportNewsResultsRU);
                break;

            case "/artR":
                sendNewsRU(message, artNewsResultsRU);
                break;

            case "/helpR":
                sendMessage(message,  "Приносим извинение за неполадки, программа находиться в стадии разработки " +
                        "\n" + "\n" +
                        "Если вы нашли неполадки или хотите расширить функциональность программы пишите на почту javacoder1707@gmail.com");
                help(message);
                break;



            //English command
            case "/english":
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
                sendNewsUK(message, resultsNewsOfUK);
                break;

            case "/newsWorld":
                sendNewsUK(message, worldNewsResultsUK);
                break;

            case "/politic":
                sendNewsUK(message, politicNewsResultsUK);
                break;

            case "/motors":
                sendNewsUK(message, motorsNewsResultsUK);
                break;

            case "/sport":
                sendNewsUK(message, sportNewsResultsUK);
                break;

            case "/help":
                sendMessage(message,
                        "We apologize for the problems, the program is under development" +
                                "\n" + "\n" +
                                "If you find a problem or want to expand the functionality of the program, write to javacoder1707@gmail.com");
                help(message);
                break;

            default:
                sendMessage(message, "This is invalid command!!!\n" + "Нет такой команды!!!");
                sendMessage(message, "Enter:\nВведите:\n\n /start");
                break;
        }
        USERS.add(message.getFrom());
        BotDB.addUser(message.getFrom().getFirstName(), message.getFrom().getLastName(), message.getFrom().getId());



        if(HOUR.format(new Date()).equals("23")){
            try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                writer.write("\n" + USERS.size() + " people used JavaNewsUKBot at " + FORMAT.format(new Date()) + " \n \n");
                USERS.clear();
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
