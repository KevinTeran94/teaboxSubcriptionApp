package ca.georgebrown.comp3074.t02;

import java.io.Serializable;

public class SubscriptionPlanPage implements Serializable {

    private int id;
    private String page_name;
    private float price;
    private String page_description;
    private String page_description_1;
    private String page_description_2;
    private String page_description_3;
    private int active;

    public SubscriptionPlanPage(int id, String page_name, float price,
                                String page_description_1,String page_description_2,String page_description_3, int active) {
        this.id = id;
        this.page_name = page_name;
        this.price = price;
        this.page_description_1 = page_description_1;
        this.page_description_2 = page_description_2;
        this.page_description_3 = page_description_3;
        this.active = active;
    }

    public SubscriptionPlanPage(int id, float price,
                                String page_description_1,String page_description_2,String page_description_3) {
        this.id = id;
        this.price = price;
        this.page_description_1 = page_description_1;
        this.page_description_2 = page_description_2;
        this.page_description_3 = page_description_3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPage_description_1() {
        return page_description_1;
    }

    public String getPage_description_2() {
        return page_description_2;
    }

    public String getPage_description_3() {
        return page_description_3;
    }

    public void setPage_description_1(String page_description_1) {
        this.page_description_1 = page_description_1;
    }

    public void setPage_description_2(String page_description_2) {
        this.page_description_2 = page_description_2;
    }

    public void setPage_description_3(String page_description_3) {
        this.page_description_3 = page_description_3;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
