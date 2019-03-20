package sample;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Calculations {

    final int num_routes = 10;
    int MAX_FITNESS = 0;
    ArrayList<int []> valid_routes = new ArrayList<>();

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

        for (int i = 0; i < num_routes; i++) {
            currentRoute = pop.calcRoute();

            Fitness fit = new Fitness();
            fit.calcDistance(currentRoute, distances);

            double fitness = fit.getFitness();

            //Selection
            if(fitness <= MAX_FITNESS)
                valid_routes.add(currentRoute);
        }
    }
}
