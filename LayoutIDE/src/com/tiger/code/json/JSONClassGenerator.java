package com.tiger.code.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.tiger.code.model.JArray;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JField;
import com.tiger.code.model.JPackage;
import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.primary.Primatives;

public class JSONClassGenerator
{
	public int CHILD_CLASS_COUNT = 0;
	
	public static final String CHILD_CLASS_NAME = "ChildClass";
	
	public JSONClassGenerator()
	{
		
	}
	
	public List<JClass> json2Classes(String json, String rootClassName)
	{
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(json);
		
		List<JClass> classes = jsonElement2Classes(jsonElement, rootClassName);
		CHILD_CLASS_COUNT = 0;
		return classes;
	}
	
	public List<JClass> json2Classes(String json)
	{
		return json2Classes(json, "RootClass");
	}
	
	private List<JClass> jsonElement2Classes(JsonElement jsonElement, String rootClassName)
	{
		ArrayList<JClass> clazzes = new ArrayList<JClass>();
		if(jsonElement instanceof JsonObject)
		{
			clazzes.addAll(jsonObject2Classes((JsonObject) jsonElement, rootClassName));
		}
		else if(jsonElement instanceof JsonArray)
		{
			clazzes.addAll(jsonArray2Classes((JsonArray) jsonElement, rootClassName));
		}
		
		return clazzes;
	}
	
	private List<JClass> jsonObject2Classes(JsonObject jsonObject, String rootClassName)
	{
		//获取当前jsonObject类名
		rootClassName = getJsonClassName(rootClassName);
		
		ArrayList<JClass> clazzes = new ArrayList<JClass>();
		JClass jClass = new JClass(new JPackage(), JActionScope.PUBLIC, rootClassName, null);;
		
		Set<Entry<String, JsonElement>> setEntries = jsonObject.entrySet();
		Iterator<Map.Entry<String,JsonElement>> jsonIterator = setEntries.iterator();
		while (jsonIterator.hasNext())
		{
			Map.Entry<String, JsonElement> jsonEntry = jsonIterator.next();
			JsonElement jsonElement = jsonEntry.getValue();
			if(jsonElement.isJsonPrimitive())
			{
				JsonPrimitive jsonPrimitive = (JsonPrimitive)jsonElement;
				if(jsonPrimitive.isNumber())
				{
					Number number = jsonPrimitive.getAsNumber();
					if(number instanceof Integer || number instanceof Short || number instanceof Byte)
					{
						JField jField = new JField(JActionScope.PRIVATE, 
								Primatives.newIntegerClass(), jsonEntry.getKey());
						jClass.addField(jField);
					}
					else if(number instanceof Long)
					{
						JField jField = new JField(JActionScope.PRIVATE, 
								Primatives.newLongClass(), jsonEntry.getKey());
						jClass.addField(jField);
					}
					else if(number instanceof Float)
					{
						JField jField = new JField(JActionScope.PRIVATE, 
								Primatives.newFloatClass(), jsonEntry.getKey());
						jClass.addField(jField);
					}
					else if(number instanceof Double || number instanceof BigDecimal)
					{
						JField jField = new JField(JActionScope.PRIVATE, 
								Primatives.newDoubleClass(), jsonEntry.getKey());
						jClass.addField(jField);
					}
				}
				else if(jsonPrimitive.isString())
				{
					JField jField = new JField(JActionScope.PRIVATE, 
							Primatives.newStringClass(), jsonEntry.getKey());
					jClass.addField(jField);
				}
				else if(jsonPrimitive.isBoolean())
				{
					JField jField = new JField(JActionScope.PRIVATE, 
							Primatives.newBooleanClass(), jsonEntry.getKey());
					jClass.addField(jField);
				}
			}
			else if(jsonElement instanceof JsonObject)
			{
				List<JClass> childClasses = jsonObject2Classes((JsonObject)jsonElement, rootClassName);
				if(childClasses.size() > 0)
				{
					clazzes.addAll(childClasses);
					JField jField = new JField(JActionScope.PRIVATE, childClasses.get(0), jsonEntry.getKey());
					jClass.addField(jField);
				}
			}
			else if(jsonElement instanceof JsonArray)
			{
				List<JClass> childClasses = jsonArray2Classes((JsonArray)jsonElement, rootClassName);
				if(childClasses.size() > 0)
				{
					clazzes.addAll(childClasses);
					JField jField = new JArray(JActionScope.PRIVATE, childClasses.get(0), jsonEntry.getKey());
					jClass.addField(jField);
				}
			}
		}
		
		Log.d("class", jClass.toString());
		
		clazzes.add(jClass);
		
		return clazzes;
	}
	
	private List<JClass> jsonArray2Classes(JsonArray jsonArray, String rootClassName)
	{
		ArrayList<JClass> clazzes = new ArrayList<JClass>();
		JsonElement jsonElement = jsonArray.get(0);
		if(jsonElement instanceof JsonObject)
		{
			clazzes.addAll(jsonObject2Classes((JsonObject) jsonElement, rootClassName));
		}
		else if(jsonElement instanceof JsonArray)
		{
			clazzes.addAll(jsonArray2Classes((JsonArray) jsonElement, rootClassName));
		}
		
		return clazzes;
	}
	
	private String getJsonClassName(String rootClassName)
	{
		if(CHILD_CLASS_COUNT == 0)
		{
			CHILD_CLASS_COUNT++;
			return rootClassName;
		}
		
		rootClassName = rootClassName + CHILD_CLASS_NAME + CHILD_CLASS_COUNT;
		CHILD_CLASS_COUNT++;
		return rootClassName;
	}
}
