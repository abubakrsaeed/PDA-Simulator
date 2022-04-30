public class Stack {

    private java.util.Stack<String> stack;

    public Stack(){
        stack = new java.util.Stack<>();
    }

    public String push(String s){
        return stack.push(s);
    }

    public String pop(){
        return stack.pop();
    }

    public String peek(){
        return stack.peek();
    }
}
