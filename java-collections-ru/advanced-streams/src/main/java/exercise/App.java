package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    private static String getLogs(String log) {
        log = log.replaceAll("environment=", "");
        log = log.replaceAll("\"", "");
        String result = "";

        String[] logs = log.split(",");

        for (String l : logs) {
            if(l.startsWith("X_FORWARDED_")) {
                if (result.equals(""))
                    result += l.replaceAll("X_FORWARDED_", "");
                else
                    result += "," + l.replaceAll("X_FORWARDED_", "");

            }
        }
        return result;
    }
    public static String getForwardedVariables(String log) {
        String[] logs = log.split("\n");
        String result = "";
        for(String l : logs) {
            if(l.startsWith("environment")) {
                if (result.equals(""))
                    result += getLogs(l);
                else if(!getLogs(l).equals(""))
                    result += "," + getLogs(l);
            }
        }
        return result;
    }
}
//END
