package ca.georgebrown.comp3074.t02;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract(){};

    public static class SubscriptionPlanPageEntity implements BaseColumns{
        public static final String table_name="SubscriptionPlanPage";
        public static final String column_page_name="page_name";
        public static final String column_price = "price";
        public static final String column_page_description_1 = "page_description_1";
        public static final String column_page_description_2 = "page_description_2";
        public static final String column_page_description_3 = "page_description_3";
        public static final String column_active = "active";

        public static final String sql_create = "CREATE TABLE "+table_name+" ( "
                +_ID+" INTEGER PRIMARY KEY, "
                +column_page_name+" TEXT, "
                +column_price+" DECIMAL, "
                +column_page_description_1+" TEXT, "
                +column_page_description_2+" TEXT, "
                +column_page_description_3+" TEXT, "
                +column_active+" INTEGER )";

        public static final String sql_drop="DROP TABLE IF EXISTS "+table_name;

    }

    public static  class UserEntity implements BaseColumns{
        public static final String table_name="User_table";
        public static final String column_firstName="firstName";
        public static final String column_lastName="lastName";
        public static final String column_email="email";
        public static final String column_password="password";
        public static final String column_address="address";
        public static final String column_city="city";
        public static final String column_province="province";
        public static final String column_postalCode="postalCode";
        public static final String column_phone="phone";
        public static final String column_role="role";
        public static final String column_cardHolder="cardHolder";
        public static final String column_cardNumber="cardNumber";
        public static final String column_expiryDate="expiryDate";
        public static final String column_cvv="cvv";

        public static final String sql_create = "CREATE TABLE "+table_name+" ( "
                +_ID+" INTEGER PRIMARY KEY, "
                +column_firstName+" TEXT, "
                +column_lastName+" TEXT, "
                +column_email+" TEXT, "
                +column_password+" TEXT, "
                +column_address+" TEXT, "
                +column_city+" TEXT, "
                +column_province+" TEXT, "
                +column_postalCode+" TEXT, "
                +column_phone+" TEXT, "
                +column_role+" TEXT, "
                +column_cardHolder+" TEXT, "
                +column_cardNumber+" INTEGER, "
                +column_expiryDate+" TEXT, "
                +column_cvv+" INTEGER )";

        public static final String sql_drop="DROP TABLE IF EXISTS "+table_name;

    }

    public static class OrderEntity implements BaseColumns{
        public static final String table_name="Order_table";
        public static final String column_orderID="orderID";
        public static final String column_subscriptionPlanID="subscriptionPlanID";
        public static final String column_customerID="customerID";
        public static final String column_orderDate="orderDate";
        // this value comes from subscription plan
        public static final String column_price="price";

        public static final String sql_create="CREATE TABLE "+table_name+" ( "
                +column_orderID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +column_subscriptionPlanID+" INTEGER, "
                +column_customerID+" INTEGER, "
                +column_orderDate+" TEXT, "
                +column_price+" DECIMAL )";

        public static final String sql_drop="DROP TABLE IF EXISTS "+table_name;
    }

    public static class ProductEntity implements BaseColumns{
        public static final String table_name="Product_table";
        public static final String column_productID="productID";
        public static final String column_teaName="teaName";
        public static final String column_quantity="quantity";
        public static final String column_price="price";

        public static final String sql_create="CREATE TABLE "+table_name+" ( "
                +column_productID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +column_teaName+" TEXT, "
                +column_quantity+" INTEGER, "
                +column_price+" DECIMAL )";

        public static final String sql_drop="DROP TABLE IF EXISTS "+table_name;

    }

    public static class SubscriptionPlanProductEntity implements BaseColumns{
        public static final String table_name="SubscriptionPlanProduct_table";
        public static final String column_subscriptionPlanProductID="subscriptionPlanProductID";
        public static final String column_subscriptionPlanID="subscriptionPlanID";
        public static final String column_productID="productID";

        public static final String sql_create="CREATE TABLE "+table_name+" ( "
                +column_subscriptionPlanProductID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +column_subscriptionPlanID+" INTEGER, "
                +column_productID+" INTEGER ) ";

        public static final String sql_drop="DROP TABLE IF EXISTS "+table_name;

    }

    public static  class GalleryEntity implements BaseColumns{
        public static final String table_name="Galley_table";
        public static final String column_teaName="teaName";
        public static final String column_teaDescription="teaDescription";
        public static final String column_active = "active";

        public static final String sql_create = "CREATE TABLE "+table_name+" ( "
                +_ID+" INTEGER PRIMARY KEY, "
                +column_teaName+" TEXT, "
                +column_teaDescription+" TEXT, "
                +column_active+" INTEGER )";


        public static final String sql_drop="DROP TABLE IF EXISTS "+table_name;

    }
}
