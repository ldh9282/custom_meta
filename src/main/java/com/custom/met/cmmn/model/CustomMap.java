package com.custom.met.cmmn.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.JdbcUtils;

/**
 * <pre>
 * 클래스명: CustomMap
 * 설명: 공통모델로 사용하기 위한 커스텀맵
 * 
 * 1. put 할때 key 값이 언더스코어 방식이면 카멜케이스로 변환 
 * 2. get 할때 value 값을 각종 타입으로 변환
 * 3. toString 할때 테이블형식으로 하고 key 넣은 순서를 가진다
 * </pre>
 */
public class CustomMap extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	public CustomMap() {
		
	}
	
	public CustomMap(Map<String, Object> map) {

		for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
	}
	
	@Override
	public Object put(String key, Object value) {
		if (key != null && key.contains("_")) {
	        key = JdbcUtils.convertUnderscoreNameToPropertyName(key);
	    }
	    return super.put(key, value);
	}
	
	public void set(String key, String value) {
		put(key, value);
	}
	public void set(String key, int value) {
		put(key, Integer.valueOf(value));
	}
	public void set(String key, long value) {
		put(key, Long.valueOf(value));
	}
	public void set(String key, float value) {
		put(key, Float.valueOf(value));
	}
	public void set(String key, double value) {
		put(key, Double.valueOf(value));
	}
	public void set(String key, boolean value) {
		put(key, Boolean.valueOf(value));
	}
	public void set(String key, Object value) {
		put(key, value);
	}
	
	public String getString(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getString ::: key is null");
		}
		
		String value = null;
		
		Object object = null;
		object = get(key);
		
		if (object == null) {
			return "";
		} else if (object instanceof String) {
			value = (String) object;
		} else if (object instanceof Number) {
			Number number = (Number) object;
			value = number.toString();
		} else if (object instanceof CustomMap) {
			CustomMap customMap = (CustomMap) object;
			value = "CustomMap " + customMap.toHashMap().toString();
		} else if (object instanceof List<?>) {
			List<?> list = (List<?>) object;
	        if (list.isEmpty()) {
	            value = "Empty List";
	        } else {
	            value = "List<" + list.get(0).getClass().getSimpleName() + "> [" + list.size() + "]";
	        }
		} else {
			value = object.toString();
		}
		
		return value;
	}
	
	public String getString(String key, String defaultValue) {
		Object object = null;
		object = get(key);
		
		if (object == null || (object instanceof String && "".equals((String) object))) {
			return defaultValue;
		} else {
			return getString(key);
		}
	}

	public int getInt(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getInt ::: key is null");
		}
		
		int returnValue = 0;
		
		Object object = null;
		object = get(key);
		
		if (object == null) {
			throw new RuntimeException("CustomMap.getInt ::: value is null");
		} else if (object instanceof String) {
			returnValue = Integer.parseInt((String) object);
		} else if (object instanceof Integer) {
			Integer value = null;
			try {
				value = (Integer) object;
				returnValue = value.intValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getInt ::: value is not Integer [" + value + "]");
			}
		} else if (object instanceof Number) {
			Number value = null;
			try {
				value = (Number) object;
				returnValue = value.intValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getInt ::: value is not Number [" + value + "]");
			}
		}
		
		return returnValue;
	}
	
	public int getInt(String key, int defaultValue) {
		Object object = null;
		object = get(key);
		
		if (object == null || (object instanceof String && "".equals((String) object))) {
			return defaultValue;
		} else {
			return getInt(key);
		}
	}
	
	
	public long getLong(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getLong ::: key is null");
		}
		
		long returnValue = 0;
		
		Object object = null;
		object = get(key);
		
		if (object == null) {
			throw new RuntimeException("CustomMap.getLong ::: value is null");
		} else if (object instanceof String) {
			returnValue = Long.parseLong((String) object);
		} else if (object instanceof Long) {
			Long value = null;
			try {
				value = (Long) object;
				returnValue = value.longValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getLong ::: value is not Long [" + value + "]");
			}
		} else if (object instanceof Number) {
			Number value = null;
			try {
				value = (Number) object;
				returnValue = value.longValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getLong ::: value is not Number [" + value + "]");
			}
		}
		
		return returnValue;
	}
	
	public long getLong(String key, long defaultValue) {
		Object object = null;
		object = get(key);
		
		if (object == null || (object instanceof String && "".equals((String) object))) {
			return defaultValue;
		} else {
			return getLong(key);
		}
	}
	
	public float getFloat(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getFloat ::: key is null");
		}
		
		float returnValue = 0;
		
		Object object = null;
		object = get(key);
		
		if (object == null) {
			throw new RuntimeException("CustomMap.getFloat ::: value is null");
		} else if (object instanceof String) {
			returnValue = Float.parseFloat((String) object);
		} else if (object instanceof Float) {
			Float value = null;
			try {
				value = (Float) object;
				returnValue = value.floatValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getFloat ::: value is not Float [" + value + "]");
			}
		} else if (object instanceof Number) {
			Number value = null;
			try {
				value = (Number) object;
				returnValue = value.floatValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getFloat ::: value is not Number [" + value + "]");
			}
		}
		
		return returnValue;
	}
	
	public float getFloat(String key, float defaultValue) {
		Object object = null;
		object = get(key);
		
		if (object == null || (object instanceof String && "".equals((String) object))) {
			return defaultValue;
		} else {
			return getFloat(key);
		}
	}
	
	public double getDouble(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getDouble ::: key is null");
		}
		
		double returnValue = 0;
		
		Object object = null;
		object = get(key);
		
		if (object == null) {
			throw new RuntimeException("CustomMap.getDouble ::: value is null");
		} else if (object instanceof String) {
			returnValue = Double.parseDouble((String) object);
		} else if (object instanceof Double) {
			Double value = null;
			try {
				value = (Double) object;
				returnValue = value.doubleValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getDouble ::: value is not Double [" + value + "]");
			}
		} else if (object instanceof Number) {
			Double value = null;
			try {
				value = (Double) object;
				returnValue = value.doubleValue();
				
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getDouble ::: value is not Number [" + value + "]");
			}
		}
		
		return returnValue;
	}
	
	public double getDouble(String key, double defaultValue) {
		Object object = null;
		object = get(key);
		
		if (object == null || (object instanceof String && "".equals((String) object))) {
			return defaultValue;
		} else {
			return getDouble(key);
		}
	}
	
	public boolean getBoolean(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getBoolean ::: key is null");
		}
		
		
		Object object = null;
		object = get(key);
		
		if (object == null) {
			throw new RuntimeException("CustomMap.getBoolean ::: value is null");
		}
		
		if (object instanceof String) {
			try {
				return Boolean.valueOf(object.toString()).booleanValue();
			} catch (Exception e) {
				throw new RuntimeException("CustomMap.getBoolean ::: value is not Boolean [" + object.toString() + "]");
			}
		} else if (object instanceof Boolean) {
			return ((Boolean) object).booleanValue();
		}
		
		return false;
	}
	
	public boolean getBoolean(String key, boolean defaultValue) {
		Object object = null;
		object = get(key);
		
		if (object == null || (object instanceof String && "".equals((String) object))) {
			return defaultValue;
		} else {
			return getBoolean(key);
		}
	}

	public CustomMap getCustomMap(String key) {
	    if (key == null) {
	        throw new RuntimeException("CustomMap.getCustomMap ::: key is null");
	    }
	    
	    Object value = get(key);
	    
	    if (value == null) {
	        return null;
	    }
	    
	    if (!(value instanceof HashMap<?, ?>)) {
	        throw new RuntimeException("CustomMap.getCustomMap ::: value is not a HashMap");
	    }

	    return new CustomMap((HashMap<String, Object>) value);
	}
	
	public List<CustomMap> getCustomMapList(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getCustomMapList ::: key is null");
		}
		
		Object value = get(key);
		
		if (value == null) {
			throw new RuntimeException("CustomMap.getCustomMapList ::: value is null");
		}
		
		if (!(value instanceof List<?>)) {
			throw new RuntimeException("CustomMap.getCustomMapList ::: value is not list");
		}
		
		List<Object> tempList = (List<Object>) value;
		
		List<CustomMap> returnList = new ArrayList<>();
		
		for (int i = 0; i < tempList.size(); i++) {
			Object itemObject = tempList.get(i);
			if (itemObject instanceof CustomMap) {
				returnList.add((CustomMap) itemObject);
			} else if (itemObject instanceof HashMap<?, ?>) {
				CustomMap customMap = new CustomMap((HashMap<String, Object>) itemObject);
				returnList.add(customMap);
			} else {
				throw new RuntimeException("CustomMap.getCustomMapList ::: item of list is not map");
			}
		}
		
		return returnList;
	}
	
	public Object getObject(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getObject ::: key is null");
		}
		return get(key);
	}
	public List<?> getObjectList(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getObjectList ::: key is null");
		}
		
		Object value = get(key);
		
		if (value == null) {
			throw new RuntimeException("CustomMap.getObjectList ::: value is null");
		}
		if (!(value instanceof List<?>)) {
			throw new RuntimeException("CustomMap.getObjectList ::: value is not list");
		}
		
		List<?> objectList = (List<?>) value;
		
		
		return objectList;
	}
	
	public List<String> getList(String key) {
		if (key == null) {
			throw new RuntimeException("CustomMap.getList ::: key is null");
		}
		
		Object value = get(key);
		
		if (value == null) {
			throw new RuntimeException("CustomMap.getList ::: value is null");
		}
		if (!(value instanceof List<?>)) {
			throw new RuntimeException("CustomMap.getList ::: value is not list");
		}
		
		List<?> objectList = (List<?>) value;
		
		List<String> stringList = new ArrayList<>();
		
		for (Object object : objectList) {
			String str = "";
			if (object == null) {
				str = "";
			} else if (object instanceof String) {
				str = (String) object;
			} else if (object instanceof Number) {
				Number number = (Number) object;
				str = number.toString();
			} else {
				str = object.toString();
			}
			stringList.add(str);
		}
		
		
		return stringList;
	}
	
	public HashMap<String, Object> toHashMap() {
	    HashMap<String, Object> hashMap = new HashMap<>();
	    for (Map.Entry<String, Object> entry : this.entrySet()) {
	        String key = entry.getKey();
	        Object value = entry.getValue();
	        if (value instanceof CustomMap) {
	            hashMap.put(key, ((CustomMap) value).toHashMap());
	        } else if (value instanceof List<?>) {
	            List<?> list = (List<?>) value;
	            List<Object> newList = new ArrayList<>();
	            for (Object item : list) {
	                if (item instanceof CustomMap) {
	                    newList.add(((CustomMap) item).toHashMap());
	                } else if (item instanceof List<?>) {
	                    newList.add(convertListToHashMap((List<?>) item));
	                } else {
	                    newList.add(item);
	                }
	            }
	            hashMap.put(key, newList);
	        } else {
	            hashMap.put(key, value);
	        }
	    }
	    return hashMap;
	}
	
    private List<Object> convertListToHashMap(List<?> list) {
        List<Object> newList = new ArrayList<>();
        for (Object item : list) {
            if (item instanceof CustomMap) {
                newList.add(((CustomMap) item).toHashMap());
            } else if (item instanceof List<?>) {
                newList.add(convertListToHashMap((List<?>) item));
            } else {
                newList.add(item);
            }
        }
        return newList;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        List<String> keyList = new ArrayList<>(keySet());
//        Collections.sort(keyList);

        int maxKeyLength = 0;
        int maxValueLength = 0;
        int cutValueLength = 50;
        for (String key : keyList) {
            maxKeyLength = Math.max(maxKeyLength, key.length());
            if (getString(key).length() >= cutValueLength) {
            	String cutValue = getString(key).substring(0, cutValueLength) + "...(" + (getString(key).length() - cutValueLength) +")";
            	maxValueLength = Math.max(maxValueLength, cutValue.length());
            } else {
            	
            	maxValueLength = Math.max(maxValueLength, getString(key).length());
            }
        }
        
        
        int width = 80 > (maxKeyLength + maxValueLength) * 2 ? 80 : (maxKeyLength + maxValueLength) * 2;
        
        sb.append("\n");
        sb.append(repeatString("─", width));
        sb.append("\n");
        sb.append(centerString("[CustomMap]", width));
        sb.append("\n\n");
        sb.append(centerString("key", width / 2));
        sb.append(centerString("value", width / 2));
        sb.append("\n");
        sb.append(repeatString("-", width));
        sb.append("\n\n");

        for (String key : keyList) {
            sb.append(centerString(padRight(key, maxKeyLength + 1), width / 2));
            if (getString(key).length() >= cutValueLength) {
            	String cutValue = getString(key).substring(0, cutValueLength) + "...(" + (getString(key).length() - cutValueLength) +")";
            	sb.append(centerString(padRight(cutValue, maxValueLength + 1), width / 2));

            } else {
            	
            	sb.append(centerString(padRight(getString(key), maxValueLength + 1), width / 2));
            }
            sb.append("\n");
        }
        sb.append("\n");
        sb.append(repeatString("─", width));
        sb.append("\n");
        
        return sb.toString();
    }

    private static String padRight(String str, int length) {
        return String.format("%-" + length + "s", str);
    }
    
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    private static String centerString(String str, int width) {
        if (str.length() >= width) {
            return str;
        } else {
            int totalPadding = width - str.length();
            int leftPadding = totalPadding / 2;
            int rightPadding = totalPadding - leftPadding;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < leftPadding; i++) {
                sb.append(" ");
            }
            sb.append(str);
            for (int i = 0; i < rightPadding; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
    }
    
}
