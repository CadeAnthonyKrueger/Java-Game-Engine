package Game_Files.Helpers;

import Game_Files.Enums.AdjacentReturnTypes;
import Game_Files.Enums.GridSpaceDirections;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Interfaces.AdjacentReturnType;
import Game_Files.Managers.GridManager;

import java.util.ArrayList;
import java.util.function.Predicate;

import static Game_Files.Enums.GridSpaceDirections.*;

public class AdjacentGridSpaces {

    private final ArrayList<Pair<GridSpace<EntityObject>, GridSpaceDirections>> adjacentSpaces = new ArrayList<>();

    public AdjacentGridSpaces(Coordinate<Integer> gridCoords)
    {
        SetAdjacentGridSpaces(gridCoords);
    }

    public void SetAdjacentGridSpaces(Coordinate<Integer> gridCoords)
    {
        for (GridSpaceDirections direction : GridSpaceDirections.values())
        {
            int rowCheck = gridCoords.GetRow() + direction.yInc;
            int colCheck = gridCoords.GetCol() + direction.xInc;
            GridSpace<EntityObject> space = GridManager.GetGridSpace(new Coordinate<>(rowCheck, colCheck));
            if (space != null) {
                Pair<GridSpace<EntityObject>, GridSpaceDirections> pair = new Pair<>(space, direction);
                adjacentSpaces.add(pair);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> GetAdjacentSpaces
    (
            Predicate<T> condition,
            boolean diagonal,
            AdjacentReturnTypes type
    ) {
        ArrayList<T> availableSpaces = new ArrayList<>();
        for (Pair<GridSpace<EntityObject>, GridSpaceDirections> space : adjacentSpaces) {
            if (space != null && condition.test((T) space.T1())) {
                boolean cond1 = !diagonal && space.T2() != UP_LEFT && space.T2() != UP_RIGHT;
                boolean cond2 = space.T2() != DOWN_LEFT && space.T2() != DOWN_RIGHT;
                if (!(cond1 && cond2)) {
                    switch(type) {
                        case ALL: availableSpaces.add((T) space); break;
                        case SPACES: availableSpaces.add((T) space.T1()); break;
                        case DIRECTIONS: availableSpaces.add((T) space.T2()); break;
                    }
                }
            }
        }
        System.out.println(availableSpaces);
        return availableSpaces;
    }

}
