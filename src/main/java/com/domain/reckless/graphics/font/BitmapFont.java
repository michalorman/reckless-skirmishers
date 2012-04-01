package com.domain.reckless.graphics.font;

import com.domain.reckless.graphics.bitmap.Bitmap;

import java.util.Map;
import java.util.TreeMap;

public class BitmapFont {
    public static final int DEFAULT_FONT_SPACING = 2;
    //TODO check performance, swap to array if necessary.
    private Map<Character, BitmapCharacter> characters;

    public BitmapFont() {
        characters = new TreeMap<>();
    }

    public void addCharacter(char character, Bitmap bitmap) {
        BitmapCharacter bitmapCharacter = new BitmapCharacter(character, bitmap);
        characters.put(character, bitmapCharacter);
    }

    public void write(Bitmap bitmap, int x, int y, String str) {
        write(bitmap, x, y, str, DEFAULT_FONT_SPACING);
    }

    public void write(Bitmap bitmap, int x, int y, String str, int spacing) {
        int stringLength = str.length();
        for (int i = 0; i < stringLength; i++) {
            Character ch = str.charAt(i);
            BitmapCharacter bitmapCharacter = characters.get(ch);
            if (bitmapCharacter != null) {
                bitmap.blit(bitmapCharacter.bitmap, x + (i * bitmapCharacter.bitmap.getWidth()) + spacing, y);
            }
        }
    }

    public static BitmapFont load(String filename, String letters, int letterW, int letterH) {
        int index = 0;
        Bitmap[][] bitmaps = Bitmap.loadTiles(filename, letterW, letterH);
        BitmapFont bitmapFont = new BitmapFont();
        //Could use for each loop, but this provides better readability.
        for (int row = 0; row < bitmaps.length; row++) {
            for (int col = 0; col < bitmaps[row].length; col++) {
                bitmapFont.addCharacter(letters.charAt(index), bitmaps[row][col]);
                index++;
            }
        }
        return bitmapFont;
    }
}

class BitmapCharacter implements Comparable<BitmapCharacter> {
    char character;
    Bitmap bitmap;

    public BitmapCharacter(char character, Bitmap bitmap) {
        this.character = character;
        this.bitmap = bitmap;
    }

    public int compareTo(BitmapCharacter bitmapCharacter) {
        return character - bitmapCharacter.character;
    }
}