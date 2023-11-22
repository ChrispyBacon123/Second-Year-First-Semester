/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;


// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
//                              --> Add additional edge
// void printPath( String w )   --> Print path after alg is run
// void unweighted( String s )  --> Single-source unweighted
// void dijkstra( String s )    --> Single-source weighted
// void negative( String s )    --> Single-source negative weighted
// void acyclic( String s )     --> Single-source acyclic
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm.  Exceptions are thrown if errors are detected.

public class Graph
{
    public static final double INFINITY = Double.MAX_VALUE;
    Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
    private int v_count =0;
    private int pq_count=0;
    private int e_count=0;

    /**
     * Add a new edge to the graph.
     */
    public void addEdge( String sourceName, String destName, double cost )
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adj.add( new Edge( w, cost ) );
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPath( String destName )
    {
        Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            System.out.print( "(Cost is: " + w.dist + ") " );
            printPath( w );
            System.out.println( );
        }
    }

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    private void printPath( Vertex dest )
    {
        if( dest.prev != null )
        {
            printPath( dest.prev );
            System.out.print( " to " );
        }
        System.out.print( dest.name );
    }
    
    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }

    /**
     * Single-source unweighted shortest-path algorithm.
     */
    public void unweighted( String startName )
    {
        clearAll( ); 

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        Queue<Vertex> q = new LinkedList<Vertex>( );
        q.add( start ); start.dist = 0;

        while( !q.isEmpty( ) )
        {
            Vertex v = q.remove( );

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                if( w.dist == INFINITY )
                {
                    w.dist = v.dist + 1;
                    w.prev = v;
                    q.add( w );
                }
            }
        }
    }

    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra) 
     * using priority queues based on the binary heap
     */
    public void dijkstra( String startName )
    {
        PriorityQueue<Path> pq = new PriorityQueue<Path>( );

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        clearAll( );
        pq.add( new Path( start, 0 ) ); start.dist = 0;
        
        int nodesSeen = 0;
        while( !pq.isEmpty( ) && nodesSeen < vertexMap.size( ) )
        {
            //Priority queue is being processed
            pq_count = pq_count + (int)(Math.log(pq.size())/Math.log(2));
            Path vrec = pq.remove( );
            Vertex v = vrec.dest;
            if( v.scratch != 0 )  // already processed v
                continue;

            // vertex is being processed
            v.scratch = 1;
            nodesSeen++;
            v_count += 1;
            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;
                
                if( cvw < 0 )
                    throw new GraphException( "Graph has negative edges" );
                    
                      //Edge is being processed 
                      e_count = e_count+1;
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist +cvw;
                    w.prev = v;

                     //Priority queue is being processed
                    pq.add( new Path( w, w.dist ) );
                    pq_count = pq_count + (int)(Math.log(pq.size())/Math.log(2));
                }
            }
        }
    }

    /**
     * Single-source negative-weighted shortest-path algorithm.
     * Bellman-Ford Algorithm
     */
    public void negative( String startName )
    {
        clearAll( ); 

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        Queue<Vertex> q = new LinkedList<Vertex>( );
        q.add( start ); start.dist = 0; start.scratch++;

        while( !q.isEmpty( ) )
        {
            Vertex v = q.remove( );
            if( v.scratch++ > 2 * vertexMap.size( ) )
                throw new GraphException( "Negative cycle detected" );

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;
                
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                      // Enqueue only if not already on the queue
                    if( w.scratch++ % 2 == 0 )
                        q.add( w );
                    else
                        w.scratch--;  // undo the enqueue increment    
                }
            }
        }
    }

    /**
     * Single-source negative-weighted acyclic-graph shortest-path algorithm.
     */
    public void acyclic( String startName )
    {
        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        clearAll( ); 
        Queue<Vertex> q = new LinkedList<Vertex>( );
        start.dist = 0;
        
          // Compute the indegrees
		Collection<Vertex> vertexSet = vertexMap.values( );
        for( Vertex v : vertexSet )
            for( Edge e : v.adj )
                e.dest.scratch++;
            
          // Enqueue vertices of indegree zero
        for( Vertex v : vertexSet )
            if( v.scratch == 0 )
                q.add( v );
       
        int iterations;
        for( iterations = 0; !q.isEmpty( ); iterations++ )
        {
            Vertex v = q.remove( );

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;
                
                if( --w.scratch == 0 )
                    q.add( w );
                
                if( v.dist == INFINITY )
                    continue;    
                
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                }
            }
        }
        
        if( iterations != vertexMap.size( ) )
            throw new GraphException( "Graph has a cycle!" );
    }

    /**
     * Process a request; return false if end of file.
     */
    public static boolean processRequest( Scanner in, Graph g )
    {

            try
            {
                System.out.print( "Start node: " );
                // String startName = in.nextLine();
                String startName = Graph.generateInput(g.vertexMap.size());
                System.out.println(startName);
                if(startName.equals("x")){System.exit(0);}
                
                System.out.print( "Destination node: " );
                String destName = Graph.generateInput(g.vertexMap.size());
                while(startName.equals(destName)){
                    destName = Graph.generateInput(g.vertexMap.size()-1);
                }
                System.out.println(destName);
                
                    g.dijkstra( startName );
                    g.printPath( destName );
            }
        
        catch( NoSuchElementException e )
          { return false; }
        catch( GraphException e )
          { System.err.println( e ); }
        return true;
    }

    public static void main(String [ ] args )
    {
        int edges=0;

        //Redirecting output of terminal into text file
        try{
     //Instantiating the File class
      File file = new File("data/Data.txt");
      //Instantiating the PrintStream class
      PrintStream stream = new PrintStream(file);
      System.setOut(stream);
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        try
        {   
            String outputLine = "";
            for(int i=1;i<=25;i++){
                Graph g = new Graph( );
                FileReader fin = new FileReader("data/Graph"+i+".txt");
                System.out.println("Graph"+i+":");
                Scanner graphFile = new Scanner( fin );

                // Read the edges and insert
                String line;
                while( graphFile.hasNextLine( ) )
                {
                    line = graphFile.nextLine( );
                    StringTokenizer st = new StringTokenizer( line );

                    try
                    {
                        if( st.countTokens( ) != 3 )
                        {
                            if(st.nextToken().equals("The")){
                                Scanner scLine = new Scanner(line);
                                scLine.next();
                                scLine.next();
                                scLine.next();
                                
                                if(scLine.next().equals("edges")){
                                    scLine.next();
                                    scLine.next();
                                    scLine.next();
                                    scLine.next();
                                    edges = scLine.nextInt();
                                }
                            }
                            continue;
                        }
                        String source  = st.nextToken( );
                        String dest    = st.nextToken( );
                        int    cost    = Integer.parseInt( st.nextToken( ) );
                        g.addEdge( source, dest, cost );
                    }
                    catch( NumberFormatException e )
                    {System.out.println(e);}
                }
                //Formatting for data 
                System.out.println(g.vertexMap.size( )+" vertices");
                System.out.println(edges + " edges\n");

                for(int j=0; j<20; j++){

 

                Scanner in = new Scanner( System.in );
                processRequest( in, g );
               
            }

            //Formatting of data 
            if (g.getE_count()<1000) {
                
                outputLine = ""+g.vertexMap.size()+","+edges+","+g.getV_count()+","+g.getE_count()+","+g.getPQ_count()+"\n";
            } else 
            {
                 outputLine = ""+g.vertexMap.size()+","+edges+","+g.getV_count()+","+g.getE_count()+","+g.getPQ_count()+"\n";
            }
            
            GraphGenerator.writeResult(outputLine,"Results");
            //Formatting for data 
            System.out.println("----------------------------------------------------\n\n");
        }
        
        }
         catch( IOException e )
           { System.err.println( e ); }
    }


    /**
     * Generates a letter to serve as a vertex to be inputted into the dijkstra algorithm
     * @param vert the number of verticies in the graph
     * @return a letter to serve as a vertex in the graph
     */
    private static String generateInput(int vert){
        String[] letters = {"A","B","C","D","E","F","G","H","I","J",
                            "K","L","M","N","O","P","Q","R","S","T",
                            "U","V","W","X","Y","Z"};
        int randNum = (int)(Math.random()*vert);
        String vert1 = letters[randNum];
        return vert1;
    }

    //Getters required to get the following variables in the main method 
    public int getV_count(){return v_count/20;}
    public int getE_count(){return e_count/20;}
    public int getPQ_count(){return pq_count/20;}


}
