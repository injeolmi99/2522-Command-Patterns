package ClickerGame.Commands;

import ClickerGame.Command;
import ClickerGame.GameLogic;

/**
 * Command to upgrade click power.
 * Applies to both manual click (CommandClick) and auto-clicker (CommandAutoClicker).
 *
 * @author Szymon Zemojtel
 * @version 1
 */
public class CommandUpgrade implements Command
{
    private final GameLogic logic;

    /**
     * Constructs an upgrade command.
     *
     * @param logic the game logic
     */
    public CommandUpgrade(final GameLogic logic)
    {
        this.logic = logic;
    }

    /**
     * Executes the upgrade logic.
     */
    public void execute()
    {
        logic.upgradeClickPower();
    }
}
