package a8.utils;

import java.util.Comparator;

import org.springframework.core.Ordered;

public class OrderedCompartor implements Comparator<Ordered>{

	@Override
	public int compare(Ordered o1, Ordered o2) {
		
		Integer value1 = o1.getOrder();
		Integer value2 = o2.getOrder();
		
		return value1.compareTo(value2);
		
	}

}
