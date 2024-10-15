package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.Crocodile;
import Game_Files.Interfaces.FactoryParameter;

public class CrocodileFactory extends AbstractFactory {

    public CrocodileFactory() {}

    @Override
    protected Crocodile CreateNew(FactoryParameter<?> param) {
        return new Crocodile();
    }

}
