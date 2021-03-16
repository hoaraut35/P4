package com.hoarauthomas.p04_withnotify.utils;



import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.hoarauthomas.p04_withnotify.R;
import org.hamcrest.Matcher;

public class DeleteItemAction implements ViewAction
{
        @Override
        public Matcher<View> getConstraints() {
            return null;
        }

        @Override
        public String getDescription() {
            return "Click on specific button";
        }

        @Override
        public void perform(UiController uiController, View view) {
            View button = view.findViewById(R.id.view_delete_meeting);
            // Maybe check for null
            button.performClick();
        }
    }