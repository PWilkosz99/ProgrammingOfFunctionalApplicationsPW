package TrainModel;

import java.util.ArrayList;

public class TrainsContainer {
    public ArrayList<Train> trainList;

    public TrainsContainer() {
        trainList = new ArrayList<Train>();
    }

    public void add(Train train){
        trainList.add(train);
    }
}
