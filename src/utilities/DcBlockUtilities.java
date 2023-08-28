package utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.BlockIterator;


public abstract class DcBlockUtilities {
	
	// Collection<Block> getBlocks
	// limit + filter
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist, Integer limit, 
			Predicate<Material> filter) {
		Collection<Block> blocks = new ArrayList<Block>();
		Integer counter = 0;
		while ( iterator.hasNext() && ((counter < limit) || (limit < 0)) ) {
			Block block = iterator.next();
			if (block.getLocation().distance(start_loc) >= max_dist) {
				break;
			}
			if (filter == null) {
				if (block.getType() != Material.AIR) {
					blocks.add(block);
					counter++;
				}
			} else {
				if (filter.test(block.getType())) {
					blocks.add(block);
					counter++;
				}
			}
		}
		return blocks;
	}
	
	// limit
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist, Integer limit) {
		return getBlocks(iterator, start_loc, max_dist, limit, null);
	}
	
	// filter
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist, Predicate<Material> filter) {
		return getBlocks(iterator, start_loc, max_dist, -1, filter);
	}
	
	//
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist) {
		return getBlocks(iterator, start_loc, max_dist, -1, null);
	}
	
	
	// Block getBlock
	// filter
	public static Block getBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist, 
			Predicate<Material> filter) {
		Collection<Block> blocks = getBlocks(iterator, start_loc, max_dist, 1, filter);
		if (blocks.size() <= 0) {
			return null;
		}
		Iterator<Block> iterator_block = blocks.iterator();
		return iterator_block.next();
	}
	
	//
	public static Block getBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist) {
		Collection<Block> blocks = getBlocks(iterator, start_loc, max_dist, 1, null);
		if (blocks.size() <= 0) {
			return null;
		}
		Iterator<Block> iterator_block = blocks.iterator();
		return iterator_block.next();
	}
	
	
	// Boolean hasBlock
	// filter
	public static Boolean hasBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist, 
			Predicate<Material> filter) {
		Collection<Block> blocks = getBlocks(iterator, start_loc, max_dist, 1, filter);
		if (blocks.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//
	public static Boolean hasBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist) {
		Collection<Block> blocks = getBlocks(iterator, start_loc, max_dist, 1, null);
		if (blocks.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
