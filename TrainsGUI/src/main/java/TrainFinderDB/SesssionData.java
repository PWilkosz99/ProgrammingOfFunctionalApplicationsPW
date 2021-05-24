package TrainFinderDB;

import TrainModel.TrainMatchedModel;
import TrainModel.TrainsContainer;

import java.util.ArrayList;

public class SesssionData {
    public static ArrayList<TrainMatchedModel> matchTrains;
    public static ArrayList<TrainMatchedModel> boughtTickesFor = new  ArrayList<TrainMatchedModel>();
    public static Boolean bought = false;
    public static String lastBoughtName;
    public static TrainsContainer trainsContainer = new TrainsContainer();

    public static Integer startID = -1;
    public static Integer finishID = -1;
}
