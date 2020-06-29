import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.parser.ParserRU;
import uk.parser.ParserUK;
import java.io.IOException;

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
                sendMsg(message,
                        "Привет\uD83D\uDD90, чтобы узнать новости введите команду /newsR" +
                                "\n" + "\n" +
                                "Чтобы узнать новости на тему политики \n \uD83C\uDFDB, введите команду /policyR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему электроники \uD83D\uDCF1, введите команду /mobileR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему авто\n \uD83D\uDE98, введите команду /carR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему спорта \n⚽️, введите команду /sportR\n" +
                                "\n" +
                                "Чтобы узнать новости на тему искусства \uD83C\uDFA8, введите команду /artR\n" +
                                "\n" +
                                "Если возникли неполадки введите команду - /helpR");
                break;

            case "/newsR":
                try {
                    ParserRU.getPars().forEach(x -> sendMsg(message, x.toString()));
                    System.out.println("Sent successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/policyR":
                try {
                    for (int i = 0; i < 5 ; i++) {
                        sendMsg(message, ParserRU.getParsPolicy().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/mobileR":
                try {
                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, ParserRU.getParsMobile().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch(IOException e){
                    e.printStackTrace();
                }
                break;
            case "/carR":
                try {
                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, ParserRU.getParsAuto().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;

            case "/sportR":
                try {
                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, ParserRU.getParsSport().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch(IOException e){
                    e.printStackTrace();
                }
                break;

            case "/artR":
                try {
                    for (int i = 0; i < 5; i++) {
                        sendMsg(message, ParserRU.getParsArt().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch(IOException e){
                    e.printStackTrace();
                }
                break;

            case "/helpR":
                sendMsg(message,  "Приносим извинение за неполадки, программа находиться в стадии разработки " +
                        "\n" + "\n" +
                        "Если вы нашли неполадки или хотите расширить функциональность программы пишите на почту javacoder1707@gmail.com");
                break;




            case "/English":
            case "hi":
            case "Hello":
                sendMsg(message,
                        "Hi\uD83D\uDD90, to get the all news, enter the command \n /newsAll" +
                                "\n" + "\n" +
                                "To get news on the topic of UK \uD83C\uDDEC\uD83C\uDDE7, enter the command /newsUK \n" +
                                "\n" +
                                "To get news on the topic of world \uD83C\uDF0D, enter the command /newsWorld \n" +
                                "\n" +
                                "To get news about politic \uD83C\uDFDB, enter the command /politic \n" +
                                "\n" +
                                "To get news about cars \uD83D\uDE98, enter the command /motors \n" +
                                "\n" +
                                "To get news about the sport \uD83C\uDFC8, enter the command /sport \n" +
                                "\n" +
                                "If you have problems, enter the command\n - /help");
                break;

            case "/newsAll":
                try {
                    ParserUK.getParsAll().forEach(x -> sendMsg(message, x.toString()));
                    System.out.println("Sent successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/newsUK":
                try {
                    for(int i = 0; i < 5; i++){
                        sendMsg(message, ParserUK.getParsUK().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch (IOException e){
                    e.printStackTrace();
                }
                break;

            case "/newsWorld":
                try {
                    for(int i = 0; i < 5; i++){
                        sendMsg(message, ParserUK.getParsWorld().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/politic":
                try {
                    for(int i = 0; i < 5; i++){
                        sendMsg(message, ParserUK.getParsPolitic().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/motors":
                try {
                    for(int i = 0; i < 5; i++){
                        sendMsg(message, ParserUK.getParsMotors().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/sport":
                try {
                    for(int i = 0; i < 5; i++){
                        sendMsg(message, ParserUK.getParsSport().get(i).toString());
                    }
                    System.out.println("Sent successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "/help":
                sendMsg(message,
                        "We apologize for the problems, the program is under development" +
                                "\n" + "\n" +
                                "If you find a problem or want to expand the functionality of the program, write to javacoder1707@gmail.com");
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
        return "1237517329:AAHOKBf9a3XulgmsR2pBF-GHmnuv_4hkgCA";
    }
}
