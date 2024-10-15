package Game_Files.Helpers;

import Game_Files.Enums.GridSpaceDirections;
import Game_Files.Enums.TerrainTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EdgeTypeMap {

    private final HashMap<HashSet<GridSpaceDirections>, TerrainTypes.EdgeTypes> map = new HashMap<>();

    public EdgeTypeMap () {
        GenerateMap();
        //System.out.println(map);
    }

    private void GenerateMap() {
        for (TerrainTypes.EdgeTypes value : TerrainTypes.EdgeTypes.values()) {
            for (GridSpaceDirections[] direction : value.GetKeys()) {
                HashSet<GridSpaceDirections> key = new HashSet<>(List.of(direction));
                map.put(key, value);
            }
        }
    }

    public TerrainTypes.EdgeTypes GetEdge(ArrayList<GridSpaceDirections> edges) {
        return map.get(new HashSet<>(edges));
    }

}
