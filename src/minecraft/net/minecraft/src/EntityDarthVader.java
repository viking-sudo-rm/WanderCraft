package net.minecraft.src;

public class EntityDarthVader extends EntityMob
{

    public EntityDarthVader(World world)
    {
        super(world);
        texture = "/mob/darthvader.png";
		attackStrength = 13;
    }

    protected int getDropItemId()
	{
		return Item.redlightsaber.shiftedIndex;
	}
	
	protected void dropFewItems(boolean flag, int i)
    {
		dropItem(Item.redlightsaber.shiftedIndex, 1);
        dropItem(302 + rand.nextInt(4), 1);
    }
	
	public int getMaxHealth()
    {
        return 60;
    }
	
	public ItemStack getHeldItem() {
		return new ItemStack(Item.redlightsaber,1);
	}
	
}
