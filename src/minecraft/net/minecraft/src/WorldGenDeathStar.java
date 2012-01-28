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
		int num = random.nextInt(101);
		if (num == 0) {
			for (int x1 = x - RADIUS; x1 < x + RADIUS; x1++) {
				for (int y1 = y - RADIUS; y1 < y + RADIUS; y1++) {
					for (int z1 = z - RADIUS; z1 < z + RADIUS; z1++) {
						if (inRange(getDistance(x, y, z, x1, y1, z1), RADIUS, 1)) {
							world.setBlockWithNotify(x1, y1, z1, Block.machinery.blockID);
						}
					}
				}
			}
		}
		return true;
	}
	
	private double getDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
		return (double)Math.sqrt( (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1) );
	}
	
	private boolean inRange(double num1, double num2, int range) {
		return (Math.abs(num2 - num1) <= range);
	}
}