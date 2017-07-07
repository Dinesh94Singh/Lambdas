package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        Employee e = (Employee) obj;
        if (this.getName() == e.getName() && this.getAge() == e.getAge())
            return true;
        else
            return false;
    }
}

interface UpperConcat {
    String upperAndConcat(String s1, String s2);
}

public class Main {

    public static void main(String[] args) {
        /*
        Lambda expressions takes 3 arguments, 1 is parameters passed, followed by arrow and end by body
         */

        // Single line Lambda function and doesn't require a ;
        new Thread(() -> System.out.println("Lamba Thread Declaration")).start();

        // multiline Thread
        new Thread(() -> {
            System.out.println("Lambda");
            System.out.println("Thread");
            System.out.println("Declaration");
        }).start();

        Employee Dinesh = new Employee("Dinesh Singh", 23);
        Employee Divya = new Employee("Divya Chekuri", 25);
        Employee Sowmya = new Employee("Sowmya Godala", 18);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Dinesh);
        employeeList.add(Divya);
        employeeList.add(Sowmya);

        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Employee e : employeeList) {
            System.out.println(e.getName() + " " + e.getAge());
        }

        // With Lambda
        Collections.sort(employeeList, (e1, e2) ->
                e1.getName().compareTo(e2.getName()));

        employeeList.forEach((employee -> System.out.println(employee.getName())));

        String sillyString = doStringStuff(new UpperConcat() {
                                               @Override
                                               public String upperAndConcat(String s1, String s2) {
                                                   return s1.toUpperCase().concat(s2.toUpperCase());
                                               }
                                           },
                employeeList.get(0).getName(), employeeList.get(1).getName());

        // With Lambda's (Lambda's to variables)
        UpperConcat uc = (s1, s2) -> s1.toUpperCase().concat(s2.toUpperCase());
        String sillyString2 = doStringStuff(uc, employeeList.get(0).getName(), employeeList.get(1).getName());
        System.out.println(sillyString + " " + sillyString2);

        // Nested Lambda's
        UpperConcat uc2 = (s1, s2) -> s1.toUpperCase().concat(s2.toUpperCase());

        String sillyString3 = doStringStuff(uc2,"String1","String2");
        System.out.println(sillyString3);
    }

    static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}
