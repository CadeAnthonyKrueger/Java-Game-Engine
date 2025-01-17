package Game_Files.Managers;

import Game_Files.Enums.AdjacentReturnTypes;
import Game_Files.Enums.GridSpaceDirections;
import Game_Files.Enums.TerrainTypes;
import Game_Files.Factories.TerrainFactory;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;
import Game_Files.GameObjects.TerrainObjects.TerrainObject;
import Game_Files.Helpers.Coordinate;
import Game_Files.Helpers.EdgeTypeMap;
import Game_Files.Helpers.Pair;

import java.util.ArrayList;
import java.util.Random;

import static Game_Files.Enums.GridSpaceDirections.UP_LEFT;
import static Game_Files.Enums.TerrainTypes.*;

public class TerrainManager {

    private TerrainManager() { }

    private static TerrainManager instance;

    private synchronized static TerrainManager getInstance()
    {
        if (instance == null) { instance = new TerrainManager(); }
        return instance;
    }

    private static TerrainFactory terrainFactory;
    private static final int gridSize = (int) GridManager.GetGridSize();
    private static final Random random = new Random();

    private final ArrayList<GridSpace<EntityObject>> riverEdges = new ArrayList<>();
    private ArrayList<GridSpace<EntityObject>> landEdges = new ArrayList<>();

    public static void Initialize() { getInstance()._Initialize(); }

    private void _Initialize() {
        terrainFactory = new TerrainFactory();
        GenerateTerrain();
    }

    private void GenerateTerrain() {
        CreateRiver();
        CreateLand(riverEdges, LandTypes.GRASS);
        CreateRiverBank();
    }

    private void CreateRiver() {
        int currentRow = gridSize - 1;
        int fillValue = Math.max((int) (0.5*gridSize), (random.nextInt((int) (0.7*gridSize)) + 1)); // 7
        int startingCol = random.nextInt(gridSize - fillValue + 1);
        for (int i = 0; i < gridSize; i++) {
            if (i != 0) {
                if (i % 2 != 0) {
                    fillValue = ForceInBounds(fillValue + (random.nextInt(3) - 1), 5, 7);
                    startingCol = startingCol + (random.nextInt(3) - 1);
                    startingCol = ForceInBounds(startingCol, 0, gridSize - fillValue);
                }
            }
            //System.out.println("Fill Value: " + fillValue + " Starting Col: " + startingCol);
            for (int j = 0; j < fillValue; j++) {
                GridSpace<EntityObject> gridSpace = GridManager.GetGridSpace(new Coordinate<>(currentRow, startingCol));
                if (j == 0) {
                    riverEdges.add(gridSpace);
                    PlaceTerrain(WATER, null, gridSpace);
                } else if (j == fillValue - 1) {
                    riverEdges.add(gridSpace);
                    PlaceTerrain(WATER, null, gridSpace);
                }
                PlaceTerrain(WATER, null, gridSpace);
                startingCol++;
            }
            startingCol -= fillValue;
            currentRow--;
        }
    }

    private void CreateLand(ArrayList<GridSpace<EntityObject>> edges, TerrainTypes.LandTypes subType) {
        ArrayList<GridSpace<EntityObject>> newEdges = new ArrayList<>();
        for(GridSpace<EntityObject> edge : edges) {
            ArrayList<GridSpace<EntityObject>> availableSpaces = edge.GetAdjacentSpaces((space) ->
                    space.Contains((TerrainTypes) null), true, AdjacentReturnTypes.SPACES);
            System.out.println(availableSpaces);
            for(GridSpace<EntityObject> adjSpace : availableSpaces) {
                newEdges.add(adjSpace);
                PlaceTerrain(LAND, subType, adjSpace);
            }
        }
        if (subType == LandTypes.GRASS) {
            landEdges = newEdges;
            CreateLand(newEdges, LandTypes.DENSE_GRASS);
        } else if (!newEdges.isEmpty()) {
            CreateLand(newEdges, LandTypes.SWAMPY_GRASS);
        }
    }

    private void CreateRiverBank() {
        EdgeTypeMap edgeMap = new EdgeTypeMap();
        for (GridSpace<EntityObject> edge : landEdges) {
            ArrayList<GridSpaceDirections> adjSpaces = edge.GetAdjacentSpaces(space ->
                    space.Contains(WATER), true, AdjacentReturnTypes.DIRECTIONS);
            TerrainTypes.EdgeTypes edgeType = edgeMap.GetEdge(adjSpaces);
            if (edgeType != null) {
                PlaceTerrain(EDGE, edgeMap.GetEdge(adjSpaces), edge);
            }
        }
    }

    private int ForceInBounds(int value, int lower, int upper) {
        return Math.max(Math.min(value, upper), lower);
    }

    private void PlaceTerrain(TerrainTypes type, TerrainTypes.TerrainSubTypes<?> subType, GridSpace<EntityObject> gridSpace) {
        gridSpace.SetTerrainType(type);
        Pair<TerrainTypes, TerrainTypes.TerrainSubTypes<?>> terrainTile = new Pair<>(type, subType);
        ((TerrainObject) (terrainFactory.GetEntity(terrainTile))).SetCurrentGridSpace(gridSpace);
    }

}
