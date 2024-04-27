import java.util.ArrayList;
import java.util.Stack;
// TODO: 
// String -> infix = done
// infix -> postfix = done
// postfix -> calculate 
// it is shit, and i might fix it in the future 

/*
 * i kept creating new arraylist on each function 
 * i could make the process much slower than it should
 * but it will be more readable 
 * OOP sucks that is what it is 
 */

public class App {
    public static void main(String[] args) throws Exception { 
        String california = "1/1";
        double result = calculate(california);
        System.out.println(result);
    }

    // String -> infix
    static ArrayList<String> parser(String number){
        ArrayList <String> stored = new ArrayList<>();
        String cars = "";
        for(int i = 0; i  < number.length(); i++){
            switch(number.charAt(i)){ 
                // case switch -> if else statement by any day 
                default:    
                    cars = cars + number.charAt(i);
                    break;
                case '*', '/', '+', '-':
                    stored.add(cars);
                    stored.add(""+number.charAt(i));
                    cars = "";
                    break;
            }
        }        
        stored.add(cars);
        return stored;
    }

    static int presedence(String ops){
        switch(ops){
            case "+", "-":
                return 1;
            case "*", "/":
                return 2;
            default:
                return 3;
        }        
    }

    static boolean isOperator(String operator){
        // i can actualy just use switch case 
        // on calculate function but why not 
        switch(operator){
            case "*", "/", "+", "-":
                return true;
        }
        return false;
    }
    static boolean lowerPrecedence(String ops1, String ops2){
        if(presedence(ops1) < presedence(ops2)){
            return true;
        }
        else{
            return false;
        }
    }

    // infix -> prefix 
    static ArrayList<String> postfix(String number){
        ArrayList<String> stored = parser(number);
        ArrayList<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for(String item:stored){
            if(isOperator(item)){
                while(!stack.empty() &&  lowerPrecedence(item, stack.peek())){
                    postfix.add(stack.pop());
                }
                stack.push(item);
            }
            else{
                postfix.add(item);
            }
            
        }
        while(!stack.empty()){
            postfix.add(stack.pop());
        }
        return postfix;
    }
    // fuckit we jumping into two function lol 
    static double calculate(String number){
        ArrayList<String> stored = postfix(number);
        Stack<Double>stack = new Stack<>(); 
        for(String item:stored){
            if(isOperator(item)){
                Double a = stack.pop();
                Double b = stack.pop();
                switch(item){
                    case "+":
                        stack.push(a+b);
                        break;
                    case "-":
                        stack.push(b-a);
                        break;
                    case "/":
                        stack.push(b/a);
                        break;
                    case "*":
                        stack.push(a*b);
                        break;
                }
            }
            else{
                stack.push(Double.parseDouble(item));
            }
        }
        return stack.pop();
    }
}

