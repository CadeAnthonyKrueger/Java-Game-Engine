package Game_Files.GameObjects.TerrainObjects;

import Engine.GameObjects.GameObject;
import Engine.Helpers.CONSTANTS;
import Engine.Misc.ALARM_ID;
import Game_Files.Enums.TerrainTypes;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Helpers.Pair;
import Game_Files.Helpers.Sprite;
import Game_Files.Interfaces.FactoryObject;
import Game_Files.Managers.GridManager;

import java.awt.*;

public abstract class TerrainObject extends GameObject implements FactoryObject {

    protected GridSpace<EntityObject> currentGridSpace;
    protected Sprite sprite;
    protected Pair<TerrainTypes, TerrainTypes.TerrainSubTypes<?>> terrainTypes;
    protected int spritePosition;
    protected int gridSpaceSize = (int) GridManager.GetGridSpaceSize();

    public TerrainObject(Pair<TerrainTypes, TerrainTypes.TerrainSubTypes<?>> types) {
        SetRenderer("pixel");
        this.terrainTypes = types;
        this.spritePosition = terrainTypes.T1().GetTile() == null ?
                terrainTypes.T2().GetTile() : terrainTypes.T1().GetTile();
    }

    public void Initialize()
    {
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/4, ALARM_ID.ALARM_0);
        drawObject.SubmitDrawRegistration();
        updateObject.SubmitUpdateRegistration();
    }

    public void Deinitialize()
    {
        alarmObject.SubmitAlarmDeregistration(ALARM_ID.ALARM_0);
        drawObject.SubmitDrawDeregistration();
        updateObject.SubmitUpdateDeregistration();
        currentGridSpace = null;
    }

    public GridSpace<EntityObject> GetCurrentGridSpace() { return currentGridSpace; }

    public void SetCurrentGridSpace(GridSpace<EntityObject> gridSpace)
    {
        currentGridSpace = gridSpace;
    }

    @Override
    public void GameDraw(Graphics2D g2)
    {
        if (currentGridSpace != null)
        {
            sprite.DrawSprite(g2, currentGridSpace.GetGridCoords(), spritePosition);
        }
    }

}
