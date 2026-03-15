public class DayOneEightySeven {
    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
    }

    public static int titleToNumber(String columnTitle) {
        int value = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            char letter = columnTitle.charAt(i);
            value = value * 26 + (letter - 'A' + 1);
        }

        return value;
    }
}
