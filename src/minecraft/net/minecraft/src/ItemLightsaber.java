package net.minecraft.src;

public class ItemLightsaber extends Item {
	
	private int weaponDamage;
	
	public ItemLightsaber(int i) {
		super(i);
		maxStackSize = 1;
		weaponDamage = 13;
	}
	
	public int getDamageVsEntity(Entity entity) {
		return weaponDamage;
	}
	
	public boolean hitEntity(ItemStack itemstack,EntityLiving entityliving,EntityLiving entityliving1) {
		playSound(entityliving);
		return true;
	}
	
	public boolean canHarvestBlock(Block block) {
		return true;
	}
	
	private void playSound(Entity entity) {
		entity.worldObj.playSoundAtEntity(entity,"snorri.saber",1.0F,1.0F);
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer) {
		EntityLightsaber entityLightsaber = new EntityLightsaber(world, entityPlayer, 2.0F,shiftedIndex);
		System.out.println(shiftedIndex);
		switch (shiftedIndex) {
			case 2286:
			entityLightsaber = (EntityLightsaber) new EntityLightsaberBlue(world, entityPlayer, 2.0F,shiftedIndex);
			break;
			case 2287:
			entityLightsaber = (EntityLightsaber) new EntityLightsaberRed(world, entityPlayer, 2.0F,shiftedIndex);
			break;
			case 2288:
			entityLightsaber = (EntityLightsaber) new EntityLightsaberGreen(world, entityPlayer, 2.0F,shiftedIndex);
			break;
			case 2289:
			entityLightsaber = (EntityLightsaber) new EntityLightsaberPurple(world, entityPlayer, 2.0F,shiftedIndex);
			break;
		}
		if (! world.multiplayerWorld) {
			world.entityJoinedWorld(entityLightsaber);
			entityPlayer.inventory.consumeInventoryItem(shiftedIndex);
		}
		return itemstack;
	}
	
}