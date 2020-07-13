package com.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.parser.ParserRU;
import ru.parser.ResultRU;
import uk.parser.ParserUK;
import uk.parser.ResultUK;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text){
        SendMessage s = new SendMessage();
        s.setChatId(message.getChatId());
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String txt = message.getText();
        switch (txt) {
            case "/start":
                sendMsg(message, "Chose a language \n /Russian or /English");
                break;

            case "/Russian":
            case "Привет":
            case "привет":
                sendMsg(message,
                        "Привет\uD83D\uDD90 чтобы узнать все новости на данный момент введите команду /newsAllR" +
                                "\n" + "\n" +
                                "Чтобы узнать новости на тему политики \n \uD83C\uDFDB введите команду /policyR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему электроники \uD83D\uDCF1 введите команду /mobileR\n" +
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
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true);) {
                    ParserRU.allNewsResults().forEach(x -> sendMsg(message, x.toString()));
                    
                    System.out.println("ALL_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
                    writer.write("ALL_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/policyR":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true);) {
                    List<ResultRU> results = ParserRU.policyNewsResults();

                    for (int i = 0; i < 5 ; i++) {
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("POLICY_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("POLICY_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "/mobileR":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true);) {
                    List<ResultRU> results = ParserRU.mobileNewsResults();

                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("MOBILE_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("MOBILE_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch(IOException | InterruptedException e){
                    e.printStackTrace();
                }
                break;

            case "/carR":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true);) {
                    List<ResultRU> results = ParserRU.carsNewsResults();

                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("CAR_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("CAR_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch(IOException | InterruptedException e){
                    e.printStackTrace();
                }
                break;

            case "/sportR":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                    List<ResultRU> results = ParserRU.sportNewsResults();

                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("SPORT_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("SPORT_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch(IOException | InterruptedException e){
                    e.printStackTrace();
                }
                break;

            case "/artR":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                    List<ResultRU> results = ParserRU.artNewsResults();

                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("ART_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("ART_RU: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch(IOException | InterruptedException e){
                    e.printStackTrace();
                }
                break;

            case "/helpR":
                sendMsg(message,  "Приносим извинение за неполадки, программа находиться в стадии разработки " +
                        "\n" + "\n" +
                        "Если вы нашли неполадки или хотите расширить функциональность программы пишите на почту javacoder1707@gmail.com");

                System.out.println("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
                    writer.write("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;




            case "/English":
            case "hi":
            case "Hello":
            case "Hi":
            case "hello":
                sendMsg(message,
                        "Hi\uD83D\uDD90 to get the all news, enter the command \n /newsAll" +
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
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                    ParserUK.allNewsResults().forEach(x -> sendMsg(message, x.toString()));

                    System.out.println("ALL_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());
                    writer.write("ALL_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/newsUK":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true);) {
                    List<ResultUK> results = ParserUK.newsOfUKResults();

                    for(int i = 0; i < 5; i++){
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("NEWS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("NEWS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException | InterruptedException e){
                    e.printStackTrace();
                }
                break;

            case "/newsWorld":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                    List<ResultUK> results = ParserUK.worldNewsResults();

                    for(int i = 0; i < 5; i++){
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("WORLD_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("WORLD_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "/politic":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                    List<ResultUK> results = ParserUK.politicNewsResults();

                    for(int i = 0; i < 5; i++){
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("POLITIC_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("POLITIC_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "/motors":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true);) {
                    List<ResultUK> results = ParserUK.motorsNewsResults();

                    for(int i = 0; i < 5; i++){
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("MOTORS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("MOTORS_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "/sport":
                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)) {
                    List<ResultUK> results = ParserUK.sportNewsResults();

                    for(int i = 0; i < 5; i++){
                        sendMsg(message, results.get(i).toString());
                        Thread.sleep(1000);
                    }

                    System.out.println("SPORT_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                    writer.write("SPORT_UK: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "/help":
                sendMsg(message,
                        "We apologize for the problems, the program is under development" +
                                "\n" + "\n" +
                                "If you find a problem or want to expand the functionality of the program, write to javacoder1707@gmail.com");

                System.out.println("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date());

                try(FileWriter writer = new FileWriter("C:\\Users\\Тигр\\Desktop\\dataBot.txt", true)){
                  writer.write("HELP: Sent successfully to " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + " at " + new Date() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                sendMsg(message, "This command does not exist!!!");
                sendMsg(message, "Enter /start");
                break;
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

