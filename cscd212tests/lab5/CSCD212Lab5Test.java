package cscd212tests.lab5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import cscd212classes.lab5.lifeform.Human;
import cscd212classes.lab5.lifeform.LifeForm;
import cscd212classes.lab5.lifeform.Martian;
import cscd212classes.lab5.lifeform.StarBellySneetch;
import cscd212classes.lab5.recovery.RecoveryFractional;
import cscd212classes.lab5.recovery.RecoveryLinear;
import cscd212classes.lab5.recovery.RecoveryNone;
import cscd212interfaces.lab5.recovery.RecoveryBehavior;

public class CSCD212Lab5Test
{
   private String testName;
   private int testCurrentLifePoints;
   private int testMaxLifePoints;
   private RecoveryBehavior testRecoveryFractional;
   private RecoveryBehavior testRecoveryLinear;
   private RecoveryBehavior testRecoveryNone;
   private int testArmorPoints;
   private int testDamage;
   private Human testHuman;
   private Martian testMartian;
   private StarBellySneetch testStarBellySneetch;

   @BeforeEach
   public void initilizeFields()
   {
      testName = "Test Name";
      testCurrentLifePoints = 100;
      testMaxLifePoints = 110;
      testRecoveryFractional = new RecoveryFractional(0.30);
      testRecoveryLinear = new RecoveryLinear(30);
      testRecoveryNone = new RecoveryNone();
      testArmorPoints = 50;
      testDamage = 10;
      testHuman = new Human(testName, testCurrentLifePoints, testMaxLifePoints, testArmorPoints);
      testMartian = new Martian(testName, testCurrentLifePoints, testMaxLifePoints, testRecoveryFractional);
      testStarBellySneetch = new StarBellySneetch(testName, testCurrentLifePoints, testMaxLifePoints,
            testRecoveryFractional);
   }

   @Nested
   @DisplayName("Preconditions")
   public class TestPreconditions
   {
      @Nested
      @DisplayName("LifeForms")
      public class TestPreconditionsLifeforms
      {
         @Test
         @DisplayName("exception on name is null")
         public void nameIsNull()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(null, testCurrentLifePoints, testMaxLifePoints, testArmorPoints);
            });
         }

         @Test
         @DisplayName("exception on name is an empty String")
         public void nameIsEmpty()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(new String(""), testCurrentLifePoints, testMaxLifePoints, testArmorPoints);
            });
         }

         @Test
         @DisplayName("exception on max life points < 0")
         public void maxLifeLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, -1, testMaxLifePoints, testArmorPoints);
            });
         }

         @Test
         @DisplayName("exception on max life points = 0")
         public void maxLifeIsZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, 0, testMaxLifePoints, testArmorPoints);
            });
         }

         @Test
         @DisplayName("exception on current life points < 0")
         public void currentLifeLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, testCurrentLifePoints, -1, testArmorPoints);
            });
         }

         @Test
         @DisplayName("exception on current life points = 0")
         public void currentLifeIsZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, testCurrentLifePoints, 0, testArmorPoints);
            });
         }

         @Test
         @DisplayName("exception on max life points less than current")
         public void maxLessThanCurrent()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, 5, 4, testArmorPoints);
            });
         }

         @Test
         @DisplayName("no exception on max life points = current")
         public void maxEqualsCurrent()
         {
            try
            {
               new Human(testName, testCurrentLifePoints, testCurrentLifePoints, testArmorPoints);
            } catch (IllegalArgumentException e)
            {
               fail("LifeForm Constructor threw an exception on a valid paramater (current = max)");
            }
         }
      }

      @Nested
      @DisplayName("Humans")
      public class TestPreconditionsHumans
      {
         @Test
         @DisplayName("Constructor exception message is correct")
         public void constructorExceptionMessage()
         {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
               new Human(null, testCurrentLifePoints, testMaxLifePoints, testArmorPoints);
            });

            assertEquals("Incorrect exception message for null name,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Human(new String(""), testCurrentLifePoints, testMaxLifePoints, testArmorPoints);
            });

            assertEquals("Incorrect exception message for empty name,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, -1, testMaxLifePoints, testArmorPoints);
            });

            assertEquals("Incorrect exception message for current life points < 0,",
                  "Bad Params in LifeForm Constructor", exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, testCurrentLifePoints, -1, testArmorPoints);
            });
            assertEquals("Incorrect exception message for max life points < 0,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, 5, 4, testArmorPoints);
            });

            assertEquals("Incorrect exception message for max life points less than current,",
                  "Bad Params in LifeForm Constructor", exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, testCurrentLifePoints, testMaxLifePoints, -1);
            });
            assertEquals("Incorrect exception message for negative armor points,", "Bad Params in Human Constructor",
                  exception.getMessage());
         }

         @Test
         @DisplayName("constructor exception on armor less than 0")
         public void armorLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Human(testName, testCurrentLifePoints, testMaxLifePoints, -1);
            });
         }

         @Test
         @DisplayName("no construction exception on armor = 0")
         public void armorIsZero()
         {
            try
            {
               new Human(testName, testCurrentLifePoints, testMaxLifePoints, 0);
            } catch (IllegalArgumentException e)
            {
               fail("Human Constructor threw an exception on a valid paramater (armor = 0)");
            }
         }

         @Test
         @DisplayName("setArmor exception message is correct")
         public void setArmorExceptionMessage()
         {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
               testHuman.setArmorPoints(-1);
            });

            assertEquals("Incorrect exception message setArmor,", "Bad Params in setArmorPoints",
                  exception.getMessage());
         }

         @Test
         @DisplayName("setArmorPoints exception on armor less than 0")
         public void setArmorLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               testHuman.setArmorPoints(-1);
            });
         }

         @Test
         @DisplayName("no setArmorPoints exception on armor = 0")
         public void setArmorIsZero()
         {
           try
            {
               testHuman.setArmorPoints(0);
            } catch (IllegalArgumentException e)
            {
               fail("Human setArmorPoints threw an exception on a valid paramater (armor = 0)");
            }
         }

         @Test
         @DisplayName("takeHit exception message is correct")
         public void takeHitExceptionMessage()
         {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
               testHuman.takeHit(-1);
            });

            assertEquals("Incorrect exception message for Human takeHit,", "Bad Params in takeHit",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               testHuman.takeHit(0);
            });

            assertEquals("Incorrect exception message for Human takeHit,", "Bad Params in takeHit",
                  exception.getMessage());
         }

         @Test
         @DisplayName("takeHit exception on damage less than 0")
         public void takeHitLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               testHuman.takeHit(-1);
            });
         }

         @Test
         @DisplayName("takeHit exception on damage = 0")
         public void takeHitIsZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               testHuman.takeHit(0);
            });
         }

         @Test
         @DisplayName("no takeHit exception on damage > 0")
         public void takeHitIsOne()
         {
            try
            {
               testHuman.takeHit(1);
            } catch (IllegalArgumentException e)
            {
               fail("Human setArmorPoints threw an exception on a valid paramater (armor = 0)");
            }
         }

      }
      @Nested
      @DisplayName("Aliens")
      public class TestPreconditionsAliens
      {
         @Test
         @DisplayName("exception on null recovery")
         public void recoveryIsNull()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               new Martian(testName, testCurrentLifePoints, testMaxLifePoints, null);
            });
         }

         @Test
         @DisplayName("setCurrentLifePoints exception message is correct")
         public void takeHitExceptionMessage()
         {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
               testMartian.setCurrentLifePoints(-1);
            });

            assertEquals("Incorrect exception message for setCurrentLifePoints,", "Bad Params in setCurrentLifePoints",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               testMartian.setCurrentLifePoints(0);
            });

            assertEquals("Incorrect exception message for setCurrentLifePoints,", "Bad Params in setCurrentLifePoints",
                  exception.getMessage());
            
            exception = assertThrows(IllegalArgumentException.class, () -> {
               testMartian.setCurrentLifePoints(testMaxLifePoints + 10);
            });

            assertEquals("Incorrect exception message for setCurrentLifePoints,", "Bad Params in setCurrentLifePoints",
                  exception.getMessage());
         }

         @Test
         @DisplayName("setCurrentLifePoints exception on points < 0")
         public void setCurrentLifePointsLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               testMartian.setCurrentLifePoints(-1);
            });
         }

         @Test
         @DisplayName("setCurrentLifePoints exception on points = 0")
         public void setCurrentLifePointsIsZero()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               testMartian.setCurrentLifePoints(0);
            });
         }
         
         @Test
         @DisplayName("setCurrentLifePoints exception on points > max")
         public void setCurrentLifePointsGreaterThanMax()
         {
            assertThrows(IllegalArgumentException.class, () -> {
               testMartian.setCurrentLifePoints(testMaxLifePoints + 10);
            });
         }
      }

      @Nested
      @DisplayName("Martians")
      public class TestPreconditionsMartians
      {
         @Test
         @DisplayName("Constructor exception message is correct")
         public void constructorExceptionMessage()
         {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
               new Martian(null, testCurrentLifePoints, testMaxLifePoints, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for null name,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Martian(new String(""), testCurrentLifePoints, testMaxLifePoints, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for empty name,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Martian(testName, -1, testMaxLifePoints, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for current life points < 0,",
                  "Bad Params in LifeForm Constructor", exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Martian(testName, testCurrentLifePoints, -1, testRecoveryFractional);
            });
            assertEquals("Incorrect exception message for max life points < 0,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Martian(testName, 5, 4, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for max life points less than current,",
                  "Bad Params in LifeForm Constructor", exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new Martian(testName, testCurrentLifePoints, testMaxLifePoints, null);
            });
            assertEquals("Incorrect exception message for negative armor points,", "Bad Params in Alien Constructor",
                  exception.getMessage());
         }
      }

      @Nested
      @DisplayName("StarBellySneetchs")
      public class TestPreconditionsStarBellySneetch
      {
         @Test
         @DisplayName("Constructor exception message is correct")
         public void constructorExceptionMessage()
         {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
               new StarBellySneetch(null, testCurrentLifePoints, testMaxLifePoints, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for null name,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new StarBellySneetch(new String(""), testCurrentLifePoints, testMaxLifePoints, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for empty name,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new StarBellySneetch(testName, -1, testMaxLifePoints, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for current life points < 0,",
                  "Bad Params in LifeForm Constructor", exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new StarBellySneetch(testName, testCurrentLifePoints, -1, testRecoveryFractional);
            });
            assertEquals("Incorrect exception message for max life points < 0,", "Bad Params in LifeForm Constructor",
                  exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new StarBellySneetch(testName, 5, 4, testRecoveryFractional);
            });

            assertEquals("Incorrect exception message for max life points less than current,",
                  "Bad Params in LifeForm Constructor", exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () -> {
               new StarBellySneetch(testName, testCurrentLifePoints, testMaxLifePoints, null);
            });
            assertEquals("Incorrect exception message for negative armor points,", "Bad Params in Alien Constructor",
                  exception.getMessage());
         }

      }
   }

   @Nested
   @DisplayName("Martian")
   public class TestMartian
   {
      @Test
      @DisplayName("getLifePoints works")
      public void getLifePointsWorks()
      {
         assertEquals(testCurrentLifePoints, testMartian.getLifePoints());
      }

      @Test
      @DisplayName("getName works")
      public void getNameWorks()
      {
         assertEquals(testName, testMartian.getName());
      }

      @Test
      @DisplayName("takeHit works for damage < life points")
      public void takeHitWorksLowDamage()
      {
         int newLifePoints = testCurrentLifePoints - testDamage;
         testMartian.takeHit(testDamage);
         assertEquals(newLifePoints, testMartian.getLifePoints());
      }

      @Test
      @DisplayName("takeHit works for damage = life points")
      public void takeHitWorksEqualDamage()
      {
         testMartian.takeHit(testCurrentLifePoints);
         assertEquals(0, testMartian.getLifePoints());
      }

      @Test
      @DisplayName("takeHit works for damage > life points")
      public void takeHitWorksLargeDamage()
      {
         testMartian.takeHit(1000);
         assertEquals(0, testMartian.getLifePoints());
      }

      @Test
      @DisplayName("toString works")
      public void toStringWorks()
      {
         assertEquals(
               testName + " has " + testCurrentLifePoints + " life points and has recovery mode of RecoveryFractional",
               testMartian.toString());
      }
      
      @Test
      @DisplayName("setCurrentLifePoints works")
      public void setCurrentLifePointsWorks()
      {
         testMartian.setCurrentLifePoints(testCurrentLifePoints - 10);
         assertEquals(testCurrentLifePoints - 10, testMartian.getLifePoints());
      }
      
      @Test
      @DisplayName("getRecoveryBehavior works")
      public void getRecoveryBehaviorWorks()
      {
         assertEquals(testRecoveryFractional, testMartian.getRecoveryBehavior());
      }
      
      @Test
      @DisplayName("setRecoveryBehavior works")
      public void setRecoveryBehaviorWorks()
      {
         testMartian.setRecoveryBehavior(testRecoveryLinear);
         assertEquals(testRecoveryLinear, testMartian.getRecoveryBehavior());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional above base")
      public void recoverWorksFractionalAboveBase()
      {
         testMartian.setCurrentLifePoints(testCurrentLifePoints - 50);
         assertEquals("Test Name has had 15 recovery points added their current life points", testMartian.recover());
         assertEquals(testCurrentLifePoints - 50 + 15, testMartian.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional below base")
      public void recoverWorksFractionalBelowBase()
      {
         testMartian.setCurrentLifePoints(testCurrentLifePoints - 90);
         assertEquals("Test Name has had 10 recovery points added their current life points", testMartian.recover());
         assertEquals(testCurrentLifePoints - 90 + 10, testMartian.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional hitting max")
      public void recoverWorksFractionalHittingMax()
      {
         testMartian.setCurrentLifePoints(testCurrentLifePoints - 10);
         assertEquals("Test Name has had 20 recovery points added their current life points", testMartian.recover());
         assertEquals(testCurrentLifePoints - 10 + 20, testMartian.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryLinear below max")
      public void recoverWorksLinearBelowMax()
      {
         testMartian.setCurrentLifePoints(testCurrentLifePoints - 50);
         testMartian.setRecoveryBehavior(testRecoveryLinear);
         assertEquals("Test Name has had 30 recovery points added their current life points", testMartian.recover());
         assertEquals(testCurrentLifePoints - 50 + 30, testMartian.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional hitting max")
      public void recoverWorksLinearHittingMax()
      {
         testMartian.setCurrentLifePoints(testCurrentLifePoints - 5);
         testMartian.setRecoveryBehavior(testRecoveryLinear);
         assertEquals("Test Name has had 15 recovery points added their current life points", testMartian.recover());
         assertEquals(testCurrentLifePoints - 5 + 15, testMartian.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryNone")
      public void recoverWorksNone()
      {
         testMartian.setCurrentLifePoints(testCurrentLifePoints - 5);
         testMartian.setRecoveryBehavior(testRecoveryNone);
         assertEquals("Test Name has had 0 recovery points added their current life points", testMartian.recover());
         assertEquals(testCurrentLifePoints - 5, testMartian.getLifePoints());
      }
   }
   @Nested
   @DisplayName("StarBellySneetch")
   public class TestStarBellySneetch
   {
      @Test
      @DisplayName("getLifePoints works")
      public void getLifePointsWorks()
      {
         assertEquals(testCurrentLifePoints, testStarBellySneetch.getLifePoints());
      }

      @Test
      @DisplayName("getName works")
      public void getNameWorks()
      {
         assertEquals(testName, testStarBellySneetch.getName());
      }

      @Test
      @DisplayName("takeHit works for damage < life points")
      public void takeHitWorksLowDamage()
      {
         int newLifePoints = testCurrentLifePoints - testDamage;
         testStarBellySneetch.takeHit(testDamage);
         assertEquals(newLifePoints, testStarBellySneetch.getLifePoints());
      }

      @Test
      @DisplayName("takeHit works for damage = life points")
      public void takeHitWorksEqualDamage()
      {
         testStarBellySneetch.takeHit(testCurrentLifePoints);
         assertEquals(0, testStarBellySneetch.getLifePoints());
      }

      @Test
      @DisplayName("takeHit works for damage > life points")
      public void takeHitWorksLargeDamage()
      {
         testStarBellySneetch.takeHit(1000);
         assertEquals(0, testStarBellySneetch.getLifePoints());
      }

      @Test
      @DisplayName("toString works")
      public void toStringWorks()
      {
         assertEquals(
               testName + " has " + testCurrentLifePoints + " life points and has recovery mode of RecoveryFractional",
               testStarBellySneetch.toString());
      }
      
      @Test
      @DisplayName("setCurrentLifePoints works")
      public void setCurrentLifePointsWorks()
      {
         testStarBellySneetch.setCurrentLifePoints(testCurrentLifePoints - 10);
         assertEquals(testCurrentLifePoints - 10, testStarBellySneetch.getLifePoints());
      }
      
      @Test
      @DisplayName("getRecoveryBehavior works")
      public void getRecoveryBehaviorWorks()
      {
         assertEquals(testRecoveryFractional, testStarBellySneetch.getRecoveryBehavior());
      }
      
      @Test
      @DisplayName("setRecoveryBehavior works")
      public void setRecoveryBehaviorWorks()
      {
         testStarBellySneetch.setRecoveryBehavior(testRecoveryLinear);
         assertEquals(testRecoveryLinear, testStarBellySneetch.getRecoveryBehavior());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional above base")
      public void recoverWorksFractionalAboveBase()
      {
         testStarBellySneetch.setCurrentLifePoints(testCurrentLifePoints - 50);
         assertEquals("Test Name has had 15 recovery points added their current life points", testStarBellySneetch.recover());
         assertEquals(testCurrentLifePoints - 50 + 15, testStarBellySneetch.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional below base")
      public void recoverWorksFractionalBelowBase()
      {
         testStarBellySneetch.setCurrentLifePoints(testCurrentLifePoints - 90);
         assertEquals("Test Name has had 10 recovery points added their current life points", testStarBellySneetch.recover());
         assertEquals(testCurrentLifePoints - 90 + 10, testStarBellySneetch.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional hitting max")
      public void recoverWorksFractionalHittingMax()
      {
         testStarBellySneetch.setCurrentLifePoints(testCurrentLifePoints - 10);
         assertEquals("Test Name has had 20 recovery points added their current life points", testStarBellySneetch.recover());
         assertEquals(testCurrentLifePoints - 10 + 20, testStarBellySneetch.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryLinear below max")
      public void recoverWorksLinearBelowMax()
      {
         testStarBellySneetch.setCurrentLifePoints(testCurrentLifePoints - 50);
         testStarBellySneetch.setRecoveryBehavior(testRecoveryLinear);
         assertEquals("Test Name has had 30 recovery points added their current life points", testStarBellySneetch.recover());
         assertEquals(testCurrentLifePoints - 50 + 30, testStarBellySneetch.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryFractional hitting max")
      public void recoverWorksLinearHittingMax()
      {
         testStarBellySneetch.setCurrentLifePoints(testCurrentLifePoints - 5);
         testStarBellySneetch.setRecoveryBehavior(testRecoveryLinear);
         assertEquals("Test Name has had 15 recovery points added their current life points", testStarBellySneetch.recover());
         assertEquals(testCurrentLifePoints - 5 + 15, testStarBellySneetch.getLifePoints());
      }
      
      @Test
      @DisplayName("recover works with RecoveryNone")
      public void recoverWorksNone()
      {
         testStarBellySneetch.setCurrentLifePoints(testCurrentLifePoints - 5);
         testStarBellySneetch.setRecoveryBehavior(testRecoveryNone);
         assertEquals("Test Name has had 0 recovery points added their current life points", testStarBellySneetch.recover());
         assertEquals(testCurrentLifePoints - 5, testStarBellySneetch.getLifePoints());
      }
   }
   @Nested
   @DisplayName("Human")
   public class TestHuman
   {
      @Test
      @DisplayName("getLifePoints works")
      public void getLifePointsWorks()
      {
         assertEquals(testCurrentLifePoints, testHuman.getLifePoints());
      }

      @Test
      @DisplayName("getName works")
      public void getNameWorks()
      {
         assertEquals(testName, testHuman.getName());
      }

      @Test
      @DisplayName("takeHit works for damage < armor points")
      public void takeHitWorksLowDamage()
      {
         int newArmorPoints = testArmorPoints - testDamage;
         testHuman.takeHit(testDamage);
         assertEquals(newArmorPoints, testHuman.getArmorPoints());
         assertEquals(testCurrentLifePoints, testHuman.getLifePoints());
      }

      @Test
      @DisplayName("takeHit works for damage = armor points")
      public void takeHitWorksEqualDamage()
      {
         testHuman.takeHit(testArmorPoints);
         assertEquals(0, testHuman.getArmorPoints());
         assertEquals(testCurrentLifePoints, testHuman.getLifePoints());
      }

      @Test
      @DisplayName("takeHit works for damage > armor points but < armor + life")
      public void takeHitWorksLargeDamage()
      {
         testHuman.takeHit(testArmorPoints + testCurrentLifePoints - 10);
         assertEquals(0, testHuman.getArmorPoints());
         assertEquals(10, testHuman.getLifePoints());
      }
      
      @Test
      @DisplayName("takeHit works for damage > armor + life")
      public void takeHitWorksFatalDamage()
      {
         testHuman.takeHit(testArmorPoints + testCurrentLifePoints + 10);
         assertEquals(0, testHuman.getArmorPoints());
         assertEquals(0, testHuman.getLifePoints());
      }

      @Test
      @DisplayName("toString works")
      public void toStringWorks()
      {
         assertEquals(
               testName + " has " + testCurrentLifePoints + " life points and " + testArmorPoints + " armor points",
               testHuman.toString());
      }
      
      @Test
      @DisplayName("getArmorPoints works")
      public void getArmorPointsWorks()
      {
         assertEquals(testArmorPoints, testHuman.getArmorPoints());
      }
      
      @Test
      @DisplayName("setArmorPoints works")
      public void setArmorPointsWorks()
      {
         testHuman.setArmorPoints(testArmorPoints - 10);
         assertEquals(testArmorPoints - 10, testHuman.getArmorPoints());
      }
      
     
   }
}
