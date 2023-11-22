import java.io.*;
import java.util.*;
public class GraphGenerator {

    public static final String[] LETTERS = {"A","B","C","D","E","F","G","H","I","J",
                                            "K","L","M","N","O","P","Q","R","S","T",
                                            "U","V","W","X","Y","Z"};


    /**
     * Randomly generates the edges of a graph based on the graph in the form: "[Edge1] [Edge2] [Weight]"
     * @param numVert the number of vertices in the graph
     * @param numEdge the number of edges in the graph 
     * @return a string of all the edges
     */
    public static String generateConnections(int numVert, int numEdge){
        if(numVert>numEdge){
            System.out.println("The number of verticies (nodes) of the graph must be less than the number of edges in the graph");
            return "";
        }
        int count=0;
        int letterCount=0;
        int randNum;
        String output="";
        String line="";
        String vert1="",vert2=" ";
        String checkVert1, checkVert2;
        Scanner scOut,scCheck;
        boolean same = false, exit = false;
        

        //Ensuring that all nodes have at least one connection in the graph 
        for(int i=0; i<(numVert-1);i++){
            if(i%3 ==0 ){
                letterCount++;
            }
            if(i==0){
                letterCount--;
            }
           
            vert1 = LETTERS[letterCount];
            vert2 = LETTERS[i+1];
            randNum = (int)(Math.random()*10)+1;
            line = vert1+" "+vert2+" "+randNum;
            if(i>0){
                scOut = new Scanner(output);

                for (int j=0; j<count; j++){
                    scCheck = new Scanner(scOut.nextLine());
                    checkVert1 = scCheck.next();
                    checkVert2 = scCheck.next();
                    if(vert1.equals(checkVert1) && vert2.equals(checkVert2)){
                        same = true;
                        j=count;
                    }
                }

            }
            if(!same){
                output=output+line+"\n";
                count++;
            }
            same = false;
        }



        //Generating remainder of lines 
        while(count<numEdge){
         //Generating edges 
         randNum = (int)(Math.random()*numVert);
         vert1 = LETTERS[randNum];
         randNum = (int)(Math.random()*numVert);
         vert2 = LETTERS[randNum];
       
         //Checking to make sure edge does not lead to itself
         if(!vert1.equals(vert2)){
             exit = true;
         }

        //Generating weight + Line
         if(exit){
            randNum = (int)(Math.random()*10)+1;
            line = vert1+" "+vert2+" "+randNum;
        //Checking to see if edge has been created before
        scOut = new Scanner(output);
        for (int i=0; i<count; i++){
            scCheck = new Scanner(scOut.nextLine());
            checkVert1 = scCheck.next();
            checkVert2 = scCheck.next();
            if(vert1.equals(checkVert1) && vert2.equals(checkVert2)){
                same = true;
                i=count;
            }
        }
        if(!same){
            output=output+line+"\n";
            count++;
        }
        same = false;
            }
        exit= false;
        }
        output += "---------------------------\nThe number of vertices in this graph is "+numVert+"\nThe number of edges in this graph is "+numEdge;
        return output;
    }


/**
 * Creates a file and writes the graph edges to it
 * @param num the number of the file to be generated 
 * @param output the data to be wirtten to the textfile 
 */
    public static void writeFile(int num, String output){
    try{
        File file = new File("data/Graph"+num+".txt");
        FileWriter out = new FileWriter(file);
        out.write(output.trim());
        out.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
}



/**
 * Appends the results text file with the results of the experiment
 * @param result the summarized results of the experiment
 */
public static void writeResult(String result,String fileName){
    try{
        FileWriter out = new FileWriter("data/"+fileName+".txt",true);
        out.write(result);
        out.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
}



}
