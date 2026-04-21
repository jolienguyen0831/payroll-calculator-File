package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class Payroll {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Employee[] employees = new Employee[20];
        int i = 0;

        try {
            System.out.print("Please enter a name of file you want to read: ");
            String nameFileToRead = input.nextLine();
            FileReader fileReader = new FileReader(nameFileToRead);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            String line = bufferedReader.readLine();
            while (line != null) {
                createEmployeesArray(line, employees, i);
                line = bufferedReader.readLine();
//                displayEmployee(employees[i]);
                i++;
            }
            System.out.print("Please enter a name of file you want to create:");
            String nameFileToWrite = input.nextLine();
            FileWriter writer = new FileWriter(nameFileToWrite);
            BufferedWriter bufWriter = new BufferedWriter(writer);
            writer.write("ID | Name | GrossPay\n\n");
            String text;
            for (int j = 0; j < i; j++) {
                text = String.format("%s | %s | $%s%n",
                        employees[j].getEmployeeId(),
                        employees[j].getName(),
                        employees[j].getGrossPay());
                bufWriter.write(text);
                bufWriter.newLine();
            }

            bufferedReader.close();
            bufWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
