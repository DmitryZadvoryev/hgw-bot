package bot;

import config.Config;
import data.Factory;
import data.TeamFactory;
import data.entities.Team;
import data.entities.User;
import exceptions.OnlyGroupChatExceptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.BotService;
import util.TimeUtil;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static constants.Constants.*;
import static validation.Validation.requireNonNull;


public class Bot extends TelegramLongPollingBot {

    private Map<String, LocalTime> timer = new LinkedHashMap<>();
    private final Random rn = new Random();
    private final TimeUtil timeUtil = new TimeUtil();
    private final BotService bot = new BotService();

    public void onUpdateReceived(Update update) {

        TeamFactory.getInstance().getTeams();

        Message msg = update.getMessage();

        String id = String.valueOf(msg.getFrom().getId());
        String firstName = msg.getFrom().getFirstName();
        String lastName = msg.getFrom().getLastName();
        String fullName = getFullName(firstName, lastName);

        User user = new User(id, fullName);

        if(msg.getChat().isSuperGroupChat()) {
            switch (msg.getText().toLowerCase().trim()) {
                case ("/help"):
                    sendMsg(msg, " /поймать снитч\n" + " " + "/список\n" + " " + "/очки\n");
                    break;

                case ("/надеть шляпу"):
                    try {
                        User alreadyUser = Factory.getInstance().getUserDAO().get(id);
                        if (requireNonNull(alreadyUser)) {
                            String message = bot.userAlreadyInDatabase(alreadyUser);
                            sendReplyMsg(msg, message);
                        } else {
                            List<Team> teamList = Factory.getInstance().getTeamDAO().list();
                            int randomValue = rn.nextInt(NUMBER_OF_TEAMS) + 1;
                            String message = bot.rollTeam(user, randomValue, teamList);
                            sendReplyMsg(msg, message);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case ("/поймать снитч"):
                    try {
                        String userID = user.getUserID();
                        User us = Factory.getInstance().getUserDAO().get(userID);
                        if (us.getTeam().getTeamname() != null) {
                            int random = rn.nextInt(RANDOM_VALUE) + 1;
                            timeUtil.initTimer(timer, id);
                            long time = Duration.between(timer.get(id), timeUtil.getCurrentTime()).toMinutes();
                            if (time >= CD) {
                                try {
                                    if (random < CHANCE) {
                                        User currentUser = Factory.getInstance().getUserDAO().get(id);
                                        List<Team> list = Factory.getInstance().getTeamDAO().list();
                                        String message = bot.catchSnitch(currentUser, list);
                                        sendReplyMsg(msg, message);
                                        timer.put(id, timeUtil.getLastTry());
                                    } else {
                                        sendReplyMsg(msg, "Ты усердно всматриваешься в небо, но снитч нигде не виден");
                                        timer.put(id, timeUtil.getLastTry());
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                String timer = timeUtil.getTimer(time);
                                sendReplyMsg(msg, timer);
                            }
                        }
                    } catch (NullPointerException | SQLException e) {
                        sendReplyMsg(msg, "Сначала надень шляпу!");
                    }
                    break;
                case ("/очки"):
                    try {
                        List<Team> list = Factory.getInstance().getTeamDAO().sortedList();
                        sendMsg(msg, bot.getTeamPoints(list));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case ("/список"):
                    try {
                        List<Team> teamsList = Factory.getInstance().getTeamDAO().list();
                        sendMsg(msg, bot.getList(teamsList));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        } else {
            try {
                throw new OnlyGroupChatExceptions("Бот работает только в групповом чате!");
            } catch (OnlyGroupChatExceptions e) {
                sendMsg(msg, "Бот работает только в групповом чате!");
            }
        }
    }

    public void sendReplyMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return Config.BOT_NAME;
    }

    public String getBotToken() {
        return Config.BOT_TOKEN;
    }

    public static String getFullName(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder();
        if (firstName != null && lastName != null) {
            sb.append(firstName.trim())
                    .append(" ")
                    .append(lastName.trim());
            return sb.toString();
        } else if (firstName != null && lastName == null) {
            return firstName.trim();
        } else {
            return lastName.trim();
        }
    }
}
