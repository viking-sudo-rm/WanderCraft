package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntityWookie extends EntityWolf{

	private static final ItemStack defaultHeldItem;
	
	public EntityWookie(World world) {
		super(world);
		texture = "/mob/wookie.png";
	}
	

	public int getMaxHealth(){
		return 30;
	}

	
	public String getEntityTexture()
    {
        if(isWolfTamed())
        {
            return "/mob/wookie_tame.png";
        }
        if(isWolfAngry())
        {
            return "/mob/wookie_angry.png";
        } else
        {
            return super.getEntityTexture();
        }
    }
	
	//not used shiza for sitting and shiza
	
	public boolean isWolfSitting() {
		return false;
    }
	
	public float setTailRotation() {
		return 0F;
    }
	
	//not used shiza for sitting and shiza
	
	public void fire(Entity entity) {
		EntityBullet bullet = new EntityBullet(worldObj, this, 1.0F);
		double d = entity.posX - posX;
        double d1 = entity.posZ - posZ;
		double d2 = (entity.posY + (double)entity.getEyeHeight()) - 0.69999998807907104D - bullet.posY;
		float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
		worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
		worldObj.entityJoinedWorld(bullet);
		bullet.setArrowHeading(d, d2 + (double)f1, d1, 1.6F, 12F);
		attackTime = 20;
		rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
        hasAttacked = true;
	}
	
	public void attackEntity(Entity entity, float f) {
		fire(entity);
    }
	
	public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if(!isWolfTamed())
        {
            if(itemstack != null && itemstack.itemID == Item.bone.shiftedIndex && !isWolfAngry())
            {
                itemstack.stackSize--;
                if(itemstack.stackSize <= 0)
                {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
                if(!worldObj.multiplayerWorld)
                {
                    if(rand.nextInt(3) == 0)
                    {
                        setIsTamed(true);
                        setPathToEntity(null);
                        setEntityHealth(20);
                        setOwner(entityplayer.username);
                        showHeartsOrSmokeFX(true);
                        worldObj.setEntityState(this, (byte)7);
                    } else
                    {
                        showHeartsOrSmokeFX(false);
                        worldObj.setEntityState(this, (byte)6);
                    }
                }
                return true;
            }
        } else
        {
            if(itemstack != null && (Item.itemsList[itemstack.itemID] instanceof ItemFood))
            {
                ItemFood itemfood = (ItemFood)Item.itemsList[itemstack.itemID];
                if(itemfood.getIsWolfsFavoriteMeat() && dataWatcher.getWatchableObjectInt(18) < 20)
                {
                    itemstack.stackSize--;
                    heal(itemfood.getHealAmount());
                    if(itemstack.stackSize <= 0)
                    {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    return true;
                }
            }
            if(entityplayer.username.equalsIgnoreCase(getWolfOwner()))
            {
                if(!worldObj.multiplayerWorld)
                {
                    setIsSitting(!isWolfSitting());
                    isJumping = false;
                    setPathToEntity(null);
                }
                return true;
            }
        }
        return super.interact(entityplayer);
    }
	
	public ItemStack getHeldItem() {
        return defaultHeldItem;
    }
	
    static 
    {
        defaultHeldItem = new ItemStack(Item.swordGold, 1);
    }

}