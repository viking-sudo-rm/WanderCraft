package net.minecraft.src;

import java.util.Random;
import java.lang.Math;
import java.util.Arrays;

public class WorldGenDeathStar extends WorldGenerator
{	
	public WorldGenDeathStar() {
	}
	
	public boolean generate(World world, Random random, int x, int y, int z) {
		int RADIUS = random.nextInt(11) + 10;
		EntityLiving troopers[] = new EntityLiving[6];
		int num = random.nextInt(50);
		if (num == 0 && !blocksInArea(world, x, y, z, RADIUS)) {
			System.out.println("Death star generated at " + x + ", " + y + ", " + z);
			for (int x1 = x - RADIUS; x1 < x + RADIUS; x1++) {
				for (int y1 = y - RADIUS; y1 < y + RADIUS; y1++) {
					for (int z1 = z - RADIUS; z1 < z + RADIUS; z1++) {
						if (inRange(getDistance(x, y, z, x1, y1, z1), RADIUS, 1) || (y1 == y && getDistance(x, y, z, x1, y1, z1) <= RADIUS && getDistance(x, y, z, x1, y1, z1) > 3 )) {
							world.setBlockWithNotify(x1, y1, z1, Block.machinery.blockID);
						}
					}
				}
			}
			for (int x1 = x - 1; x1 <= x+1; x1++) {
				for (int y1 = y - 1; y1 <= y+1; y1++) {
					for (int z1 = z - 1; z1 <= z+1; z1++) {
						world.setBlockWithNotify(x1, y1, z1, Block.glass.blockID);
					}
				}
			}
						
			world.setBlockWithNotify(x, y, z, Block.deathstarcore.blockID);
			
			world.setBlockWithNotify(x+3, y, z, Block.glowStone.blockID);
			world.setBlockWithNotify(x, y, z+3, Block.glowStone.blockID);
			world.setBlockWithNotify(x-3, y, z, Block.glowStone.blockID);
			world.setBlockWithNotify(x, y, z-3, Block.glowStone.blockID);
			
			for (int i = 0; i < troopers.length - 1; i++) {
				troopers[i] = (EntityLiving)EntityList.createEntityInWorld("StormTrooper", world);
				
				double x1 = (double)x + (world.rand.nextDouble() - world.rand.nextDouble()) * 10D;
				double z1 = (double)z + (world.rand.nextDouble() - world.rand.nextDouble()) * 10D;
				troopers[i].setLocationAndAngles(x1, y+5, z1, world.rand.nextFloat() * 360F, 0.0F);
				
				world.entityJoinedWorld(troopers[i]);
				
				System.out.println("Stormtrooper spawned");
			}
			
			troopers[5] = (EntityLiving)EntityList.createEntityInWorld("DarthVader", world);
			
			double x1 = (double)x + (world.rand.nextDouble() - world.rand.nextDouble()) * 6D + 4D;
			double z1 = (double)z + (world.rand.nextDouble() - world.rand.nextDouble()) * 6D + 4D;
			troopers[5].setLocationAndAngles(x1, y+5, z1, world.rand.nextFloat() * 360F, 0.0F);
			
			world.entityJoinedWorld(troopers[5]);
			System.out.println("Vader spawned");
		}
		return true;
	}
	
	private double getDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
		return (double)Math.sqrt( (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1) );
	}
	
	private boolean inRange(double num1, double num2, int range) {
		return (Math.abs(num2 - num1) <= range);
	}
	
	private boolean blocksInArea(World world, int x, int y, int z, int distance) {
		for (int x1 = x - (distance + 10); x1 < x + (distance + 10); x1++) {
			for (int y1 = y - (distance + 10); y1 < y + (distance + 10); y1++) {
				for (int z1 = z - (distance + 10); z1 < z + (distance + 10); z1++) {
					if (world.getBlockId(x1, y1, z1) != 0 || y1 >= 125) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean inAir(int x, int y, int z) {
		return (y >= 85);
	}
}