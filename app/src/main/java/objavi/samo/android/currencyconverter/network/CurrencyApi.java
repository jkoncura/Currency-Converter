package objavi.samo.android.currencyconverter.network;

import java.util.List;

import objavi.samo.android.currencyconverter.model.Currency;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyApi {

    @GET("/api/v1/rates/daily/")
    Call<List<Currency>> getCurrencies();
}
