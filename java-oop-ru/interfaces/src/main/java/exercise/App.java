package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> inputList, int n) {
        List <String> result = new ArrayList<>();
        result = inputList.stream()
                .sorted(Comparator.comparing(x -> x.getArea()))
                .map(x ->x.toString())
                .limit(n)
                .toList();
        return result;
    }
}
// END
