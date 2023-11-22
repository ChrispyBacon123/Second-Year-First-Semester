/**
 * Abstract implementation of hash table storing strings.
 *
 * @author Christopher blignaut 
 *         BLGCHR003
 * @version 15/5/2023
 */
import java.util.*;
import java.io.*;
public class Optomize {
    static int optProbe = Integer.MAX_VALUE;
    static int[] optweight = new int[9];
    static int bestNum = 0;


   
    public static void main(String[] args) {
        //int maxWeight  = 4;

        int[] newWeights = new int[9];

        generateWeight(0, newWeights, 4);
        try{
            PrintWriter writer = new PrintWriter(new File("results.txt"));
            writer.write(optProbe+" "+bestNum);
            writer.close();
            System.out.println("done");
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }



    public static void generateWeight(int index, int[] weights, int maxWeight){

        int totalProbes;

        if(index ==weights.length){
            final HashTable table = new LPHashTable(37);
            table.setWeights(weights.clone());
            totalProbes=0;

            try{
                Scanner scFile = new Scanner(new File("mydata.txt"));
                while(scFile.hasNext()){
                    String key = scFile.nextLine();
                    table.insert(key);
                }
                totalProbes = table.getProbeCount();
            }
            catch(FileNotFoundException e){
                System.out.println(e);
            }

            if(totalProbes<optProbe){
                optProbe = totalProbes;
                bestNum = 1;
            }
            else if(totalProbes ==optProbe){
                bestNum++;
            }
        }
        
        else{
            for(int i=0; i<=maxWeight;i++){
                weights[index]=i;
                generateWeight(index+1, weights, maxWeight);
            }
        }
    }
}