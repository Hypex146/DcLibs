package utilities;

import java.util.Collection;
import java.util.TreeMap;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.entity.Entity;


public abstract class DcEntityUtilities {
	
	// Collection<Entity> getNearbyEntities
	// xyz + limit with direction
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double x, Double y, Double z, Predicate<Entity> filter, Integer limit, Boolean reverse) {
		Collection<Entity> entities = loc.getWorld().getNearbyEntities(loc, x, y, z, filter);
		TreeMap<Double, Entity> entities_tree = new TreeMap<Double, Entity>();
		for (Entity entity : entities) {
			entities_tree.put(entity.getLocation().distance(loc), entity);
		}
		entities.clear();
		for (int i = 0; i < entities_tree.size(); i++) {
			if (i >= limit) {
				break;
			}
			if (reverse) {
				entities.add(entities_tree.pollLastEntry().getValue());
			} else {
				entities.add(entities_tree.pollFirstEntry().getValue());
			}
		}
		return entities;
	}
	
	// xyz + limit
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double x, Double y, Double z, Predicate<Entity> filter, Integer limit) {
		return getNearbyEntities(loc, x, y, z, filter, limit, false);
	}
	
	// sphere
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter) {
		Collection<Entity> entities = loc.getWorld().getNearbyEntities(loc, dist, dist, dist, filter);
		return entities;
	}
	
	// sphere + limit
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter, Integer limit) {
		return getNearbyEntities(loc, dist, dist, dist, filter, limit, false);
	}
	
	// sphere + limit with direction
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter, Integer limit, Boolean reverse) {
		return getNearbyEntities(loc, dist, dist, dist, filter, limit, reverse);
	}
	
}
