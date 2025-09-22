package fifteen;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Fifteen_15 {
    public static void main(String[] args) {
//        reverseString();
//        checkParenthies();
//        postflixExpression();
//        generateBinaryNumber();
        reverse();
    }
    public static  void reverse(){
        Stack<Integer> s=new Stack();
        Stack<Integer> s2=new Stack<>();
        int k=3;
        Queue<Integer> q=new LinkedList();
        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);
        q.add(50);

        for(int i=0;i<k;i++){
            s.push((Integer) ((LinkedList<?>) q).pop());
        }
        for(int i=0;i<k;i++){
            s2.push(s.pop());
        }

        for(int i=0;i<k;i++){
            ((LinkedList<Integer>) q).addFirst(s2.pop());
        }
        System.out.println(q);
    }

    public static void generateBinaryNumber() {
        int n = 5;
        Queue<String> queue = new LinkedList<>();
        queue.add("1");

        for (int i = 0; i < n; i++) {
            String current = queue.poll();
            System.out.print(current + " ");

            queue.add(current + "0");
            queue.add(current + "1");
        }
    }

    public static void postflixExpression() {
        String str = "23*54*+9-";
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == '+' || str.charAt(i) == '-') {
                char op = str.charAt(i);
                Integer i1 = stack.pop();
                Integer i2 = stack.pop();

                if (str.charAt(i) == '*') {
                    Integer sum = i2 * i1;
                    stack.push(sum);
                } else if (str.charAt(i) == '+') {
                    Integer sum = i2 + i1;
                    stack.push(sum);
                } else if (str.charAt(i) == '/') {
                    Integer sum = i2 / i1;
                    stack.push(sum);
                } else if (str.charAt(i) == '-') {
                    Integer sum = i2 - i1;
                    stack.push(sum);
                }


            } else {
                stack.push((int) str.charAt(i) - 48);
            }
        }

        System.out.println("Ans=" + stack.peek());
    }

    public static void reverseString() {
        Stack s = new Stack();
        s.add('H');
        s.add("E");
        s.add("L");
        s.add("L");
        s.add("O");
        String reverse = "";
        while (!s.isEmpty()) {
            reverse += s.peek();
            s.pop();
        }
        System.out.println(reverse);
    }

    public static void checkParenthies() {
        Stack<Character> stack = new Stack<>();
        String parentheses = "{}{}{({})}";

        for (int i = 0; i < parentheses.length(); i++) {
            char ch = parentheses.charAt(i);

            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty()) {
                    System.out.println("Invalid");
                    return;
                }
                char top = stack.peek();
                if ((ch == '}' && top == '{') ||
                        (ch == ']' && top == '[') ||
                        (ch == ')' && top == '(')) {
                    stack.pop();
                } else {
                    System.out.println("Invalid");
                    return;
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

    }
}
