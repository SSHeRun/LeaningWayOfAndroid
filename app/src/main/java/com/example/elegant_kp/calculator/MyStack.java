package com.example.elegant_kp.calculator;

import java.util.LinkedList;

/**
 * Created by elegant_kp on 2018/5/18
 * 用Linklist模拟栈.
 */

public class MyStack {
    private LinkedList list;

    public MyStack() {
        this.list  = new LinkedList();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Object getTop() {
        //模拟取栈顶元素
        return list.getFirst();
    }

    public void myPush(Object object) {
        list.addFirst(object);
    }

    public Object myPop() {
        Object obj = list.getFirst();
        list.removeFirst();
        return obj;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
