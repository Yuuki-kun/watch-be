package com.watchbe.watchbedemo.codetest;

public class childA extends father{
    public childA(fatherB fatherB) {
        super(fatherB);
    }

    @Override
    protected void test2() {
        System.out.println("childA");
    }
}
