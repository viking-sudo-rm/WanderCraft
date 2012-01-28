// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Achievement, Item, Block

public class AchievementList
{

    public static int minDisplayColumn;
    public static int minDisplayRow;
    public static int maxDisplayColumn;
    public static int maxDisplayRow;
    public static List achievementList;
    public static Achievement openInventory;
    public static Achievement mineWood;
    public static Achievement buildWorkBench;
    public static Achievement buildPickaxe;
    public static Achievement buildFurnace;
    public static Achievement acquireIron;
    public static Achievement buildHoe;
    public static Achievement makeBread;
    public static Achievement bakeCake;
    public static Achievement buildBetterPickaxe;
    public static Achievement cookFish;
    public static Achievement onARail;
    public static Achievement buildSword;
    public static Achievement killEnemy;
    public static Achievement killCow;
    public static Achievement flyPig;
    public static Achievement snipeSkeleton;
    public static Achievement diamonds;
    public static Achievement portal;
    public static Achievement ghast;
    public static Achievement blazeRod;
    public static Achievement potion;
    public static Achievement theEnd;
    public static Achievement theEnd2;
    public static Achievement enchantments;
    public static Achievement overkill;
    public static Achievement bookcase;
	//SnorriDev
	public static Achievement mayTheForceBeWithYou;
	public static Achievement shootEmUp;
	public static Achievement youAreNotMyFather;
	public static Achievement likeFatherLikeSon;
	public static Achievement thisIsntStarWars;
	//SnorriDev

    public AchievementList()
    {
    }

    public static void func_27374_a()
    {
    }

    static 
    {
        achievementList = new ArrayList();
        openInventory = (new Achievement(0, "openInventory", 0, 0, Item.book, null)).setIndependent().registerAchievement();
        mineWood = (new Achievement(1, "mineWood", 2, 1, Block.wood, openInventory)).registerAchievement();
        buildWorkBench = (new Achievement(2, "buildWorkBench", 4, -1, Block.workbench, mineWood)).registerAchievement();
        buildPickaxe = (new Achievement(3, "buildPickaxe", 4, 2, Item.pickaxeWood, buildWorkBench)).registerAchievement();
        buildFurnace = (new Achievement(4, "buildFurnace", 3, 4, Block.stoneOvenActive, buildPickaxe)).registerAchievement();
        acquireIron = (new Achievement(5, "acquireIron", 1, 4, Item.ingotIron, buildFurnace)).registerAchievement();
        buildHoe = (new Achievement(6, "buildHoe", 2, -3, Item.hoeWood, buildWorkBench)).registerAchievement();
        makeBread = (new Achievement(7, "makeBread", -1, -3, Item.bread, buildHoe)).registerAchievement();
        bakeCake = (new Achievement(8, "bakeCake", 0, -5, Item.cake, buildHoe)).registerAchievement();
        buildBetterPickaxe = (new Achievement(9, "buildBetterPickaxe", 6, 2, Item.pickaxeStone, buildPickaxe)).registerAchievement();
        cookFish = (new Achievement(10, "cookFish", 2, 6, Item.fishCooked, buildFurnace)).registerAchievement();
        onARail = (new Achievement(11, "onARail", 2, 3, Block.rail, acquireIron)).setSpecial().registerAchievement();
        buildSword = (new Achievement(12, "buildSword", 6, -1, Item.swordWood, buildWorkBench)).registerAchievement();
        killEnemy = (new Achievement(13, "killEnemy", 8, -1, Item.bone, buildSword)).registerAchievement();
        killCow = (new Achievement(14, "killCow", 7, -3, Item.leather, buildSword)).registerAchievement();
        flyPig = (new Achievement(15, "flyPig", 8, -4, Item.saddle, killCow)).setSpecial().registerAchievement();
        snipeSkeleton = (new Achievement(16, "snipeSkeleton", 7, 0, Item.bow, killEnemy)).setSpecial().registerAchievement();
        diamonds = (new Achievement(17, "diamonds", -1, 5, Item.diamond, acquireIron)).registerAchievement();
        portal = (new Achievement(18, "portal", -1, 7, Block.obsidian, diamonds)).registerAchievement();
        ghast = (new Achievement(19, "ghast", -4, 8, Item.ghastTear, portal)).setSpecial().registerAchievement();
        blazeRod = (new Achievement(20, "blazeRod", 0, 9, Item.blazeRod, portal)).registerAchievement();
        potion = (new Achievement(21, "potion", 2, 8, Item.potion, blazeRod)).registerAchievement();
        theEnd = (new Achievement(22, "theEnd", 3, 10, Item.eyeOfEnder, blazeRod)).setSpecial().registerAchievement();
        theEnd2 = (new Achievement(23, "theEnd2", 4, 13, Block.field_41050_bK, theEnd)).setSpecial().registerAchievement();
        enchantments = (new Achievement(24, "enchantments", -4, 4, Block.enchantmentTable, diamonds)).registerAchievement();
        overkill = (new Achievement(25, "overkill", -4, 1, Item.swordDiamond, enchantments)).setSpecial().registerAchievement();
        bookcase = (new Achievement(26, "bookcase", -3, 6, Block.bookShelf, enchantments)).registerAchievement();
		
		//SnorriDev add Gandalf Staff
		mayTheForceBeWithYou = (new Achievement(27, "mayTheForceBeWithYou", -2, 0, Item.greenlightsaber, openInventory)).registerAchievement();
		shootEmUp = (new Achievement(28, "shootEmUp", -6, 0, Item.blasterpistol, mayTheForceBeWithYou)).registerAchievement();
		youAreNotMyFather = (new Achievement(29, "youAreNotMyFather", -4, -2, Item.redlightsaber, mayTheForceBeWithYou)).registerAchievement();
		likeFatherLikeSon = (new Achievement(30, "likeFatherLikeSon", -4, -4, Item.helmetChain, youAreNotMyFather)).registerAchievement();
		thisIsntStarWars = (new Achievement(31, "thisIsntStarWars", 0, -2, Item.gandalfstaff, mayTheForceBeWithYou)).registerAchievement();
		//SnorriDev
		
        System.out.println((new StringBuilder()).append(achievementList.size()).append(" achievements").toString());
    }
}
