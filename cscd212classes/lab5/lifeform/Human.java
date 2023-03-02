package cscd212classes.lab5.lifeform;


/**
 * Represents a Human which is a child of LifeForm
 * @NOTE If a precondition is not met then the message will be "Bad Params in MethodName" <br>
 * @NOTE MethodName will be replaced with the actual method name, if constructor, replace with "ClassName Constructor"
 */
public class Human extends LifeForm {
	//Fields
	private int armorPoints;

   /**
    * The constructor to create a Human object which calls the super constructor and sets armorPoints to the default of
    * 100
    * @param name Representing the name
    * @param currentLifePoints Representing the life points
    */
	public Human(final String name, final int currentLifePoints) {
		super(name, currentLifePoints);
		
		this.armorPoints = 100;
	}

	   /**
	    * The constructor to create a Human object which calls the super constructor and then sets the armorPoints
	    * @param name Representing the name
	    * @param currentLifePoints Representing the life points
	    * @param armorPoints Representing the armor points
	    * @throws IllegalArgumentException If armorPoints is < 0
	    */
	public Human(final String name, final int currentLifePoints, final int maxLifePoints, final int armorPoints) {
		super(name, currentLifePoints, maxLifePoints);
			
		if (armorPoints < 0) throw new IllegalArgumentException("Bad Params in Human Constructor");
			
		this.armorPoints = armorPoints;
	}	
   /**
    * Returns the Human's current armor points
    * @return int The Human's current armor points
    */
	public int getArmorPoints() {
		return this.armorPoints;
	}

   /**
    * Sets the Human's current armor points
    * @param armorPoints Representing this Human's new armor point value
    * @throws IllegalArgumentException If armorPoints is < 0
    */
	public void setArmorPoints(final int armorPoints) {
		if (armorPoints < 0) throw new IllegalArgumentException("Bad Params in setArmorPoints");
		
		this.armorPoints = armorPoints;
	}

   /**
    * When Humans with armorPoints take damage, the damage is mitigated by the armor 1 to 1. Instead of reducing life
    * points, the damage will reduce the armor points until the armor points value is 0. If a Human's armor points are
    * reduced to 0, the remaining damage is applied by calling the parent's takeHit method.
    * @param damage int representing the damage being applied to this Human
    * @throws IllegalArgumentException if the incoming damage is <= 0
    * @NOTE it is not possible to have negative armor points
    */
	public void takeHit(final int damage) {
		if (damage <= 0) throw new IllegalArgumentException("Bad Params in takeHit");
		
		/* Current Issue:
		 * The damage value, is saved and also final. So I can't modify it.
		 */
		int newDamage = 0;
		if (damage > this.armorPoints) {
			//super.takeHit(damage);
			//this.armorPoints = 0;
			newDamage = damage - this.armorPoints;
			this.armorPoints = 0;
			super.takeHit(newDamage);
		}
		if (damage <= this.armorPoints) {
			//super.takeHit(damage - this.armorPoints);
			//this.armorPoints = this.armorPoints - damage;
			this.armorPoints = this.armorPoints - damage;
		}
		/*
		if ((damage > this.armorPoints) && (damage < this.armorPoints + this.currentLifePoints)) {
			this.currentLifePoints = this.currentLifePoints - (damage - this.armorPoints);
			//damage = damage - this.armorPoints;
			this.armorPoints = 0;
		}
		if (damage < this.armorPoints) {
			this.armorPoints = this.armorPoints - damage;
			//damage = 0;
		}
		if (damage > this.armorPoints + this.currentLifePoints) {
			//damage = damage - (this.armorPoints + this.currentLifePoints);
			this.armorPoints = 0;
			this.currentLifePoints = 0;
		}
		if (damage == this.armorPoints) {
			this.armorPoints = 0;
		}
		*/
	}

	/**
	 * Calls the parents toString and appends " and " + armorPoints + " armor points"
	 * @return String Representing the parents toString and the appended armor points
	 */
	public String toString() {
		return super.toString() + " and " + this.armorPoints + " armor points";
	}
}