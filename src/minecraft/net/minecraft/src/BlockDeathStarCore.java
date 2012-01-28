package net.minecraft.src;

public class BlockDeathStarCore extends Block {
	
	public BlockDeathStarCore(int i, int j) {
		super(i,j,Material.tnt);
	}
	
	public void explode(World world,int x,int y,int z) {
		EntityTNTPrimed entitytntprimed = new EntityDeathStarCorePrimed(world, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
		world.entityJoinedWorld(entitytntprimed);
	}
	
	public void harvestBlock(World world,EntityPlayer entityplayer,int x,int y,int z,int l) {
		explode(world,x,y,z);
	}
	
	public void onBlockDestroyedByExplosion(World world,int x,int y,int z) {
		explode(world,x,y,z);
	}
	
	public boolean canProvidePower() {
		return true;
	}
	
}