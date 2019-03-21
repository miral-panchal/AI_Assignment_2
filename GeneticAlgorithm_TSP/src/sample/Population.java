package sample;

import java.util.Random;

public class Population {

    private String [] cities;
    private int [] routes;

    Population(String[] cities) {
        this.cities = cities;
    }

    public int [] calcRoute() {
        Random rand = new Random();
        int num_cities = cities.length;
        routes = new int[num_cities];

        for (int i = 0; i < routes.length; i++) {
            routes[i] = -1;
        }

        int i = 0;
        do {
            // Obtain a number between [0 - number of cities] to get random path.
            int n = rand.nextInt(num_cities);
            if(check(n,routes)) {               //if the next selected number is not in our list add it
                routes[i] = n;
                i++;
            }
        }while(i < num_cities);

        return routes;
    }

    private boolean check(int num, int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == num)
                return false;
        }
        return true;
    }

}
