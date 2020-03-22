package ca.georgebrown.comp3074.t02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbHelper extends SQLiteOpenHelper {

    public static final String database_name="TeaBox.db";

    public DbHelper(Context context) {super(context, database_name, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.SubscriptionPlanPageEntity.sql_create);
        db.execSQL(DatabaseContract.UserEntity.sql_create);
        db.execSQL(DatabaseContract.ProductEntity.sql_create);
        db.execSQL(DatabaseContract.OrderEntity.sql_create);
        Log.d("DB","DB Created!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.SubscriptionPlanPageEntity.sql_drop);
        db.execSQL(DatabaseContract.UserEntity.sql_drop);
        db.execSQL(DatabaseContract.ProductEntity.sql_drop);
        onCreate(db);
    }

    //THE FOLLOWING ARE METHODS FOR THE SUBSCRIPTION PLAN PAGES

    public boolean insertSubcriptionPlanPage(String page_name, float price,
                                             String page_description_1, String page_description_2, String page_description_3,
                                             int active) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.SubscriptionPlanPageEntity.column_page_name, page_name);
        cv.put(DatabaseContract.SubscriptionPlanPageEntity.column_price,price);
        cv.put(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_1, page_description_1);
        cv.put(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_2, page_description_2);
        cv.put(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_3, page_description_3);
        cv.put(DatabaseContract.SubscriptionPlanPageEntity.column_active, active);


        long result = db.insert(DatabaseContract.SubscriptionPlanPageEntity.table_name,null,cv);

        if(result == -1){
            return false;
        } else {
            Log.d("DB","Insert page to "+page_name);
            return true;
        }
    }

    public SubscriptionPlanPage getSubsciptionPlanPageById(int subId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.SubscriptionPlanPageEntity.table_name +" WHERE "+
                DatabaseContract.SubscriptionPlanPageEntity._ID+"= " + subId;
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            int id = (int)c.getLong(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity._ID));
            String page_name = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_name));
            float price = c.getFloat(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_price));
            String page_description_1 = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_1));
            String page_description_2 = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_2));
            String page_description_3 = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_3));
            int active = c.getInt(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_active));

            SubscriptionPlanPage s = new SubscriptionPlanPage(id, page_name, price, page_description_1, page_description_2, page_description_3,active);
            return s;
        }
        c.close();
        return null;
    }

    public SubscriptionPlanPage getSubscriptionPlanPage(SQLiteDatabase db, long pageId){
        String[] projection  = {
                DatabaseContract.SubscriptionPlanPageEntity._ID,
                DatabaseContract.SubscriptionPlanPageEntity.column_page_name,
                DatabaseContract.SubscriptionPlanPageEntity.column_price,
                DatabaseContract.SubscriptionPlanPageEntity.column_page_description_1,
                DatabaseContract.SubscriptionPlanPageEntity.column_page_description_2,
                DatabaseContract.SubscriptionPlanPageEntity.column_page_description_3,
                DatabaseContract.SubscriptionPlanPageEntity.column_active
        };

        String selection = DatabaseContract.SubscriptionPlanPageEntity._ID+"= ? ";
        String[] selectionArgs = {Long.toString(pageId)};

        Cursor c = db.query(DatabaseContract.SubscriptionPlanPageEntity.table_name,
                projection,
                selection,
                selectionArgs,
                null,null,null);

        if(c.moveToFirst()){
            int id = (int)c.getLong(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity._ID));
            String page_name = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_name));
            float price = c.getFloat(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_price));
            String page_description_1 = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_1));
            String page_description_2 = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_2));
            String page_description_3 = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_3));
            int active = c.getInt(
                    c.getColumnIndexOrThrow(DatabaseContract.SubscriptionPlanPageEntity.column_active));

            SubscriptionPlanPage s = new SubscriptionPlanPage(id, page_name, price, page_description_1, page_description_2, page_description_3,active);
            return s;
        }
        c.close();
        return null;
    }

    //This method is to update the Sub Plan based on data from admin edit Sub Plan
    public boolean updateSubPlan(SubscriptionPlanPage subscriptionPlanPage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.SubscriptionPlanPageEntity.column_price, subscriptionPlanPage.getPrice());
        values.put(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_1, subscriptionPlanPage.getPage_description_1());
        values.put(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_2, subscriptionPlanPage.getPage_description_2());
        values.put(DatabaseContract.SubscriptionPlanPageEntity.column_page_description_3, subscriptionPlanPage.getPage_description_3());


        db.update(DatabaseContract.SubscriptionPlanPageEntity.table_name, values, "page_name= ?", new String[]{subscriptionPlanPage.getPage_name()});
        return  true;

    }

    //THE FOLLOWING ARE METHODS RELATED TO USERS

    // this method is for when users first register
    public boolean insertUser(String firstName, String lastName, String email, String password, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.UserEntity.column_firstName, firstName);
        cv.put(DatabaseContract.UserEntity.column_lastName, lastName);
        cv.put(DatabaseContract.UserEntity.column_email, email);
        cv.put(DatabaseContract.UserEntity.column_password, password);
        cv.put(DatabaseContract.UserEntity.column_role, role);

        long result = db.insert(DatabaseContract.UserEntity.table_name, null, cv);

        if(result==-1){
            return false;
        }else{
            Log.d("DB", "Insert user "+firstName+" log from db method");
            return true;
        }
    }

    // this method is for the login fragment
    public User loginUser (String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.UserEntity.table_name +" WHERE "+
                DatabaseContract.UserEntity.column_email+"= '" + email + "' AND "+
                DatabaseContract.UserEntity.column_password+ "= '"+password+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount()<=0){
            cursor.close();
            return  null;
        } else{
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String email2 = cursor.getString(3);
            String password2 = cursor.getString(4);
            String address = cursor.getString(5);
            String city  = cursor.getString(6);
            String province = cursor.getString(7);
            String postalCode = cursor.getString(8);
            String phone = cursor.getString(9);
            String role = cursor.getString(10);
            String cardHolderName = cursor.getString(11);
            int cardNumber = cursor.getInt(12);
            String expiryDate = cursor.getString(13);
            int cvv = cursor.getInt(14);


            User user = new User(id, firstName, lastName, email2, password2, address, city, province,
                    postalCode, phone, role, cardHolderName, cardNumber, expiryDate, cvv);
            Log.d("DB"," values read into string and cardNUmber is: "+cardNumber);
            cursor.close();
            return user;

        }
    }

    //This method is to get a User by their email
    public User getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.UserEntity.table_name +" WHERE "+
                DatabaseContract.UserEntity.column_email+"= '" + email + "' ";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount()<=0){
            cursor.close();
            return  null;
        } else {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String email2 = cursor.getString(3);
            String password2 = cursor.getString(4);
            String address = cursor.getString(5);
            String city = cursor.getString(6);
            String province = cursor.getString(7);
            String postalCode = cursor.getString(8);
            String phone = cursor.getString(9);
            String role = cursor.getString(10);
            String cardHolderName = cursor.getString(11);
            int cardNumber = cursor.getInt(12);
            String expiryDate = cursor.getString(13);
            int cvv = cursor.getInt(14);


            User user = new User(id, firstName, lastName, email2, password2, address, city, province,
                    postalCode, phone, role, cardHolderName, cardNumber, expiryDate, cvv);
            cursor.close();
            return user;
        }
    }

    public User getUserById(int userID){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.UserEntity.table_name +" WHERE "+
                DatabaseContract.UserEntity._ID+"= " + userID;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount()<=0){
            cursor.close();
            return  null;
        } else {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String email2 = cursor.getString(3);
            String password2 = cursor.getString(4);
            String address = cursor.getString(5);
            String city = cursor.getString(6);
            String province = cursor.getString(7);
            String postalCode = cursor.getString(8);
            String phone = cursor.getString(9);
            String role = cursor.getString(10);
            String cardHolderName = cursor.getString(11);
            int cardNumber = cursor.getInt(12);
            String expiryDate = cursor.getString(13);
            int cvv = cursor.getInt(14);


            User user = new User(id, firstName, lastName, email2, password2, address, city, province,
                    postalCode, phone, role, cardHolderName, cardNumber, expiryDate, cvv);
            cursor.close();
            return user;
        }
    }

    //This method is to update the user based on data from edit my profile
    public boolean updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UserEntity.column_firstName, user.getFirstName());
        values.put(DatabaseContract.UserEntity.column_lastName, user.getLastName());
        values.put(DatabaseContract.UserEntity.column_address, user.getAddress());
        values.put(DatabaseContract.UserEntity.column_city, user.getCity());
        values.put(DatabaseContract.UserEntity.column_province, user.getProvince());
        values.put(DatabaseContract.UserEntity.column_postalCode, user.getPostalCode());
        values.put(DatabaseContract.UserEntity.column_phone, user.getPhone());

        db.update(DatabaseContract.UserEntity.table_name, values, "email= ?", new String[]{user.getEmail()});
        return  true;

    }

    // This function is to get all Orders
    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.UserEntity.table_name;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    // This method is for when user clicks to save card for future use on Checkout fragment
    public boolean updateUserWithCreditCard(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UserEntity.column_firstName, user.getFirstName());
        values.put(DatabaseContract.UserEntity.column_lastName, user.getLastName());
        values.put(DatabaseContract.UserEntity.column_address, user.getAddress());
        values.put(DatabaseContract.UserEntity.column_city, user.getCity());
        values.put(DatabaseContract.UserEntity.column_province, user.getProvince());
        values.put(DatabaseContract.UserEntity.column_postalCode, user.getPostalCode());
        values.put(DatabaseContract.UserEntity.column_phone, user.getPhone());
        values.put(DatabaseContract.UserEntity.column_cardHolder, user.getCardHolderName());
        values.put(DatabaseContract.UserEntity.column_cardNumber,user.getCardNumber());
        values.put(DatabaseContract.UserEntity.column_expiryDate, user.getExpiryDate());
        values.put(DatabaseContract.UserEntity.column_cvv, user.getCvv());

        db.update(DatabaseContract.UserEntity.table_name, values, "email= ?", new String[]{user.getEmail()});
        return  true;

    }

    // The following methods are related to Product table and Order tables

    public boolean insertProduct(String teaName, int quantity, float unitPrice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.ProductEntity.column_teaName, teaName);
        cv.put(DatabaseContract.ProductEntity.column_quantity, quantity);
        cv.put(DatabaseContract.ProductEntity.column_price, unitPrice);

        long result = db.insert(DatabaseContract.ProductEntity.table_name, null, cv);
        if(result==-1){
            return false;
        } else{
            Log.d("DB","Insert Product "+teaName+" log from db method");
            return true;
        }
    }

    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> p = new ArrayList<Product>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.ProductEntity.table_name;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String teaName = cursor.getString(1);
            int quantity = cursor.getInt(2);
            Float unitPrice = cursor.getFloat(3);

            Product product = new Product(id, teaName, quantity, unitPrice);
            p.add(product);
        }
        return p;
    }

    public boolean updateProduct(int productID,int change){
        SQLiteDatabase db = this.getWritableDatabase();
        String query  = "UPDATE "+DatabaseContract.ProductEntity.table_name+" SET "+
                DatabaseContract.ProductEntity.column_quantity+" = "+
                DatabaseContract.ProductEntity.column_quantity+" - "+change+" WHERE "+
                DatabaseContract.ProductEntity.column_productID+" = "+productID;
        db.execSQL(query);
        return true;
    }

    public long createOrder(int userId, int subplanId, float orderPrice ){
        SQLiteDatabase db = this.getWritableDatabase();
        //get current date
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String date = s.format(new Date());

        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.OrderEntity.column_customerID, userId);
        cv.put(DatabaseContract.OrderEntity.column_subscriptionPlanID, subplanId);
        cv.put(DatabaseContract.OrderEntity.column_price, orderPrice);
        cv.put(DatabaseContract.OrderEntity.column_orderDate, date);

        long result = db.insert(DatabaseContract.OrderEntity.table_name, null, cv);
        if(result ==-1){
            return  -1;
        } else{

            // in these statements is where we specify how many units each order/plan will take
            // currently I have randomly assigned units to plans, Please adjust as necessary
            // The following is all the IDs for the product in the Product table
//            Mango Strawberry Black Tea	1
//            Fruits and Nuts Tisane		2
//            Spring Chinary Black Tea	    3
//            Nilgiri Jasmine Black Tea	    4
//            Rose Mint White Tea		    5
//            Orange Pineapple Black Tea	6
//            Paan Rose Green Tea		    7
//            Pure Matcha Green Tea		    8

            if(subplanId == 1){
                this.updateProduct(1,1);
                this.updateProduct(2,1);
                this.updateProduct(3,1);

            }else if(subplanId == 2){
                this.updateProduct(4,4);
                this.updateProduct(5,5);
                this.updateProduct(6,6);
            }else{
                this.updateProduct(1,6);
                this.updateProduct(2,6);
                this.updateProduct(3,6);
                this.updateProduct(4,6);
                this.updateProduct(7,6);
                this.updateProduct(8,6);
            }
            return result;
        }
    }

    public Order getOrder(long orderId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.OrderEntity.table_name +" WHERE "+
                DatabaseContract.OrderEntity.column_orderID+"= " + orderId;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount()<=0){
            cursor.close();
            return  null;
        } else {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            int subPlanId = cursor.getInt(1);
            int customerId = cursor.getInt(2);
            String date = cursor.getString(3);
            Float orderPrice = cursor.getFloat(4);

            Order order = new Order(id, subPlanId, customerId, date, orderPrice);
            return order;
        }
    }

    // This function is to get all Orders
    public Cursor getAllOrders(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.OrderEntity.table_name;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor getOrdersByUserId(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.OrderEntity.table_name +" WHERE "+
                DatabaseContract.OrderEntity.column_customerID+"= " + id;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    // This function is to get all Products
    public Cursor getAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.ProductEntity.table_name;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}
