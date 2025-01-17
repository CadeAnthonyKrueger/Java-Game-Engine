package Game_Files.Managers.EntityManagers;

import Game_Files.Factories.SeaGrassFactory;
import Game_Files.Helpers.Coordinate;
import Game_Files.Helpers.SpawnBehavior;

import static Game_Files.Enums.SpawnBehaviors.*;

public class SeaGrassManager extends AbstractEntityManager
{

    public SeaGrassManager() { factory = new SeaGrassFactory(); }

    @Override
    public void MoveAll() {}

    @Override
    public SpawnBehavior<?> SpawnHow()
    {
        switch(liveEntities.size())
        {
            case 0 -> { return new SpawnBehavior<>(FIXED, new Coordinate<>(4, 4)); }
            case 1 -> { return new SpawnBehavior<>(FIXED, new Coordinate<>(4, 5)); }
            case 2 -> { return new SpawnBehavior<>(FIXED, new Coordinate<>(5, 4)); }
            case 3 -> { return new SpawnBehavior<>(FIXED, new Coordinate<>(5, 5)); }
        }
        return null;
    }

    @Override
    public boolean ShouldSpawn() {
        return false;
    }

}
