package a8.bug.fix;

import com.opencsv.bean.ColumnPositionMappingStrategy;

public class EightaColumnPositionMappingStrategy<T> extends ColumnPositionMappingStrategy<T>{

/*
 
	@Override
    public void setType(Class<? extends T> type) throws CsvBadConverterException {
        super.setType(type);
        if (!columnsExplicitlySet) {
            SortedMap<Integer, BeanField> cols = new TreeMap<Integer, BeanField>();
            for (BeanField beanField : fieldMap.values()) {
                if (beanField
                        .getField()
                        .getAnnotation(CsvCustomBindByPosition.class) != null) {
                    cols.put(beanField
                            .getField()
                            .getAnnotation(CsvCustomBindByPosition.class)
                            .position(), beanField);
                } else if (beanField
                        .getField()
                        .getAnnotation(CsvBindByPosition.class) != null) {
                    cols.put(beanField
                            .getField()
                            .getAnnotation(CsvBindByPosition.class)
                            .position(), beanField);
                }
                // We must purposefully ignore CsvBind here.
            }

            if (!cols.isEmpty()) {
                columnMapping = new String[cols.lastKey()];	//<<< XXX BUGFIX: was: columnMapping = new String[cols.lastKey() + 1]; 
                for (Map.Entry<Integer, BeanField> entry : cols.entrySet()) {
                    columnMapping[entry.getKey()] = entry
                            .getValue()
                            .getField()
                            .getName()
                            .toUpperCase()
                            .trim();
                }
                resetIndexMap();
                createIndexLookup(columnMapping);
            } else {
                columnMapping = new String[0];
            }
        }
    }
	
*/
}
