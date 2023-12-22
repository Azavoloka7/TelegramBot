package ua.javarush;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static ua.javarush.TelegramBotContent.*;
import static ua.javarush.TelegramBotUtils.*;


public class MyFirstTelegramBot extends TelegramLongPollingBot
{



    @Override
    public String getBotUsername()
    {
        // TODO: додай ім'я бота в лапки нижче
        return "Zavo_l_oka_bot";
    }


    @Override
    public String getBotToken() {
        // TODO: додай токен бота в лапки нижче
        return "6694993519:AAEMO0aKCwt0tYgBFKl9Wo5XIA9-nkxJm7g";
    }
    @Override
    public void onUpdateReceived(Update update)
    {
        long chatId = getChatId(update);
        if (update.hasMessage() && update.getMessage().getText().equals("/start"))
        {
            sendMessage(chatId, 0, "step_1_pic", STEP_1_TEXT, Map.of(
                    "Злам холодильника" , "step_1_btn"));
        }
        if (update.hasCallbackQuery())
        {
            if (update.getCallbackQuery().getData().equals("step_1_btn")&& getGlories(chatId)==0)
            {
                sendMessage(chatId, 20, "step_2_pic", STEP_2_TEXT, Map.of(
                        "Взяти сосиску! +20 слави", "step_2_btn",
                        "Взяти рибку! +20 слави", "step_2_btn",
                        "Скинути банку з огірками! +20 слави", "step_2_btn"));
            }
            if (update.getCallbackQuery().getData().equals("step_2_btn")&& getGlories(chatId)==20)
            {
                sendMessage(chatId, 30, "step_3_pic", STEP_3_TEXT, Map.of(
                        "Злам робота пилососа" , "step_3_btn"));
            }
            if (update.getCallbackQuery().getData().equals("step_3_btn")&& getGlories(chatId)==50)
            {
                sendMessage(chatId, 30, "step_4_pic", STEP_4_TEXT, Map.of(
                        "Відправити робопилосос за їжею! +30 слави", "step_4_btn",
                        "ВПроїхатися на робопилососі! +30 слави", "step_4_btn",
                        "Тікати від робопилососа! +30 слави", "step_4_btn"));
            }
            if (update.getCallbackQuery().getData().equals("step_4_btn")&& getGlories(chatId)==80)
            {
                sendMessage(chatId, 40, "step_5_pic", STEP_5_TEXT, Map.of(
                        "Одягнути та включити GoPro!" , "step_5_btn"));
            }
            if (update.getCallbackQuery().getData().equals("step_5_btn")&& getGlories(chatId)==120)
            {
                sendMessage(chatId, 40, "step_6_pic", STEP_6_TEXT, Map.of(
                        "Відправити робопилосос за їжею! +30 слави", "step_6_btn",
                        "ВПроїхатися на робопилососі! +30 слави", "step_6_btn",
                        "Тікати від робопилососа! +30 слави", "step_6_btn"));
            }
            if (update.getCallbackQuery().getData().equals("step_6_btn")&& getGlories(chatId)==160)
            {
                sendMessage(chatId, 50, "step_7_pic", STEP_7_TEXT, Map.of(
                        "Злам пароля" , "step_7_btn"));
            }
            if (update.getCallbackQuery().getData().equals("step_7_btn")&& getGlories(chatId)==210)
            {
                sendMessage(chatId, 50, "step_8_pic", STEP_8_TEXT, Map.of(
                        "Вийти на подвір'я" , "step_8_btn"));
            }
            if (update.getCallbackQuery().getData().equals("step_8_btn")&& getGlories(chatId)==260)
            {
                sendMessage(chatId, 50, "final_pic", FINAL_TEXT, Map.of());
           }
        }
    }



    private void sendMessage(Long chatId, int glories, String picName, String text, Map<String, String> buttons ){
        addGlories(chatId, glories);
        SendPhoto photoMessage = createPhotoMessage(chatId, picName);
        executeAsync(photoMessage);
        SendMessage message = createMessage(chatId, text, buttons);
        sendApiMethodAsync(message);

    }


    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}