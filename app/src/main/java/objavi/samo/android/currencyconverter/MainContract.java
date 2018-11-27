package objavi.samo.android.currencyconverter;

import java.util.List;
import java.util.Map;

import objavi.samo.android.currencyconverter.model.Currency;

public interface MainContract {

    /**
     * Call when user interacts with the view
     */

    interface Presenter {

        void onSubmitButtonClick(String spinnerFrom, String spinnerTo, String amount, Map<String, Currency> currencyMap); // do the calculation for currency conversion

        void requestDataFromServer();
    }
    /**
     * For updating the view
     */

    interface MainView {

        void populateSpinners(List<Currency> currencyArrayList); //updateThe view

        void onResponseFailure(Throwable throwable);

        void showResult(String result);

        void showError(String error);

        void showToast(String toastText);

    }

    /**
     * To fetch the data from web service
     */
    interface GetCurrencyInteractor {

        interface OnFinishedListener {
            void onFinished (List<Currency> currencyList);
            void onFailure(Throwable t);
        }

        void getCurrencyArrayList(OnFinishedListener onFinishedListener);


    }
}
