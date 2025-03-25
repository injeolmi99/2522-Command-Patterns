package ClickerGame.Commands;

import ClickerGame.Command;
import ClickerGame.GameLogic;

/**
 * Command to add an auto-clicker.
 *
 * @author Szymon Zemojtel
 * @version 1
 */
public class CommandAutoClicker implements Command
{
    private final GameLogic logic;

    /**
     * Constructs an auto-clicker command.
     *
     * @param logic the game logic
     */
    public CommandAutoClicker(final GameLogic logic)
    {
        this.logic = logic;
    }

    /**
     * Executes auto-clicker purchase logic.
     */
    public void execute()
    {
        logic.addAutoClicker();
    }
}
