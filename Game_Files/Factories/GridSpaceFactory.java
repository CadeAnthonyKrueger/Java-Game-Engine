package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Interfaces.FactoryParameter;

public class GridSpaceFactory extends AbstractFactory {

    public GridSpaceFactory() {}

    @Override
    protected GridSpace<EntityObject> CreateNew(FactoryParameter<?> param) { return new GridSpace<>(); }

}
