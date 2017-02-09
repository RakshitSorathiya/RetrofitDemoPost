package demo.android.retrofitdemopost;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import demo.android.retrofitdemopost.api.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private ProgressDialog mProgressDialog;
    private TextView tv_status, tv_page, tv_display;
    private EditText et_status, et_page;
    private Button btn_show;
    private ApiService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please Wait....");

        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_page = (TextView) findViewById(R.id.tv_page);
        tv_display = (TextView) findViewById(R.id.tv_display);

        et_status = (EditText) findViewById(R.id.et_status);
        et_page = (EditText) findViewById(R.id.et_page);

        btn_show = (Button) findViewById(R.id.btn_show);

        mAPIService = ApiUtils.getApiService();

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = et_status.getText().toString().trim();
                String page = et_page.getText().toString().trim();
                if (!TextUtils.isEmpty(status) && !TextUtils.isEmpty(page)) {
                    sendPost(status, page);
                }
            }

            /*public void sendPost(String status, String page) {

                // RxJava
                mAPIService.savePost(status, page, 1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Data>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Data data) {
                                showResponse(data.toString());
                            }
                        });
            }*/
            public void sendPost(String status, String page) {
                mAPIService.savePost(status, page, 1).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {

                        if (response.isSuccessful()) {
                            showResponse(response.body().toString());
                            Log.d(TAG, "post submitted to API." + response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Log.e(TAG, "Unable to submit post to API.");
                    }
                });
            }


            public void showResponse(String response) {
                if (tv_display.getVisibility() == View.GONE) {
                    tv_display.setVisibility(View.VISIBLE);
                }
                tv_display.setText(response.toString());
            }
        });
    }
}
