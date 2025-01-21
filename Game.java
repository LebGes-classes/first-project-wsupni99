import java.util.Scanner;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_LIVES = 3; // Максимальное количество жизней

    public static void startGame() {
        // Создаём игрока с начальной позицией и жизнями
        Player player = new Player(1, 1, MAX_LIVES);

        // Переход между уровнями
        transitionBetweenLevels(player);
    }

    private static boolean playLevel(char[][] maze, Player player) {
        while (true) {
            clearConsole();
            System.out.println("Осталось жизней: " + player.getLives());
            Maze.printMaze(maze); // Печатаем лабиринт

            System.out.println("Введите направление (W/A/S/D): ");
            String input = scanner.nextLine().trim(); // Читаем ввод

            // Проверка пустой строки
            if (input.isEmpty()) {
                System.out.println("Ошибка: введите направление или команду");
                continue;
            }

            // Обработка выхода
            if (input.equals("/")) {
                clearConsole();
                System.out.println("Вы вышли из текущей игры. До свидания!");
                return false; // Завершаем текущую игру
            }
            if (input.equals("/quit")) {
                clearConsole();
                System.out.println("Программа завершена. До свидания!");
                System.exit(0); // Немедленно завершаем программу
            }

            // Обработка направления
            int[] newPosition = moveTo(input.toUpperCase().charAt(0), player); // Получаем новые координаты
            int newX = newPosition[0];
            int newY = newPosition[1];

            if (maze[newX][newY] == '#') {
                System.out.println("Вы врезались в стену!");
                player.loseLife(); // Уменьшаем жизни
                if (!player.isAlive()) {
                    clearConsole();
                    System.out.println("=======================================");
                    System.out.println(" У вас кончились жизни. Игра окончена");
                    System.out.println("=======================================");
                    return false; // Завершаем игру
                }
            } else if (maze[newX][newY] == 'E') {
                clearConsole();
                System.out.println("Вы нашли выход! Уровень завершён!");
                return true; // Завершаем уровень
            } else {
                // Обновление позиции игрока
                maze[player.getX()][player.getY()] = ' '; // Очищаем старую позицию
                player.setPosition(newX, newY); // Устанавливаем новую позицию
                maze[player.getX()][player.getY()] = 'P'; // Обновляем лабиринт
            }
        }
    }

    // Метод для очистки консоли
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Метод для обработки направления
    public static int[] moveTo(char move, Player player) {
        int newX = player.getX();
        int newY = player.getY();
        switch (move) {
            case 'W': newX--; break;
            case 'A': newY--; break;
            case 'S': newX++; break;
            case 'D': newY++; break;
            default:
                System.out.println("Неверное направление. Используйте W/A/S/D.");
                break;
        }
        return new int[] { newX, newY }; // Возвращаем новые координаты
    }

    // Метод для перемещения между уровнями
    public static void transitionBetweenLevels(Player player) {
        // Уровни лабиринта
        char[][][] levels = {
            Maze.generateLevel1(),
            Maze.generateLevel2(),
            Maze.generateLevel3()
        };

        // Перемещение
        for (int level = 0; level < levels.length; level++) {

            if (!playLevel(levels[level], player)) {
                System.out.println("Игра завершена. Попробуйте снова!");
                return;
            }
            // Сбрасываем позицию игрока на начало уровня
            player.setPosition(1, 1);
        }
        System.out.println("====================================");
        System.out.println(" Поздравляем! Вы прошли все уровни!");
        System.out.println("====================================");
        System.out.println("Хотите сыграть ещё?");

    }
}
