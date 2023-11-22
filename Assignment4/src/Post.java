public class Post{
    private String title;
    private String video;
    private int likes;

    /**
     *  A Constructor that will create a Post object 
     * @param t the title of the video
     * @param v the video format 
     * @param l the number of likes the post has 
     */
    public Post(String t, String v, int l){
        title = t;
        video = v;
        likes = l;
    }

    /**
     * Gets the number of likes of the post 
     * @return the number of likes of the post 
     */
    public int getLikes(){return likes;}
    
     /**
     * Represents the fields of the object as a string 
     * @return the fields of the object represented as a string 
     */
    public String toString(){
        String out ="";
        out = out+"Title: "+title;
        out = out+"\nVideo: "+video;
        out = out+"\nNumber of likes: "+likes+"\n";
        return out;

    }
}