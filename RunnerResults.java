/*
 * Created by: Joshua Guinness
 * On November 20, 2016
 * To be able to effectively catologue runners and their statistics
 */
package runner.results;

// Imports
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunnerResults {

    // Method which imports the runners and their stats from a textfile
    public static ArrayList<Runners> importRunners() {

        // Creates a new array list of object Runners
        ArrayList<Runners> runners = new ArrayList<>();

        // Creates a new file to read
        File textfile = new File("RunnersDatabase.txt");

        try {

            // Adds everyline of the text file to the runners array list
            Scanner file = new Scanner(textfile);
            while (file.hasNextLine()) {

                runners.add(new Runners(file.next(), file.next().charAt(0), file.nextInt(), file.nextDouble()));
            }
        } 

        // Catch statement
        catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

        return runners;
    }

    // Method to export the runners array list back to the text file
    public static void exportRunners(ArrayList<Runners> runners) {

        try {
            
            // Creates new writers
            FileWriter file = new FileWriter("RunnersDatabase.txt");
            BufferedWriter output = new BufferedWriter(file);
            
            /* Outputs every element in the runners array list to the textfile,
            closing the file after completion */
            for (int i = 0; i < runners.size() - 1; i++) {
                output.write(runners.get(i).getInfo());
                output.newLine();
            }
            output.write(runners.get(runners.size() - 1).getInfo());
            output.close();
            file.close();
        } 
        
        catch (IOException e) {
        }
    }
    
    // Method to print off the runners array list
    public static ArrayList<Runners> print(ArrayList<Runners> runners) {

        for (int i = 0; i < runners.size(); i++) {
            System.out.println(runners.get(i).getInfo());
        }

        return runners;
    }
    
    // Method to print off the runners array list with numbers in front of each element
    public static ArrayList<Runners> printWithNumbers(ArrayList<Runners> runners) {

        for (int i = 0; i < runners.size(); i++) {
            System.out.println(i + 1 + ". " + runners.get(i).getInfo());
        }

        return runners;
    }
    
    // Main menu
    public static ArrayList<Runners> menu(ArrayList<Runners> runners) {
        
        //
        System.out.println("Welcome to the 100 metre race runners database. Please select an option.");

        System.out.println("\nRunner Database Menu: ");
        System.out.println("\n1. Display Database");
        System.out.println("2. Add to database");
        System.out.println("3. Remove from database");
        System.out.println("4. Change info");
        System.out.println("5. Sort");
        System.out.println("6. Search");
        System.out.println("7. Organize by Sex");
        System.out.println("0. Close\n");

        try {
            
            // Takes the user input and runs the method they pick
            Scanner kb = new Scanner(System.in);
            int menuInput = kb.nextInt();
            
            switch (menuInput) {
                case 0:
                    close(runners);
                    break;
                case 1:
                    display(runners);
                    break;
                case 2:
                    add(runners);
                    break;
                case 3:
                    remove(runners);
                    break;
                case 4:
                    changeInfo(runners);
                    break;
                case 5:
                    sort(runners);
                    break;
                case 6:
                    search(runners);
                    break;
                case 7:
                    organize(runners);
                    break;
                default:
                    
                    // Outputs instructions and reruns the method
                    System.out.println("Please enter a value between 0-6");
                    System.out.println();
                    menu(runners);
                    break;
            }

        } 
        
        // Outputs instructions and reruns the method
        catch (InputMismatchException e) {

            System.out.println("Please enter a value between 0-6");
            System.out.println();
            menu(runners);
        }

        return runners;
    }

    // Method to run an end menu after the user is deciding to quit
    public static ArrayList<Runners> endingMenu(ArrayList<Runners> runners) {

        System.out.println("\nWould you like to: ");
        System.out.println("\n1. Close.");
        System.out.println("2. Go to the menu.");
        
        // Runs a method to either close the program, or go to the menu, based on a user choice
        try {
            
            Scanner kb = new Scanner(System.in);
            int userChoice1 = kb.nextInt();

            switch (userChoice1) {
                case 1:
                    close(runners);
                    break;
                case 2:
                    menu(runners);
                    break;
                default:
                    
                    //Outputs instructions and reruns the method
                    System.out.println("\nPlease enter either 1 or 2");
                    endingMenu(runners);
                    break;
            }
        } 
        
        // Outputs instructions and reruns the method
        catch (InputMismatchException e) {
            System.out.println("Please enter either 1 or 2");
            System.out.println();
            endingMenu(runners);
        }
        
        return runners;
    }

    // Method to close the program
    public static ArrayList<Runners> close(ArrayList<Runners> runners) {

        System.out.println("\nWould you like to export before you close?");
        System.out.println("\n1. Yes");
        System.out.println("2. No");
        
        // Either exports and exists, or exits based on user's decision
        try {

            Scanner kb = new Scanner(System.in);
            int choice = kb.nextInt();

            switch (choice) {
                case 1:
                    exportRunners(runners);
                    System.exit(0);
                case 2:
                    System.exit(0);
                default:
                    
                    //Outputs instructions and reruns method
                    System.out.println("\nPlease enter either 1 or 2");
                    close(runners);
                    break;
            }
        } 
        
        // Outputs instructions and reruns method
        catch (InputMismatchException e) {
            System.out.println("Please enter either 1 or 2");
            System.out.println();
            close(runners);
        }
        return runners;
    }
    
    // Method to display the runners array list
    public static ArrayList<Runners> display(ArrayList<Runners> runners) {
        
        // Prints off the runners array list
        print(runners);

        // Goes to ending menu
        endingMenu(runners);
        
        return runners;
    }
    
    // Method to add to the runners array list
    public static ArrayList<Runners> add(ArrayList<Runners> runners) {
        
        // Gets what the user is entering for each category and adds it to the array
        try {
            
            Scanner kb = new Scanner(System.in);

            System.out.print("\nName: ");
            String name = kb.nextLine();

            System.out.print("Sex: ");
            char sex = kb.nextLine().charAt(0);

            System.out.print("Age: ");
            int age = kb.nextInt();

            System.out.print("Time: ");
            double time = kb.nextDouble();

            runners.add(new Runners(name, sex, age, time));

            endingMenu(runners);
        } 
        
        // Outputs Instructions and reruns the method
        catch (InputMismatchException e) {
            System.out.println("Please enter a correct input");
            System.out.println();
            add(runners);
        }
        
        return runners;
    }
    
    // Method to remove an element from the runners array list
    public static ArrayList<Runners> remove(ArrayList<Runners> runners) {

        try {
            
            // Prints off the runners array list with numbers
            System.out.println("\nEnter the numeber of the entry you would like to remove\n");
            printWithNumbers(runners);
            
            // Gets the user choice of which element to remove
            Scanner kb = new Scanner(System.in);
            int toRemove = kb.nextInt();
            
            // Removes the element if it matches one of the numbers listed
            if(toRemove <= runners.size() && toRemove >= 1){
                runners.remove(toRemove - 1);    
            }
            else{
                System.out.println("Please enter a number from 1 - " + runners.size());
                remove(runners);
            }
            
            endingMenu(runners);
        } 
        
        // Outputs instructions and reruns the method
        catch (InputMismatchException e) {
            System.out.println("Please enter a number from 1 - " + runners.size());
            System.out.println();
            remove(runners);
        }
        
        return runners;
    }

    // Method to change one of the elements of the runners array list
    public static ArrayList<Runners> changeInfo(ArrayList<Runners> runners) {

        try {
            
            // Prints off the runners array list with numbers 
            System.out.println("\nEnter the numeber of the entry you would like to change\n");
            printWithNumbers(runners);
            
            /* Ensures the number of the element to change being entered by the user
            is within the range of the runners array */
            Scanner kb = new Scanner(System.in);
            int toChange = kb.nextInt();
            
            if (toChange <= runners.size() && toChange >= 1){
                
            }
            else{
                System.out.println("Please enter a number between 1 - " + runners.size());
                changeInfo(runners);
            }
            
            // User picks which part of the object they would like to change
            System.out.println("Enter the number of the one you would like to change");

            System.out.println("1. " + runners.get(toChange - 1).getName());
            System.out.println("2. " + runners.get(toChange - 1).getSex());
            System.out.println("3. " + runners.get(toChange - 1).getAge());
            System.out.println("4. " + runners.get(toChange - 1).getTime());

            int toChangeInfo = kb.nextInt();
            
            try {
                
                /* User enters the information that is to be changed, the change occurs, 
                and a message is outputed */
                switch (toChangeInfo) {
                    case 1:
                        System.out.println("Please enter the new name");
                        String newName = kb.next();
                        runners.get(toChange - 1).changeName(newName);
                        System.out.println("\nChanged.");
                        break;
                    case 2:
                        System.out.println("Please enter the new sex");
                        char newSex = kb.next().charAt(0);
                        runners.get(toChange - 1).changeSex(newSex);
                        System.out.println("\nChanged.");
                        break;
                    case 3:
                        System.out.println("Please enter the new age");
                        int newAge = kb.nextInt();
                        runners.get(toChange - 1).changeAge(newAge);
                        System.out.println("\nChanged.");
                        break;
                    case 4:
                        System.out.println("Please enter the new time");
                        double newTime = kb.nextDouble();
                        runners.get(toChange - 1).changeTime(newTime);
                        System.out.println("\nChanged.");
                        break;
                    default:
                        System.out.println("Please enter a number between 1 - 4");
                        System.out.println();
                        changeInfo(runners);
                        break;
                }
            } 
            
            // Outputs instructions and reruns the method
            catch (InputMismatchException e) {
                System.out.println("Please enter a correct input");
                System.out.println();
                changeInfo(runners);
            }

            endingMenu(runners);
        } 
        
        // Outputs instructions and reruns the method
        catch (InputMismatchException e) {
            System.out.println("Please enter a number between 1-4");
            System.out.println();
            changeInfo(runners);
        }

        return runners;
    }

    // Method to sort the runners array list
    public static ArrayList<Runners> sort(ArrayList<Runners> runners) {

        try {
            
            // Prompts user for what category they would like to sort by
            System.out.println("What would you like to sort by?");

            System.out.println("\n1. Alphabetical");
            System.out.println("2. Sex");
            System.out.println("3. Age");
            System.out.println("4. Time");

            Scanner kb = new Scanner(System.in);
            int sortChoice = kb.nextInt();

            // Sorts the runners array list depending on category selected by user
            switch (sortChoice) {
                case 1:

                    /* Loop to restart the loop below it at the beginning of the array
                    so that it runs through the array as many times as needed */
                    for (int i = 0; i < runners.size() - 1; i++) {
                        
                        /* Compares two elements in the runners array and places the one
                        with the first letter that occurs later in the alphabet as far 
                        back in the array as possible */
                        for (int j = 0; j < runners.size() - 1; j++) {
                           
                            if (runners.get(j).getName().compareTo(runners.get(j + 1).getName()) > 0) {

                                Collections.swap(runners, j, j + 1);
                            }
                        }
                    }
                    print(runners);
                    break;

                case 2:

                    /* Loop to restart the loop below it at the beginning of the array
                    so that it runs through the array as many times as needed */
                    for (int i = 0; i < runners.size() - 1; i++) {
                        
                        /* Compares two elements in the runners array and places the one
                        with the first letter that occurs later in the alphabet as far 
                        back in the array as possible */
                        for (int j = 0; j < runners.size() - 1; j++) {

                            if (runners.get(j).getSex().compareTo(runners.get(j + 1).getSex()) > 0) {

                                Collections.swap(runners, j, j + 1);
                            }
                        }
                    }
                    
                    // Reverses the runners arra
                    Collections.reverse(runners);
                    print(runners);

                    break;

                case 3:

                    /* Loop to restart the loop below it at the beginning of the array
                    so that it runs through the array as many times as needed */
                    for (int i = 0; i < runners.size() - 1; i++) {

                        /* Compares every number in an array placing the largest number as far 
                        back in the array as possible */
                        for (int j = 0; j < runners.size() - 1; j++) {

                            if (runners.get(j).getAge() > runners.get(j + 1).getAge()) {

                                Collections.swap(runners, j, j + 1);
                            }
                        }

                    }
                    printWithNumbers(runners);

                    break;

                case 4:

                    /* Loop to restart the loop below it at the beginning of the array
                    so that it runs through the array as many times as needed */
                    for (int i = 0; i < runners.size() - 1; i++) {

                        /* Compares every number in an array placing the largest number as far 
                        back in the array as possible */
                        for (int j = 0; j < runners.size() - 1; j++) {

                            if (runners.get(j).getTime() > runners.get(j + 1).getTime()) {

                                Collections.swap(runners, j, j + 1);
                            }
                        }
                    }
                    printWithNumbers(runners);
                    break;
                default:
                    
                    // Ouputs instructions and reruns the array
                    System.out.println("Please a number between 1 - 4");
                    System.out.println();
                    sort(runners);
                    break;

            }
            endingMenu(runners);
        } 
        
        // Ouputs insturctions and rerun the array
        catch (InputMismatchException e) {
            System.out.println("Please a number between 1 - 4");
            System.out.println();
            sort(runners);
        }

        return runners;
    }

    // Method to search for an element in the runners array list
    public static ArrayList<Runners> search(ArrayList<Runners> runners) {

        try {
            
            // Prompts a user for a decsion of what they would want to search by
            System.out.println("What would you like to search by:");
            System.out.println("\n1. Name");
            System.out.println("2. Sex");
            System.out.println("3. Age");
            System.out.println("4. Time");

            Scanner kb = new Scanner(System.in);
            int choice = kb.nextInt();
            
            // Creates a new array list where positively searched stuff is added
            ArrayList<Runners> runnersTemp = new ArrayList<>();
            try {
                
                // Searches a specific category based on user choice
                switch (choice) {

                    case 1:
                        
                        // Gets the user search and places it in a variable
                        System.out.println("Enter the name you are searching for");
                        String searchOne = kb.next();

                        /* Compares the elements in the runners array list to the 
                        to the input by the user, adding the element to temporary array list 
                        if they are the same */
                        for (int i = 0; i < runners.size() - 1; i++) {

                            if ((runners.get(i).getName()).equals(searchOne)) {

                                runnersTemp.add(runners.get(i));

                            } 
                            else {

                            }
                        }
                        
                        // Outputs a message if nothing is found matching that input
                        if(runnersTemp.size() == 0){
                            System.out.println("\nThere is nothing found for your search.");
                        }
                        else{
                            
                            /* Prints out the temporary array list with all the elements 
                            found by the search */
                            System.out.println("\nHere is what we have found:");
                            System.out.println();
                            print(runnersTemp);
                        } 
                        
                        break;
                        
                    case 2:
                        
                        // Gets the user search and places it in a variable
                        System.out.println("Enter the sex you are searching for");
                        String searchTwo = kb.next();

                        /* Compares the elements in the runners array list to the 
                        to the input by the user, adding the element to temporary array list 
                        if they are the same */
                        for (int i = 0; i < runners.size() - 1; i++) {

                            if ((runners.get(i).getSex()).equals(searchTwo)) {

                                runnersTemp.add(runners.get(i));

                            } 
                            else {

                            }
                        }
                        
                        // Outputs a message if nothing is found matching that input
                        if(runnersTemp.size() == 0){
                            System.out.println("\nThere is nothing found for your search.");
                        }
                        else{
                            
                            /* Prints out the temporary array list with all the elements 
                            found by the search */
                            System.out.println("\nHere is what we have found:");
                            System.out.println();
                            print(runnersTemp);
                        } 

                        break;
                        
                    case 3:
                        
                        // Gets the user search and places it in a variable
                        System.out.println("Enter the age you are searching for");
                        int searchThree = kb.nextInt();

                        /* Compares the elements in the runners array list to the 
                        to the input by the user, adding the element to temporary array list 
                        if they are the same */
                        for (int i = 0; i < runners.size() - 1; i++) {
                            
                            if (runners.get(i).getAge() == searchThree) {

                                runnersTemp.add(runners.get(i));

                            } 
                            else {

                            }
                        }
                        
                        // Outputs a message if nothing is found matching that input
                        if(runnersTemp.size() == 0){
                            System.out.println("\nThere is nothing found for your search.");
                        }
                        else{
                            
                            /* Prints out the temporary array list with all the elements 
                            found by the search */
                            System.out.println("\nHere is what we have found:"); 
                            System.out.println();
                            print(runnersTemp);
                        } 
                        break;
                        
                    case 4:

                        // Gets the user search and places it in a variable
                        System.out.println("Enter the time you are searching for");
                        double searchFour = kb.nextDouble();

                        /* Compares the elements in the runners array list to the 
                        to the input by the user, adding the element to temporary array list 
                        if they are the same */
                        for (int i = 0; i < runners.size() - 1; i++) {
                            
                            if (runners.get(i).getTime() == searchFour) {

                                runnersTemp.add(runners.get(i));

                            } 
                            else {

                            }
                        }
                        
                        // Outputs a message if nothing is found matching that input
                        if(runnersTemp.size() == 0){
                            System.out.println("\nThere is nothing found for your search.");
                        }
                        else{
                            
                            /* Prints out the temporary array list with all the elements 
                            found by the search */
                            System.out.println("\nHere is what we have found:");
                            System.out.println();
                            print(runnersTemp);
                        } 
                        

                        break;
                        
                    default:
                        
                        // Outputs an instruction and reruns the method
                        System.out.println("\nPlease enter a number between 1 - 4");
                        System.out.println();
                        search(runners);
                        break;
                }  
            } 
            
            // Outputs an instruction and reruns the method
            catch (InputMismatchException e) {
                System.out.println("\nPlease enter a correct input");
                System.out.println();
                search(runners);
            }
            
            endingMenu(runners);
        } 
        
        // Outputs an instruction and reruns the method
        catch (InputMismatchException e) {
            System.out.println("\nPlease enter a number between 1 - 4");
            System.out.println();
            search(runners);
        }
        return runners;
    }
   
    public static ArrayList<Runners> organize(ArrayList<Runners> runners) {

        // Creates new temporary array lists
        ArrayList<Runners> males = new ArrayList<Runners>();
        ArrayList<Runners> females = new ArrayList<Runners>();
        ArrayList<Runners> others = new ArrayList<Runners>();

        // Creates a new string variable to be compared to
        String male = "male";
        String female = "female";

        //Runs through every element in the runners array list
        for (int i = 0; i < runners.size(); i++) {

            /* Adds the element to a male array list, female array list
                    or other array list depending upon the sex of the element */
            if (runners.get(i).getSex().equals(male)) {
                males.add(runners.get(i));
            } else if (runners.get(i).getSex().equals(female)) {
                females.add(runners.get(i));
            } else {
                others.add(runners.get(i));
            }
        }

        // Prints off the males and females
        System.out.println();
        print(males);
        System.out.println();
        print(females);
        System.out.println();
        print(others);

        endingMenu(runners);

        return runners;
    }

    // Main method
    public static void main(String[] args) {

        // Creates a new array list which is filled with what is imported by textfile
        ArrayList<Runners> runners = importRunners();
        
        // Runs the menu method
        menu(runners);

    }
}
