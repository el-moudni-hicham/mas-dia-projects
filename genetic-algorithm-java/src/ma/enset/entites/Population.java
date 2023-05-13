package ma.enset.entites;

import java.util.*;

public class Population {
    ArrayList<Individual> individuals = new ArrayList<>();
    Individual firstFitness;
    Individual secondFitness;
    private static int populaion = 30;

    Random random = new Random();

    public void initializePopulation(){
        for (int i = 0; i < populaion ; i++) {
            individuals.add(new Individual());
        }
    }

    public void calculateIndividualFitnessValue(){
        for (int i = 0; i < populaion; i++) {
            individuals.get(i).calculateFitness();
        }
    }

    public void sortPoplation(){
        Collections.sort(individuals, Collections.reverseOrder());
    }
    public void selection(){
        firstFitness = individuals.get(0);
        secondFitness = individuals.get(1);
    }

    public void crossover(){
        int crossoverPoint = 1 + new Random().nextInt(9);

        Individual individual1 = new Individual();
        Individual individual2 = new Individual();

        for (int i = 0; i < individual1.getGenes().length; i++) {
            individual1.getGenes()[i] = firstFitness.getGenes()[i];
            individual2.getGenes()[i] = secondFitness.getGenes()[i];
        }

        for (int i = 0; i < crossoverPoint; i++) {
            individual1.getGenes()[i] = secondFitness.getGenes()[i];
            individual2.getGenes()[i] = firstFitness.getGenes()[i];
        }

        individuals.set(individuals.size()-2, individual1);
        individuals.set(individuals.size()-1, individual2);

        //System.out.println("Crossover Point : " + crossoverPoint);
    }

    public void mutation(){
        int index = random.nextInt(10);
        if(individuals.get(individuals.size()-2).getGenes()[index] == 1)
            individuals.get(individuals.size()-2).getGenes()[index] = 0;
        else individuals.get(individuals.size()-2).getGenes()[index] = 1;

        index = random.nextInt(10);
        if(individuals.get(individuals.size()-1).getGenes()[index] == 1)
            individuals.get(individuals.size()-1).getGenes()[index] = 0;
        else individuals.get(individuals.size()-1).getGenes()[index] = 1;
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public Individual getBestIndividual(){
        return individuals.get(0);
    }
}
