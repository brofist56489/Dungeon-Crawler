package com.gb.war.items;

import java.util.Random;


public class RandomItem {
	public static Item genRanItem() {
		
		Random rand = new Random();
		int chance = rand.nextInt(13);
		//Bow
			if(chance == 0)
				return new BowItem(rand.nextInt(5) + 1);
		//Crossbow
			if(chance == 1)
				return new CrossbowItem(rand.nextInt(5) + 1);
		//EarthWand
//			if(chance == 2)
//				return new EarthWandItem(rand.nextInt(5) + 1);
		//FireWand
			if(chance == 3)
				return new FireWandItem(rand.nextInt(5) + 1);
		//IceWand
			if(chance == 4)
				return new IceWandItem(rand.nextInt(5) + 1);
		//LightningWand
			if(chance == 5)
				return new LightningWandItem(rand.nextInt(5) + 1);
		//Spear
			if(chance == 6)
				return new SpearItem(rand.nextInt(5) + 1);
		//ThowingKnife
			if(chance == 7)
				return new ThrowingKnifeItem(rand.nextInt(5) + 1);
		//Shuriken
			if(chance == 8)
				return new ShurikenItem(rand.nextInt(5) + 1);
			if(chance == 9)
				return new DungeonTeleporterItem(rand.nextInt(5) + 1);
			if(chance == 10)
				return new SheepWandItem(rand.nextInt(5) + 1);
			if(chance == 11)
				return new EarthWandItem(rand.nextInt(5) + 1);
			if(chance == 12)
				return new CowWandItem(rand.nextInt(5) + 1);
			return null;
	}
}
