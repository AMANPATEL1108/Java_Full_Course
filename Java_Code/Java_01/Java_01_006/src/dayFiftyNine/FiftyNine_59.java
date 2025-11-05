package dayFiftyNine;

public class FiftyNine_59 {
    public static void main(String[] args) {
        int a = 5;
        int[] c = {1, 2, 5};
        System.out.println(change(a, c));
        String st = "1-23-45 6";
        System.out.println(reformatNumber(st));
    }

    public static String reformatNumber(String number) {
        number = number.replaceAll("-", "").replaceAll(" ", "");
        String ans = "";
        for (int i = 0; i < number.length(); i++) {
            if (i > 0 && i % 3 == 0)
                ans += "-" + number.substring(i, i + 1);
            else
                ans += number.substring(i, i + 1);
        }
        String[] split = ans.split("-");
        if (split[split.length - 1].length() == 1) {
            split[split.length - 1] = split[split.length - 2].substring(split[split.length - 2].length() - 1)
                    + split[split.length - 1];
            split[split.length - 2] = split[split.length - 2].substring(0, split[split.length - 2].length() - 1);
        }
        return String.join("-", split);
    }

    public static int change(int amount, int[] coins) {
        int dp[] = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

}
