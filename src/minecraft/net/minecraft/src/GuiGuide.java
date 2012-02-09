package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import java.util.List;

public class GuiGuide extends GuiScreen {

	private static GuiScreen parent;
	private static GuiSlotGuide container;
	public static int selectedEntry;

	public GuiGuide(GuiScreen gui) {
		parent = gui;
		selectedEntry = -1;
	}
	
	public void open(int i) {
		System.out.println("open!");
		mc.displayGuiScreen(new GuiGuideEntry(this,(IRecipe)getEntryList().get(i)));
	}
	
	public List getEntryList() {
		return CraftingManager.getInstance().snorriRecipes;
	}
	
	public void initGui() {
		StringTranslate stringtranslate = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
        controlList.clear();
		controlList.add(new GuiButton(0, width / 2, height - 50, 100, 20, stringtranslate.translateKey("gui.cancel")));
		controlList.add(new GuiButton(1,width / 2 - 110, height - 50, 100, 20, stringtranslate.translateKey("guide.open")));
		container = new GuiSlotGuide(this);
	}
	
	protected void actionPerformed(GuiButton guibutton) {
		if (! guibutton.enabled) {
			return;
		}
		else if (guibutton.id == 0) {
			mc.displayGuiScreen(parent);
		}
		else if (guibutton.id == 1) {
			if (inBounds()) open(selectedEntry);
		}
		else {
			container.actionPerformed(guibutton);
		}
	}
	
	public boolean inBounds() {
		return selectedEntry >= 0 && selectedEntry < getEntryList().size();
	}
	
	public void drawScreen(int i, int j, float f) {
		StringTranslate stringtranslate = StringTranslate.getInstance();
		drawDefaultBackground();
		container.drawScreen(i,j,f);
		drawCenteredString(fontRenderer, stringtranslate.translateKey("guide.title"), width / 2, 20, 0xffffff);
		super.drawScreen(i, j, f);
    }
	
	public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

}