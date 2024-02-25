package com.thien.ingredients.gui.view;

import com.thien.ingredients.bussiness.components.DataValidation;
import com.thien.ingredients.bussiness.services.DispensingDrinkDAO;
import com.thien.ingredients.bussiness.services.ManageIngredientDAO;
import com.thien.ingredients.bussiness.services.ManageMenuItemDAO;
import com.thien.ingredients.gui.utilities.DataInputter;
import com.thien.ingredients.gui.utilities.Menu;

public class IngredientsController {

    private static Menu menu;
    private String ingredientPathFile, menuPathFile, orderPathFile;
    private DataValidation dataValidation;

    public IngredientsController(String ingredientPathFile, String menuPathFile, String orderPathFile) {
        this.ingredientPathFile = ingredientPathFile;
        this.menuPathFile = menuPathFile;
        this.orderPathFile = orderPathFile;
        this.dataValidation = new DataValidation();
    }
    
    public void mainMenu(String menutitle) {
        menu = new Menu(menutitle);
        menu.addOption("Manage ingredients");
        menu.addOption("Manage beverage recipes");
        menu.addOption("Dispensing beverages");
        menu.addOption("Report");
        menu.addOption("Store data to files");
        menu.addOption("Exit program");
        // 6 options

        int choice = 0;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            System.out.println(choice);
             switch (choice) {
                case 1:
                    menuManageIngredients("Manage ingredients", ingredientPathFile);
                    break;
                case 2:
                    menuManageMenuItem("Manage beverage recipes", menuPathFile, ingredientPathFile);
                    break;
                case 3:

                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                default:
                    System.out.println("------Exit program------");
                    break;
             }
        } while (choice > 0 && choice <= 5);
    }

    private void menuManageIngredients(String subMenuTitle, String ingredientPathFile) {
        menu = new Menu(subMenuTitle);
        menu.addOption("Add an ingredient");
        menu.addOption("Update ingredient information");
        menu.addOption("Delete ingredient");
        menu.addOption("Show all ingredients");
        menu.addOption("Return to main menu");
        // 5 options

        ManageIngredientDAO manageIngredientDAO = new ManageIngredientDAO(ingredientPathFile);

        String prefixId = "I"; // Ingredient's ID like "Ixx..." (x is a number)

        int choice = 0;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    System.out.println("----You are going to add new ingredients----");
                    do {
                        manageIngredientDAO.addNew(prefixId);
                    } while (DataInputter.getYN("Do you want to continue add new ingredient?"));             
                    break;
                case 2:
                    System.out.println("----You are going to update ingredient's information----");
                    String id = dataValidation.inputId(prefixId);
                    if (!manageIngredientDAO.isExit(id)) System.out.println("There no ingredient found");
                    else {
                        manageIngredientDAO.display(id); // Show infor before update
                        manageIngredientDAO.update(id);
                        manageIngredientDAO.display(id); // Show infor after update
                    }
                    break;
                case 3:
                    System.out.println("----You are going to delete ingredient----");
                    manageIngredientDAO.showAll(); // Show ingredient collection
                    // Cho nguoi dung nhap ID
                    manageIngredientDAO.delete(dataValidation.inputId(prefixId));
                    break;
                case 4:
                    manageIngredientDAO.showAll();
                    break;
                default:
                    System.out.println("----Return to main menu----");
                    break;
            }
        } while (choice > 0 && choice <= 4);
    }

    private void menuManageMenuItem(String subMenuTitle, String menuPathFile, String ingredientPathFile) {
        menu = new Menu(subMenuTitle);
        menu.addOption("Add the drink to the menu");
        menu.addOption("Update the drink information");
        menu.addOption("Delete the drink from the menu");
        menu.addOption("Show all drink");
        menu.addOption("Return to main menu");
        // 5 options

        ManageMenuItemDAO manageMenuItemDAO = new ManageMenuItemDAO(menuPathFile, ingredientPathFile);

        String prefixId = "D"; // Drink's ID like "Dxx..." (x is a number)

        int choice = 0;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    System.out.println("----You are going to add new ingredients----");
                    do {
                        manageMenuItemDAO.addNew(prefixId);
                    } while (DataInputter.getYN("Do you want to continue add new drink?"));
                    break;
                case 2:
                    System.out.println("----You are going to update drink recipe information----");
                    String id = dataValidation.inputId(prefixId);
                    if (!manageMenuItemDAO.isExit(id)) System.out.println("There no drink found");
                    else {
                        manageMenuItemDAO.display(id); // Show infor before update
                        manageMenuItemDAO.update(id);
                        manageMenuItemDAO.display(id); // Show infor after update
                    }
                    break;
                case 3:
                    System.out.println("----You are going to delete ingredient----");
                    manageMenuItemDAO.showAll(); // Show drink list
                    manageMenuItemDAO.delete(dataValidation.inputId(prefixId));
                    break;
                case 4:
                    manageMenuItemDAO.showAll();
                    break;
                default:
                    System.out.println("----Return to main menu----");
                    break;
            }
        } while (choice > 0 && choice <= 4);
    }

    private void menuDispensableDrink(String subMenuTitle, String ingredientPathFile, String menuPathFile, String orderPathFile) {
        menu = new Menu(subMenuTitle);
        menu.addOption("Dispensing the drink");
        menu.addOption("Update the dispensing drink");
        menu.addOption("Return to main menu");

        DispensingDrinkDAO dispensingDrinkDAO = new DispensingDrinkDAO(ingredientPathFile, menuPathFile, orderPathFile);

        String prefixID = "O"; // Order ID like "Oxx.." (x is a number)

        int choice = 0;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    dispensingDrinkDAO.dispensingDrink(prefixID);
                    break;
                case 2:
                   // dispensingDrinkDAO.updateDispensingDrink(id, quantity);
                    break;
                default:
                    System.out.println("---Return to main menu---");
                    break;
            }
        } while (choice > 0 && choice <= 2);

    }




}
