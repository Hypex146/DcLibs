package utilities;

import java.util.Collection;
import java.util.function.Predicate;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;


public abstract class DcOtherUtilities {
	
	
	/**
	 * <p>
	 * createPredicate
	 * </p><p>
	 * Note: 1. include
	 * </p>
	 * @param filter - Objects to compare
	 * @param contain - Selecting objects that are in the list?
	 * @return Predicate - filter
	 * @since 0.0.1
	 */
	public static Predicate<Entity> createPredicate(
			Collection<EntityType> filter, Boolean contain) {
		Predicate<Entity> predicate;
		if (filter == null) {
			predicate = new Predicate<Entity>() {
				@Override
				public boolean test(Entity t) {
					return !contain;
				}
			};
		} else {
			predicate = new Predicate<Entity>() {
				@Override
				public boolean test(Entity t) {
					if (filter.contains(t.getType())) {
						return contain;
					} else {
						return contain;
					}
				}
			};
		}
		return predicate;
	}
	
	/**
	 * <p>
	 * Note: 2.
	 * </p>
	 * @see #createPredicate(Collection, Boolean)
	 */
	public static Predicate<Entity> createPredicate(
			Collection<EntityType> filter) {
		return createPredicate(filter, true);
	}

}
