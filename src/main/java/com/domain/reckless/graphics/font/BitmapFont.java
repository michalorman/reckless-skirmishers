package com.domain.reckless.graphics.font;

import com.domain.reckless.graphics.bitmap.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class BitmapFont {
    public static final int DEFAULT_FONT_SPACING = 2;
    //TODO check performance, swap to array if necessary.
    private Map<Character, Bitmap> characters;

    public BitmapFont() {
        characters = new HashMap<>();
    }

    public void addCharacter(char character, Bitmap bitmap) {
        characters.put(character, bitmap);
    }

    public void write(Bitmap bitmap, int x, int y, String str) {
        write(bitmap, x, y, str, DEFAULT_FONT_SPACING);
    }

    public void write(Bitmap bitmap, int x, int y, String str, int spacing) {
        int stringLength = str.length();
        for (int i = 0; i < stringLength; i++) {
            Character ch = str.charAt(i);
            Bitmap letter = characters.get(ch);
            if (letter != null) {
                bitmap.blit(letter, i * letter.getWidth() + spacing, y);
            }
        }
    }

    public static BitmapFont load(String filename, String letters, int letterW, int letterH) {
        Bitmap[][] bitmaps = Bitmap.loadTiles(filename, letterW, letterH);
        BitmapFont bitmapFont = new BitmapFont();
        int index = 0;
        for (int row = 0; row < bitmaps.length; row++) {
            for (int col = 0; col < bitmaps[row].length; col++) {
                bitmapFont.characters.put(letters.charAt(index), bitmaps[row][col]);
                index++;
            }
        }
        return bitmapFont;
    }
}
