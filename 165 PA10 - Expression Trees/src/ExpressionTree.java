import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.MalformedInputException;
import java.util.*;


/**
 *
 */
public class ExpressionTree extends ZTree {

    public Queue<String> parse(String expression) {
        Queue<String> infix = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "(?<=[-+*()%/])|(?=[-+*()%/])", true);
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if(!token.trim().isEmpty()) {
                infix.add(token.trim());
            }
        }
        return infix;
    }

    public List<String> convert(Queue<String> infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> operators = new ArrayDeque<>(); // used as a stack
        while (!infix.isEmpty()) {
            String token = infix.poll();
            if (isOperator(token)) {
                while (!operators.isEmpty() && order(token) <= order(operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    postfix.add(operators.pop());
                }
                operators.pop();
            } else {
                postfix.add(token);
            }
        }
        while (!operators.isEmpty()) {
            postfix.add(operators.pop());
        }
        return postfix;
    }
    
    private int order(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            default:
                return -1;
        }
    }

    @Override
    public void build(List<String> postfix) {
        Deque<Node> stack = new ArrayDeque<>();
        for (String token : postfix) {
            Node node = new Node(token);
            if (isOperator(token)) {
                Node right = stack.pop();
                Node left = stack.pop();
                node.right = right;
                node.left = left;
            }
            stack.push(node);
        }
        root = stack.pop();
    }

    @Override
    public String prefix() {
        return prefixRecursive(root);
    }

    /**
     * Concatenates the tokens in the expression tree returned from the
     * {@link #build(List)} method in <b>prefix</b> order.
     * <p>
     * Accumulate the operator first, then the string from the left
     * and right subtrees. Add an extra space after each token.
     * <p>
     * Our implementation of this method is ~2-6 lines of code.
     * @param current the root node
     * @return the tokens in prefix order
     */
    public String prefixRecursive(Node current) {
        if (current == null) {return "";}
        String left = prefixRecursive(current.left);
        String right = prefixRecursive(current.right);
        return current.token + " " + left + right;
    }

    @Override
    public String infix() {
        return infixRecursive(root);
    }

    /**
     * Concatenates the tokens in the expression tree returned from the
     * {@link #build(List)} method in <b>infix</b> order.
     * <ol>
     *     <li> Accumulate the string from the left subtree
     *     <li> Add the operator
     *     <li> Accumulate the string from the right subtree
     * </ol>
     * This method should add parentheses to maintain the correct evaluation order,
     * add a left parentheses before traversing the left subtree, and a right
     * parentheses after traversing the right subtree.
     * Do not add any space to the expression string.
     * <p>
     * Our implementation of this method is ~2-6 lines of code.
     * @param current
     * @return the tokens in infix order
     */
    public String infixRecursive(Node current) {
        if (current == null) {return "";}
        String left = infixRecursive(current.left);
        String right = infixRecursive(current.right);
        if (isOperator(current.token)) {
            return "(" + left + current.token + right + ")";
        } else {
            return current.token;
        }
    }

    @Override
    public String postfix() {
        return postfixRecursive(root);
    }

    /**
     * Concatenates the tokens in the expression tree returned from the
     * {@link #build(List)} method in <b>postfix</b> order.
     * First accumulate the string from the left and right subtrees, then add the
     * operator. Add an extra space after each token.
     * <p>
     * Our implementation of this method is ~2-6 lines of code.
     * @param current reference to the current node (starts with root)
     * @return a String representing the tree in postfix order
     */
    public String postfixRecursive(Node current) {
        if (current == null) {return "";}
        String left = postfixRecursive(current.left);
        String right = postfixRecursive(current.right);
        return left + right + current.token + " ";
    }

    public int evaluate() {
        return evaluateRecursive(root);
    }

    /**
     * Traverses the expression tree and produces the correct answer, which should be an integer.
     * To evaluate, call the recursive version of the method to get the result from the left
     * subtree, do the same for the right subtree, then combine these two results using the
     * operator. A case statement based on the operator is needed to perform the mathematical operation.
     * <p>
     * Our implementation uses one helper method (~7 lines of code) and is, itself, ~2 lines of code.
     * @param current
     * @return
     */
    public int evaluateRecursive(Node current) {
        if (current == null) {return 0;}
        if (isInteger(current.token)) {return Integer.parseInt(current.token);}
        int left = evaluateRecursive(current.left);
        int right = evaluateRecursive(current.right);
        switch (current.token) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            case "%":
                return left % right;
            default:
                return 0;
        }
    }
    
        // Test code for 
    public static void main(String[] args) {

        // Instantiate student code
        ExpressionTree eTree = new ExpressionTree();

        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();

        System.out.println("Original Expression: " + expression);

        // Verify parse
        Queue<String> infix = eTree.parse(expression);
        System.out.println("Infix Tokens: " + infix.toString());

        // Verify convert
        List<String> postfix = eTree.convert(infix);
        System.out.println("Postfix Tokens: " + postfix.toString());

        // Verify build
        eTree.build(postfix);
        System.out.println("Build: complete");

        // Verify prefix
        System.out.println("Prefix: " + eTree.prefix());

        // Verify infix
        System.out.println("Infix: " + eTree.infix());

        // Verify postfix
        System.out.println("Postfix: " + eTree.postfix());

        // Verify evaluate
        System.out.println("Evaluate: " + eTree.evaluate());

        // Verify display
        System.out.println("Display: complete");
    }

   

}
