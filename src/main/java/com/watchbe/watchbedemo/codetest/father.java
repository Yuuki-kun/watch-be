package com.watchbe.watchbedemo.codetest;

public abstract class father {
    protected final fatherB fatherB;

    protected father(com.watchbe.watchbedemo.codetest.fatherB fatherB) {
        this.fatherB = fatherB;
    }
    protected void test(){
        System.out.println("father");
    }
    protected abstract void test2();
}
