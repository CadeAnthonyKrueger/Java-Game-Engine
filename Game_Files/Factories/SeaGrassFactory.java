package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.SeaGrass;
import Game_Files.Interfaces.FactoryParameter;

public class SeaGrassFactory extends AbstractFactory {

    public SeaGrassFactory() {}

    @Override
    protected SeaGrass CreateNew(FactoryParameter<?> param) {
        return new SeaGrass();
    }

}
