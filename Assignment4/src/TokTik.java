import java.util.*;
import java.io.*;
public class TokTik {
    
    public static void main(String[] args) {
        //Loading in the file
        Account account;
        String command,option,name,video;
        String title="";
        String description="";
        int likes;
        Account acc; 
        Post post;
        boolean quit = false;
        BinarySearchTree <Account> tree = new BinarySearchTree<Account>(); 


        // Accepting user input 
        Scanner scInput = new Scanner(System.in);
        String menu = "\nChoose an action from the menu:\n"+
                      "1. Find the profile description for a given account\n"+  
                      "2. List all accounts\n"+
                      "3. Create an account\n"+
                      "4. Delete an account\n"+
                      "5. Display all posts for a single account\n"+
                      "6. Add a new post for an account\n"+
                      "7. Load a file of actions from disk and process this\n"+
                      "8. Quit\n"+
                      "Enter your choice: ";
        System.out.print(menu);
        String choice=scInput.next();  //Choice is a String to allow for inputs that aren't numbers 
        if (choice.equals("8")){quit=true;}
        while(!quit){

           //Find the profile and output it's description
            if(choice.equals("1")){

                option = "";

                while(!option.equalsIgnoreCase("no")){
                    System.out.print("Enter the account name: ");
                    scInput.nextLine();
                    name = scInput.nextLine();
                    acc = new Account(name);

                    if (tree.find(acc) ==null ) {
                    System.out.println("Account not found\nWant to try again?\nYes or No");
                    option = scInput.next().trim();
                    } 
                    else{
                    System.out.println("\n"+tree.find(acc).data.getDescription()+"\n");
                    option = "no";
                    }
                }   
            }


            //List Accounts
            else if(choice.equals("2")){
                System.out.println("\n");
                tree.inOrder();
                System.out.println();
            }
            
            //Create Account  
            else if(choice.equals("3")){
                option = "";

                while(!option.equalsIgnoreCase("no")){
                    System.out.println("Enter new account name\nPlease ensure you leave no space at the end");
                    scInput.nextLine();
                    String accName = scInput.nextLine();
                    account = new Account(accName);
                    if(tree.find(account) == null){
                        System.out.println("Enter desc");
                        String desc = scInput.nextLine();
                        tree.insert(new Account(accName,desc));
                        System.out.println("\nThe account "+accName+" "+desc+" has just been created\n");
                        option = "no";
                    }
                    else{
                        System.out.println("An account already exists with that name\nWant to try again?\nYes or No");
                        option = scInput.next().trim();
                            }


                   
                    
                }
            }

            //Delete Account add while loop
            else if(choice.equals("4")){
                option = "";

                while(!option.equalsIgnoreCase("no")){
                    
                    System.out.println("Enter the name of the account which you would like to delete:");
                    scInput.nextLine();
                    name = scInput.nextLine();
                    account = new Account(name);
                    if (tree.find(account) == null) {
                        System.out.println("There is no account with that name\nWant to try again?\nYes or No");
                        option = scInput.next().trim();
                    } 
                    else {
                        System.out.println("Are you sure you wish to delete "+name+"?\nYes or no");
                        if (scInput.nextLine().trim().equalsIgnoreCase("yes")) {
                            tree.delete(tree.find(account).data);
                            System.out.println("Deleted the account with the name: "+ name);
                            option = "no";
                        } 
                        else {
                            option = "no";
                        }
                    }
            }

            }

            //Find the profile and output all it's posts
            else if(choice.equals("5")){
                option = "";

                while(!option.equalsIgnoreCase("no")){
                    System.out.print("Enter the account name: ");
                    name = scInput.next();
                    acc = new Account(name);

                    if (tree.find(acc) ==null ) {
                    System.out.println("Account not found\nWant to try again?\nYes or No");
                    option = scInput.next().trim();
                    } 
                    else{
                    System.out.println("\n\n"+tree.find(acc).data.getPosts()+"\n");
                    option = "no";
                    }
                }   
            }
            
            //Add post to account add while loop
            else if(choice.equals("6")){

                option = "";

                while(!option.equalsIgnoreCase("no")){
                    
                    System.out.println("To which account do you want to add a post?");
                    scInput.nextLine();
                    name=scInput.nextLine().trim();
                    account = new Account(name);

                    if (tree.find(account) == null) {
                        System.out.println("Account not found\nWant to try again?\nYes or No");
                        option = scInput.next().trim();
                    } 
                    else {
                        System.out.println("Enter the title of the video:");
                        title = scInput.nextLine();
                        System.out.println("Enter the video format:");
                        video = scInput.nextLine();
                        System.out.println("Enter the number of likes that the video has:");
                        try{
                        likes = scInput.nextInt();
                        tree.find(account).data.addPost(new Post(title,video,likes));
                        option = "no";
                        }
                        catch(InputMismatchException e){
                            System.out.println("Error, number entered is not a whole number");
                            option = "yes";
                        }
                    }
                    
            }
         }

            //Load file of actions
            else if(choice.equals("7")){
                try{
                    Scanner scFile= new Scanner(new File("dataset.txt"));
                   
                    Scanner scLine;
                    while (scFile.hasNextLine()){
                        scLine = new Scanner(scFile.nextLine()).useDelimiter(" ");
                        command = scLine.next();
            
                        if (command.equals("Create")) {
                            name = scLine.next();
                            description=scLine.nextLine().trim();
                            account = new Account(name, description);
                            if(tree.find(account) == null){
                            tree.insert(account);
                            }
                        } 
                        
                        else {
                            name = scLine.next();
                            video = scLine.next();
                            likes = scLine.nextInt();
                            title = scLine.nextLine();
                            title.trim();
                            acc = new Account(name);
                            post = new Post(title, video, likes);
            
                            //Ensures that application can crash gracefully 
                            if (tree.find(acc).data != null) {
                                tree.find(acc).data.addPost(post);
                            } 
                            
                        }
            
                    }
            
                    scFile.close();
                    System.out.println("File loaded");
                    }
                    catch (FileNotFoundException e){
                        System.out.println("File not found ");
                    }
                    
            }
           
            //Quit
            else if(choice.equals("8")){
                quit = true;
            }
            else {
                System.out.println("Invalid input, please enter one of the options");
            }
            if(choice.equals("8")){
                System.out.println("Goodbye!\nHave a nice day :)");
                quit = true;}


            if(!quit){
                System.out.print(menu);
                choice=scInput.next();
            }
        }
        scInput.close();
    }

}
