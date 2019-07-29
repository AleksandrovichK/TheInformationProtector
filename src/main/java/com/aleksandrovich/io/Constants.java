package com.aleksandrovich.io;

import java.awt.*;

/**
 * @author AleksandrovichK
 */
public interface Constants {
    int MAIN_FRAME_WIDTH = 500;
    int MAIN_FRAME_HEIGHT = 270;

    int LICENSE_FRAME_WIDTH = 340;
    int LICENSE_FRAME_HEIGHT = 150;

    String LINE_SEPARATOR = System.getProperty("line.separator");

    Color MAIN_BG_COLOR = new Color(32, 32, 32);
    Color MAIN_TEXT_FIELD_COLOR = new Color(48, 48, 48);
    Color LOGO_COLOR = new Color(196, 202, 198);

    Color ACTIVATION_COLOR = new Color(15, 128, 109);

    Font FONT_10 = new Font("Microsoft JhengHei Light", Font.BOLD, 10);
    Font FONT_12 = new Font("Microsoft JhengHei Light", Font.BOLD, 12);

    interface Bounds {
        Rectangle EXIT_BUTTON_BOUNDS = new Rectangle(MAIN_FRAME_WIDTH - 22 - 5, 5, 22, 22);
        Rectangle PASSFIELD_BOUNDS = new Rectangle(40, 160, MAIN_FRAME_WIDTH - 40 - (MAIN_FRAME_WIDTH - EXIT_BUTTON_BOUNDS.x), 20);
        Rectangle PASSLABEL_BOUNDS = new Rectangle(PASSFIELD_BOUNDS.x, PASSFIELD_BOUNDS.y + 20, 400, 20);
        Rectangle LOGO_BOUNDS = new Rectangle(190, 250, 340, 20);

        Rectangle PASSWORD_BUTTON_BOUNDS = new Rectangle(PASSFIELD_BOUNDS.x + PASSFIELD_BOUNDS.width - 22, PASSFIELD_BOUNDS.y + 22, 22, 22);

        Rectangle LICENSE_TEXT_BOUNDS = new Rectangle(22, 100, LICENSE_FRAME_WIDTH - 44, 20);
        Rectangle LICENSE_LABEL_BOUNDS = new Rectangle(LICENSE_TEXT_BOUNDS.width/2 - 5, 120, 220, 20);
    }
}
