// The CropControl class - part of the control layer
// class contains all of the calculation methods for managing the crops
// Author: Lance
// Date last modified: May 2018
//-------------------------------------------------------------

package Control;

import Model.CropData;
import Model.Game;
import java.util.Random;

/**
 *
 * @author Lance
 */
public class CropControl 
{
    // constants
    private static final int LAND_BASE = 17;
    private static final int LAND_RANGE = 11;

    // random number generator
    private static Random random = new Random();
    
    // calcLandPrice() method
    // Purpose: Calculate a random land cost between 17 and 26 bushels/acre
    // Parameters: none
    // Returns: the land price
    public static int calcLandPrice()
    {
    int landPrice = random.nextInt(LAND_RANGE) + LAND_BASE;
    return landPrice;
    }
     
           
    // sellLand method
    // Purpose: Sell land - subtracts from the acres owned
    // Parameters: the price of land, the number of acres to sell, and
    // a reference to a CropData object
    // Pre-conditions: acresToSell >= 0 and acresToSell <= acresOwned
    // Returns: the number of acres owned after the sale
    
public static int sellLand(int landPrice, int acresToSell, CropData cropData)
{
    
    //if acresToSell < 0, return -1
    if (acresToSell < 0)
    return -1;
    
    //if acresToSell > acresOwned, return -1
   int acresOwned = cropData.getAcresOwned();
   if (acresToSell > acresOwned)
   return -1;

   //acresOwned = acresOwned - acresToSell
    acresOwned -= acresToSell;
    cropData.setAcresOwned(acresOwned);
  
    //wheatInStore = wheatInStore + acresToSell * landPrice
    int wheatInStore = cropData.getWheatInStore();
    wheatInStore -= (acresToSell * landPrice);
    cropData.setWheatInStore(wheatInStore);

    //return acresOwned
    return acresOwned;
}

    // BuyLand method
    // Purpose: Buy land - Adds to the acres owned
    // Parameters: the price of land, the number of acres to buy, and
    // a reference to a CropData object
    // Pre-conditions: acresToBuy >= 0 and acresToBuy < landPrice * 
    // wheatInStore and wheatInStore < acresToBuy * landPrice and population <
    // (acresOwned + acresToBuy) / 10   
    // Returns: the number of acres owned after the pruchace
    // Returns: the amount of wheat owned after the pruchace.

public static int buyLand(int landPrice, int acresToBuy, CropData cropData)
{
    
    //if acresToBuy < 0, return -1
    if (acresToBuy < 0)
    return -1;
       
   //acresToBuy <= landPrice * wheatInStore
   int wheatInStore = cropData.getWheatInStore();
   if (acresToBuy > landPrice * wheatInStore)
   return -1;
   
   //wheatInStore < acresToBuy * landPrice
  if (acresToBuy * landPrice > wheatInStore)
  return -1;
      
  //population < (acresOwned + acresToBuy) / 10 
   int acresOwned = cropData.getAcresOwned();
   int population = cropData.getPopulation();
   if ((acresToBuy + acresOwned) /10 < population)
   return -1;
   //return population;
    
   //acresOwned = acresOwned + acresToBuy
    acresOwned += acresToBuy;
    cropData.setAcresOwned(acresOwned);
    
    //wheatInStore = wheatInStore - landPrice * acresToBuy
    wheatInStore -= (landPrice * acresToBuy);
    cropData.setWheatInStore(wheatInStore);
    
     //return acresOwned
    return acresOwned;
        
}

// setOffering method
    // Purpose: Pay Offering - Determines the percent of the harvest to use in
    // offerings
    // Parameters: the percent wheat to pay in offerings and
    // a reference to a CropData object
    // Pre-conditions: percentOffering >= 0 and percentOffering <= 100
    // Returns: the amount of wheat for paid for offerings
    //Author: Lance Aberanathy

public static int setOffering(int percentOffering, CropData cropData)
{
    //if percentOffering < 0, return -1
    if (percentOffering < 0)
    return -1;
    
    //if percentOffering > 100, return -1
     if (percentOffering > 100)
     return -1;
     
     //Amount of wheat to pay in offerings. 
    int harvest = cropData.getHarvest();
    int offeringBushels = ((harvest * percentOffering) /100);
    cropData.setOfferingBushels(offeringBushels);
    cropData.setHarvestAfterOffering(harvest - offeringBushels);
    
    //Returns: the amount of wheat for paid for offerings
    return offeringBushels;
}

    /**
     * feedPeople Method
     * 
     * @param bushelsToFeed
     * @param cropData
     * @return bushelsToFeed
     */
    public static int feedPeople(int bushelsToFeed, CropData cropData) {
        // 1. Ask the user “How many bushels of grain do you want to give to the people?”

        // 2. User enters a value.
	

        // 3. Check to make sure that the value is positive. If it is not, show a message and ask the user to // enter the value again.
	if (0 > bushelsToFeed) 
            return -1;

        // 4. Make sure that the city has this much wheat in storage. If not, show a message and ask the 
        // user to enter the value again.
        int wheatInStore = cropData.getWheatInStore();
	if (wheatInStore < bushelsToFeed)
            return -1;

        // 5. Subtract this amount from the wheat in storage. Display the amount of wheat you have left.
	wheatInStore -= bushelsToFeed; 
        cropData.setWheatInStore(wheatInStore);

        // 6. Update the game state to save how many bushels of wheat you have set aside to feed the 
        // people.
	return bushelsToFeed;
    }
    
    /**
     * @param desiredAcres
     * @param cityLandAvailable
     * @param cropData
     * @return
     */
    public static int plantCrops(int desiredAcres, int cityLandAvailable, CropData cropData) {
    // 1. Ask the user “How many acres of land do you want to plant?

    // 2. User enters a value.

    // 3. Check to make sure that the value is positive. If it is not, show a message and ask the user to // enter the value again.
        if (desiredAcres < 0)
            return -1;

    // 4. Check to make sure the city has this much land. If not, show a message and ask the user to 
    // enter a new value.
        if (desiredAcres > cityLandAvailable)
    //message  “Not sufficient amount of land available, enter a valid land amount 
            return -1;
            

    // 5. Make sure that the city has enough wheat in storage to plant this many acres (You can plant // 2 acres with one bushel of wheat). If not, show a message and ask the user to enter the value 
    // again.
        int wheatInStore = cropData.getWheatInStore();
        if (wheatInStore < (desiredAcres / 2)) 
    //message  “Not enough wheat in storage, enter a valid value”
            return -1; 


    // 6. Calculate the number of bushels required to plant the crops.
        int bushelsRequired = bushelsRequired = desiredAcres / 2;

    // 7. Subtract this amount from the wheat in storage. Display the amount of wheat you have left.
            wheatInStore -= bushelsRequired;
            cropData.setWheatInStore(wheatInStore);

    // 8. Update game state to save how many acres have been planted.	
        return desiredAcres;

    }
}