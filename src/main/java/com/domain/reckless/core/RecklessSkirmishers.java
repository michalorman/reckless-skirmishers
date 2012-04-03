package com.domain.reckless.core;

import com.domain.reckless.core.setting.InMemorySettings;
import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.game.FixedLengthGameLoop;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.game.GameLoop;
import com.domain.reckless.game.StrategyDrivenGameContext;
import com.domain.reckless.game.strategy.DefaultRenderStrategy;
import com.domain.reckless.game.strategy.DefaultUpdateStrategy;
import com.domain.reckless.graphics.FrameContext;
import com.domain.reckless.graphics.SwingApplicationFrame;
import com.domain.reckless.i18n.I18n;
import com.domain.reckless.i18n.PropertiesI18n;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.domain.reckless.core.RecklessSkirmishers.CmdArgs.parseCmdArgs;

public class RecklessSkirmishers {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecklessSkirmishers.class);

    enum CmdArgs {
        SET_LOCALE("locale", Settings.Setting.LOCALE), // --locale=en|pl

        DRAW_FPS("drawFps", Settings.Setting.DRAW_FPS), // --drawFps=true|false
        DRAW_BBOX("drawBBox", Settings.Setting.DRAW_BBOX), // --drawBBox=true|false

        GAME_RES("gameRes") { // --gameRes=640x480
            @Override
            public void handleArg(String cmdArg, Settings settings) {
                String[] res = cmdArg.split("x");
                settings.setSetting(Settings.Setting.SCREEN_WIDTH, res[0]);
                settings.setSetting(Settings.Setting.SCREEN_HEIGHT, res[1]);
            }
        },
        SCALE("scale", Settings.Setting.SCREEN_SCALE);

        private String cmd;
        private Settings.Setting setting;

        CmdArgs(String cmd) {
            this(cmd, null);
        }

        CmdArgs(String cmd, Settings.Setting setting) {
            this.cmd = cmd;
            this.setting = setting;
        }

        public boolean matches(String cmdArg) {
            return cmdArg.startsWith("--" + cmd + "=");
        }

        public void handleArg(String value, Settings settings) {
            settings.setSetting(setting, value);
        }

        static void parseCmdArgs(String[] commandLineArgs, Settings settings) {
            for (String commandLineArg : commandLineArgs) {
                for (CmdArgs cmdArg : values()) {
                    if (cmdArg.matches(commandLineArg)) {
                        cmdArg.handleArg(commandLineArg.substring(commandLineArg.indexOf("=") + 1).trim(), settings);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        LOGGER.info("Bootstraping Reckless Skirmishers...");

        System.setProperty("sun.java2d.xrender", "true");

        Settings settings = new InMemorySettings();

        parseCmdArgs(args, settings);

        try {
            I18n i18n = new PropertiesI18n(settings);

            FrameContext frameContext = new SwingApplicationFrame.Builder(
                    settings.getInt(Settings.Setting.SCREEN_WIDTH),
                    settings.getInt(Settings.Setting.SCREEN_HEIGHT))
                    .scale(settings.getDouble(Settings.Setting.SCREEN_SCALE))
                    .title(i18n.t("game.ui.title"))
                    .build();

            GameContext context = new StrategyDrivenGameContext(
                    frameContext,
                    new DefaultUpdateStrategy(),
                    new DefaultRenderStrategy(),
                    settings);

            GameLoop gameLoop = new FixedLengthGameLoop(context);
            Thread thread = new Thread(gameLoop);
            thread.start();
        } catch (IOException e) {
            LOGGER.error("Failed to load locale file.", e);
        }
    }
}
