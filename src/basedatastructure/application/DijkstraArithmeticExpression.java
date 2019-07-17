package basedatastructure.application;

import basedatastructure.EasyStack;
import basedatastructure.LinkedStack;
import basedatastructure.ResizingArrayStack;

/**
 * Dijkstra arithmetic expression algorithm.
 * @author yehaoyu
 */
public class DijkstraArithmeticExpression {
    private EasyStack<Double> valueStack;
    private EasyStack<String> operatorStack;

    public DijkstraArithmeticExpression() {
        this.valueStack    = new LinkedStack<>();
        this.operatorStack = new ResizingArrayStack<>();
    }

    public double calculateExpression(String expression) {
        String[] split = expression.split(" ");
        for(String str : split) {
            switch (str) {
                case "(":
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    operatorStack.push(str);
                    break;
                case ")":
                    calculate();
                    break;
                default:
                    valueStack.push(toDouble(str));
            }
        }
        return valueStack.pop();
    }

    private void calculate() {
        String operator = operatorStack.pop();
        switch (operator) {
            case "+":
                valueStack.push(valueStack.pop() + valueStack.pop());
                break;
            case "-":
                valueStack.push(valueStack.pop() - valueStack.pop());
                break;
            case "*":
                valueStack.push(valueStack.pop() * valueStack.pop());
                break;
            case "/":
                double denominator = valueStack.pop();
                double numerator   = valueStack.pop();
                valueStack.push(numerator / denominator);
                break;
        }
    }

    private double toDouble(String str) {
        return Double.parseDouble(str);
    }
}
