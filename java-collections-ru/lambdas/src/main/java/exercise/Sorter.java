package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {

    private static int StringDateToDay (Map<String, String> name1, Map<String, String> name2) {
        String[] name1Date = name1.get("birthday").split("-");
        int name1Int = Integer.parseInt(name1Date[0]) * 365 + Integer.parseInt(name1Date[1]) * 31 + Integer.parseInt(name1Date[2]);

        String[] name2Date = name2.get("birthday").split("-");
        int name2Int = Integer.parseInt(name2Date[0]) * 365 + Integer.parseInt(name2Date[1]) * 31 + Integer.parseInt(name2Date[2]);
        if (name1Int > name2Int)
            return 1;
        else if (name1Int < name2Int)
            return -1;
        else
            return 0;
    }
    public static List<String> takeOldestMans (List<Map<String, String>> users ) {
        List<String> names = users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted((name1, name2) -> StringDateToDay(name1,name2))
                .map(name -> name.get("name"))
                .collect(Collectors.toList());
        return names;
    }
}
// END
