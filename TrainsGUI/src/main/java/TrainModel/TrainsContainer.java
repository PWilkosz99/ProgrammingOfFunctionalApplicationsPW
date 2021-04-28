package TrainModel;

import java.util.ArrayList;

public class TrainsContainer {
    public ArrayList<Train> trainList;

    public TrainsContainer() {
        trainList = new ArrayList<Train>();
    }

    public void add(Train train) {
        trainList.add(train);
    }

    public ArrayList<Train> searchConnections(String from, String to, int hour) {
        ArrayList<Train> connections = new ArrayList<Train>();
        for (Train t : trainList) {
            Boolean hit = false;
            Boolean connection = false;
            for (int i = 0; i < t.stationList.size(); i++) {
                if (hour == -1) {
                    if (t.stationList.get(i).toString().equals(from)) {
                        hit = true;
                        System.out.println(t.stationList.get(i));
                    }
                } else {
                    if (t.stationList.get(i).toString().equals(from) && t.timeTableList.get(i).equals(hour)) {
                        hit = true;
                        System.out.println(t.stationList.get(i));
                    }
                }

                if (hit) {
                    if (t.stationList.get(i).toString().equals(to)) {
                        connection = true;
                        System.out.println(t.stationList.get(i));
                        break;
                    }
                }
            }
            if (connection) {
                connections.add(t);
            }
        }
        return connections;
    }
}
