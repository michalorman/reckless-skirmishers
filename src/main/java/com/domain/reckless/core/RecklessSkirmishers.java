package com.domain.reckless.core;

import com.domain.reckless.core.setting.InMemorySettings;
import com.domain.reckless.core.setting.Settings;
import com.domain.reckless.game.DefaultGameLoop;
import com.domain.reckless.game.GameLoop;
import com.domain.reckless.graphics.DefaultScreen;
import com.domain.reckless.graphics.Screen;
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
        DRAW_FPS("drawFps", Settings.Setting.DRAW_FPS), // --drawFps=1|0
        GAME_RES("gameRes") { // --gameRes=640x480
            @Override
            public void handleArg(String cmdArg, Settings settings) {
                String[] res = cmdArg.split("x");
                settings.setSetting(Settings.Setting.SCREEN_WIDTH, res[0]);
                settings.setSetting(Settings.Setting.SCREEN_HEIGHT, res[1]);
            }
        };

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

        Settings settings = new InMemorySettings();

        parseCmdArgs(args, settings);

        try {
            I18n i18n = new PropertiesI18n(settings);
            Screen screen = new DefaultScreen.Builder(settings.getSettingAsInt(Settings.Setting.SCREEN_WIDTH),
                    settings.getSettingAsInt(Settings.Setting.SCREEN_HEIGHT))
                    .scale(2.0)
                    .title(i18n.t("game.ui.title"))
                    .build();
            GameLoop gameLoop = new DefaultGameLoop(screen);
            Thread thread = new Thread(gameLoop);
            thread.start();
        } catch (IOException e) {
            LOGGER.error("Failed to load locale file.", e);
        }
    }
}
