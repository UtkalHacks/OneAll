package shiv2.save.life.line.line2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class user_abuse extends AppCompatActivity {
    private WebView web;

    ProgressBar pgb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_abuse);
        pgb = findViewById(R.id.PGQ );
        web = (WebView) findViewById(R.id.web1);
        web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.getSettings().setAppCacheEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setUseWideViewPort(true);


        web.getSettings().setEnableSmoothTransition(true);

        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);




        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pgb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pgb.setVisibility(View.INVISIBLE);

            }
        });
        web.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdamh84kT9-UEEIeuGhKeuzJDJ-ne4AE79RCBC2sN0T_KkPoA/viewform?vc=0&c=0&w=1");
        WebSettings Webset= web.getSettings();
        Webset.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {

        if(web.canGoBack()){
            web.goBack();
        }

        else{
            super.onBackPressed();
        }
    }

}
