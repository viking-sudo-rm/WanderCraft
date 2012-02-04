package net.minecraft.src;

public class EntityXWing extends EntityBoat {

	private static final float pi = 3.1415F;
	public float velocity = 0F;
	private int timeInAir = 0;
	public float pitch = 0F;
	public float yaw = 0F;
	public CooldownMeter machineGun;
	public CooldownMeter missileLauncher;
	
	//
	//TODO
	//Better bullet velocities
	//GUI
	//Model/render
	//Pitch rotation
	//

	public EntityXWing(World world) {
		super(world);
		machineGun = new CooldownMeter(75,0D,3000D,10D);
		missileLauncher = new CooldownMeter(7,20D,5000D,1000D);
	}
	
	private float applyTerminal(float v) {
		if (v > 1.0F) {
			return 1.0F;
		}
		return v;
	}
	
	private float deg2rad(float degrees) {
		return degrees * (pi / 180F);
	}
	
	@Override
	public void onUpdate() {
		onEntityUpdate();
		moveEntity(motionX, motionY, motionZ);
		if (riddenByEntity != null) {
			float acceleration = 0.01F * ((EntityPlayerSP)riddenByEntity).movementInput.moveForward;
			pitch = riddenByEntity.rotationPitch;
			yaw = riddenByEntity.rotationYaw;
			velocity = applyTerminal(velocity + acceleration);
			float velocityFlat = MathHelper.cos(deg2rad(pitch)) * velocity;
			motionY = -MathHelper.sin(deg2rad(pitch)) * velocity;
			motionX = -MathHelper.sin(deg2rad(yaw)) * velocityFlat;
			motionZ = MathHelper.cos(deg2rad(yaw)) * velocityFlat;				
			
			setRotation(yaw - 90,pitch);
			riddenByEntity.setRotation(yaw,pitch);
			
			System.out.println(machineGun.isOverheated());
			if (((EntityPlayerSP)riddenByEntity).movementInput.moveStrafe > 0.0F && machineGun.shoot()) {
				fire1((float) motionY);
			}
			if (((EntityPlayerSP)riddenByEntity).movementInput.moveStrafe < 0.0F && missileLauncher.shoot()) {
				fire2((float) motionY);
			}
			
			if (riddenByEntity.isDead) {
				riddenByEntity = null;
			}
			
		}
		
		motionY -= 0.001D * (double)timeInAir;
		
		if (onGround) {
			timeInAir = 0;
			velocity *= 0.9F;
			System.out.println(motionY);
			if ((float)motionY < -0.2F) {
				explode();
			}
		}
		else {
			timeInAir++;
			velocity *= 0.99F;
		}
		
    }
	
	public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        if(worldObj.multiplayerWorld || isDead)
        {
            return true;
        }
        func_41017_d(-func_41016_i());
        func_41019_c(10);
        func_41015_b(func_41020_g() + i * 10);
        setBeenAttacked();
        if(func_41020_g() > 40)
        {
            if(riddenByEntity != null)
            {
                riddenByEntity.mountEntity(this);
            }
            setEntityDead();
        }
        return true;
    }

	
	public void fire1(float deltay) {
		float infront = 4F;
		float toside = 2.5F;
		float basex = (float) (posX - MathHelper.sin(deg2rad(yaw)) * infront);
		float basez = (float) (posZ + MathHelper.cos(deg2rad(yaw)) * infront);
		float xtrans = (float) (MathHelper.cos(deg2rad(yaw)) * toside);
		float ztrans = (float) (MathHelper.sin(deg2rad(yaw)) * toside);
		EntityBullet bullet = new EntityBullet(worldObj,basex + xtrans,posY - 0.6D,basez - ztrans);
		EntityBullet bullet1 = new EntityBullet(worldObj,basex - xtrans,posY - 0.6D,basez + ztrans);
		bullet.setVelocity(motionX  * 30D,deltay * 30D,motionZ * 30D);
		bullet1.setVelocity(motionX * 30D,deltay * 30D,motionZ * 30D);
		//bullet.setVelocity(motionX + (motionX / motionX) * 60D,deltay + (deltay / motionX) * 60D,motionZ + (motionZ / motionX) * 60D);
		//bullet1.setVelocity(motionX * (motionX / motionX) * 60D,deltay + (deltay / motionX) * 60D,motionZ + (motionZ / motionX) * 60D);
		if (! worldObj.multiplayerWorld) {
			worldObj.entityJoinedWorld(bullet);
			worldObj.entityJoinedWorld(bullet1);
		}
	}
	
	public void fire2(float deltay) {
		float infront = 4F;
		float toside = 2.5F;
		float basex = (float) (posX - MathHelper.sin(deg2rad(yaw)) * infront);
		float basez = (float) (posZ + MathHelper.cos(deg2rad(yaw)) * infront);
		float xtrans = (float) (MathHelper.cos(deg2rad(yaw)) * toside);
		float ztrans = (float) (MathHelper.sin(deg2rad(yaw)) * toside);
		EntityMissile bullet = new EntityMissile(worldObj,basex + xtrans,posY - 0.6D,basez - ztrans);
		EntityMissile bullet1 = new EntityMissile(worldObj,basex - xtrans,posY - 0.6D,basez + ztrans);
		bullet.setVelocity(motionX  * 20D,deltay * 20D,motionZ * 20D);
		bullet1.setVelocity(motionX * 20D,deltay * 20D,motionZ * 20D);
		//bullet.setVelocity(motionX + (motionX / motionX) * 60D,deltay + (deltay / motionX) * 60D,motionZ + (motionZ / motionX) * 60D);
		//bullet1.setVelocity(motionX * (motionX / motionX) * 60D,deltay + (deltay / motionX) * 60D,motionZ + (motionZ / motionX) * 60D);
		if (! worldObj.multiplayerWorld) {
			worldObj.entityJoinedWorld(bullet);
			worldObj.entityJoinedWorld(bullet1);
		}
	}
	
	public void explode() {
		setEntityDead();
		worldObj.createExplosion(null,posX,posY,posZ,7.0F);
	}
	
	    public void updateRiderPosition() {
        if (riddenByEntity == null) {
            return;
        }
		else {
            riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ);
            return;
        }
    }

}