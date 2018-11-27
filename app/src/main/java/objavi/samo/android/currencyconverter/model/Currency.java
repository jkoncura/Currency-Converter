package objavi.samo.android.currencyconverter.model;

import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("currency_code")
    private String currencyCode;

    @SerializedName("unit_value")
    private String unitValue;

    @SerializedName("buying_rate")
    private String buyingRate;

    @SerializedName("selling_rate")
    private String sellingRate;

    public Currency(String currencyCode, String unitValue, String buyingRate, String sellingRate) {
        this.currencyCode = currencyCode;
        this.unitValue = unitValue;
        this.buyingRate = buyingRate;
        this.sellingRate = sellingRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public String getBuyingRate() {
        return buyingRate;
    }

    public String getSellingRate() {
        return sellingRate;
    }
}
