package exercise;

// BEGIN
class App {
    public static void printSquare(Circle circle) {
        double result = 0;
        try {
            result = circle.getSquare();
            System.out.println((int)Math.ceil(result));
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        }
        System.out.println("Вычисление окончено");
    }
}
// END
