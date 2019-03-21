package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Calculations {

    final int num_routes = 10;
    int MAX_DISTANCE = 0;
    int best_fit = 0;
    ArrayList<Route> routes = new ArrayList<>();

    Route parent1, parent2;

    Random rand = new Random();

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

            routes.add(new Route(distance, currentRoute));
//            System.out.println();

        }

//        System.out.println();
//        System.out.println("MAX DISTANCE: "+ MAX_DISTANCE);

        //Calculate Fitness
        for (int i = 0; i < routes.size(); i++) {
            int fitness = fit.getFitness(MAX_DISTANCE,routes.get(i).get_distance());
            routes.get(i).setFitness(fitness);
//            System.out.println("Fitness "+fitness);
        }

        sortFittest();
        int n1 = rand.nextInt(routes.size()/4);
        int n2 =  rand.nextInt(routes.size()/4);

        while(n1==n2) {n2 = rand.nextInt(routes.size()/4);}

        parent1 = routes.get(n1);
        parent2 = routes.get(n2);

        Route nextGeneration =  getChild(parent1,parent2);

        mutate(nextGeneration);

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

    public Route getChild(Route p1, Route p2){
        final int size = p2.getPath().length;
        ArrayList temp = new ArrayList();

        //choose 2 random points to split at
        int s1 =  (int)(Math.random() * size-1) + 1;
        int s2 =  (int)(Math.random() * size-1) + 1;


        while(s1==s2) {s2 = rand.nextInt(routes.size()/2);}

        //make the smaller one the start and the larger one the end
        int start = Math.min(s1, s2);
        int end = Math.max(s1, s2);


        int [] c = new int[size];
        int [] elite = new int[end-start];

        int next = end+1;
        //end of loop
        System.out.println("Start "+start+"   End "+p2.getPath()[5]);
        for(int i = 0; i < c.length; i++ ){
            if(i <= start && i >= end) {
                c[i] = p1.getPath()[i];
                elite[i] = p1.getPath()[i];
            }

            else if(i < end) {
                boolean contains = false;
                for (int j = 0; j <elite.length; j++) {
                    if(elite[j] == p2.getPath()[i]){
                        contains = true;
                        break;
                    }
                }
                if(!contains) {
                    c[next] = p2.getPath()[i];
                    next++;
                }
            }
        }

        //start
        for (int i = 0; i <= end; i++) {
            if(next <= size)
                next = 0;
            boolean contains = false;
            for (int j = 0; j <elite.length; j++) {
                if(elite[j] == p2.getPath()[i]){
                    contains = true;
                    break;
                }
            }
            if(!contains) {
                c[next] = p2.getPath()[i];
                next++;
            }
        }

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }

        return null;
    }

    public void mutate(Route r) {
        //choose 2 random points to split at
        int s1 =  (int)(Math.random() * r.getPath().length-1);
        int s2 =  (int)(Math.random() * r.getPath().length-1);

        while(s1==s2) {s2 = rand.nextInt(routes.size()/2);}

        int c1 = r.getPath()[s1];
        int c2 = r.getPath()[s2];

        r.getPath()[s1] = c2;
        r.getPath()[s2] = c1;
    }

}
