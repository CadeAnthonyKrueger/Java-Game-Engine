package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.Fish;
import Game_Files.Interfaces.FactoryParameter;

public class FishFactory extends AbstractFactory {

    public FishFactory() {}

    @Override
    protected Fish CreateNew(FactoryParameter<?> param) {
        return new Fish();
    }

}
