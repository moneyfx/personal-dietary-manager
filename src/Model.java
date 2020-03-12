import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
    Indining indining;
    Outdining outdining;

//    ArrayList indining = new ArrayList<Indining>();
//    ArrayList outdining = new ArrayList<Outdining>();



    public void setIndining(Indining indining) {
        this.indining = indining;

        setChanged();
        notifyObservers();
    }

    public void setOutdining(Outdining outdining) {
        this.outdining = outdining;

        setChanged();
        notifyObservers();
    }



}
