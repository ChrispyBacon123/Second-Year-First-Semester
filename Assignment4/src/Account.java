import java.util.ArrayList;

public class Account implements Comparable<Account> {
    private String name;
    private String description; 
    private ArrayList<Post> posts = new ArrayList<Post>();
    private int count = 0;

    /**
     * Constructor to make an Account object 
     * @param n the name of the account 
     * @param d the decription of the account 
     */
    public Account(String n, String d){
        name=n;
        description=d;
    }

    /**
     * Constructor to make an Account object to be used to search the binary tree 
     * @param n the name of the account 
     */
    public Account(String n){
        name=n;
    }


    /**
     * Gets the name of the account
     * @return the name of the account
     */
    public String getName(){return name;}
    
    /**
     * Gets the decription of the account 
     * @return the description of the account 
     */
    public String getDescription(){return "The profile description is: "+description;}

    /**
     * Changes the description of the account 
     * @param d the new description of the account
     */
    public void setDescription(String d){description=d;}

    /**
     * Changes the name of the account 
     * @param n the new name of the account 
     */
    public void setName(String n){name=n;}

    /**
     * Returns all the posts made by the account 
     * @return all the posts made by the account as a string 
     */
    public String getPosts(){
        String out ="";
        for(int i=0; i<count;i++){
            out = out+posts.get(i).toString()+"\n";
        }
        return out;
    }

    /**
     * Adds a post to the Account object 
     * @param p the post to be added 
     */
    public void addPost(Post p){
        posts.add(p);
        count++;
    }

     /**
     * Compares two accounts using their usernames 
     * @param other the other account to be compared with
     * @return 0 if the account usernames are the same, 1 if the username is alphabetically earlier than the other 
     *         and -1 if the username is alphabetically after the other
     */
    public int compareTo(Account other){
        return name.compareTo(other.getName());
    }

     /**
     * Returns the fields of the account object as a string  
     * @return the fields of the account object as a string
     */
    public String toString(){
            int likes = 0;
            for(int i=0; i<posts.size();i++){
                likes = likes + posts.get(i).getLikes();
            }
            return name+"\n"+description+" who has "+posts.size()+" posts and "+likes+" likes";
        
    }
}