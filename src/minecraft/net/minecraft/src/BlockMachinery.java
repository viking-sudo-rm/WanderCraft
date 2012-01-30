package net.minecraft.src;

import java.util.Random;

public class BlockMachinery extends Block {

	public BlockMachinery(int i, int j) {
        super(i, j, Material.iron);
    }
	
	public int idDropped(int i, Random random, int j) {
		return Block.button.blockID;
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.getBlockId(x,y - 1,z) == Block.deathstarcore.blockID) {
			if (! world.multiplayerWorld) {
			world.setBlockWithNotify(x,y,z,0);
			world.setBlockWithNotify(x,y - 1,z,0);
			EntityXWing xwing = new EntityXWing(world);
			xwing.setLocationAndAngles((double) x, (double) y, (double) z, 0F, 0F);
			world.entityJoinedWorld(xwing);
			System.out.println("X-wing spawned");
			}
		}
	}

}