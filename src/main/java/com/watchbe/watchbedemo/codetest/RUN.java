package com.watchbe.watchbedemo.codetest;

public class RUN {
    public static void main(String[] args) {
        fatherB fatherB = new fatherB();
        father father = new childA(fatherB);
        father.test();
        father.test2();
    }
}
