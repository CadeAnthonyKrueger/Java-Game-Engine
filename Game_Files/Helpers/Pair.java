package Game_Files.Helpers;

import Game_Files.Interfaces.AdjacentReturnType;
import Game_Files.Interfaces.FactoryObject;
import Game_Files.Interfaces.FactoryParameter;

public class Pair<T1, T2> implements FactoryParameter<Pair<T1, T2>>, AdjacentReturnType {

    T1 t1;
    T2 t2;

    public Pair(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T1 T1() { return t1; }
    public T2 T2() { return t2; }

    @Override
    public Pair<T1, T2> get() {
        return this;
    }

}
