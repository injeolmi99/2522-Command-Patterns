package ClickerGame.Commands;

import ClickerGame.Command;
import ClickerGame.GameLogic;

/**
 * Command to handle manual clicks.
 *
 * @author Szymon Zemojtel
 * @version 1
 */
public final class CommandClick implements Command
{
    private final GameLogic logic;

    /**
     * Constructs a click command (manual clicks).
     *
     * @param logic the game logic
     */
    public CommandClick(final GameLogic logic)
    {
        this.logic = logic;
    }

    /**
     * Executes a click action.
     */
    public void execute()
    {
        logic.click();
    }
}
