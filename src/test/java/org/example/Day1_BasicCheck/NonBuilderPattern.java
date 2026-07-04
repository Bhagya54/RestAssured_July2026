package org.example.Day1_BasicCheck;

public class NonBuilderPattern {

    public void step1(){
        System.out.println("Step1");
    }

    public void step2(){
        System.out.println("Step2");
    }

    public void step3(){
        System.out.println("Step3");
    }

    public static void main(String[] args) {
        NonBuilderPattern bb1 = new NonBuilderPattern();
        bb1.step1();
        bb1.step2();
        bb1.step3();
    }
}
