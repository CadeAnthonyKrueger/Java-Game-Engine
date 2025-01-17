package Game_Files.Helpers;

import Engine.ResourceManagement.SpriteSheet;
import Engine.ResourceManagement.SpriteSheetManager;
import Game_Files.Enums.SpriteDirections;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.function.BiFunction;

import static Game_Files.Enums.SpriteDirections.*;

public class Sprite {

    private final SpriteSheet spriteSheet;

    private final int height, width, cols;

    private int currentAnimation = 0;

    private SpriteDirections direction = DOWN;

    private final Random random = new Random();

    public Sprite(String key, int height, int width)
    {
        this.spriteSheet = SpriteSheetManager.Get(key);
        this.height = height;
        this.width = width;
        this.cols = spriteSheet.GetSpriteSheetCols();
    }

    public Sprite(String key, double sizeDivisor)
    {
        this.spriteSheet = SpriteSheetManager.Get(key);
        this.height = (int) (spriteSheet.GetSpriteHeight() / sizeDivisor);
        this.width = (int) (spriteSheet.GetSpriteWidth() / sizeDivisor);
        this.cols = spriteSheet.GetSpriteSheetCols();
    }

    public void DrawSprite(Graphics2D g2, Coordinate<Integer> gridCoords)
    {
        Coordinate<Float> worldCoords = ConvertGridToWorldSpace(gridCoords);
        BufferedImage img = spriteSheet.GetSprite(currentAnimation);
        g2.drawImage(img, worldCoords.GetCol().intValue(), worldCoords.GetRow().intValue(),
            width, height, null);
    }

    public void DrawSprite(Graphics2D g2, Coordinate<Integer> gridCoords, int spriteLocation)
    {
        Coordinate<Float> worldCoords = ConvertGridToWorldSpace(gridCoords);
        BufferedImage img = spriteSheet.GetSprite(spriteLocation);
        g2.drawImage(img, worldCoords.GetCol().intValue(), worldCoords.GetRow().intValue(),
                width, height, null);
    }

    private Coordinate<Float> ConvertGridToWorldSpace(Coordinate<Integer> gridCoords)
    {
        float squareSize = GridManager.GetGridSpaceSize();
        BiFunction<Integer, Integer, Integer> func = (n, spriteDimension) ->
            (int) ((squareSize * n + (squareSize / 2)) - spriteDimension / 2);
        float worldRow = func.apply(gridCoords.GetRow(), height);
        float worldCol = func.apply(gridCoords.GetCol(), width);
        return new Coordinate<>(worldRow, worldCol);
    }

    public void UpdateSprite()
    {
        currentAnimation++;
        if (currentAnimation >= (direction.ordinal() * cols) + cols)
        {
            currentAnimation = direction.ordinal() * cols;
        }
        if (random.nextInt(0, 10) == 5) // 1/10 chance to change directions randomly
        {
            direction = values()[random.nextInt(0, values().length)];
        }
    }

}
