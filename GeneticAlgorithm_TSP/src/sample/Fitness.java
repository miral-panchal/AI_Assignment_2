package sample;

import java.util.ArrayList;

public class Fitness {

    double fitness = 0;
    int distance = 0;

    public void calcDistance(int [] r, ArrayList<String []> d) {

        int pos = 0,pos_2 = 0,cost = 0;
        for (int i = 0; i < r.length; i++) {

            if(i == r.length-1) {
                pos = r[i];
                pos_2 =  r[0];
            }
            else {
                pos = r[i];
                pos_2 = r[i+1];
            }
            String [] a = d.get(pos);
            cost = Integer.parseInt(a[pos_2]);

//            System.out.println("1st: "+pos+ " 2nd: "+pos_2+ " COST:" + cost);

            distance += cost;
        }
    }

    double getFitness() {
        if(fitness == 0)
            fitness = 1/(double)distance;
        return fitness;
    }

    public void crossover(int [] r, int [] nr) {

    }

    public void mutation() {

    }




}
