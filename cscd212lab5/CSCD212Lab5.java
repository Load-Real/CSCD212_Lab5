package cscd212lab5;

import cscd212classes.lab5.lifeform.*;
import cscd212classes.lab5.recovery.*;

public class CSCD212Lab5
{
   public static void main(final String[] args)
   {
      Martian marvin = new Martian("Marvin the Martian", 100);
      System.out.print(marvin + "\n");
      System.out.print("\n");

      System.out.print("Marvin is shot and takes 80 damage.\n");
      marvin.takeHit(80);
      System.out.print(marvin + "\n");
      System.out.print("\n");

      System.out.print("Marvin finds a special item which gives him the ability of percentage based recovery.\n");
      marvin.setRecoveryBehavior(new RecoveryFractional(.30));
      System.out.print("Marvin uses his recovery ability\n");
      System.out.print(marvin.recover() + "\n");
      System.out.print(marvin + "\n");
      System.out.print("\n");
      
      System.out.print("Marvin uses his recovery ability 3 times in a row\n");
      System.out.print(marvin.recover() + "\n");
      System.out.print(marvin + "\n");
      System.out.print(marvin.recover() + "\n");
      System.out.print(marvin + "\n");
      System.out.print(marvin.recover() + "\n");
      System.out.print(marvin + "\n");
      System.out.print("\n");

      StarBellySneetch starry = new StarBellySneetch("Starry the Star Belly Sneetch", 75, 75, new RecoveryLinear(10));
      System.out.print(starry + "\n");
      System.out.print("\n");

      System.out.print("Starry falls off a cliff and takes 5 damage\n");
      starry.takeHit(5);
      System.out.print(starry + "\n");
      System.out.print("\n");

      System.out.print("Starry uses their recovery ability\n");
      System.out.print(starry.recover() + "\n");
      System.out.print(starry + "\n");
      System.out.print("\n");

      Human hughMann = new Human("Hugh Mann the Human", 50, 50, 100);
      System.out.print(hughMann + "\n");
      System.out.print("\n");

      System.out.print("Hugh Mann is mauled by an unknown creature for 80 damage\n");
      hughMann.takeHit(80);
      System.out.print(hughMann + "\n");
      System.out.print("\n");

      System.out.print("Attempting to escape, Hugh Mann is caught in a storm and suffers 30 elemental damage\n");
      hughMann.takeHit(30);
      System.out.print(hughMann + "\n");
      System.out.print("\n");

      System.out.print("Hugh Mann replaces his broken armor with a 20 armor tunic\n");
      hughMann.setArmorPoints(20);
      System.out.print(hughMann + "\n");
      System.out.print("\n");

      System.out.print(
            "Returning back to base from the Storm, Hugh Mann runs into the same creature and takes 70 damage\n");
      hughMann.takeHit(70);
      System.out.print(hughMann + "\n");
      System.out.print("\n");

   }

}