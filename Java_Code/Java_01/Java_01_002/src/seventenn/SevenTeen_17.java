package seventenn;



public class SevenTeen_17 {
    public static void main(String[] args) {
//        Stack stack = new Stack();
//        stack.push(10);
//        stack.push(20);
//        System.out.println(stack.peek());
//        System.out.println(stack);
//        System.out.println(stack);
//        stack.add(0, 12);
//        System.out.println(stack);
//        System.out.println(stack.search(10));
//        System.out.println(stack.isEmpty());

        Queue q=new Queue();
        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);
        q.add(50);
        System.out.println(q);
        q.poll();
        System.out.println(q);
        q.remove();
        System.out.println(q);
        System.out.println(q.contain(50));
        System.out.println(q.size());
        System.out.println(q.peek());
        q.clear();
        System.out.println(q);
    }
}
