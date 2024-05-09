package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            System.out.print("Выберите команду: ");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "0":
                    System.out.println("\nЗавершение работы...");
                    return;
                default:
                    System.out.println("\nНеизвестная команда!");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("0 - Выход");
    }

    private static void addNewDish() {
        System.out.print("\nВведите тип блюда: ");
        String dishType = scanner.nextLine();
        System.out.print("Введите название блюда: ");
        String dishName = scanner.nextLine();

        if (!dishName.isEmpty() && !dishType.isEmpty()) {
            dc.addDish(dishType, dishName);
        } else {
            System.out.println("Название типа и блюда не может быть пустым!");
        }
    }

    private static void generateDishCombo() {
        if (dc.getDishes().isEmpty()) {
            System.out.println("\nСначала нужно добавить хотя бы одно блюдо!");
            return;
        }

        System.out.println("\nНачинаем конструировать обед...");

        System.out.print("Введите количество наборов, которые нужно сгенерировать: ");
        int comboCount = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> dishTypeList = new ArrayList<>();

        System.out.println(
                "Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String dishName = scanner.nextLine();
        while (!dishName.isEmpty()) {
            if (dc.getDishes().containsKey(dishName)) {
                dishTypeList.add(dishName);
            } else {
                System.out.println("Данного типа ещё нет в меню!");
            }
            dishName = scanner.nextLine();
        }

        if (dishTypeList.isEmpty()) {
            System.out.println("Не было введено ни одного типа!");
            return;
        }

        ArrayList<ArrayList<String>> comboList = dc.generateCombos(comboCount, dishTypeList);

        for (int i = 0; i < comboList.size(); i++) {
            System.out.println("- Комбо " + (i + 1) + ":");
            for (String dish : comboList.get(i)) {
                System.out.println("  - " + dish);
            }
        }
    }
}
