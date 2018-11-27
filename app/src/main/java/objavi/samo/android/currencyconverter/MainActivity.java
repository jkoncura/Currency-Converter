package objavi.samo.android.currencyconverter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objavi.samo.android.currencyconverter.model.Currency;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, MainContract.MainView {

    private EditText mTextAmount;
    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private Button mButtonSubmit;
    private TextView mTextResult;

    private Map<String, Currency> mCurrencyMap;
    private ArrayAdapter<String> mAdapter;
    private List<String> mCurrencyCodeList;

    private Context mContext = MainActivity.this;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextAmount = findViewById(R.id.txt_amount);
        mSpinnerFrom = findViewById(R.id.spinner_from);
        mSpinnerTo = findViewById(R.id.spinner_to);
        mButtonSubmit = findViewById(R.id.btn_submit);
        mTextResult = findViewById(R.id.text_result);

        mCurrencyCodeList = new ArrayList<>();
        mCurrencyMap = new HashMap<>();

        mButtonSubmit.setOnClickListener(this);

        mPresenter = new MainPresenterImpl(this, new GetCurrencyInteractorImpl());
        mPresenter.requestDataFromServer();
    }

    @Override
    public void populateSpinners(List<Currency> currencyArrayList) {
        for (Currency currency : currencyArrayList){
            mCurrencyCodeList.add(currency.getCurrencyCode());
            mCurrencyMap.put(currency.getCurrencyCode(), currency);
        }
        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, mCurrencyCodeList);
        mSpinnerFrom.setAdapter(mAdapter);
        mSpinnerTo.setAdapter(mAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(mContext,
                "Couldn't fetch data from the server: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void showResult(String result) {
        mTextResult.setText(result);
    }

    @Override
    public void showError(String error) {
        mTextAmount.setError(error);
    }

    @Override
    public void showToast(String toastText) {
        Toast.makeText(mContext, toastText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        String amount = mTextAmount.getText().toString().trim();
        String spinnerFrom = mSpinnerFrom.getSelectedItem().toString();
        String spinnerTo = mSpinnerTo.getSelectedItem().toString();

        mPresenter.onSubmitButtonClick(amount,
                spinnerFrom,
                spinnerTo,
                mCurrencyMap);
    }
}
