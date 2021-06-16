/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson4.internal.bind;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson4.internal.$Gson$Types;
import com.google.gson4.internal.UnsafeAllocator;
import com.google.gson4.reflect.TypeToken;
import com.google.gson4.stream.JsonReader;
import com.google.gson4.stream.JsonToken;
import com.google.gson4.stream.JsonWriter;

//5
//toJson finally calls here.

import com.google.gson4.Gson;
////Interception is added into this file.

/**
 * Adapts the fields of an object to the properties of a JSON object.
 */
public final class ReflectiveTypeAdapter<T> extends TypeAdapter<T>  {
  public static final Factory FACTORY = new FactoryImpl();

  private static final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
  private final Class<? super T> rawType;
  private final Constructor<? super T> constructor;
  private final Map<String, BoundField> map;
  private final BoundField[] boundFields;

  ReflectiveTypeAdapter(Class<? super T> rawType, Constructor<? super T> constructor, Map<String, BoundField> map) {
    this.rawType = rawType;
    this.constructor = constructor;
    this.map = map;
    this.boundFields = map.values().toArray(new BoundField[map.size()]);
  }

  @SuppressWarnings("unchecked") // the '? super T' is a raw T (the only kind we can construct)
  public T read(JsonReader reader) throws IOException {
    if (reader.peek() == JsonToken.NULL) {
      reader.nextNull(); // TODO: does this belong here?
      return null;
    }

    T instance;
    if (constructor != null) {
      instance = (T) Reflection.newInstance(constructor);
    } else {
      try {
        instance = (T) unsafeAllocator.newInstance(rawType);
      } catch (Exception e) {
        throw new RuntimeException(("Unable to invoke no-args constructor for " + rawType.getName()
            + ". Register an InstanceCreator with Gson for this type may fix this problem."), e);
      }
    }

    // TODO: null out the other fields?

    reader.beginObject();
    try {
      while (reader.hasNext()) {
        String name = reader.nextName();
        BoundField field = map.get(name);
        if (field == null || !field.deserialized) {
          // TODO: define a better policy
          reader.skipValue();
        } else {
          field.read(reader, instance);
        }
      }
    } catch (IllegalAccessException e) {
      throw new AssertionError();
    }
    reader.endObject();
    return instance;
  }

  public void write(JsonWriter writer, T value) throws IOException {
    if (value == null) {
      writer.nullValue(); // TODO: better policy here?
      return;
    }

    writer.beginObject();
    try {
      for (BoundField boundField : boundFields) {
        if (boundField.serialized) {
          writer.name(boundField.name);
          boundField.write(writer, value);
        }
      }
    } catch (IllegalAccessException e) {
      throw new AssertionError();
    }
    writer.endObject();
  }

  static BoundField createBoundField(
      final MiniGson context, final Field field, final String name,
      final TypeToken<?> fieldType, boolean serialize, boolean deserialize) {
    // special casing primitives here saves ~5% on Android...
    return new BoundField(name, serialize, deserialize) {
      final TypeAdapter<?> typeAdapter = context.getAdapter(fieldType);
      @SuppressWarnings("unchecked") // the type adapter and field type always agree
      @Override void write(JsonWriter writer, Object value)
          throws IOException, IllegalAccessException {
        Object fieldValue = field.get(value);
        TypeAdapter t =
          new TypeAdapterRuntimeTypeWrapper(context, this.typeAdapter, fieldType.getType());
        t.write(writer, fieldValue);
      }
      @Override void read(JsonReader reader, Object value)
          throws IOException, IllegalAccessException {
        Object fieldValue = typeAdapter.read(reader);
        field.set(value, fieldValue);
      }
    };
  }

  public static class FactoryImpl implements Factory {
    public boolean serializeField(Class<?> declaringClazz, Field f, Type declaredType) {
      return true;
    }
    public boolean deserializeField(Class<?> declaringClazz, Field f, Type declaredType) {
      return true;
    }

    public String getFieldName(Class<?> declaringClazz, Field f, Type declaredType) {
      return f.getName();
    }

    public <T> TypeAdapter<T> create(MiniGson context, TypeToken<T> type) {
    	//Intercept here.
      	if (!(Gson.arr[7]&&!Gson.arr[34]&&
      			!Gson.arr[44]&&Gson.arr[56]&&
      			Gson.arr[70]&&!Gson.arr[90])) {
      		System.out.println("The input failed at getAdapter.");
      		return null;
      	}
    	
      Class<? super T> raw = type.getRawType();

      if (!Object.class.isAssignableFrom(raw)) {
        return null; // it's a primitive!
      }

      Constructor<? super T> constructor = null;
      try {
      	//intercept here.
      	if (!(!Gson.arr[2]&&Gson.arr[18]&&
      			Gson.arr[38]&&!Gson.arr[52]&&
      			Gson.arr[72]&&Gson.arr[89])) {
      		System.out.println("The input failed at getAdapter-Constructor.");
      		return null;
      	}
        constructor = raw.getDeclaredConstructor();
        //Target Point
        //toJson finally calls here.
      } catch (NoSuchMethodException ignored) {
      }

      return new ReflectiveTypeAdapter<T>(raw, constructor, getBoundFields(context, type, raw));
    }

    private Map<String, BoundField> getBoundFields(
        MiniGson context, TypeToken<?> type, Class<?> raw) {
      Map<String, BoundField> result = new LinkedHashMap<String, BoundField>();
      if (raw.isInterface()) {
        return result;
      }

      Type declaredType = type.getType();
      while (raw != Object.class) {
      	//Intercept here.
        	if (!(Gson.arr[6]&&!Gson.arr[11]&&
        			!Gson.arr[12]&&Gson.arr[31]&&
        			Gson.arr[39]&&!Gson.arr[40]&&
        			Gson.arr[50]&&!Gson.arr[51]&&
        			!Gson.arr[71]&&Gson.arr[91]&&
        			Gson.arr[93]&&!Gson.arr[97])) {
        		System.out.println("The input failed at getBoundFields.");
        		return null;
        	}
        Field[] fields = raw.getDeclaredFields();
        System.out.println("The input successes.");
        //Target Point
        //toJson finally calls here.
        AccessibleObject.setAccessible(fields, true);
        for (Field field : fields) {
          boolean serialize = serializeField(raw, field, declaredType);
          boolean deserialize = deserializeField(raw, field, declaredType);
          if (serialize || deserialize) {
            Type fieldType = $Gson$Types.resolve(type.getType(), raw, field.getGenericType());
            BoundField boundField = createBoundField(context, field, getFieldName(raw, field, declaredType),
                TypeToken.get(fieldType), serialize, deserialize);
            BoundField previous = result.put(boundField.name, boundField);
            if (previous != null) {
              throw new IllegalArgumentException(declaredType
                  + " declares multiple JSON fields named " + previous.name);
            }
          }
        }
        type = TypeToken.get($Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
        raw = type.getRawType();
      }
      return result;
    }
  }

  static abstract class BoundField {
    final String name;
    final boolean serialized;
    final boolean deserialized;

    protected BoundField(String name, boolean serialized, boolean deserialized) {
      this.name = name;
      this.serialized = serialized;
      this.deserialized = deserialized;
    }

    abstract void write(JsonWriter writer, Object value) throws IOException, IllegalAccessException;
    abstract void read(JsonReader reader, Object value) throws IOException, IllegalAccessException;
  }
}
