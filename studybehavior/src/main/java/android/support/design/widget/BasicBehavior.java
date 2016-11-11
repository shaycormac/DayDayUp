package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import static android.view.View.VISIBLE;

/**自定义Behavior支持所有View作为FAB
 * @author 作者： Android2(范方方)
 * @datetime 创建时间：2016-11-11 09:09 GMT+8
 * @email 邮箱： 574583006@qq.com
 */
public class BasicBehavior<T extends View> extends CoordinatorLayout.Behavior<T>
{

    // We only support the FAB <> Snackbar shift movement on Honeycomb and above. This is
    // because we can use view translation properties which greatly simplifies the code.
    private static final boolean SNACKBAR_BEHAVIOR_ENABLED = Build.VERSION.SDK_INT >= 11;

    private ValueAnimatorCompat mFabTranslationYAnimator;
    private float mFabTranslationY;
    private Rect mTmpRect;

    //添加
    private ListenerAnimatorOverBuild listenerAnimatorOverBuild;

    public BasicBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        listenerAnimatorOverBuild = new ListenerAnimatorOverBuild();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent,
                                   T child, View dependency) {
        // We're dependent on all SnackbarLayouts (if enabled)
        return SNACKBAR_BEHAVIOR_ENABLED && dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, T child,
                                          View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            updateFabTranslationForSnackbar(parent, child, dependency);
        } else if (dependency instanceof AppBarLayout) {
            // If we're depending on an AppBarLayout we will show/hide it automatically
            // if the FAB is anchored to the AppBarLayout
            updateFabVisibility(parent, (AppBarLayout) dependency, child);
        }
        return false;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, T child,
                                       View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            updateFabTranslationForSnackbar(parent, child, dependency);
        }
    }

    private boolean updateFabVisibility(CoordinatorLayout parent,
                                        AppBarLayout appBarLayout, T child) {
        final CoordinatorLayout.LayoutParams lp =
                (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (lp.getAnchorId() != appBarLayout.getId()) {
            // The anchor ID doesn't match the dependency, so we won't automatically
            // show/hide the FAB
            return false;
        }

        if (child.getVisibility() != VISIBLE) {
            // The view isn't set to be visible so skip changing it's visibility
            return false;
        }

        if (mTmpRect == null) {
            mTmpRect = new Rect();
        }

        // First, let's get the visible rect of the dependency
        final Rect rect = mTmpRect;
        ViewGroupUtils.getDescendantRect(parent, appBarLayout, rect);

        if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
            // If the anchor's bottom is below the seam, we'll animate our FAB out
            if (listenerAnimatorOverBuild.isFininsh())
                scaleHide(child,listenerAnimatorOverBuild.build());
           // child.hide(null, false);
        } else {
            // Else, we'll animate our FAB back in
           // child.show(null, false);
            scaleShow(child, null);
        }
        return true;
    }

    private void updateFabTranslationForSnackbar(CoordinatorLayout parent,
                                                 final T fab, View snackbar) {
        final float targetTransY = getFabTranslationYForSnackbar(parent, fab);
        if (mFabTranslationY == targetTransY) {
            // We're already at (or currently animating to) the target value, return...
            return;
        }

        final float currentTransY = ViewCompat.getTranslationY(fab);

        // Make sure that any current animation is cancelled
        if (mFabTranslationYAnimator != null && mFabTranslationYAnimator.isRunning()) {
            mFabTranslationYAnimator.cancel();
        }

        if (fab.isShown()
                && Math.abs(currentTransY - targetTransY) > (fab.getHeight() * 0.667f)) {
            // If the FAB will be travelling by more than 2/3 of it's height, let's animate
            // it instead
            if (mFabTranslationYAnimator == null) {
                mFabTranslationYAnimator = ViewUtils.createAnimator();
                mFabTranslationYAnimator.setInterpolator(
                        AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                mFabTranslationYAnimator.setUpdateListener(
                        new ValueAnimatorCompat.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimatorCompat animator) {
                                ViewCompat.setTranslationY(fab,
                                        animator.getAnimatedFloatValue());
                            }
                        });
            }
            mFabTranslationYAnimator.setFloatValues(currentTransY, targetTransY);
            mFabTranslationYAnimator.start();
        } else {
            // Now update the translation Y
            ViewCompat.setTranslationY(fab, targetTransY);
        }

        mFabTranslationY = targetTransY;
    }

    private float getFabTranslationYForSnackbar(CoordinatorLayout parent,
                                                T fab) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset,
                        ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }

        return minOffset;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, T child,
                                 int layoutDirection) {
        // First, lets make sure that the visibility of the FAB is consistent
        final List<View> dependencies = parent.getDependencies(child);
        for (int i = 0, count = dependencies.size(); i < count; i++) {
            final View dependency = dependencies.get(i);
            if (dependency instanceof AppBarLayout
                    && updateFabVisibility(parent, (AppBarLayout) dependency, child)) {
                break;
            }
        }
        // Now let the CoordinatorLayout lay out the FAB
        parent.onLayoutChild(child, layoutDirection);
        // Now offset it if needed
       // offsetIfNeeded(parent, child);
        return true;
    }

    /**
     * Pre-Lollipop we use padding so that the shadow has enough space to be drawn. This method
     * offsets our layout position so that we're positioned correctly if we're on one of
     * our parent's edges.
     */
   /* private void offsetIfNeeded(CoordinatorLayout parent, T fab)
    {
        final Rect padding = fab.mShadowPadding;

        if (padding != null && padding.centerX() > 0 && padding.centerY() > 0) {
            final CoordinatorLayout.LayoutParams lp =
                    (CoordinatorLayout.LayoutParams) fab.getLayoutParams();

            int offsetTB = 0, offsetLR = 0;

            if (fab.getRight() >= parent.getWidth() - lp.rightMargin) {
                // If we're on the left edge, shift it the right
                offsetLR = padding.right;
            } else if (fab.getLeft() <= lp.leftMargin) {
                // If we're on the left edge, shift it the left
                offsetLR = -padding.left;
            }
            if (fab.getBottom() >= parent.getBottom() - lp.bottomMargin) {
                // If we're on the bottom edge, shift it down
                offsetTB = padding.bottom;
            } else if (fab.getTop() <= lp.topMargin) {
                // If we're on the top edge, shift it up
                offsetTB = -padding.top;
            }

            fab.offsetTopAndBottom(offsetTB);
            fab.offsetLeftAndRight(offsetLR);
        }
    }*/

    //设置动画
    public static final ViewPropertyAnimatorListener DEFAULT_OUT_ANIMATOR_LISTENER = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {

        }

        @Override
        public void onAnimationEnd(View view) {
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View view) {

        }
    };
    public static class ListenerAnimatorOverBuild
    {
        //动画是否执行完毕
        private boolean isEnd;
        private ViewPropertyAnimatorListener animatorListener;

        public ListenerAnimatorOverBuild()
        {

            animatorListener = new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {
                    isEnd = true;
                }

                @Override
                public void onAnimationEnd(View view) {
                    view.setVisibility(View.GONE);
                    isEnd = false;
                }

                @Override
                public void onAnimationCancel(View view) {
                    isEnd = false;
                }
            };
        }

        public boolean isFininsh()
        {
            return !isEnd;
        }
        
        public  ViewPropertyAnimatorListener build()
        {
            return animatorListener;
        }
    }


    public static final FastOutSlowInInterpolator FASTOUTSLOWININTERPOLATOR = new FastOutSlowInInterpolator();
    
    public static  void scaleShow(View view,ViewPropertyAnimatorListener listener)
    {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(1000)
                .setInterpolator(FASTOUTSLOWININTERPOLATOR).setListener(listener).start();
    }
    public static void scaleShow(View view) {
        scaleShow(view, null);
    }

    public static void scaleHide(View view) {
        scaleHide(view, DEFAULT_OUT_ANIMATOR_LISTENER);
    }

    public static void scaleHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(1000)
                .setInterpolator(FASTOUTSLOWININTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }
    
}
