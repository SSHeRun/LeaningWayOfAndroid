package com.example.elegant_kp.calculator;

import java.util.Stack;

/**
 * Created by elegant_kp on 2018/5/18.
 */

public class Calculate {
    private MyStack opStack = new MyStack();//操作符栈
    private MyStack numStack = new MyStack();//操作数栈

    public Calculate() { }

    public double getResult(String exp) {
        exp = removeStrSpace(exp);  //防止有空格输入
        // 如果算术表达式尾部没有‘=’号，则在尾部添加‘=’，表示结束符
        if (exp.length() > 1 && !"=".equals(exp.charAt(exp.length() - 1) + "")) {
            exp += "=";
        }
        // 检查表达式是否合法
        if (!isStandard(exp)) {
            System.err.println("错误：算术表达式有误！");
            return 0;
        }
        // 用于缓存数字，因为数字可能是多位的
        StringBuffer temp = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i); // 获取一个字符
            if (isNumber(ch)) { // 若当前字符是数字
                temp.append(ch); // 加入到数字缓存中
            } else { // 非数字的情况
                String tempStr = temp.toString(); // 将数字缓存转为字符串
                if (!tempStr.isEmpty()) {
                    double num = Double.parseDouble(tempStr); // 将数字字符串转为double型数
                    numStack.myPush(num); // 将数字压栈
                    temp = new StringBuffer(); // 重置数字缓存
                }
                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!comparePri(ch) && !opStack.isEmpty()) {
                    double b = (double) numStack.myPop(); // 出栈，取出数字，后进先出
                    double a = (double) numStack.myPop();
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    switch ((char) opStack.myPop()) {
                         case '+':
                             numStack.myPush(a + b);
                             break;
                         case '-':
                             numStack.myPush(a - b);
                             break;
                         case '*':
                             numStack.myPush(a * b);
                             break;
                         case '/':
                             numStack.myPush(a / b);
                             break;
                         default:
                             break;
                    }
                } // while循环结束

                if (ch != '=') {
                    opStack.myPush(new Character(ch)); // 符号入栈
                    if (ch == ')') { // 去括号
                        opStack.myPop();//（1+2）：  ）
                        opStack.myPop();//:+
                    }
                }

            }   //else
        } // for循环结束

        return (double) numStack.myPop(); // 返回计算结果
    }
     /*去除表达式中的所有空格*/
    public String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    public boolean isStandard(String str) {
        if (str.isEmpty())
            return false;
        MyStack stack = new MyStack();

//        boolean b = false;//用来标记等号是否存在多个

        for (int i = 0; i < str.length(); i++) {
           char n = str.charAt(i);  //得到i位置字符
            char next = str.charAt(i + 1);
             // 判断字符是否合法
           if (!(isNumber(n) || "(".equals(n + "") || ")".equals(n + "")
                   || "+".equals(n + "") || "-".equals(n + "")
                   || "*".equals(n + "") || "/".equals(n + ""))) {
                         return false;
           }
           // 将左括号压栈，用来给后面的右括号进行匹配
           if ("(".equals(n + "")) {
               stack.myPush(n);
           }
           if (")".equals(n + "")) { // 匹配括号
               if (stack.isEmpty() || !"(".equals((char) stack.myPop() + "")) // 括号是否匹配,多右括号
                   return false;
               }
             }
             // 可能会有缺少右括号的情况
           if (!stack.isEmpty())
                   return false;
      // 检查'='号是否不在末尾
//         if (!("=".equals(str.charAt(str.length() - 1) + "")))
//             return false;
        return true;
    }

    public boolean isNumber(char num) {
        if (num >= '0' && num <= '9' || num == '.') {
            return true;
        }
        return false;
    }


    public boolean comparePri(char symbol) {
               if (opStack.isEmpty()) { // 空栈返回ture
                   return true;
               }
               // 符号优先级说明（从高到低）:
               // 第1级: (
               // 第2级: * /
               // 第3级: + -
                // 第4级: )

                char top = (char) opStack.getTop(); // 查看堆栈顶部的对象，注意不是出栈
                if (top == '(') {
                        return true;
                    }
                // 比较优先级
                switch (symbol) {
                     case '(': // 优先级最高
                        return true;
                     case '*': {
                         if (top == '+' || top == '-') // 优先级比+和-高
                             return true;
                         else
                             return false;
                       }
                     case '/': {
                         if (top == '+' || top == '-') // 优先级比+和-高
                             return true;
                         else
                             return false;
                         }
                     case '+':
                             return false;
                     case '-':
                             return false;
                     case ')': // 优先级最低
                             return false;
                     case '=': // 结束符
                             return false;
                     default:
                             break;
                }
                return true;
             }

}
