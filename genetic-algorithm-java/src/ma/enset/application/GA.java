package ma.enset.application;

import ma.enset.entites.Individual;
import ma.enset.entites.Population;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GA {
    private static final int MAX_IT = 50;
    private static final int MAX_FITNESS = 10;

    public static void main(String[] args) {
        Population population = new Population();
        population.initializePopulation();
        population.calculateIndividualFitnessValue();
        population.sortPoplation();

        population.getIndividuals().forEach(i -> {
            System.out.println("Chromosome : " + Arrays.toString(i.getGenes()) + " --> Fitness Value : " + i.getFitness());
        });

        System.out.println("--------- After Selection and Crossover and Mutation Operations ---------");

        int it = 0;
        while (it<MAX_IT && population.getBestIndividual().getFitness()<MAX_FITNESS) {
            population.selection();
            population.crossover();

            if (new Random().nextInt(101) < 50)
                population.mutation();

            population.calculateIndividualFitnessValue();
            population.sortPoplation();
            System.out.println("Best Individual --> " +
                    " Chromosome : " + Arrays.toString(population.getBestIndividual().getGenes()) +
                    " Fitness Value : " + population.getBestIndividual().getFitness());
        }
        it ++;
    }
}
