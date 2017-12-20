package vn.edu.imic.demojsoup.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.imic.demojsoup.adapter.ArticleAdapter;
import vn.edu.imic.demojsoup.model.Article;

/**
 * Created by MyPC on 18/12/2017.
 */

public class DownloadTask extends AsyncTask<String,Void,List<Article>>{
    private static final String TAG = DownloadTask.class.getSimpleName();
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> listArticle;
    private ArticleAdapter articleAdapter;
    public DownloadTask(Context context, RecyclerView recyclerView) {
        listArticle = new ArrayList<>();
        articleAdapter = new ArticleAdapter(context,listArticle);
        layoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(articleAdapter);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Article> doInBackground(String... strings) {
        Document document = null;
        List<Article> articles = new ArrayList<>();
        try {
            document = Jsoup.connect(strings[0]).get();
            /**
             * Lấy html theo đường có cấu trúc thẻ như sau (
             * div#latest-news > div.row > div.col-md-6 hoặc chỉ cần dùng  div.col-md-6
             */
            if (document != null){
                Elements element = document.select("div#latest-news > div.row > div.col-md-6");
                for (Element e : element) {
                    Article a = new Article();
                    Element titleSubject = e.getElementsByTag("h3").first();
                    Element imgSubject = e.getElementsByTag("img").first();
                    Element linkSubject = e.getElementsByTag("a").first();
                    Element description = e.getElementsByTag("h4").first();

                    if (titleSubject != null){
                        a.setTitle(titleSubject.text());
                        Log.d(TAG, "Title: " + titleSubject.text());
                    }
                    if (imgSubject != null){
                        a.setThumbnail(imgSubject.text());
                        Log.d(TAG, "Image: " + imgSubject.text());
                    }
                    if (linkSubject != null){
                        a.setUrl(linkSubject.text());
                        Log.d(TAG, "Link: " + linkSubject.text());
                    }
                    if (description != null){
                        a.setDescription(description.text());
                        Log.d(TAG, "Description: " + description.text());
                    }

                    articles.add(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        super.onPostExecute(articles);
        listArticle.addAll(articles);
        articleAdapter.notifyDataSetChanged();
    }
}
