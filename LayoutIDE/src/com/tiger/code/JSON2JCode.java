package com.tiger.code;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public class JSON2JCode
{
	public JSON2JCode()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse("{}");
		
		if(jsonElement instanceof JsonObject)
		{
			Set<Entry<String, JsonElement>> setEntries = ((JsonObject)jsonElement).entrySet();
			Iterator<Map.Entry<String,JsonElement>> jsonIterator = setEntries.iterator();
			while (jsonIterator.hasNext())
			{
				Map.Entry<String, JsonElement> jsonEntry = jsonIterator.next();
				JsonElement jsonElement2 = jsonEntry.getValue();
				if(jsonElement2.isJsonPrimitive())
				{
					JsonPrimitive jsonPrimitive = (JsonPrimitive)jsonElement2;
					if(jsonPrimitive.isNumber())
					{
						Number number = jsonPrimitive.getAsNumber();
						if(number instanceof Integer || number instanceof Short || number instanceof Byte)
						{
							stringBuilder.append("private int" + jsonEntry.getKey() + ";\n");
						}
						else if(number instanceof Long)
						{
							stringBuilder.append("private long" + jsonEntry.getKey() + ";\n");
						}
						else if(number instanceof Float)
						{
							stringBuilder.append("private float" + jsonEntry.getKey() + ";\n");
						}
						else if(number instanceof Double)
						{
							stringBuilder.append("private double" + jsonEntry.getKey() + ";\n");
						}
					}
					else if(jsonPrimitive.isString())
					{
						stringBuilder.append("private String" + jsonEntry.getKey() + ";\n");
					}
					else if(jsonPrimitive.isBoolean())
					{
						stringBuilder.append("private boolean" + jsonEntry.getKey() + ";\n");
					}
				}
				else 
				{
					//TODO 递归写入
				}
			}
			
			stringBuilder.append("}");
			Log.d("JSON2JCode", stringBuilder.toString());
		}
		else if(jsonElement instanceof JsonArray)
		{
			
		}
	}
}
