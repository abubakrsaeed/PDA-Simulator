import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Stack;

public class PDA {

    public static void printArrayList(ArrayList<String> l){

        for(String s : l)
        {
            System.out.print(s + " ");
        }

        System.out.println();
    }

    public static class Transition {

        public String startState;
        public String endState;
        public String alphabet;
        public String ToPush;
        public String ToPop;

        public void print(){
            System.out.println("--------------------------");
            System.out.println("Start state: " + startState);
            System.out.println("transition var: " + alphabet);
            System.out.println("to pop: " + ToPop);
            System.out.println("to push: " + ToPush);
            System.out.println("end State: " + endState);
            System.out.println("--------------------------");
        }
    }

    public static void main(String ar[]) throws IOException {
        StringTokenizer st;
        Scanner sc = new Scanner(new File("Input.txt"));

        int noOfAlphabets = sc.nextInt();
        System.out.println("noOfAlphabets: " + noOfAlphabets);
        int noOfStackVars = sc.nextInt();
        System.out.println("noOfStackVars: " + noOfStackVars);
        int noOfGoalStates = sc.nextInt();
        System.out.println("noOfGoalStates: " + noOfGoalStates);
        int noOfStates = sc.nextInt();
        System.out.println("noOfStates: " + noOfStates);

        sc.nextLine();
        ArrayList<String> states = new ArrayList<>();

        st = new StringTokenizer(sc.nextLine());

        while (st.hasMoreTokens()) {
            states.add(st.nextToken());
        }

        printArrayList(states);


        String startState = sc.nextLine();
        System.out.println("start state: " + startState);

        ArrayList<String> goalStates = new ArrayList<>();

        st = new StringTokenizer(sc.nextLine());

        while (st.hasMoreTokens()) {
            goalStates.add(st.nextToken());
        }
        printArrayList(goalStates);

        ArrayList<String> stackAlphabets = new ArrayList<>();

        st = new StringTokenizer(sc.nextLine());

        while (st.hasMoreTokens()) {
            stackAlphabets.add(st.nextToken());
        }
        printArrayList(stackAlphabets);


        String initialStackSymbol = sc.nextLine();
        System.out.println("initial stack symbol: " + initialStackSymbol);


        ArrayList<String> inputAlphabets = new ArrayList<>();

        st = new StringTokenizer(sc.nextLine());

        while (st.hasMoreTokens()) {
            inputAlphabets.add(st.nextToken());
        }

        printArrayList(inputAlphabets);


        int numOfTransitions = sc.nextInt();
        System.out.println("Number of transitions: " + numOfTransitions);

        sc.nextLine();

        ArrayList<Transition> transitions = new ArrayList<>();

        for(int i = 0 ; i < numOfTransitions ; i++)
        {
            String transitionString = sc.nextLine();
            st = new StringTokenizer(transitionString);
            Transition t = new Transition();
            t.startState = st.nextToken();
            t.alphabet = st.nextToken();
            t.ToPop = st.nextToken();
            t.ToPush = st.nextToken();
            t.endState = st.nextToken();
            transitions.add(t);
        }

        for(Transition t : transitions){
            t.print();
        }

        FileOutputStream f = new FileOutputStream("output.txt");
        PrintWriter fw = new PrintWriter(f);

        while(sc.hasNextLine()){
            Stack<String> stack = new Stack();
            String stringToDetect = sc.nextLine();
            System.out.println("String to detect: " + stringToDetect);
            stringToDetect = "E" + stringToDetect + "E";
            int stringToDetectLength = stringToDetect.length();
            String currentStartState = startState;
            System.out.print(currentStartState);
            fw.print(currentStartState);

            for(int i = 0 ; i < stringToDetectLength ; i ++)
            {
                String currentAlphabet = stringToDetect.charAt(i) + "";

                for(Transition t : transitions)
                {
                    if(t.startState.equals(currentStartState) && t.alphabet.equals(currentAlphabet))
                    {
                        if(!t.ToPop.equals("E")){

                            if(stack.isEmpty()){
                                System.out.println();
                                fw.println();
                                System.out.println("rejected");
                                fw.println("rejected");
                                return;
                            }

                            if(!stack.pop().equals(t.ToPop)){
                                System.out.println();
                                fw.println();
                                System.out.println("rejected");
                                fw.println("rejected");
                                return;
                            }
                        }

                        if(!t.ToPush.equals("E")){
                            stack.push(t.ToPush);
                        }

                        System.out.print(t.endState);
                        fw.print(t.endState);

                        currentStartState = t.endState;

                        break;
                    }
                }
            }



            if(goalStates.indexOf(currentStartState) != -1 || stack.isEmpty())
            {
                System.out.println();
                fw.println();
                System.out.println("Accepted");
                fw.println("Accepted");
            }
            else{

                System.out.println();
                fw.println();
                System.out.println("Rejected");
                fw.println("Rejected");
            }

        }

        fw.flush();
        fw.close();

    }
}