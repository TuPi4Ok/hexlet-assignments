package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {


    private static boolean isFree (String email) {
        String[] domen = email.split("@");
        if(domen[1].equals("gmail.com") || domen[1].equals("yandex.ru") || domen[1].equals("hotmail.com"))
            return true;
        return false;
    }
    public static int getCountOfFreeEmails(List<String> emails) {
        int count_emails = emails.stream()
//                .filter(email -> StringUtils.isNotBlank(email))
                .filter(email -> isFree(email))
                .count();
        return count_emails;
    }
}
// END
