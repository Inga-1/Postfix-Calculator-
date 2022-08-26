import java.util.Stack;
import java.io.*;

class PostFixCalc
{
    public static void main(String args[]) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        PostFixCalc calc = new PostFixCalc();
        try {
            while ((line = reader.readLine()) != null) 
            {
                if(line.isBlank())
                {
                    continue;
                }
                System.out.println(calc.evalPostfix(line));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static boolean isOperator(String op){
        if (op.charAt(0) == '+' || op.charAt(0) == '-' || op.charAt(0) == '/' || op.charAt(0) == '*'){
            return true;
        }
        return false;
    }

    public static boolean isNumber(String number){
        try{
            Integer.parseInt(number);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

	public String evalPostfix(String exp)
	{
        Stack<Integer> stack = new Stack<>();
        String[] elems = exp.trim().split("\\s+");
        for(String elem : elems){
            if (isNumber(elem)){
                stack.push(Integer.parseInt(elem));
            }
            else if (elem.length() == 1 && isOperator(elem)){
                if(stack.size() < 2){
                    return "Malformed expression";
                }
                int val1 = stack.pop();
                int val2 = stack.pop();
                
                switch(elem.charAt(0)){
                    case '+':
                        stack.push(val2+val1);
                        break;
                        
                    case '-':
                        stack.push(val2- val1);
                        break;
                        
                    case '/':
                        if (val1== 0){
                            return "Zero division";
                        }
                        stack.push(val2/val1);
                        break;
                        
                    case '*':
                        stack.push(val2*val1);
                        break;
                    }
                }
                else{
                    return "Malformed expression";
                }
            }
        
        if (stack.size() != 1){
            return "Malformed expression";
        }
        return stack.pop().toString();
	}
}