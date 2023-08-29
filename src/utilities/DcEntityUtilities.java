package utilities;

import java.util.Collection;
import java.util.TreeMap;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;


public abstract class DcEntityUtilities {
	
	
	// Collection<Entity> getNearbyEntities
	// 1. xyz + limit with direction
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
	
	// 2. xyz + limit
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double x, Double y, Double z, Predicate<Entity> filter, Integer limit) {
		return getNearbyEntities(loc, x, y, z, filter, limit, false);
	}
	
	// 3. sphere
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter) {
		Collection<Entity> entities = loc.getWorld().getNearbyEntities(
				loc, dist, dist, dist, filter);
		return entities;
	}
	
	// 4. sphere + limit
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter, Integer limit) {
		return getNearbyEntities(loc, dist, dist, dist, filter, limit, false);
	}
	
	// 5. sphere + limit with direction
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter, Integer limit, Boolean reverse) {
		return getNearbyEntities(loc, dist, dist, dist, filter, limit, reverse);
	}
	
	
	// Boolean inSight
	// 1. filter
	public static Boolean inSight(LivingEntity observer, LivingEntity observed, 
			Double max_dist, double cos_detect_angle, Predicate<Material> filter) {
		if (observer.equals(observed)) {
			return false;
			}
	    Location observer_eye = observer.getEyeLocation();
	    Vector observer_observed = 
	    		observed.getEyeLocation().toVector().subtract(observer_eye.toVector());
	    double cos_angle = observer_observed.normalize().dot(observer_eye.getDirection());
	    if (DcBlockUtilities.hasBlock(new BlockIterator(observer), 
	    		observer_eye, max_dist, filter)) {
	    	return false;
	    }
	    return cos_angle >= cos_detect_angle;
	}
	
	// 2.
	public static Boolean inSight(LivingEntity observer, LivingEntity observed, 
			Double max_dist, double cos_detect_angle) {
		return inSight(observer, observed, max_dist, cos_detect_angle, null);
	}
	
}
