package vn.edu.imic.demojsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.edu.imic.demojsoup.asynctask.DownloadTask;

public class MainActivity extends AppCompatActivity {
    /**
     * JSoup là một thư viện mạnh mẽ giúp bóc tách các thành phần
     * html thành các đối tượng trong Java Android
     * @param savedInstanceState
     */
    private static final String BASE_URL = "https://guu.vn/cat/toc-dep";
    @BindView(R.id.rv_item)
    RecyclerView rvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new DownloadTask(this,rvItem).execute(BASE_URL);
    }

}
