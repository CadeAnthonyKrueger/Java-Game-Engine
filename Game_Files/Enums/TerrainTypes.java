package Game_Files.Enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import static Game_Files.Enums.GridSpaceDirections.*;

public enum TerrainTypes {

    WATER(10),
    EDGE(null),
    LAND(null);

    private final Integer spriteTile;

    TerrainTypes(Integer spriteTile) {
        this.spriteTile = spriteTile;
    }

    public Integer GetTile() {
        return spriteTile;
    }

    public interface TerrainSubTypes<T> {
        Integer GetTile();
    }

    public enum EdgeTypes implements TerrainSubTypes<EdgeTypes> {
        TOP_LEFT(6, new GridSpaceDirections[][] {{DOWN_RIGHT}}),
        TOP(7, new GridSpaceDirections[][] {
                {DOWN_LEFT, DOWN, DOWN_RIGHT},
                {DOWN_LEFT, DOWN},
                {DOWN, DOWN_RIGHT},
                {DOWN}
        }),
        TOP_RIGHT(8, new GridSpaceDirections[][] {{DOWN_LEFT}}),
        LEFT(9, new GridSpaceDirections[][] {
                {UP_RIGHT, GridSpaceDirections.RIGHT, DOWN_RIGHT},
                {UP_RIGHT, GridSpaceDirections.RIGHT},
                {GridSpaceDirections.RIGHT, DOWN_RIGHT},
                {GridSpaceDirections.RIGHT}
        }),
        RIGHT(11, new GridSpaceDirections[][] {
                {UP_LEFT, GridSpaceDirections.LEFT, DOWN_LEFT},
                {UP_LEFT, GridSpaceDirections.LEFT},
                {GridSpaceDirections.LEFT, DOWN_LEFT},
                {GridSpaceDirections.LEFT}
        }),
        BOTTOM_LEFT(12, new GridSpaceDirections[][] {{UP_RIGHT}}),
        BOTTOM(13, new GridSpaceDirections[][] {
                {UP_LEFT, UP, UP_RIGHT},
                {UP_LEFT, UP},
                {UP, UP_RIGHT},
                {UP}
        }),
        BOTTOM_RIGHT(14, new GridSpaceDirections[][] {{UP_LEFT}}),

        TOP_LEFT_INDENT(1, new GridSpaceDirections[][] {
                {GridSpaceDirections.LEFT, UP_LEFT, UP},
                {GridSpaceDirections.LEFT, UP_LEFT, UP, UP_RIGHT},
                {GridSpaceDirections.LEFT, UP_LEFT, UP, DOWN_LEFT},
                {GridSpaceDirections.LEFT, UP_LEFT, UP, UP_RIGHT, DOWN_LEFT}
        }),
        TOP_RIGHT_INDENT(2, new GridSpaceDirections[][] {
                {UP, UP_RIGHT, GridSpaceDirections.RIGHT},
                {UP, UP_RIGHT, GridSpaceDirections.RIGHT, UP_LEFT},
                {UP, UP_RIGHT, GridSpaceDirections.RIGHT, DOWN_RIGHT},
                {UP, UP_RIGHT, GridSpaceDirections.RIGHT, UP_LEFT, DOWN_RIGHT}
        }),
        BOTTOM_LEFT_INDENT(4, new GridSpaceDirections[][] {
                {GridSpaceDirections.LEFT, DOWN_LEFT, DOWN},
                {GridSpaceDirections.LEFT, DOWN_LEFT, DOWN, UP_LEFT},
                {GridSpaceDirections.LEFT, DOWN_LEFT, DOWN, DOWN_RIGHT},
                {GridSpaceDirections.LEFT, DOWN_LEFT, DOWN, UP_LEFT, DOWN_RIGHT}
        }),
        BOTTOM_RIGHT_INDENT(5, new GridSpaceDirections[][] {
                {GridSpaceDirections.RIGHT, DOWN_RIGHT, DOWN},
                {GridSpaceDirections.RIGHT, DOWN_RIGHT, DOWN, UP_RIGHT},
                {GridSpaceDirections.RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT},
                {GridSpaceDirections.RIGHT, DOWN_RIGHT, DOWN, UP_RIGHT, DOWN_LEFT}
        });

        private final Integer spriteTile;
        private final GridSpaceDirections[][] possibleDirections;
        //private final Predicate<Collection<?>> condition;

        EdgeTypes(Integer spriteTile, GridSpaceDirections[][] possibleDirections)//}, Predicate<Collection<?>> condition)
        {
            this.spriteTile = spriteTile;
            this.possibleDirections = possibleDirections;
            //this.condition = condition;
        }

        @Override
        public Integer GetTile() {
            return spriteTile;
        }

        public GridSpaceDirections[][] GetKeys() {
            return this.possibleDirections;
        }

    }

    public enum LandTypes implements TerrainSubTypes<EdgeTypes> {
        GRASS(0),
        DENSE_GRASS(1),
        BUSHY_GRASS(2),
        SWAMPY_GRASS(3);

        private final Integer spriteTile;

        LandTypes(Integer spriteTile) {
            this.spriteTile = spriteTile;
        }

        @Override
        public Integer GetTile() {
            return spriteTile;
        }
    }

}
