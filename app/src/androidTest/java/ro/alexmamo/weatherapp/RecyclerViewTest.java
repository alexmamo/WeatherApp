package ro.alexmamo.weatherapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ro.alexmamo.weatherapp.cities.CitiesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {
    private View view;

    @Rule
    public ActivityTestRule<CitiesActivity> activityRule = new ActivityTestRule<>(CitiesActivity.class, true, true);

    @Before
    public void setUp() {
        view = activityRule.getActivity().getWindow().getDecorView();
    }

    @Test
    public void testRecyclerViewVisibility() {
        onView(ViewMatchers.withId(R.id.cities_recycler_view))
                .inRoot(RootMatchers.withDecorView(Matchers.is(view)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewItemClick() {
        onView(ViewMatchers.withId(R.id.cities_recycler_view))
                .inRoot(RootMatchers.withDecorView(Matchers.is(view)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testRecyclerViewScrollToLastItem() {
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.cities_recycler_view);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        int itemCount = 0;
        if (adapter != null) {
            itemCount = adapter.getItemCount();
        }

        onView(ViewMatchers.withId(R.id.cities_recycler_view))
                .inRoot(RootMatchers.withDecorView(Matchers.is(view)))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    @Test
    public void testRecyclerViewItemHoldsSpecificText() {
        RecyclerViewMatcher recyclerViewMatcher = new RecyclerViewMatcher(R.id.cities_recycler_view);
        onView(recyclerViewMatcher.atPosition(0))
                .check(matches(hasDescendant(withText("Brasov"))));
        onView(recyclerViewMatcher.atPosition(1))
                .check(matches(hasDescendant(withText("Bucharest"))));
    }
}