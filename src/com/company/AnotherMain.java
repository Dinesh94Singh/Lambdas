package com.company;

import java.util.Random;

public class AnotherMain {
    // Nested Block Lambda's
    public static void main(String[] args) {
        AnotherClass ac = new AnotherClass();
        String concatenated_String = ac.doSomething();
        System.out.println(concatenated_String);
        String concatenated_String2 = ac.doSomething2();
        System.out.println(concatenated_String2);
        String concatenated_String3 = ac.doSomething3();
        System.out.println(concatenated_String3);

        int a[] = new int[10];
        Random rand = new Random();
        for(int i=0;i<10;i++){
            a[0] = rand.nextInt();
        }

        for(int x:a){
            new Thread(()-> System.out.println(x)).start();
        }

    }
}
class AnotherClass{

    String doSomething3(){
        int i=0;
        // The below function actually behaves like a nested block of code not inner class
        UpperConcat uc = (s1,s2) -> {
            System.out.println("The Lambda Expression class is "+ getClass().getSimpleName());
            // It is not error, because, it is not inner class, it is just a nested block
            System.out.println("i in the Lambda Expression is "+ i);
            return s1.toUpperCase().concat(s2.toUpperCase());
        };
        return Main.doStringStuff(uc,"String1","String2");
    }
    String doSomething2(){
        // Code Block
        int i=0;
        {
            UpperConcat uc = new UpperConcat() {
                @Override
                public String upperAndConcat(String s1, String s2) {
                    // This is error because, it is like error class
                    // System.out.println("i within anonymous class "+ i); //because, I need to be final
                    return s1.toUpperCase().concat(s2.toUpperCase());
                }
            };
            i++;
            System.out.println("i = "+i);
            System.out.println("The Another Class name is"+getClass().getSimpleName());
            return Main.doStringStuff(uc,"String1","String2");

        }

    }
    String doSomething(){
        // usually this would be a anonymous class
        UpperConcat uc = (s1,s2) -> {
            System.out.println("The Lambda Expressions is "+getClass().getSimpleName());
            return s1.toUpperCase().concat(s2.toUpperCase());
        };
        System.out.println("Another Class is "+getClass().getSimpleName());
        return Main.doStringStuff(uc,"String1","String2");

        /*System.out.println("The AnotherClass class's name is "+getClass().getSimpleName());
        return Main.doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The Anonymous Class name is "+getClass().getSimpleName());
                return s1.toUpperCase().concat(s2.toUpperCase());
            }
        },"String1","String2");*/
    }

    void printValue(){
        int number = 25;
        Runnable r = () -> {
            try{
                Thread.sleep(5000);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
            System.out.println("value"+number);
        };
        new Thread(r).start();
    }
}
