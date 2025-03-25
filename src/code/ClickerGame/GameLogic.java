package ClickerGame;

/**
 * Core game state logic.
 *
 * @author Szymon Zemojtel
 * @version 1
 */
public final class GameLogic
{
    private static final int BASE_SCORE         = 0;
    private static final int BASE_AUTO_CLICKERS = 0;
    private static final int BASE_POWER         = 1;
    private static final int POWER_INCREMENT    = 1;

    private static final int POWER_INCREMENT_COST = 1;
    private static final int AUTO_CLICKER_COST    = 10;


    private int curScore;
    private int curClickPower;
    private int curAutoClickers;

    /**
     * Constructs the initial game state.
     */
    public GameLogic()
    {
        curScore        = BASE_SCORE;
        curClickPower   = BASE_POWER;
        curAutoClickers = BASE_AUTO_CLICKERS;
    }

    /**
     * Increments score by click power.
     */
    public void click()
    {
        curScore += curClickPower;
    }

    /**
     * Increments the score by product of click power and multiplier.
     *
     * @param multiplier amount to multiply click power by before adding to current score.
     */
    public void click(final int multiplier)
    {
        final int scoreToAdd;
        scoreToAdd = curClickPower * multiplier;

        curScore += scoreToAdd;
    }

    /**
     * Increases click power by constant amount.
     */
    public void upgradeClickPower()
    {
        if(curScore >= POWER_INCREMENT_COST)
        {
            curScore -= POWER_INCREMENT_COST;
            curClickPower += POWER_INCREMENT;

        }
    }

    /**
     * Purchases an auto-clicker if enough score.
     */
    public void addAutoClicker()
    {
        if(curScore >= AUTO_CLICKER_COST)
        {
            curScore -= AUTO_CLICKER_COST;
            curAutoClickers++;
        }
    }

    /**
     * Performs auto-clicks based on how many auto-clickers exist.
     */
    public void autoClick()
    {
        click(curAutoClickers);
    }

    /**
     * Getter for the current score.
     *
     * @return current score
     */
    public int getCurScore()
    {
        return curScore;
    }

    /**
     * Getter for the current click power.
     *
     * @return current click power
     */
    public int getCurClickPower()
    {
        return curClickPower;
    }

    /**
     * Getter for the number of active auto-clickers.
     *
     * @return current number of auto clickers
     */
    public int getCurAutoClickers()
    {
        return curAutoClickers;
    }

    /**
     * Getter for the upgrade power increment amount.
     *
     * @return upgrade power increment amount.
     */
    static int getPowerIncrement()
    {
        return POWER_INCREMENT;
    }

    /**
     * Getter for the upgrade power increment cost.
     *
     * @return upgrade power increment cost.
     */
    static int getPowerIncrementCost()
    {
        return POWER_INCREMENT_COST;
    }

    /**
     * Getter for the auto clicker cost.
     *
     * @return auto clicker cost.
     */
    static int getAutoClickerCost()
    {
        return AUTO_CLICKER_COST;
    }

}
