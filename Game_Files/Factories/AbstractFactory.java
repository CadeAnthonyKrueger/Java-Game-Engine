package Game_Files.Factories;

import Game_Files.Interfaces.FactoryObject;
import Game_Files.Interfaces.FactoryParameter;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class AbstractFactory {

    protected final Queue<FactoryObject> recycledEntities;

    public AbstractFactory() { recycledEntities = new ArrayDeque<>(); }

    public FactoryObject GetEntity(FactoryParameter<?> param)
    {
        FactoryObject newEntity = recycledEntities.poll();
        if (newEntity == null)
        {
            newEntity = CreateNew(param);
            //System.out.println("Entity was created");
        }
        //else { System.out.println("Entity was recycled"); }
        newEntity.Initialize();
        return newEntity;
    }

    public void RecycleEntity(FactoryObject deadEntity)
    {
        recycledEntities.add(deadEntity);
        deadEntity.Deinitialize();
        //System.out.println("Entity was trashed");
    }

    protected abstract FactoryObject CreateNew(FactoryParameter<?> param);

}
