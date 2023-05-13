package ma.enset.entites;

import java.util.Random;

public class Individual implements Comparable{
    // chromosome
    private int genes[] = new int[10];
    private int fitness;

    public Individual() {
        for (int i=0 ; i < genes.length ; i++){
            genes[i] = new Random().nextInt(2);
        }
    }

    public void calculateFitness(){
        fitness = 0;
        for (int gene : genes) {
            fitness += gene;
        }

    }

    public int getFitness() {
        return fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    @Override
    public int compareTo(Object o) {
        Individual individual = (Individual) o;
        if (this.getFitness() < ((Individual) o).getFitness()) return -1;
        else if (this.getFitness() > ((Individual) o).getFitness()) return 1;
        return 0;
    }
}
