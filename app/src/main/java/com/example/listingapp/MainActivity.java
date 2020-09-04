package com.example.listingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView navigationView;
    private TextView titleTv;

    private Fragment postsFragment,commentsFragment,albumsFragment, photosFragment;
    private Fragment activeFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigationView);
        titleTv = findViewById(R.id.titleTv);

        navigationView.setOnNavigationItemSelectedListener(this);

        initFragments();
        
    }

    private void initFragments() {

        postsFragment = new PostsFragment();
        commentsFragment = new CommentsFragment();
        albumsFragment = new AlbumsFragment();
        photosFragment = new PhotosFragment();

        fragmentManager = getSupportFragmentManager();
        activeFragment = postsFragment;

        fragmentManager.beginTransaction()
                .add(R.id.frame, postsFragment,"postsFragment")
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.frame, commentsFragment,"commentsFragment")
                .hide(commentsFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.frame,albumsFragment,"albumsFragment")
                .hide(albumsFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.frame, photosFragment,"photosFragment")
                .hide(photosFragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //handle bottom nav
        switch (item.getItemId()){
            case R.id.nav_posts:
                loadPostsFragment();
                return true;

            case R.id.nav_comments:
                loadCommentsFragment();
                return true;

            case R.id.nav_albums:
                loadAlbumsFragment();
                return true;

            case R.id.nav_photos:
                loadPhotosFragments();
                return true;
        }
        return false;
    }

    private void loadPostsFragment(){
        titleTv.setText("Listing Posts Data");
        fragmentManager.beginTransaction().hide(activeFragment).show(postsFragment).commit();
        activeFragment = postsFragment;
    }
    private void loadCommentsFragment(){
        titleTv.setText("Listing Comments Data");
        fragmentManager.beginTransaction().hide(activeFragment).show(commentsFragment).commit();
        activeFragment = commentsFragment;
    }

    private void loadAlbumsFragment(){
        titleTv.setText("Listing Albums Data");
        fragmentManager.beginTransaction().hide(activeFragment).show(albumsFragment).commit();
        activeFragment = albumsFragment;
    }
    private void loadPhotosFragments(){
        titleTv.setText("Listing Photos Data");
        fragmentManager.beginTransaction().hide(activeFragment).show(photosFragment).commit();
        activeFragment = photosFragment;
    }
}