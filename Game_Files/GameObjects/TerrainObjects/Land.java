package Game_Files.GameObjects.TerrainObjects;

import Game_Files.Enums.TerrainTypes;
import Game_Files.Helpers.Pair;
import Game_Files.Helpers.Sprite;

public class Land extends TerrainObject {

    public Land(Pair<TerrainTypes, TerrainTypes.TerrainSubTypes<?>> types) {
        super(types);
        this.sprite = new Sprite("land", gridSpaceSize, gridSpaceSize);
    }

}
