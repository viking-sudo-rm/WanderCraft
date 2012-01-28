package net.minecraft.src;

public class EntityLightsaber extends EntityArrow {

	private int saber;
	
	public EntityLightsaber(World world, EntityPlayer entityPlayer, float f, int sabertype) {
		super(world, entityPlayer, f);
		saber = sabertype;
		arrow = false;
	}
	
	 public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if(worldObj.multiplayerWorld)
        {
            return;
        }
        if(inGround && doesArrowBelongToPlayer && arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(saber,1,0)))
        {
            worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.onItemPickup(this, 1);
            setEntityDead();
        }
    }
}