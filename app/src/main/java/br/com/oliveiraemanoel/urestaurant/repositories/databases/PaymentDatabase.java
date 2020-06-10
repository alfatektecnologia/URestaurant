package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.oliveiraemanoel.urestaurant.models.Payment;
import br.com.oliveiraemanoel.urestaurant.repositories.PaymentDAO;

@Database(entities ={Payment.class},version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class PaymentDatabase extends RoomDatabase {
    private static PaymentDatabase paymentDatabase;

    public abstract PaymentDAO createPaymentDAO();

    public static PaymentDatabase getInstance(Context context){
        if(paymentDatabase==null){
            paymentDatabase = Room.databaseBuilder(context,PaymentDatabase.class,"payment_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return paymentDatabase;
    }

}