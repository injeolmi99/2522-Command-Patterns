package ClickerGame;

/**
 * Invoker that triggers commands.
 *
 * @author Szymon Zemojtel
 * @version 1
 */
final class ButtonInvoker
{
    private Command command;

    /*
     * Sets the command to execute.
     *
     * @param command the command to assign
     */
    void setCommand(final Command command)
    {
        this.command = command;
    }

    /*
     * Executes the assigned command if not null.
     */
    void invoke()
    {
        if(command != null)
        {
            command.execute();
        }
    }
}
