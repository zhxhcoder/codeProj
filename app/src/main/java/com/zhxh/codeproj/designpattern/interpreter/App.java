package com.zhxh.codeproj.designpattern.interpreter;

import com.zhxh.codeproj.Log;
import com.zhxh.codeproj.designpattern.interpreter.expressionimpl.AndExpression;
import com.zhxh.codeproj.designpattern.interpreter.expressionimpl.OrExpression;
import com.zhxh.codeproj.designpattern.interpreter.expressionimpl.TerminalExpression;

/*
 * 解释器模式（Interpreter Pattern）提供了评估语言的语法或表达式的方式，它属于行为型模式。
 * 这种模式实现了一个表达式接口，该接口解释一个特定的上下文。这种模式被用在 SQL 解析、符号处理引擎等。
 * <p>
 * 主要解决：对于一些固定文法构建一个解释句子的解释器。
 */
public class App {
    public static void main(String[] args) {
        // 3. InterpreterPatternDemo 使用 Expression 类来创建规则，并解析它们
        Expression maleExpression = getMaleExpression();
        // zhxh is male: true
        Log.e("---", "zhxh is male: " + maleExpression.interpreter("zhxh"));

        Expression womanExpression = getMarriedWomanExpression();
        // Julie is married woman: true
        Log.e("---", "Julie is married woman: " + womanExpression.interpreter("Married Julie"));
    }


    /**
     * 规则：zhxh 和 michael 是男性
     */
    public static Expression getMaleExpression() {
        TerminalExpression zhxh = new TerminalExpression("zhxh");
        TerminalExpression michael = new TerminalExpression("michael");
        return new OrExpression(zhxh, michael);
    }

    /**
     * 规则：Julie 是一个已婚的女性
     */
    public static Expression getMarriedWomanExpression() {
        TerminalExpression julie = new TerminalExpression("Julie");
        TerminalExpression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }
}
