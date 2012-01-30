package net.minecraft.src;

public class EntityBullet extends EntityArrow {

	public EntityBullet(World world,EntityLiving dude, float f) {
		super(world,dude,f);
		bullet = true;
		
	}
	
	public EntityBullet(World world, double x, double y, double z) {
		super(world,x,y,z);
		bullet = true;
	}
	
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
        if(worldObj.multiplayerWorld) {
			return;
        }
        if(inGround && doesArrowBelongToPlayer && arrowShake <= 0) {
            worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            setEntityDead();
        }
    }

}