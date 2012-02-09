package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import java.util.ArrayList;

public class GuiGuideEntry extends GuiScreen {

	private GuiGuide guide;
	private IRecipe recipe;

	public GuiGuideEntry(GuiGuide parent,IRecipe r) {
		guide = parent;
		recipe = r;
	}
	
	public void initGui() {
		StringTranslate stringtranslate = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
        controlList.clear();
		controlList.add(new GuiButton(0, width / 2, height - 30, 100, 20, stringtranslate.translateKey("gui.done")));
	}
	
		protected void actionPerformed(GuiButton guibutton) {
		if (! guibutton.enabled) {
			return;
		}
		if (guibutton.id == 0) {
			mc.displayGuiScreen(guide);
		}
	}
	
	public String getRecipeName() {
		return Item.itemsList[recipe.getRecipeOutput().itemID].getItemName() + ".name";
	}
	
	public void drawScreen(int i, int j, float f) {
		StringTranslate stringtranslate = StringTranslate.getInstance();
		drawDefaultBackground();
		drawInventory();
		drawCraftingGrid(getGrid(),getIsBlock());
		drawProduct(getProduct(),isBlock(recipe.getRecipeOutput()));
		drawCenteredString(fontRenderer, stringtranslate.translateKey("guide.title") + ": " + stringtranslate.translateKey(getRecipeName()), width / 2, 20, 0xffffff);
		super.drawScreen(i, j, f);
    }
	
	public int getProduct() {
		return recipe.getRecipeOutput().getIconIndex();
	}
	
	public int[][] getGrid() {
		int[][] grid = new int[3][3];
		if (recipe instanceof ShapedRecipes) {
			ShapedRecipes shape = (ShapedRecipes) recipe;
			for (int i = 0; i < shape.recipeItems.length; i++) {
				int x = i % shape.recipeWidth;
				int y = i / shape.recipeWidth;
				if (shape.recipeItems[i] != null) {
					grid[y][x] = shape.recipeItems[i].getIconIndex();
				}
			}
		}
		if (recipe instanceof ShapelessRecipes) {
			ArrayList ingredients = (ArrayList) ((ShapelessRecipes)recipe).recipeItems;
			for (int in = 0; in < ingredients.size(); in++) {
				grid[in / 3][in % 3] = ((ItemStack) ingredients.get(in)).getIconIndex();
			}
		}
		System.out.println(grid[0][0]);
		return grid;
	}
	
	private boolean[][] getIsBlock() {
		boolean[][] isBlock = new boolean[3][3];
		if (recipe instanceof ShapedRecipes) {
			ShapedRecipes shape = (ShapedRecipes) recipe;
			for (int i = 0; i < shape.recipeItems.length; i++) {
				int x = i % shape.recipeWidth;
				int y = i / shape.recipeWidth;
				if (shape.recipeItems[i] != null) {
					isBlock[y][x] = isBlock(shape.recipeItems[i]);
				}
			}
		}
		return isBlock;
	}
	
	private boolean isBlock(ItemStack itemstack) {
		System.out.println(itemstack.getItem());
		return itemstack.getItem() instanceof ItemBlock;
	}
	
	private void drawInventory() {
		int k;
		k = mc.renderEngine.getTexture("/gui/crafting.png");
        mc.renderEngine.bindTexture(k);
        drawTexturedModalRect((width - 176) / 2, (height - 166) / 2, 0, 0, 176, 166);
	}
	
	private void drawProduct(int i,boolean flag) {
		drawSingleSlot(16 + (18 * 6),16 + (18 * 1),i,flag);
	}
	
	private void drawCraftingGrid(int[][] grid, boolean[][] isBlock) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (grid[y][x] != 0) {
					drawSingleSlot(30 + (18 * x),16 + (18 * y),grid[y][x],isBlock[y][x]);
				}
			}
		}
	}
	
	private void drawSingleSlot(int x,int y,int id,boolean flag) {
		if (! flag) {
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/gui/items.png"));
		}
		else {
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/terrain.png"));
		}
		int xtrans = (width - 176) / 2;
		int ytrans = (height - 166) / 2;
		drawTexturedModalRect(xtrans + x, ytrans + y, (id % 16) * 16, (id / 16) * 16, 16, 16);
	}
	
	public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

}