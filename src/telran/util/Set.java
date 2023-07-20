package telran.util;

public interface Set<T> extends Collection<T> {
	T get(Object pattern);
	
	default boolean setEqualsTo(Object other) {
		// TODO Checks other is Set containing the same elements (no checks of order)
		
		Set<?> otherSet = (Set<?>) other;
		if(this.size() != otherSet.size()) {
			return false;
		}
		for(Object obj:otherSet) {
			if(!obj.equals(this.get(obj))) {
				return false;
			}
		}
		return false;
	}

	
}