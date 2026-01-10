package dayOneTwentyFive;

public class OneTwentyFive {
    public static void main(String[] args) {
        int a = 38;
        System.out.println(addDigits(a));
    }

    public static int addDigits(int num) {
        int sum = 0;
        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - 48;
            sum += c;
        }
        if (String.valueOf(sum).length() == 1) {
            return sum;
        }
        return sum = addDigits(sum);
    }
}
