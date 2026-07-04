package org.example.Day1_BasicCheck;

public class BuilderPattern {

    public BuilderPattern step1(){
        System.out.println("Step1");
        return this;
    }

    public BuilderPattern step2(){
        System.out.println("Step2");
        return this;
    }

    public BuilderPattern step3(){
        System.out.println("Step3");
        return this;
    }

    public static void main(String[] args) {
        BuilderPattern bb1 = new BuilderPattern();
        bb1.step1().step2().step3();
        //RestAssured.given().when().then()
    }
}
