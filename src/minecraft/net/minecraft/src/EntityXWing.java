package net.minecraft.src;

public class EntityXWing extends EntityBoat {

	private static final float pi = 3.1415F;
	public float velocity = 0F;
	private int timeInAir = 0;
	public float pitch = 0F;
	public float yaw = 0F;
	
	//TODO
	//shooting, collisions, fixed rotation

	public EntityXWing(World world) {
		super(world);
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
	
	public void onUpdate() {
		onEntityUpdate();
		moveEntity(motionX, motionY, motionZ);
		if (riddenByEntity != null) {
			float acceleration = 0.01F * ((EntityPlayerSP)riddenByEntity).movementInput.moveForward;
			pitch = riddenByEntity.rotationPitch;
			yaw = riddenByEntity.rotationYaw;
			velocity = applyTerminal(velocity + acceleration);
			float velocityFlat = MathHelper.cos(deg2rad(pitch)) * velocity;
			motionY = MathHelper.sin(deg2rad(pitch)) * velocity;
			motionX = -MathHelper.sin(deg2rad(yaw)) * velocityFlat;
			motionZ = MathHelper.cos(deg2rad(yaw)) * velocityFlat;				
			
			setRotation(yaw - 90,pitch);
			riddenByEntity.setRotation(yaw,pitch);
			
			if (riddenByEntity.isDead) {
				riddenByEntity = null;
			}
			
		}
		
		motionY -= 0.001D * (double)timeInAir;
		
		if (onGround) {
			timeInAir = 0;
			velocity *= 0.9F;
			if (motionY > 0.25F) {
				explode();
			}
		}
		else {
			timeInAir++;
			velocity *= 0.99F;
		}
		
    }
	
	public void fire1() {
		EntityBullet bullet = new EntityBullet(worldObj,posX,posY,posZ);
		bullet.setVelocity(motionX * 2,motionY * 2,motionZ * 2);
		if (! worldObj.multiplayerWorld) {
			worldObj.entityJoinedWorld(bullet);
		}
	}
	
	public void fire2() {
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