package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Employee[] employees = new Employee[20];
        int i = 0;
        try {
            FileReader fileReader = new FileReader("employees.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            String line = bufferedReader.readLine();

            while (line != null) {

                createEmployeesArray(line, employees, i);

                line = bufferedReader.readLine();
                i++;
            }

            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("""
                Please enter your information to do your payroll calculator
                1. ID
                2. Name
                Please enter your choice:""");
        byte userChoice = input.nextByte();
        input.nextLine();
        switch (userChoice) {
            case 1 -> checkPrintEmployeeID(i, employees);
            case 2 -> checkPrintEmployeeName(i, employees);
        }
    }

    private static void checkPrintEmployeeID(int i, Employee[] employees) {
        boolean found = false;

        do {
            System.out.print("Please enter your ID:");
            byte userEnterID = input.nextByte();
            for (int j = 0; j < i; j++) {

                if (userEnterID == employees[j].getEmployeeId()) {
                    displayEmployee(employees[j]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("ID not found. Try again!");
            }
        } while (!found);
    }

    private static void checkPrintEmployeeName(int i, Employee[] employees) {
        boolean found = false;

        do {
            System.out.print("Please enter your name:");
            String userEnterName = input.nextLine();
            for (int j = 0; j < i; j++) {

                if (userEnterName.equalsIgnoreCase(employees[j].getName())) {
                    displayEmployee(employees[j]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Name not found. Try again!");
            }
        } while (!found);
    }

    private static void createEmployeesArray(String line, Employee[] employees, int i) {
        String[] tokens = line.split("\\|");
        int iD = Integer.parseInt(tokens[0]);
        String employeeName = tokens[1];
        double hour = Double.parseDouble(tokens[2]);
        double rate = Double.parseDouble(tokens[3]);

        employees[i] = new Employee(iD, employeeName, hour, rate);
    }

    private static void displayEmployee(Employee employees) {
        System.out.printf("ID: %s | Name: %s| Gross Pay: $%s%n",
                employees.getEmployeeId(),
                employees.getName(),
                employees.getGrossPay());
    }

}
