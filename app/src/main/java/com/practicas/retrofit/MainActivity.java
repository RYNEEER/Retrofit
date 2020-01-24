package com.practicas.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.practicas.retrofit.Interface.Herokuapp;
import com.practicas.retrofit.Modelo.Post;
import com.practicas.retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPosts();
    }


    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend-posts.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Herokuapp herokuapp = retrofit.create(Herokuapp.class);

        Call<List<Post>> call = herokuapp.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                    if(!response.isSuccessful()){
                        mJsonTxtView.setText("Codigo: " +response.code());
                        return;
                    }

                    List<Post> postsList = response.body();

                        for (Post post: postsList) {
                            String content = "";
                            content += "title: " + post.getTitle()+"\n";
                            content += "description: " + post.getDescription() + "\n";
                            content += "image_url::" + post.getUrl_image() + "\n\n";
                            mJsonTxtView.append(content);
                        }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }


        });
    }
}



