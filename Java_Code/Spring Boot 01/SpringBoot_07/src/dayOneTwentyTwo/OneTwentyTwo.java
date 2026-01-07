package dayOneTwentyTwo;

import java.util.ArrayList;
import java.util.List;

public class OneTwentyTwo {

    public static void main(String[] args) {
        int index = 3;
        System.out.println(getRow(index));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0) return res;
        res.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = res.size() - 1; j > 0; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
            res.add(1);
        }
        return res;

    }
}

