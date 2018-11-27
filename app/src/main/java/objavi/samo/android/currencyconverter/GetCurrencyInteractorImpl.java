package objavi.samo.android.currencyconverter;

import java.util.List;

import objavi.samo.android.currencyconverter.model.Currency;
import objavi.samo.android.currencyconverter.network.CurrencyApi;
import objavi.samo.android.currencyconverter.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCurrencyInteractorImpl implements MainContract.GetCurrencyInteractor {

    /**
     * Method responsible for getting data using the REST Api with retrofit library
     * and passing data through onFinishedListener callback
     */
    @Override
    public void getCurrencyArrayList(final OnFinishedListener onFinishedListener) {

        CurrencyApi currencyApi = RetrofitInstance.getRetrofitInstance().create(CurrencyApi.class);

        Call<List<Currency>> call = currencyApi.getCurrencies();

        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
