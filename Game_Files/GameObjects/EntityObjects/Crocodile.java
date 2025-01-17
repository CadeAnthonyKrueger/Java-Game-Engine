package Game_Files.GameObjects.EntityObjects;

import Game_Files.Enums.EntityObjects;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Helpers.Sprite;
import Game_Files.Interfaces.AdjacentReturnType;
import Game_Files.Managers.GameEntityManager;
import Game_Files.Managers.GridManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static Game_Files.Enums.AdjacentReturnTypes.SPACES;
import static Game_Files.Enums.EntityObjects.*;

public class Crocodile extends EntityObject
{

    private int fedMeter = 0;

    public Crocodile()
    {
        super();
        this.species = CROCODILE;
        this.sprite = new Sprite("croc", 1.6);
    }

    @Override
    public void Move()
    {
        if (fedMeter < 5) {
            //(In the future) This will contain a BFS for finding the closest fish
            ArrayList<GridSpace<EntityObject>> availableSpaces = currentGridSpace.GetAdjacentSpaces((space) ->
                    space.Contains(FISH), false, SPACES);
            //System.out.println("Available spaces size: " + availableSpaces.size());
            if (availableSpaces.isEmpty()) {
                availableSpaces = currentGridSpace.GetAdjacentSpaces(true);
                if (!availableSpaces.isEmpty()) {
                    GridSpace<EntityObject> space = (GridSpace<EntityObject>) availableSpaces.get(random.nextInt(availableSpaces.size()));
                    GridManager.ClearGridSpace(currentGridSpace);
                    SetCurrentGridSpace(null);
                    GridManager.FillGridSpace(space, this);
                }
            } else {
                GridSpace<EntityObject> space = availableSpaces.get(random.nextInt(availableSpaces.size()));
                GridManager.ClearGridSpace(currentGridSpace);
                SetCurrentGridSpace(null);
                //GridManager.PrintMap();
                Consume(space);
                //GridManager.PrintMap();
                GridManager.FillGridSpace(space, this);
            }
        }
        if (fedMeter > 0) { fedMeter -= 1; }
    }

    private void Consume(GridSpace<EntityObject> spotToConsume)
    {
        fedMeter += 3;
        GameEntityManager.GetManager(FISH).Despawn(spotToConsume.GetData());
        GridManager.ClearGridSpace(spotToConsume);
    }

}
