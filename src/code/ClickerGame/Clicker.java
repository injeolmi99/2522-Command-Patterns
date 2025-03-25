package ClickerGame;

import ClickerGame.Commands.CommandAutoClicker;
import ClickerGame.Commands.CommandClick;
import ClickerGame.Commands.CommandUpgrade;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
    private static final int    WINDOW_WIDTH     = 300;
    private static final int    WINDOW_HEIGHT    = 250;
    private static final int    AUTO_INTERVAL_MS = 1000;
    private static final String SCENE_TITLE      = "Clicker Game";

    private static final String PREFIX_SCORE         = "Score: ";
    private static final String PREFIX_POWER         = "Click Power: ";
    private static final String PREFIX_AUTO_CLICKERS = "Auto Clickers: ";

    private static final String TEXT_BUTTON_CLICK        = "Click";
    private static final String TEXT_BUTTON_UPGRADE      = "Upgrade (+%d Power, Costs %d)";
    private static final String TEXT_BUTTON_AUTO_CLICKER = "Buy Auto Clicker (Costs %d)";


    /**
     * Launches the GUI window and sets up game elements.
     *
     * @param primaryStage the main stage
     */
    public void start(final Stage primaryStage)
    {
        final GameLogic     logic;
        final ButtonInvoker invoker;

        final int curScore;
        final int curClickPower;
        final int curAutoClickers;

        final Label scoreLabel;
        final Label powerLabel;
        final Label autoClickerLabel;

        final String buttonTextClick;
        final String buttonTextUpgrade;
        final String buttonTextAutoClicker;

        final Button buttonClick;
        final Button buttonUpgrade;
        final Button buttonAutoClicker;

        final KeyFrame frame;
        final Duration interval;
        final Timeline autoTimeline;

        final VBox  layout;
        final Scene scene;

        final int upgradePowerIncrement;
        final int upgradeCost;
        final int autoClickerCost;

        logic   = new GameLogic();
        invoker = new ButtonInvoker();


        curScore        = logic.getCurScore();
        curClickPower   = logic.getCurClickPower();
        curAutoClickers = logic.getCurAutoClickers();

        scoreLabel       = new Label(PREFIX_SCORE + curScore);
        powerLabel       = new Label(PREFIX_POWER + curClickPower);
        autoClickerLabel = new Label(PREFIX_AUTO_CLICKERS + curAutoClickers);


        upgradePowerIncrement = GameLogic.getPowerIncrement();
        upgradeCost = GameLogic.getPowerIncrementCost();
        autoClickerCost = GameLogic.getAutoClickerCost();

        buttonTextClick = TEXT_BUTTON_CLICK;
        buttonTextUpgrade = String.format(TEXT_BUTTON_UPGRADE, upgradePowerIncrement, upgradeCost);
        buttonTextAutoClicker = String.format(TEXT_BUTTON_AUTO_CLICKER, autoClickerCost);

        buttonClick       = new Button(buttonTextClick);
        buttonUpgrade     = new Button(buttonTextUpgrade);
        buttonAutoClicker = new Button(buttonTextAutoClicker);

        // Click button actions
        buttonClick.setOnAction(e ->
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
        buttonUpgrade.setOnAction(e ->
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
        buttonAutoClicker.setOnAction(e ->
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
                          buttonClick,
                          buttonUpgrade,
                          buttonAutoClicker);

        scene = new Scene(layout,
                          WINDOW_WIDTH,
                          WINDOW_HEIGHT);


        primaryStage.setTitle(SCENE_TITLE);
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
        scoreLabel.setText(PREFIX_SCORE + logic.getCurScore());
        powerLabel.setText(PREFIX_POWER + logic.getCurClickPower());
        autoLabel.setText(PREFIX_AUTO_CLICKERS + logic.getCurAutoClickers());
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
