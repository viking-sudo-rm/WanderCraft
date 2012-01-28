package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ItemGandalfStaff extends Item {

	private static final double pi = 3.14159265;
	private static final float r = 5.0F;

	public ItemGandalfStaff (int i){
		super(i);
		maxStackSize=1;
	}
	
	private double degreesToRadians(int degrees) {
		return degrees * (pi / 180);
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer) {
		for (int j=0; j<=180; j+=5){
			float theta = (float)degreesToRadians(j);
			EntityLightningBolt entityLightningBolt = new EntityLightningBolt(world, entityPlayer.posX+(MathHelper.cos(theta)*r), entityPlayer.posY, entityPlayer.posZ+(MathHelper.sin(theta)*r));
			EntityLightningBolt entityLightningBolt1 = new EntityLightningBolt(world, entityPlayer.posX-(MathHelper.cos(theta)*r), entityPlayer.posY, entityPlayer.posZ- (MathHelper.sin(theta)*r));
			if (! world.multiplayerWorld) {
				world.entityJoinedWorld(entityLightningBolt);
				world.entityJoinedWorld(entityLightningBolt1);
			}
		}
		return itemstack;
	}
}