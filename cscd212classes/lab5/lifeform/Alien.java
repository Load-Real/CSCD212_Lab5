package cscd212classes.lab5.lifeform;

import cscd212classes.lab5.recovery.RecoveryNone;
//import cscd212classes.lab5.recovery.RecoveryFractional;
//import cscd212classes.lab5.recovery.RecoveryLinear;
import cscd212interfaces.lab5.recovery.RecoveryBehavior;

/**
 * The abstract Alien class represents a basic Alien which is a child of LifeForm <br>
 * 
 * @NOTE If a precondition is not met then the message will be "Bad Params in MethodName" <br>
 * @NOTE MethodName will be replaced with the actual method name, if constructor, replace with "ClassName Constructor"
 */
public abstract class Alien extends LifeForm {
   /**
    * The private data member recovery of type Recovery Behavior
    */
   private RecoveryBehavior recovery;
   
   /**
    * The constructor to create an Alien object which calls the 2 parameter super constructor and sets recovery to the
    * default of RecoveryNone.
    * @param name Representing the name
    * @param currentLifePoints Representing the life form's current life points
    */
  public Alien(final String name, final int currentLifePoints) {
	  super(name, currentLifePoints);
	  
	  //this.recovery = RecoveryNone.calculateRecovery(currentLifePoints);
	  this.recovery = new RecoveryNone();
  }
  /**
   * The constructor to create an Alien object which calls the 4 parameter super constructor and then sets the recovery
   * @param name Representing the name
   * @param currentLifePoints Representing the life form's current life points
   * @param currentLifePoints Representing the life form's current life points
   * @param recoveryBehavior Representing the life form's recovery behavior
   *
   * @throws IllegalArgumentException If recovery is null
   */
  public Alien(final String name, final int currentLifePoints, final int maxLifePoints, final RecoveryBehavior recovery) {
	   super(name, currentLifePoints, maxLifePoints);
	   
	   if (recovery == null) throw new IllegalArgumentException("Bad Params in Alien Constructor");
	   
	   this.recovery = recovery;
  }
  
   /**
    * Sets the Alien's current life points
    * @param currentLifePoints Representing this Alien's new life point value
    * @throws IllegalArgumentException If currentLifePoints is <= 0 or greater than MAX_LIFE_POINTS
    */
  public void setCurrentLifePoints(final int currentLifePoints) {
	  if (currentLifePoints <= 0 || currentLifePoints > MAX_LIFE_POINTS) throw new IllegalArgumentException("Bad Params in setCurrentLifePoints");
	  
	  this.currentLifePoints = currentLifePoints;
  }
  
   /**
    * Sets the Alien's current recovery
    * @param RecoveryBehavior Representing this Alien's new recovery
    * @throws IllegalArgumentException If recovery is null
    */
  public void setRecoveryBehavior(final RecoveryBehavior recovery) {
	  if (recovery == null) throw new IllegalArgumentException("Bad Params in setRecoveryBehavior");
	  
	  this.recovery = recovery;
  }
  
   /**
    * Returns the Alien's current recoveryBehavior
    * @return RecoveryBehavior The Alien's current recoveryBehavior
    */
  public RecoveryBehavior getRecoveryBehavior() {
	  return this.recovery;
  }
    
   /**
    * The recover method delegates the calculation of the recovery points to the aggregated recovery object and returns
    * a String representing the points recovered. Life points cannot recover a LifeForm past their MAX_LIFE_POINTS.
    * @NOTE This method prints the following: name + " has had " + points recovered + " recovery points added their
    * current life points"
    */
   public String recover() {
	   //Adds current life points to what we would recover, if greater, prevents it from exceeding max
	   int recoverValue = super.currentLifePoints + this.recovery.calculateRecovery(super.getLifePoints());
	   //Holds the default value recovered
	   int recoverValue2 = this.recovery.calculateRecovery(super.getLifePoints());
	   //Checks if currentLifePoints + life points recovered > the maximum life points
	   if (recoverValue >= super.MAX_LIFE_POINTS) {
		   /*Value holding sum of current life points and life points recovered set equal to the difference
		    * of the maximum life points and the current life points (prior to summation)
		    */
		   recoverValue = super.MAX_LIFE_POINTS - super.currentLifePoints;
		   //then the current life points are updated to the maximum, so that it doesn't go over the maximum
		   super.currentLifePoints = super.MAX_LIFE_POINTS;
		   /*String statement returned stating the recover value, which is now the difference between
		    * the max life points and current life points (which are no longer "current", but rather "past")
		    */
		   return super.getName() + " has had " + recoverValue + " recovery points added their current life points";
	   }
	   
	   //Done if the sum of current life points and amount recovered isn't greater than the max life points
	   /*the current life points are instead updated to be equal to the sum of the old current 
	    * life points and points recovered
	    */
	   this.currentLifePoints = recoverValue;
	   //String statement returning the 2nd recover value, which contains just the points gained
	   return super.getName() + " has had " + recoverValue2 + " recovery points added their current life points";
   }
   
   
   /**
    * Calls the parents toString and appends " and has recovery mode of " + the Class simple name of the aggregated
    * recovery object
    * @return String Representing the parents toString and the appended recovery mode
    */
   @Override
   public String toString() {
	   //Not sure how to call a specific recovery mode just yet
	   //return super.toString() + " and has recovery mode of " + what
	   return super.toString() + " and has recovery mode of " + this.recovery.getClass().getSimpleName();
   }
}