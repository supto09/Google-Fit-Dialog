package io.cloudly.bd.googlefitdialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.root_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.image_view_should_vanish)
    ImageView imageView;

    @BindView(R.id.text_view_should_vanish)
    TextView textView;

    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // customize and set the toolbar as actionbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // get the view with the BottomSheetBehavior and apply bottomSheetCallback
        View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);


        // set the different view behaviour according to sliding state
        // we should apply some more smooth animation
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
                Log.e(TAG, "onStateChanged:" + newState);

                // hide and the view for better security
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    textView.setVisibility(View.GONE);
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
                Log.e(TAG, "slideOffset: " + slideOffset);

                // animate the visibility according to scroll status
                if (slideOffset < 1f && slideOffset > .6f) {
                    float customizedOffset = (1 - slideOffset) * 5f;
                    Log.d(TAG, "Customized offset: " + customizedOffset);
                    imageView.setAlpha(customizedOffset);
                    textView.setTranslationY(1 / customizedOffset);

                }

            }
        });

        // set a peek height for bottom sheet dialog
        behavior.setPeekHeight((ScreenUtils.newInstance(this).getHeight() / 5) * 3);
    }

    @Override
    public void onBackPressed() {

        // custom onbackpress behaviour like google fit
        if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
