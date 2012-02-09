package net.minecraft.src;

public class ItemBattleAxe extends ItemSword {

	private CooldownMeter reload;

	public ItemBattleAxe(int i, EnumToolMaterial material) {
		super(i,material);
		setMaxDamage(material.getMaxUses());
		weaponDamage = 6 + material.getDamageVsEntity();
		reload = new CooldownMeter(1,2000D,2000D,2000D);
	}
	
	public int getDamageVsEntity(Entity entity) {
		if (reload.shoot()) {
			System.out.println("can do damage");
			return weaponDamage;
		}
		return 0;
	}

}