import java.util.Scanner;

public class Menu {
    // Метод для вызова меню
    public static void showStartMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isFirstRun = true;

        while (true) {
            // Приветствие и меню
            if (isFirstRun) {
                showGreeting();
                isFirstRun = false;
            }
            showMenu();

            // Обработка ввода
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 3.");
                continue;
            }

            // Обработка выбора
            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    showHelp();
                    break;
                case 3:
                    System.out.println("Выход из игры. До свидания!");
                    scanner.close();
                    return; // Завершение программы
                default:
                    System.out.println("Ошибка: выберите число от 1 до 3.");
            }
        }
    }


    // Метод для приветствия
    private static void showGreeting() {
        System.out.println("==============================");
        System.out.println(" Добро пожаловать в Лабиринт! ");
        System.out.println("==============================");
    }

    // Метод для отображения меню
    private static void showMenu() {
    System.out.println(""); 
    System.out.println("Выберите действие:");
    System.out.println("1. Начать игру");
    System.out.println("2. Помощь");
    System.out.println("3. Выход");
    }

    // Метод для очистки консоли
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Логика запуска игры
    private static void startGame() {
        clearConsole();
        System.out.println("Игра началась!");
        Game.startGame();
    }

    // Вывод помощи
    private static void showHelp() {
        clearConsole();
        System.out.println("Вы выбрали: Помощь");
        System.out.println("Используйте клавиши W, A, S, D для перемещения по лабиринту.");
        System.out.println("'#' - стена");
        System.out.println("' ' - пустая клетка");
        System.out.println("'P' - позиция игрока");
        System.out.println("'E' - выход");
        System.out.println("У игрока (Р) есть только три жизни");
        System.out.println("При столкновении со стеной отнимается одна жизнь");
        System.out.println("После прохождения первого уровня начнётся второй и т д");
        System.out.println("Удачной игры!!!");
    }
}
