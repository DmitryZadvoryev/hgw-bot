package exceptions;

public class OnlyGroupChatExceptions extends Exception {

    public OnlyGroupChatExceptions() {
    }

    public OnlyGroupChatExceptions(String message) {
        super(message);
    }

    public OnlyGroupChatExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public OnlyGroupChatExceptions(Throwable cause) {
        super(cause);
    }

    public OnlyGroupChatExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getMessage() {
        return "Бот работает только в групповых чатах!";
    }

}
