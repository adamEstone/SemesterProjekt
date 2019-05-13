package semesterprojekt;

/**
 *
 * @author FK
 */
public class Stats
{
    // initial values for the player
    private final int startLevel = 1;
    private final int startLives = 3;
    private final int startWeapon = 0;
    private final int startScore = 0;
    
    private int lives = startLives;
    private int weapon = startWeapon;
    private int level = startLevel;
    private int score = startScore;

    Stats(){
        resetLevel();
        resetLives();
        resetScore();
        resetWeapon();
    }

    private void setLives(int value)
    {
        lives = value;
    }

    public void addScore(int score)
    {
        this.score += score;
    }
    
    public void resetScore(){
        score = startScore;
    }
    
    public void resetLevel(){
        level = startLevel;
    }
    
    //TODO burde bruge en enum af en slags over våben typer
    public void resetWeapon(){
        weapon = startWeapon;
    }

    public int getScore()
    {
        return score;
    }

    public void resetLives()
    {
        setLives(startLives);
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
