public class Player {
    private int lives; // Количество жизней
    private int x;     // Позиция игрока по X
    private int y;     // Позиция игрока по Y

    public Player(int startX, int startY, int lives) {
        this.x = startX;
        this.y = startY;
        this.lives = lives;
    }

    // Получить текущие координаты
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Обновить позицию игрока
    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    // Уменьшить количество жизней
    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    // Проверить, закончились ли жизни
    public boolean isAlive() {
        return lives > 0;
    }

    // Получить оставшиеся жизни
    public int getLives() {
        return lives;
    }
}
