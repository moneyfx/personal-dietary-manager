import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

public class Model extends Observable {
    Indining indining;
    Outdining outdining;

    //ArrayList<Indining> indiningList = new ArrayList<Indining>();
    //ArrayList<Outdining> outdiningList = new ArrayList<Outdining>();

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
