import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		
		//System objects
		Scanner in = new Scanner(System.in);
		Random rand = new Random(); //A random number generator

		//Game Variables
		String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assassin"};
		int maxEnemyHealth = 80; //Max health of enemy
		int enemyAttackDamage = 25; //Damage that enemy can do
		
		//Player Variables
		int playerHealth = 100; //Health players starts with
		int playerAttackDamage = 30; //Damage the player does in combat
		int numHealthPotions = 5; //Health Potions
		int potionHealAmount = 30; //Amount of health increases on intakes of potion
		int healthPotionDropChance = 50; /*When enemies are killed they drop a potion, based on specified chance
										 Given value is in percentage*/
		
		boolean running = true; //used to run the while loop
		
		System.out.println("Welcome to THE DUNGEON!!");
		
		GAME:   //GAME is the name of the below while loop
		while(running) {
			
			System.out.println("--------------------------------------------------");
			
			int enemyHealth = rand.nextInt(maxEnemyHealth);
			String enemy = enemies[rand.nextInt(enemies.length)];
			System.out.println("\t# " + enemy + " has appeared! #\n");
			
			while(enemyHealth > 0) {
				System.out.println("\tYour HP : " + playerHealth);
				System.out.println("\t" + enemy +"'s HP : " + enemyHealth);
				System.out.println("\tYou have " + numHealthPotions + " health potions.");
				System.out.println("\n\tWhat would you like to do ?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Drink Health Potions");
				System.out.println("\t3. Run");
				
				String input = in.nextLine();
				if(input.equals("1")) {
					int damageDealt = rand.nextInt(playerAttackDamage);
					int damageTaken = rand.nextInt(enemyAttackDamage);
					
					enemyHealth -= damageDealt;
					playerHealth -= damageTaken;
					
					System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
					System.out.println("\t> You received " + damageTaken + " in retalliation from " + enemy);
					
					if(playerHealth < 1) {
						System.out.println("\t> You have taken too much damage, you're too weak to continue.");
						break;
					}
				}
				else if(input.equals("2")) {
					if(numHealthPotions > 0) {
						playerHealth += potionHealAmount;
						if(playerHealth > 100)
							playerHealth = 100;
						numHealthPotions--;
						System.out.println("\t> You drank the health potion to heal yourself for " + potionHealAmount + "."
								+ "\n\t> You now have " + playerHealth + " remaining HP"
								+ "\n\t> You have " + numHealthPotions + " health potions remaining.\n");
					}
					else {
						System.out.println("\t> You have no health potions left . Defeat enemies for a chance to get one.");
					}
				}
				else if(input.equals("3")) {
					System.out.println("\t> You ran away from " + enemy +"!");
					continue GAME; //We need to go to the outer loop, NOT the combat loop, to generate a new enemy to fight.
								   //If we hadn't put label GAME in continue we would have continued in the combat loop
				}
				else {
					System.out.println("\tInvalid Command");
				}
			}
			
			if(playerHealth < 1) {
				System.out.println("You limp out of the Dungeon, too weak from battle");
				break;
				
			}
			
			System.out.println("--------------------------------------------------");
			System.out.println(" # " + enemy + " was defeated! #");
			System.out.println(" # You have " + playerHealth + " HP left. #");
			
			if(rand.nextInt(100) < healthPotionDropChance) {
				numHealthPotions++;
				System.out.println(" # The " + enemy + " dropped a health potion. #");
				System.out.println(" # You now have " + numHealthPotions + " health potions. #");
			}
			
			System.out.println("--------------------------------------------------");
			System.out.println(" # What would you like to do now ?");
			System.out.println("1. Continue fighting ");
			System.out.println("2. Exit Dungeon ");

			String input = in.nextLine();
			
			while(!input.equals("1") && !input.equals("2")) {
				System.out.println("Invalid Command");
				input = in.nextLine();
			}
			
			if(input.equals("1")) {
				System.out.println("You continue your adventure");
			}
			else if(input.equals("2")) {
				System.out.println("You exit the Dungeon, SUCCESS.");
				break;
			}
			
		}
		
		System.out.println("########################");
		System.out.println(" # THANKS FOR PLAYING !! # ");
		System.out.println("########################");
	}
}
