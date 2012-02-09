package net.minecraft.src;

public class GuiSlotGuide extends GuiSlot {

	final GuiGuide guide;
	
	public GuiSlotGuide(GuiGuide parent) {
		super(parent.mc, parent.width, parent.height, 32, parent.height - 64, 36);
		guide = parent;
	}
	
	protected void elementClicked(int i, boolean doubleClick) {
		guide.selectedEntry = i;
		if (doubleClick && guide.inBounds()) {
			guide.open(i);
		}
	}
	
	protected void drawBackground() {
		guide.drawDefaultBackground();
	}
	
	protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
		StringTranslate stringtranslate = StringTranslate.getInstance();
		IRecipe recipe = (IRecipe) guide.getEntryList().get(i);
		String name = Item.itemsList[recipe.getRecipeOutput().itemID].getItemName() + ".name";
		guide.drawString(guide.fontRenderer,stringtranslate.translateKey(name),j + 2, k + 1,0xffffff);
	}
	
	protected boolean isSelected(int i) {
		return i == guide.selectedEntry;
	}
	
	protected int getSize() {
		return guide.getEntryList().size();
	}
	
	protected int getContentHeight() {
		return getSize() * 36;
	}

}