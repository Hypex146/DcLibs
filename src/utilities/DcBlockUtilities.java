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
	
	
	/**
	 * <p>
	 * Get blocks intersected by a given vector
	 * </p><p>
	 * Note: 1. limit, filter
	 * </p>
	 * @param iterator - Iterator defining the block selection vector
	 * @param start_loc - The position of the beginning of the vector
	 * @param max_dist - Maximum block search distance
	 * @param limit - Maximum number of selected blocks
	 * @param filter - Block selection condition
	 * @return A collection of blocks, maybe empty
	 * @since 0.0.1
	 */
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
	
	/**
	 * <p>
	 * Note: 2. limit
	 * </p>
	 * @see #getBlocks(BlockIterator, Location, Double, Integer, Predicate)
	 */
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist, Integer limit) {
		return getBlocks(iterator, start_loc, max_dist, limit, null);
	}
	
	/**
	 * <p>
	 * Note: 3. filter
	 * </p>
	 * @see #getBlocks(BlockIterator, Location, Double, Integer, Predicate)
	 */
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist, Predicate<Material> filter) {
		return getBlocks(iterator, start_loc, max_dist, -1, filter);
	}
	
	/**
	 * <p>
	 * Note: 4.
	 * </p>
	 * @see #getBlocks(BlockIterator, Location, Double, Integer, Predicate)
	 */
	public static Collection<Block> getBlocks(BlockIterator iterator, 
			Location start_loc, Double max_dist) {
		return getBlocks(iterator, start_loc, max_dist, -1, null);
	}
	
	
	/**
	 * <p>
	 * Get the first block intersecting with a given vector
	 * </p><p>
	 * Note: 1. filter
	 * <p>
	 * @param iterator - Iterator defining the block selection vector
	 * @param start_loc - The position of the beginning of the vector
	 * @param max_dist - Maximum block search distance
	 * @param filter - Block selection condition
	 * @return intersected block, maybe null
	 * @since 0.0.1
	 */
	public static Block getBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist, 
			Predicate<Material> filter) {
		Collection<Block> blocks = getBlocks(
				iterator, start_loc, max_dist, 1, filter);
		if (blocks.size() <= 0) {
			return null;
		}
		Iterator<Block> iterator_block = blocks.iterator();
		return iterator_block.next();
	}
	
	/**
	 * <p>
	 * Note: 2.
	 * </p>
	 * @see #getBlock(BlockIterator, Location, Double, Predicate)
	 */
	public static Block getBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist) {
		return getBlock(iterator, start_loc, max_dist, null);
	}
	
	
	/**
	 * <p>
	 * Does the vector intersect any block?
	 * </p><p>
	 * Note: 1. filter
	 * </p>
	 * @param iterator - Iterator defining the block selection vector
	 * @param start_loc - The position of the beginning of the vector
	 * @param max_dist - Maximum block search distance
	 * @param filter - Block selection condition
	 * @return True - if the vector intersects the block, considering 
	 * the filter, otherwise false
	 * @since 0.0.1
	 */
	public static Boolean hasBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist, 
			Predicate<Material> filter) {
		Collection<Block> blocks = getBlocks(
				iterator, start_loc, max_dist, 1, filter);
		if (blocks.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * <p>
	 * Note: 2.
	 * </p>
	 * @see #hasBlock(BlockIterator, Location, Double, Predicate)
	 */
	public static Boolean hasBlock(BlockIterator iterator, 
			Location start_loc, Double max_dist) {
		return hasBlock(iterator, start_loc, max_dist, null);
	}
	
}
