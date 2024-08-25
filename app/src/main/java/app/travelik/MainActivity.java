package app.travelik;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("https://travelik.in");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            return handleUrl(url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return handleUrl(url);
        }

        private boolean handleUrl(String url) {
            // Check if the URL is for WhatsApp, YouTube, Instagram, or Facebook
            if (url.startsWith("https://api.whatsapp.com/") || url.startsWith("whatsapp://")) {
                openExternalApp(url);
                return true; // Indicate that we've handled the URL
            } else if (url.startsWith("https://www.youtube.com/") || url.startsWith("vnd.youtube://")) {
                openExternalApp(url);
                return true; // Indicate that we've handled the URL
            } else if (url.startsWith("https://www.instagram.com/") || url.startsWith("instagram://")) {
                openExternalApp(url);
                return true; // Indicate that we've handled the URL
            } else if (url.startsWith("https://www.facebook.com/") || url.startsWith("fb://")) {
                openExternalApp(url);
                return true; // Indicate that we've handled the URL
            }
            return false; // Allow WebView to handle the URL
        }

        private void openExternalApp(String url) {
            try {
                // Create an intent to open the URL in the corresponding app
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                // Handle the error (e.g., the app is not installed)
                e.printStackTrace();
            }
        }
    }
}
