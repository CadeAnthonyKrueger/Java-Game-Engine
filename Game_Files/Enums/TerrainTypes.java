package Game_Files.Enums;

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
        TOP_LEFT(6),
        TOP(7),
        TOP_RIGHT(8),
        LEFT(9),
        RIGHT(11),
        BOTTOM_LEFT(12),
        BOTTOM(13),
        BOTTOM_RIGHT(14),

        TOP_LEFT_INDENT(1),
        TOP_RIGHT_INDENT(2),
        BOTTOM_LEFT_INDENT(4),
        BOTTOM_RIGHT_INDENT(5);

        private final Integer spriteTile;

        EdgeTypes(Integer spriteTile) {
            this.spriteTile = spriteTile;
        }

        @Override
        public Integer GetTile() {
            return spriteTile;
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
