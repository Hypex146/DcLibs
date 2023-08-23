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
	
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist, Integer limit, 
			Predicate<Material> filter, Boolean except) {
		Collection<Block> blocks = new ArrayList<Block>();
		Integer counter = 0;
		while ((iterator.hasNext()) && (counter < limit)) {
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
				if (except) {
					if (!filter.test(block.getType())) {
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
		}
		return blocks;
	}
	
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist, Integer limit) {
		return getBlocks(iterator, start_loc, max_dist, limit, null, true);
	}

	public static Block getBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist, 
			Predicate<Material> filter, Boolean except) {
		Collection<Block> blocks = getBlocks(iterator, start_loc, max_dist, 1, filter, except);
		if (blocks.size() <= 0) {
			return null;
		}
		Iterator<Block> iterator_block = blocks.iterator();
		return iterator_block.next();
	}
	
	public static Block getBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist) {
		Collection<Block> blocks = getBlocks(iterator, start_loc, max_dist, 1, null, true);
		if (blocks.size() <= 0) {
			return null;
		}
		Iterator<Block> iterator_block = blocks.iterator();
		return iterator_block.next();
	}
	
}
