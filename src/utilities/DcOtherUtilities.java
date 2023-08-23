package utilities;

import java.util.List;
import java.util.function.Predicate;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;


public abstract class DcOtherUtilities {
	
	public static Predicate<Entity> createPredicate(List<EntityType> filter) {
		if (filter == null) {
			return null;
		}
		Predicate<Entity> predicate = new Predicate<Entity>() {
			@Override
			public boolean test(Entity t) {
				if (filter.contains(t.getType())) {
					return true;
				} else {
					return false;
				}
			}
		};
		return predicate;
	}

}
