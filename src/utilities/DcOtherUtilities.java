package utilities;

import java.util.List;
import java.util.function.Predicate;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;


public abstract class DcOtherUtilities {
	
	
	// Predicate<Entity> createPredicate
	// 1. include
	public static Predicate<Entity> createPredicate(List<EntityType> filter, Boolean contain) {
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
	
	// 2.
	public static Predicate<Entity> createPredicate(List<EntityType> filter) {
		return createPredicate(filter, true);
	}

}
