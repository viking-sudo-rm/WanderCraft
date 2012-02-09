package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World

public class EntityEnt extends EntityGiantZombie
{

    public EntityEnt(World world)
    {
        super(world);
        texture = "/mob/ent.png";
        moveSpeed = 1.5F;
        attackStrength = 60;
    }

    public int getMaxHealth()
    {
        return 100;
    }
	//penis9
	protected Entity findPlayerToAttack(){
	
        EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16D);
		if(entityToAttack == null && !hasPath() && worldObj.rand.nextInt(100) == 0)
        {
            List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityMob.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(32D, 8D, 32D));
            if(!list.isEmpty())
            {
                return null;//entityToAttack=(Entity)list.get(worldObj.rand.nextInt(list.size()));
            }
			return (Entity)list.get(worldObj.rand.nextInt(list.size()));
        }
		return null;
    }
	

}