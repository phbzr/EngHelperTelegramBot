import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }



    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendButton(sendMessage);
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    try {
                        execute(sendMessage.setText(message.getText() + " how can i help"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/settings":
                    try {
                        execute(sendMessage.setText(message.getText() + " What are we going to customize?"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/lera":
                    try {
                        execute(sendMessage.setText(message.getText() + " Lerups KIKIPUPS!!!"));
                    } catch (TelegramApiException e){
                        e.printStackTrace();
                    }
            }

        }
    }

    public void sendButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstKeyRow = new KeyboardRow();

        firstKeyRow.add(new KeyboardButton("/help"));
        firstKeyRow.add(new KeyboardButton("/lera"));

        keyboardRows.add(firstKeyRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

    }


    public String getBotUsername() {
        return "EngHelperv1bot";
    }

    public String getBotToken() {
        return "**************";
    }
}
