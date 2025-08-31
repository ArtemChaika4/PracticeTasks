package edu.dnu.fpm.pz;

public class App {
    public static void main(String[] args) {
        try {
            demo();
        } catch (MyException e) {
            e.toLog();
        }
    }

    private static void demo() throws MyException {
        Time time = new Time();

        System.out.println("Встановлений час: ");
        time.setTime(9, 40, 10);
        System.out.println(time);

        System.out.println("\nЗміна часу на 23 години вперед:");
        time.shiftHours(23);
        System.out.println(time);

        System.out.println("\nЗміна часу на 128 хвилин вперед:");
        time.shiftMinutes(128);
        System.out.println(time);

        System.out.println("\nЗміна часу на 3666 секунд вперед:");
        time.shiftSeconds(3666);
        System.out.println(time);

        System.out.println("\nЗміна часу на 12 годин назад:");
        time.shiftHours(-12);
        System.out.println(time);

        System.out.println("\nЗміна часу на 69 хвилин назад:");
        time.shiftMinutes(-69);
        System.out.println(time);

        System.out.println("\nЗміна часу на 1206 секунд назад:");
        time.shiftSeconds(-1206);
        System.out.println(time);

        System.out.println("\nВстановлення некоректного часу: ");
        time.setHours(66);
    }
}
