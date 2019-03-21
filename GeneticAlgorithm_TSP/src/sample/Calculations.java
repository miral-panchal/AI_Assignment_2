package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Calculations {

    final int num_routes = 10;
    int MAX_DISTANCE = 0;
    int best_fit = 0;
    ArrayList<Route> routes = new ArrayList<>();

    Route parent1, parent2;

    Calculations(String [] city_names, ArrayList<String []> distances){

        try {
            File file = new File("TEST_DATA.csv");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            city_names = line.split(",");


            while ((line = reader.readLine()) != null) {
                String [] temp = line.split(",");
                distances.add(temp);
            }
        } catch (IOException e) { e.printStackTrace(); }


       //Initialize Population
        Population pop = new Population(city_names);

        int [] currentRoute;
        Fitness fit = new Fitness();

        for (int i = 0; i < num_routes; i++) {
            currentRoute = pop.calcRoute();

            int distance = fit.getDistance(currentRoute, distances);

            if (distance > MAX_DISTANCE)
                MAX_DISTANCE = distance;

//            System.out.println(distance);

            routes.add(new Route(distance, currentRoute));
            System.out.println();

        }

        System.out.println();
        System.out.println("MAX DISTANCE: "+ MAX_DISTANCE);

        //Calculate Fitness
        for (int i = 0; i < routes.size(); i++) {
            int fitness = fit.getFitness(MAX_DISTANCE,routes.get(i).get_distance());
            routes.get(i).setFitness(fitness);
            System.out.println("Fitness "+fitness);
        }

        sortFittest();

        int x;
        Random rand = new Random();
        int n1 = rand.nextInt(routes.size()/2);
        int n2 =  rand.nextInt(routes.size()/2);

        while(n1!=n2) {n2 = rand.nextInt(routes.size()/2);}

        parent1 = routes.get(n1);
        parent2 = routes.get(n2);



    }


    void sortFittest() {
        for (int i = 0; i < routes.size()-1; i++) {
            for (int j = 0; j < routes.size()-2; j++) {
                if(routes.get(i).getFitness() < routes.get(i+1).getFitness()) {
                    Route tmp = routes.get(i);
                    routes.set(i,routes.get(i+1));
                    routes.set(i+1,tmp);
                }
            }
        }
    }

}