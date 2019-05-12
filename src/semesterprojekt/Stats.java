package semesterprojekt;

/**
 *
 * @author FK
 */
public class Stats
{
    private int lives = 3;
    private int weapon = 0;
    private int level = 1;
    private int score = 0;

    static Stats stats = new Stats();

    public void setLives(int value)
    {
        lives = value;
    }

    public void addScore(int score)
    {
        this.score += score;
    }

    public int getScore()
    {
        return score;
    }

    public void resetLives()
    {
        setLives(3);
    }

    public void loseLife()
    {
        lives--;
    }

    public int getLives()
    {
        return lives;
    }

    public int getLevel()
    {
        return level;
    }

    public void nextLevel()
    {
        level++;
    }
}
