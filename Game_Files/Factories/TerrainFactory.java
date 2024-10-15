package Game_Files.Factories;

import Game_Files.Enums.TerrainTypes;
import Game_Files.GameObjects.TerrainObjects.Edge;
import Game_Files.GameObjects.TerrainObjects.Land;
import Game_Files.GameObjects.TerrainObjects.Water;
import Game_Files.Helpers.Pair;
import Game_Files.Interfaces.FactoryObject;
import Game_Files.Interfaces.FactoryParameter;

public class TerrainFactory extends AbstractFactory {

    public TerrainFactory() {}

    @Override
    @SuppressWarnings("unchecked")
    protected FactoryObject CreateNew(FactoryParameter<?> param) {
        Pair<TerrainTypes, TerrainTypes.TerrainSubTypes<?>> types = (Pair<TerrainTypes, TerrainTypes.TerrainSubTypes<?>>) param;
        return switch (types.T1()) {
            case WATER -> new Water(types);
            case EDGE -> new Edge(types);
            case LAND -> new Land(types);
        };
    }

}
