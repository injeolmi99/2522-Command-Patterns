package ClickerGame;

import ClickerGame.Commands.CommandAutoClicker;
import ClickerGame.Commands.CommandClick;
import ClickerGame.Commands.CommandUpgrade;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * GUI entry point for the clicker game.
 *
 * @author Szymon Zemojtel
 * @version 1
 */
public final class Clicker extends Application
{
    private static final int WINDOW_WIDTH     = 300;
    private static final int WINDOW_HEIGHT    = 250;
    private static final int AUTO_INTERVAL_MS = 1000;

    /**
     * Launches the GUI window and sets up game elements.
     *
     * @param primaryStage the main stage
     */
    public void start(final Stage primaryStage)
    {
        final GameLogic     logic;
        final ButtonInvoker invoker;

        final Label scoreLabel;
        final Label powerLabel;
        final Label autoClickerLabel;

        final Button clickButton;
        final Button upgradeButton;
        final Button autoClickerButton;

        final KeyFrame frame;
        final Duration interval;
        final Timeline autoTimeline;

        final VBox  layout;
        final Scene scene;

        logic            = new GameLogic();
        invoker          = new ButtonInvoker();
        scoreLabel       = new Label("Score: 0");
        powerLabel       = new Label("Click Power: 1");
        autoClickerLabel = new Label("Auto Clickers: 0");

        clickButton       = new Button("Click");
        upgradeButton     = new Button("Upgrade (+1 Power)");
        autoClickerButton = new Button("Buy Auto Clicker (-10)");

        // Click button actions
        clickButton.setOnAction(e ->
                                {
                                    final Command commandClick;
                                    commandClick = new CommandClick(logic);

                                    invoker.setCommand(commandClick);
                                    invoker.invoke();
                                    updateStats(scoreLabel,
                                                powerLabel,
                                                autoClickerLabel,
                                                logic);
                                });

        // Upgrade button actions
        upgradeButton.setOnAction(e ->
                                  {
                                      final Command commandUpgrade;
                                      commandUpgrade = new CommandUpgrade(logic);

                                      invoker.setCommand(commandUpgrade);
                                      invoker.invoke();
                                      updateStats(scoreLabel,
                                                  powerLabel,
                                                  autoClickerLabel,
                                                  logic);
                                  });

        // Auto-clicker purchase button actions
        autoClickerButton.setOnAction(e ->
                                      {
                                          final Command commandAutoClicker;
                                          commandAutoClicker = new CommandAutoClicker(logic);

                                          invoker.setCommand(commandAutoClicker);
                                          invoker.invoke();
                                          updateStats(scoreLabel,
                                                      powerLabel,
                                                      autoClickerLabel,
                                                      logic);
                                      });


        interval = Duration.millis(AUTO_INTERVAL_MS);
        frame    = new KeyFrame(interval,
                                e ->
                                {
                                    logic.autoClick();
                                    updateStats(scoreLabel,
                                                powerLabel,
                                                autoClickerLabel,
                                                logic);
                                });

        autoTimeline = new Timeline(frame);
        autoTimeline.setCycleCount(Timeline.INDEFINITE);
        autoTimeline.play();


        layout = new VBox(10,
                          scoreLabel,
                          powerLabel,
                          autoClickerLabel,
                          clickButton,
                          upgradeButton,
                          autoClickerButton);

        scene = new Scene(layout,
                          WINDOW_WIDTH,
                          WINDOW_HEIGHT);


        primaryStage.setTitle("Clicker Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
     * Updates all GUI stats based on game state.
     *
     * @param scoreLabel label showing score
     * @param powerLabel label showing power
     * @param autoLabel  label showing auto-clickers
     * @param logic      current game logic
     */
    private void updateStats(final Label scoreLabel,
                             final Label powerLabel,
                             final Label autoLabel,
                             final GameLogic logic)
    {
        scoreLabel.setText("Score: " + logic.getCurScore());
        powerLabel.setText("Click Power: " + logic.getCurClickPower());
        autoLabel.setText("Auto Clickers: " + logic.getAutoClickers());
    }

    /**
     * Main entry point.
     *
     * @param args unused.
     */
    public static void main(final String[] args)
    {
        launch(args);
    }
}
