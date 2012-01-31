package net.minecraft.src;

public class EntityMissile extends EntityBullet {

	public EntityMissile(World world, double x, double y, double z) {
		super(world,x,y,z);
		bullet = true;
		explosive = true;
	}

}