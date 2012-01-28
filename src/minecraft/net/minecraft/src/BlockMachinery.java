package net.minecraft.src;

import java.util.Random;

public class BlockMachinery extends Block {

	public BlockMachinery(int i, int j) {
        super(i, j, Material.iron);
    }
	
	public int idDropped(int i, Random random, int j) {
		return Block.button.blockID;
	}

}