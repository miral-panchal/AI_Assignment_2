package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Calculations {

    final int num_routes = 10;
    int MAX_DISTANCE = 0;
    ArrayList<Route> routes = new ArrayList<>();
    ArrayList<Route> parents = new ArrayList<>();



    Calculations(String [] city_names, ArrayList<String []> distances){

        try {
            File file = new File("TEST_DATA.csv");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();                       //first line is all city names
            city_names = line.split(",");                   //city names are put into this array


            while ((line = reader.readLine()) != null) {
                String [] temp = line.split(",");
                distances.add(temp);                              //get distances for cities
            }
        } catch (IOException e) { e.printStackTrace(); }


       //Initialize Population
        Population pop = new Population(city_names);

        int [] currentRoute;
        Fitness fit = new Fitness();

        for (int i = 0; i < num_routes; i++) {
            currentRoute = pop.calcRoute();                             //get random route (eg. 1,3,4,2,5,8,6,7)

            int distance = fit.getDistance(currentRoute, distances);    //get distance

            if (distance > MAX_DISTANCE)
                MAX_DISTANCE = distance;

            routes.add(new Route(distance, currentRoute));              //new route
        }




        //Routes in name format
        for (int i = 0; i < routes.size(); i++) {
            routes.get(i).setFitness(fit.getFitness(MAX_DISTANCE,routes.get(i).get_distance()));
            System.out.println(routes.get(i).getFitness());
        }





    }

    public int [] getChild(int [] parent1, int [] parent2){

        final int size = parent1.length;
        Random rand = new Random();


        //convert to string
        String p1 = Arrays.toString(parent1);
        String p2 = Arrays.toString(parent2);

        //choose 2 random points to split at
        int split1 = rand.nextInt(size -1);
        int split2 = rand.nextInt(size);

        //make the smaller one the start and the larger one the end
        int start = Math.min(split1, split2);
        int end = Math.max(split1, split2);

        //make 2 child tours
        List<Integer> child1 = new Vector<Integer>();
        List<Integer> child2 = new Vector<Integer>();

        //child1.addAll(parent1.subList(start, end));
        //child2.addAll(parent2.subList(start, end))







        return parent1;
    }




}
