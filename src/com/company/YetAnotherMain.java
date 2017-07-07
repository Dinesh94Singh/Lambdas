package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by saibaba on 7/7/17.
 */
public class YetAnotherMain {
    public static void main(String[] args) {
        Employee Dinesh = new Employee("Dinesh Singh", 23);
        Employee Divya = new Employee("Divya Chekuri", 25);
        Employee Sowmya = new Employee("Sowmya Godala", 18);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Dinesh);
        employeeList.add(Divya);
        employeeList.add(Sowmya);

        // This is better than enhanced for loop and for loop
        /*
        forEach consumes and perform the action and returns nothing and
        again take another consumer object

        we have this at java.util.functional
         */
        employeeList.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

        System.out.println("Employees 20 and Older");
        // Custom
        employeeList.forEach(employee -> {
            if (employee.getAge() > 20) {
                System.out.println(employee.getName());
            }
        });

        System.out.println("Employer 30 and younger");
        employeeList.forEach(employee -> {
            if (employee.getAge() <= 30) {
                System.out.println(employee.getName());
            }
        });
//     But here, we are wasting two lambda expressions and writing repetitive code, we can be
//     more concise, if we place a boolean

        printEmployeesByAge(employeeList, "Employees over 20", employee -> employee.getAge() > 20);
        printEmployeesByAge(employeeList, "Employees less thar 30", employee -> employee.getAge() <= 30);
        printEmployeesByAge(employeeList, "Employees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return  (employee.getAge() <= 25);
            }
        });

        IntPredicate intp = i-> i>15;
        IntPredicate intp2 = i-> i<100;
        System.out.println(intp.test(20));
        System.out.println(intp2.and(intp).test(25));

        Random rand = new Random();
        Supplier<Integer> randomSupplier = ()->rand.nextInt(1000);
        for(int i=0;i<10;i++){
            System.out.println(randomSupplier.get());
        }
    }

    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText,
                                            Predicate<Employee> ageCondition) {
        System.out.println("Employees " + ageText);
        for (Employee e : employees) {
            if (ageCondition.test(e)) {
                System.out.println(e.getName());
            }
        }
    }
}









