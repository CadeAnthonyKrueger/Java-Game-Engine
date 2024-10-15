package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Enums.AdjacentReturnTypes;
import Game_Files.Enums.EntityObjects;
import Game_Files.Enums.TerrainTypes;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.Helpers.AdjacentGridSpaces;
import Game_Files.Helpers.Coordinate;
import Game_Files.Interfaces.AdjacentReturnType;
import Game_Files.Interfaces.FactoryObject;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class GridSpace<T> extends GameObject implements FactoryObject, AdjacentReturnType
{

    private Coordinate<Integer> gridCoords;

    private Coordinate<Float> worldCoords;

    private final float size = GridManager.GetGridSpaceSize();

    private T data = null;

    private TerrainTypes terrainType = null;

    private AdjacentGridSpaces adjacentGridSpaces;

    public GridSpace() {}

    public void Initialize() {}

    public void Deinitialize() {}

    public T GetData() { return data; }

    public Coordinate<Integer> GetGridCoords() { return gridCoords; }

    public Coordinate<Float> GetWorldCoords() { return worldCoords; }

    public void SetData(T item) { data = item; }

    public void SetTerrainType(TerrainTypes terrain) { terrainType = terrain; }

    public void SetCoordinates(Coordinate<Integer> coords)
    {
        gridCoords = coords;
        worldCoords = ConvertGridToWorldSpace(gridCoords);
        drawObject.SubmitDrawRegistration();
        updateObject.SubmitUpdateRegistration();
    }

    public void SetAdjacentSpaces()
    {
        adjacentGridSpaces = new AdjacentGridSpaces(gridCoords);
    }

    public void RemoveData()
    {
        data = null;
    }

    public boolean IsEmpty() { return data == null; }

    public boolean Contains(EntityObjects entityType)
    {
        return data != null && ((EntityObject) data).species == entityType;
    }

    public boolean Contains(TerrainTypes terrainTypeCheck) { return terrainTypeCheck == terrainType; }

    public <R> ArrayList<R> GetAdjacentSpaces(boolean diagonal)
    {
        return adjacentGridSpaces.GetAdjacentSpaces(GridSpace::IsEmpty, diagonal, AdjacentReturnTypes.SPACES);
    }

    public <R> ArrayList<R> GetAdjacentSpaces
    (
        Predicate<GridSpace<EntityObject>> condition,
        boolean diagonal,
        AdjacentReturnTypes type
    ) {
        return adjacentGridSpaces.GetAdjacentSpaces(condition, diagonal, type);
    }

    // A GridSpace's world space is different from an Entity's world space
    // GridSpace uses top left corner
    private Coordinate<Float> ConvertGridToWorldSpace(Coordinate<Integer> coords)
    {
        return new Coordinate<>(size * coords.GetRow(), size * coords.GetCol());
    }

    @Override
    public void GameDraw(Graphics2D g2)
    {
        g2.setColor(new Color(0, 0, 0, 10));
        int x = worldCoords.GetCol().intValue(), y = worldCoords.GetRow().intValue();
        //g2.fillRect(x, y, (int) size, (int) size);
        g2.drawRect(x, y, (int) size, (int) size);
    }

    @Override
    public String toString()
    {
        EntityObjects containedData = null;
        if (data != null) { containedData = ((EntityObject) (data)).species; }
        return "GridSpace with " + gridCoords.toString() + " contains " + containedData + " and has terrain of " + terrainType;
    }

}
