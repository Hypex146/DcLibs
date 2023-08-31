package utilities;

import java.util.ArrayList;
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
	
	
	/**
	 * <p>
	 * Get a list of entities within a bounding box 
	 * centered around a Location
	 * </p><p>
	 * Note: 1. xyz, limit, direction
	 * </p>
	 * @param loc - The center of the bounding box
	 * @param x - The 1/2 size of the box along x axis
	 * @param y - The 1/2 size of the box along y axis
	 * @param z - The 1/2 size of the box along z axis
	 * @param filter - Only entities that fulfill this predicate 
	 * are considered, or null to consider all entities
	 * @param limit - Maximum number of selected entities
	 * @param reverse - Select the most distant entities first
	 * @return A collection of selected entities
	 * @since 0.0.1
	 */
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double x, Double y, Double z, Predicate<Entity> filter, 
			Integer limit, Boolean reverse) {
		Collection<Entity> entities = loc.getWorld().getNearbyEntities(
				loc, x, y, z, filter);
		if ( limit < 0 ) {
			return entities;
		}
		TreeMap<Double, Entity> entities_tree = new TreeMap<Double, Entity>();
		for (Entity entity : entities) {
			entities_tree.put(entity.getLocation().distance(loc), entity);
		}
		entities.clear();
		for (Integer i = 0; i < entities_tree.size(); i++) {
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
	
	/**
	 * <p>
	 * Note: 2. xyz, limit
	 * </p>
	 * @see #getNearbyEntities(Location, Double, 
	 * Double, Double, Predicate, Integer, Boolean)
	 */
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double x, Double y, Double z, Predicate<Entity> filter, 
			Integer limit) {
		return getNearbyEntities(loc, x, y, z, filter, limit, false);
	}
	
	/**
	 * <p>
	 * Note: 3. sphere
	 * </p>
	 * @see #getNearbyEntities(Location, Double, 
	 * Double, Double, Predicate, Integer, Boolean)
	 */
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter) {
		return getNearbyEntities(loc, dist, dist, dist, filter, -1, false);
	}
	
	/**
	 * <p>
	 * Note: 4. sphere, limit
	 * </p>
	 * @see #getNearbyEntities(Location, Double, 
	 * Double, Double, Predicate, Integer, Boolean)
	 */
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter, Integer limit) {
		return getNearbyEntities(loc, dist, dist, dist, filter, limit, false);
	}
	
	/**
	 * <p>
	 * Note: 5. sphere, limit, direction
	 * </p>
	 * @see #getNearbyEntities(Location, Double, 
	 * Double, Double, Predicate, Integer, Boolean)
	 */
	public static Collection<Entity> getNearbyEntities(Location loc, 
			Double dist, Predicate<Entity> filter, 
			Integer limit, Boolean reverse) {
		return getNearbyEntities(loc, dist, dist, dist, 
				filter, limit, reverse);
	}
	
	
	/**
	 * <p>
	 * Find out if one entity is in the field of view of another entity
	 * </p><p>
	 * Note: 1. filter
	 * </p>
	 * @param observer - The entity that observes
	 * @param observed - The entity being watched
	 * @param max_dist - Maximum observation distance
	 * @param cos_detect_angle - The value of the cosine 
	 * of the observer's field of view angle
	 * @param filter - A predicate that selects materials 
	 * through which the observer cannot see
	 * @return Does the observer see the observed?
	 * @since 0.0.1
	 */
	public static Boolean inSight(LivingEntity observer, 
			LivingEntity observed, 
			Double max_dist, Double cos_detect_angle, 
			Predicate<Material> filter) {
		if (observer.equals(observed)) {
			return false;
			}
	    Location observer_eye = observer.getEyeLocation();
	    Vector observer_observed = 
	    		observed.getEyeLocation().toVector().subtract(
	    				observer_eye.toVector());
	    Double cos_angle = observer_observed.normalize().dot(
	    		observer_eye.getDirection());
	    if (DcBlockUtilities.hasBlock(new BlockIterator(observer), 
	    		observer_eye, max_dist, filter)) {
	    	return false;
	    }
	    return cos_angle >= cos_detect_angle;
	}
	
	/**
	 * <p>
	 * Note: 2.
	 * </p>
	 * @see #inSight(LivingEntity, LivingEntity, Double, Double, Predicate)
	 */
	public static Boolean inSight(LivingEntity observer, 
			LivingEntity observed, Double max_dist, 
			Double cos_detect_angle) {
		return inSight(observer, observed, max_dist, cos_detect_angle, null);
	}
	
	
	/**
	 * <p>
	 * Get entities that are in the field of view of the observing entity
	 * </p><p>
	 * Note: 1. material_filter, entity_filter, limit, reverse
	 * </p>
	 * @param observer - An observer entity in whose field of 
	 * view other entities are being searched
	 * @param max_dist - Maximum observation distance
	 * @param cos_detect_angle - The value of the cosine 
	 * of the observer's field of view angle
	 * @param entity_filter - A predicate that determines which 
	 * entities should be considered, or null to consider all entities
	 * @param material_filter - A predicate that selects materials 
	 * through which the observer cannot see
	 * @param limit - Maximum number of selected entities
	 * @param reverse - Select the most distant entities first
	 * @return A collection of entities located in a given field of 
	 * view of the observer
	 * @since 0.0.1
	 */
	public static Collection<LivingEntity> getInSight(LivingEntity observer, 
			Double max_dist, Double cos_detect_angle, 
			Predicate<Entity> entity_filter, 
			Predicate<Material> material_filter, Integer limit, 
			Boolean reverse) {
		TreeMap<Double, LivingEntity> observered_tree = 
				new TreeMap<Double, LivingEntity>();
		for (Entity nearby_entity : getNearbyEntities(
				observer.getLocation(), max_dist, entity_filter)) {
			if ( !(nearby_entity instanceof LivingEntity) ) { 
				continue; 
			}
			if ( inSight(observer, (LivingEntity) nearby_entity, 
					max_dist, cos_detect_angle, material_filter) ) {
				observered_tree.put(observer.getLocation().distance(
						nearby_entity.getLocation()), 
						(LivingEntity) nearby_entity);
			}
		}
		if (limit < 0) {
			return observered_tree.values();
		}
		Collection<LivingEntity> observered = new ArrayList<LivingEntity>();
		for (Integer i = 0; i < observered_tree.size(); i++) {
			if (i >= limit) {
				break;
			}
			if (reverse) {
				observered.add(observered_tree.pollLastEntry().getValue());
			} else {
				observered.add(observered_tree.pollFirstEntry().getValue());
			}
		}
		return observered;
	}
	
	/**
	 * <p>
	 * Note: 2. material_filter, entity_filter, limit
	 * </p>
	 * @see #getInSight(LivingEntity, Double, Double, 
	 * Predicate, Predicate, Integer, Boolean)
	 */
	public static Collection<LivingEntity> getInSight(LivingEntity observer, 
			Double max_dist, Double cos_detect_angle, 
			Predicate<Entity> entity_filter, 
			Predicate<Material> material_filter, Integer limit) {
		return getInSight(observer, max_dist, cos_detect_angle, entity_filter, 
				material_filter, limit, false);
	}
	
	/**
	 * <p>
	 * Note: 3. material_filter, entity_filter
	 * </p>
	 * @see #getInSight(LivingEntity, Double, Double, 
	 * Predicate, Predicate, Integer, Boolean)
	 */
	public static Collection<LivingEntity> getInSight(LivingEntity observer, 
			Double max_dist, Double cos_detect_angle, 
			Predicate<Entity> entity_filter, 
			Predicate<Material> material_filter) {
		return getInSight(observer, max_dist, cos_detect_angle, entity_filter, 
				material_filter, -1, false);
	}
	
	/**
	 * <p>
	 * Note: 4.
	 * </p>
	 * @see #getInSight(LivingEntity, Double, Double, 
	 * Predicate, Predicate, Integer, Boolean)
	 */
	public static Collection<LivingEntity> getInSight(LivingEntity observer, 
			Double max_dist, Double cos_detect_angle) {
		return getInSight(
				observer, max_dist, cos_detect_angle, null, null, -1, false);
	}
	
	
	/**
	 * <p>
	 * Get entities that are watching another entity
	 * </p><p>
	 * Note: 1. material_filter, entity_filter, limit, reverse
	 * <p>
	 * @param observed - The entity that is the object of observation
	 * @param max_dist - Maximum observation distance
	 * @param cos_detect_angle - The value of the cosine 
	 * of the observer's field of view angle
	 * @param entity_filter -  A predicate that determines which 
	 * entities should be considered, or null to consider all entities
	 * @param material_filter - A predicate that selects materials 
	 * through which the observer cannot see
	 * @param limit - Maximum number of selected entities
	 * @param reverse - Select the most distant entities first
	 * @return A collection of entities that observes the passed entity
	 * @since 0.0.1
	 */
	public static Collection<LivingEntity> getObservers(
			LivingEntity observed, Double max_dist, Double cos_detect_angle, 
			Predicate<Entity> entity_filter, 
			Predicate<Material> material_filter, 
			Integer limit, Boolean reverse) {
		TreeMap<Double, LivingEntity> observers_tree = 
				new TreeMap<Double, LivingEntity>();
		for (Entity nearby_entity : getNearbyEntities(
				observed.getLocation(), max_dist, entity_filter)) {
			if ( !(nearby_entity instanceof LivingEntity) ) { 
				continue; 
			}
			if ( inSight((LivingEntity) nearby_entity, 
					observed, max_dist, cos_detect_angle, material_filter) ) {
				observers_tree.put(nearby_entity.getLocation().distance(
						observed.getLocation()), (LivingEntity) nearby_entity);
			}
		}
		if (limit < 0) {
			return observers_tree.values();
		}
		Collection<LivingEntity> observers = new ArrayList<LivingEntity>();
		for (Integer i = 0; i < observers_tree.size(); i++) {
			if (i >= limit) {
				break;
			}
			if (reverse) {
				observers.add(observers_tree.pollLastEntry().getValue());
			} else {
				observers.add(observers_tree.pollFirstEntry().getValue());
			}
		}
		return observers;
	}
	
	/**
	 * <p>
	 * Note: 2. material_filter, entity_filter, limit
	 * </p>
	 * @see #getObservers(LivingEntity, Double, Double, Predicate, 
	 * Predicate, Integer, Boolean)
	 */
	public static Collection<LivingEntity> getObservers(
			LivingEntity observed, Double max_dist, Double cos_detect_angle, 
			Predicate<Entity> entity_filter,
			Predicate<Material> material_filter, Integer limit) {
		return getObservers(observed, max_dist, cos_detect_angle, 
				entity_filter, material_filter, limit, false);
	}
	
	/**
	 * <p>
	 * Note: 3. material_filter, entity_filter
	 * </p>
	 * @see #getObservers(LivingEntity, Double, Double, Predicate, 
	 * Predicate, Integer, Boolean)
	 */
	public static Collection<LivingEntity> getObservers(
			LivingEntity observed, Double max_dist, Double cos_detect_angle, 
			Predicate<Entity> entity_filter,
			Predicate<Material> material_filter) {
		return getObservers(observed, max_dist, 
				cos_detect_angle, entity_filter, material_filter, -1, false);
	}
	
	/**
	 * <p>
	 * Note: 4.
	 * </p>
	 * @see #getObservers(LivingEntity, Double, Double, Predicate, 
	 * Predicate, Integer, Boolean)
	 */
	public static Collection<LivingEntity> getObservers(
			LivingEntity observed, Double max_dist, Double cos_detect_angle) {
		return getObservers(observed, max_dist, 
				cos_detect_angle, null, null, -1, false);
	}
	
}
