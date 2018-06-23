/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.TeamMember;
import java.util.Scanner;

public class ListView {
    
        Scanner keyboard = new Scanner(System.in);
        private String listMenu;
        private int max;
        
            /**
    * The displayListView method
    * Purpose: displays the menu, gets the user's input, and does the
    * selected action
    * Parameters: none
    * Returns: none
    */
    // =========================================================
        public void displayMenuView()
    {
        int menuOption;
        do
        {
            // Display the menu
            System.out.println(listMenu);
            
            // Prompt the user and get the user’s input
            menuOption = getMenuOption();
            
            // Perform the desired action
            doAction(menuOption);
            
            // Determine and display the next view
        } while (menuOption != max);
     }
    
    /**
    * The HelpMenuView constructor
    * Purpose: Initialize the menu data
    * Parameters: none
    * Returns: none
    */
    // ===================================
        
    public ListView()
    {
        listMenu = "\n" +
            "**********************************\n" +
            "* CITY OF AARON: LIST MENU *\n" +
            "**********************************\n" +
            " 1 - View the development team\n" +
            " 2 - View a list of animals\n" +
            " 3 - View a list of tools\n" +
            " 4 - View a list of provisions\n" +
            " 5 - Return to the game menu\n";

        max = 5;
    }
    
    /**
    * The getMenuOption method
    * Purpose: gets the user's input
    * Parameters: none
    * Returns: integer - the option selected
    */
    // ===================================
    public int getMenuOption()
    {
        // declare a variable to hold user’s input
        int userInput;
        
        // begin loop
        do
        {    
            // get user input from the keyboard
            userInput = keyboard.nextInt();
            
            // if it is not a valid value, output an error message
            if (userInput < 1 || userInput > max)
            {
            System.out.println("Option must be between 1 and " + max);
            }
            
        // loop back to the top if input was not valid
        } while(userInput < 1 || userInput > max);
        
        // return the value input by the user
        return userInput;
    }
    
    /**
    *The doAction method
    * Purpose: performs the selected action
    * Parameters: none
    * Returns: none
    */
    // ===================================
    public void doAction(int option)
    {
        switch (option)
        {
            case 1: // if the option is 1, call viewDevTeam( )
                displayViewDevTeam();
                break;
            case 2: // if the option is 2, call viewAnimals( )
                viewAnimals();
                break;
            case 3: // if the option is 3, call viewTools( )
                viewTools();
                break;
            case 4: // if the option is 4, call viewProvisions( )
                viewProvisions();
                break;
            case 5: // if the option is 5, return to main menu
                break;
        }        
    }
    
    private void displayViewDevTeam() {
        String[] authors = new String[4];
        authors[0] = TeamMember.Lance.getName();
        authors[1] = TeamMember.Lance.getTitle();
        authors[2] = TeamMember.Andrew.getName();
        authors[3] = TeamMember.Andrew.getTitle();
        
        System.out.println("The city of Aaron Development Team");
        System.out.println("");
        
        for (int i = 0; i < authors.length; i = i + 2){
            System.out.println(authors[i] + ", " + authors[i+1]);
            
        }
    }

    public void viewAnimals() {
    System.out.println("This option will display a list of animals."); 
    } 
    
    private void viewTools() {
    System.out.println("This option will display a list of tools");    
    }

    private void viewProvisions() {
    System.out.println("This option will display a list provisions.");

    }
    
}

    
