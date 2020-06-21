package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.Ordem;

public class RoomTypeConverters {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<Ordem> stringToOrderr(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Ordem>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static List<Item> stringToItem(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Item>>() {}.getType();

        return gson.fromJson(data, listType);
    }
    @TypeConverter
    public static String itemListToString(List<Item> items) {
        return gson.toJson(items);
    }

    @TypeConverter
    public static String orderrListToString(List<Ordem> ordemList) {
        return gson.toJson(ordemList);
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
