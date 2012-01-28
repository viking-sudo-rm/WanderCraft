package net.minecraft.src;

public class EntityXWing extends EntityMinecart {

	public EntityXWing(World world) {
		super(world);
	}
	
	protected boolean canTriggerWalking() {
		return true;
	}

}