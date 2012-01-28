package net.minecraft.src;

//change skin

public class EntityStormTrooper extends EntityMob {

    public EntityStormTrooper(World world) {
        super(world);
        texture = "/mob/stormtrooper.png";
    }
	
	public void fire(Entity entity) {
		EntityBullet bullet = new EntityBullet(worldObj, this, 1.0F);
		double d = entity.posX - posX;
        double d1 = entity.posZ - posZ;
		double d2 = (entity.posY + (double)entity.getEyeHeight()) - 0.69999998807907104D - bullet.posY;
		float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
		worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
		worldObj.entityJoinedWorld(bullet);
		bullet.setArrowHeading(d, d2 + (double)f1, d1, 1.6F, 12F);
		attackTime = 60;
		rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
        hasAttacked = true;
	}
	
	public void attackEntity(Entity entity, float f) {
        if(f < 20F && attackTime == 0) {
            for (int t = 0; t < 3; t++) {
				fire(entity);
            }
		}
    }

    protected int getDropItemId() {
		return Item.blasterpistol.shiftedIndex;
	}
	
	public int getMaxHealth() {
        return 20;
    }
	
	public ItemStack getHeldItem() {
		return new ItemStack(Item.blasterpistol,1);
	}
}
