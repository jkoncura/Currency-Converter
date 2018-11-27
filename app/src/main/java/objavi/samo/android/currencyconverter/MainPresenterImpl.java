package objavi.samo.android.currencyconverter;

import java.util.List;
import java.util.Map;

import objavi.samo.android.currencyconverter.model.Currency;

public class MainPresenterImpl implements MainContract.Presenter,
            MainContract.GetCurrencyInteractor.OnFinishedListener {

    private MainContract.MainView mMainView; // for updating the view
    private MainContract.GetCurrencyInteractor mGetCurrencyInteractor; //to fetch data from internet

    public MainPresenterImpl(MainContract.MainView mainView,
                             MainContract.GetCurrencyInteractor getCurrencyInteractor){

        this.mMainView = mainView;
        this.mGetCurrencyInteractor = getCurrencyInteractor;
    }

    @Override
    public void onSubmitButtonClick(String amount, String spinnerFrom, String spinnerTo, Map<String, Currency> currencyMap) {

        // show error in the main view if amount is empty
        if (amount.isEmpty()){
            mMainView.showError("Please input a value!");
            mMainView.showResult("");
            return;
        }
        // show toast message if currencies to be converted are the same
        if (spinnerFrom.equals(spinnerTo)) {
            mMainView.showToast("Please choose different currencies for conversion! Thank you.");
            mMainView.showResult("");
            return;
        }
        // retrieve Currency objects based on spinner values
        Currency currencyFrom = currencyMap.get(spinnerFrom);
        Currency currencyTo = currencyMap.get(spinnerTo);

        // get amount to be converted
        float amountFloat = Float.valueOf(amount);

        // conversion from currencyTo to base currency(HRK)
        float fromToToBase = (Float.parseFloat(currencyTo.getUnitValue()) / Float.parseFloat(currencyTo.getSellingRate()));

        // conversion from base currency(HRK) to currencyFrom
        float fromBaseToFrom = (Float.parseFloat(currencyFrom.getBuyingRate()) / Float.parseFloat(currencyFrom.getUnitValue()));

        float result = amountFloat * fromToToBase * fromBaseToFrom;

        mMainView.showResult(String.valueOf(result));

    }

    @Override
    public void requestDataFromServer() {
        mGetCurrencyInteractor.getCurrencyArrayList(this);
    }

    @Override
    public void onFinished(List<Currency> currencyList) {
            mMainView.populateSpinners(currencyList);
    }

    @Override
    public void onFailure(Throwable t) {
            mMainView.onResponseFailure(t);
    }
}
