/*
 * Copyright 2019 Gautham Sajith
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gsajith.mdgen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MainActivity extends AppCompatActivity {

    private static final int ANIMATION_DURATION = 300;

    MaterialTheme currentTheme;

    private ColorSet[] darkColors;
    private ColorSet[] lightColors;
    private int[] lightSurfaceColors;
    private int[] darkSurfaceColors;
    private int smallCornerRadiusVariance;
    private int mediumCornerRadiusVarianceMax;
    private int mediumCornerRadiusVarianceMin;
    private int maxElevation;
    private int minElevation;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        darkColors = MDGenUtils.generateBaseDarkColors();
        lightColors = MDGenUtils.generateBaseLightColors();
        lightSurfaceColors = MDGenUtils.generateLightSurfaceColors(getResources());
        darkSurfaceColors = MDGenUtils.generateDarkSurfaceColors(getResources());
        smallCornerRadiusVariance = getResources().getDimensionPixelSize(R.dimen.button_pill_size);
        mediumCornerRadiusVarianceMax = getResources().getDimensionPixelSize(
                R.dimen.card_max_corner);
        mediumCornerRadiusVarianceMin = getResources().getDimensionPixelSize(
                R.dimen.card_min_corner);
        maxElevation = getResources().getDimensionPixelSize(R.dimen.max_elevation);
        minElevation = getResources().getDimensionPixelSize(R.dimen.min_elevation);

        currentTheme = generateRandomTheme();

        Button generateButton = findViewById(R.id.generate_button);

        final MaterialCardView wrappingCard = findViewById(R.id.materialCardView);
        final List<MaterialCardView> cards = MDGenUtils.findViewsWithType(wrappingCard,
                MaterialCardView.class);
        final List<MaterialButton> buttons = MDGenUtils.findViewsWithType(wrappingCard,
                MaterialButton.class);
        final List<TextInputLayout> textInputLayouts = MDGenUtils.findViewsWithType(wrappingCard,
                TextInputLayout.class);
        Collections.reverse(textInputLayouts);
        final List<TextInputEditText> textInputEditTexts = MDGenUtils.findViewsWithType(
                wrappingCard, TextInputEditText.class);
        Collections.reverse(textInputEditTexts);
        final List<AppBarLayout> appBarLayouts = MDGenUtils.findViewsWithType(wrappingCard,
                AppBarLayout.class);
        final List<BottomAppBar> bottomAppBars = MDGenUtils.findViewsWithType(wrappingCard,
                BottomAppBar.class);
        final List<FloatingActionButton> floatingActionButtons = MDGenUtils.findViewsWithType(
                wrappingCard, FloatingActionButton.class);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Animator> animations = new ArrayList<>();
                MaterialTheme newTheme = generateRandomTheme();

                for (MaterialButton button : buttons) {
                    animations.addAll(createButtonAnimations(button, currentTheme, newTheme));
                }
                for (MaterialCardView card : cards) {
                    animations.addAll(createCardAnimations(card, currentTheme, newTheme));
                }
                for (TextInputLayout textInputLayout : textInputLayouts) {
                    animations.addAll(createTextInputLayoutAnimations(textInputLayout, currentTheme,
                            newTheme));
                }
                for (TextInputEditText textInputEditText : textInputEditTexts) {
                    textInputEditText.setSelection(textInputEditText.getText().length());
                }
                for (AppBarLayout appBarLayout : appBarLayouts) {
                    animations.addAll(
                            createTopAppBarAnimations(appBarLayout, currentTheme, newTheme));
                }
                for (BottomAppBar bottomAppBar : bottomAppBars) {
                    animations.addAll(
                            createBottomAppBarAnimations(bottomAppBar, currentTheme, newTheme));
                }
                for (FloatingActionButton floatingActionButton : floatingActionButtons) {
                    animations.addAll(
                            createFloatingActionButtonAnimations(floatingActionButton, currentTheme,
                                    newTheme));
                }

                currentTheme = newTheme;
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animations);
                animatorSet.setDuration(ANIMATION_DURATION);
                animatorSet.start();
            }
        });

        generateButton.callOnClick();
    }

    private List<ValueAnimator> createFloatingActionButtonAnimations(
            final FloatingActionButton floatingActionButton,
            MaterialTheme currentTheme,
            MaterialTheme newTheme) {
        boolean showBottomAppBar = newTheme.appBarVisibility == 1;
        boolean bottomAppBarCurrentlyVisible = currentTheme.appBarVisibility == 1;

        ValueAnimator fabBackgroundColorAnimator = createColorAnimator(
                currentTheme.colorSecondary.color, newTheme.colorSecondary.color,
                new ColorCallable() {
                    @Override
                    public Object call() {
                        floatingActionButton.setBackgroundTintList(
                                ColorStateList.valueOf(color));
                        return true;
                    }
                });

        ValueAnimator fabIconColorAnimator = createColorAnimator(
                currentTheme.colorSecondary.colorOn, newTheme.colorSecondary.colorOn,
                new ColorCallable() {
                    @Override
                    public Object call() {
                        floatingActionButton.setImageTintList(
                                ColorStateList.valueOf(color));
                        return true;
                    }
                });

        ValueAnimator fabTranslationAnimator = ValueAnimator.ofFloat(
                bottomAppBarCurrentlyVisible ? 0 : 1, showBottomAppBar ? 0 : 1);
        fabTranslationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                floatingActionButton.setTranslationY(
                        (Float) valueAnimator.getAnimatedValue() * floatingActionButton.getHeight()
                                * 1.5f);
            }
        });

        if (bottomAppBarCurrentlyVisible && !showBottomAppBar) {
            fabTranslationAnimator.setStartDelay(175);
        }

        return Arrays.asList(fabBackgroundColorAnimator, fabIconColorAnimator,
                fabTranslationAnimator);
    }

    private List<ValueAnimator> createBottomAppBarAnimations(final BottomAppBar bottomAppBar,
            MaterialTheme currentTheme, MaterialTheme newTheme) {
        boolean showBottomAppBar = newTheme.appBarVisibility == 1;
        boolean bottomAppBarCurrentlyVisible = currentTheme.appBarVisibility == 1;

        if (showBottomAppBar && !bottomAppBarCurrentlyVisible) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bottomAppBar.performShow();
                }
            }, ANIMATION_DURATION);
        } else if (!showBottomAppBar && bottomAppBarCurrentlyVisible) {
            bottomAppBar.performHide();
        }

        ValueAnimator backgroundColorAnimator = createColorAnimator(currentTheme.colorPrimary.color,
                newTheme.colorPrimary.color,
                new ColorCallable() {
                    @Override
                    public Object call() {
                        bottomAppBar.setBackgroundTint(
                                ColorStateList.valueOf(color));
                        return true;
                    }
                });

        ValueAnimator iconColorAnimator = createColorAnimator(currentTheme.colorPrimary.colorOn,
                newTheme.colorPrimary.colorOn, new ColorCallable() {
                    @Override
                    public Object call() {
                        Drawable drawable = bottomAppBar.getNavigationIcon();
                        if (drawable != null) {
                            drawable = DrawableCompat.wrap(drawable);
                            DrawableCompat.setTint(drawable.mutate(), color);
                            bottomAppBar.setNavigationIcon(drawable);
                        }
                        return true;
                    }
                });

        ValueAnimator roundedCornerRadiusAnimator = ValueAnimator.ofFloat(
                MDGenUtils.hasRoundedCorners(currentTheme.mediumComponentShapeAppearance)
                        ? getResources().getDimensionPixelSize(R.dimen.bab_rounded_corner_radius)
                        : 1,
                MDGenUtils.hasRoundedCorners(newTheme.mediumComponentShapeAppearance)
                        ? getResources().getDimensionPixelSize(R.dimen.bab_rounded_corner_radius)
                        : 1);
        roundedCornerRadiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                bottomAppBar.setFabCradleRoundedCornerRadius(
                        (Float) valueAnimator.getAnimatedValue());
            }
        });

        return Arrays.asList(iconColorAnimator, backgroundColorAnimator,
                roundedCornerRadiusAnimator);
    }

    private List<ValueAnimator> createTopAppBarAnimations(final AppBarLayout topAppBar,
            MaterialTheme currentTheme, final MaterialTheme newTheme) {
        boolean showTopAppBar = newTheme.appBarVisibility == 0;
        boolean topAppBarCurrentlyVisible = currentTheme.appBarVisibility == 0;
        ValueAnimator topAppBarShowAnimator = ValueAnimator.ofFloat(
                topAppBarCurrentlyVisible ? 0 : 1, showTopAppBar ? 0 : 1);
        topAppBarShowAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                topAppBar.setTranslationY(
                        (Float) valueAnimator.getAnimatedValue() * topAppBar.getHeight() * -1.1f);
            }
        });

        ValueAnimator colorAnimator = createColorAnimator(currentTheme.colorPrimary.color,
                newTheme.colorPrimary.color, new ColorCallable() {
                    @Override
                    public Object call() {
                        topAppBar.getChildAt(0).setBackgroundColor(color);
                        return true;
                    }
                });

        final TextView toolbarTitle = topAppBar.findViewById(R.id.toolbar_title);

        ValueAnimator titleColorAnimator = createColorAnimator(currentTheme.colorPrimary.colorOn,
                newTheme.colorPrimary.colorOn, new ColorCallable() {
                    @Override
                    public Object call() {
                        toolbarTitle.setTextColor(color);
                        return true;
                    }
                });


        ValueAnimator titleLetterSpacingAnimator = ValueAnimator.ofFloat(
                currentTheme.letterSpacingSmall, newTheme.letterSpacingSmall);
        titleLetterSpacingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                toolbarTitle.setLetterSpacing((Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator titleTextSizeAnimator = ValueAnimator.ofFloat(currentTheme.smallTextSize,
                newTheme.smallTextSize);
        titleTextSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                toolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        (Float) valueAnimator.getAnimatedValue());
            }
        });
        titleTextSizeAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                toolbarTitle.setAllCaps(newTheme.smallTextAllCaps);
            }
        });

        return Arrays.asList(titleLetterSpacingAnimator, titleTextSizeAnimator,
                topAppBarShowAnimator, colorAnimator, titleColorAnimator);
    }

    private List<ValueAnimator> createTextInputLayoutAnimations(
            final TextInputLayout textInputLayout, MaterialTheme currentTheme,
            MaterialTheme newTheme) {
        ValueAnimator colorAnimator = createColorAnimator(currentTheme.colorPrimary.color,
                newTheme.colorPrimary.color, new ColorCallable() {
                    @Override
                    public Object call() {
                        textInputLayout.setHintTextColor(ColorStateList.valueOf(color));
                        textInputLayout.setBoxStrokeColor(color);
                        textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(color));
                        return true;
                    }
                });

        ValueAnimator textColorAnimator = createColorAnimator(currentTheme.colorOnSurface,
                newTheme.colorOnSurface,
                new ColorCallable() {
                    @Override
                    public Object call() {
                        textInputLayout.getEditText().setTextColor(color);
                        return true;
                    }
                });

        ValueAnimator cornerRadiusAnimator = ValueAnimator.ofFloat(
                currentTheme.smallComponentShapeAppearance.getTopLeftCorner().getCornerSize(),
                newTheme.smallComponentShapeAppearance.getTopLeftCorner().getCornerSize());
        cornerRadiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textInputLayout.setBoxCornerRadii((Float) valueAnimator.getAnimatedValue(),
                        (Float) valueAnimator.getAnimatedValue(),
                        (Float) valueAnimator.getAnimatedValue(),
                        (Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator letterSpacingAnimator = ValueAnimator.ofFloat(currentTheme.letterSpacingSmall,
                newTheme.letterSpacingSmall);
        letterSpacingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textInputLayout.getEditText().setLetterSpacing(
                        (Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator editTextPaddingAnimator = ValueAnimator.ofFloat(currentTheme.xSmallPadding,
                newTheme.xSmallPadding);
        editTextPaddingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textInputLayout.getEditText().setPadding(
                        Math.round((Float) valueAnimator.getAnimatedValue()),
                        textInputLayout.getEditText().getPaddingTop(),
                        Math.round((Float) valueAnimator.getAnimatedValue()),
                        textInputLayout.getEditText().getPaddingBottom());
            }
        });

        ValueAnimator editTextSizeAnimator = ValueAnimator.ofFloat(currentTheme.smallTextSize,
                newTheme.smallTextSize);
        editTextSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textInputLayout.getEditText().setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        (Float) valueAnimator.getAnimatedValue());
            }
        });

        return Arrays.asList(colorAnimator, cornerRadiusAnimator, textColorAnimator,
                letterSpacingAnimator, editTextPaddingAnimator, editTextSizeAnimator);
    }

    private List<ValueAnimator> createButtonAnimations(final MaterialButton button,
            MaterialTheme currentTheme, final MaterialTheme newTheme) {
        final boolean transparentButton = button.getBackgroundTintList().getColorForState(
                new int[]{android.R.attr.state_enabled}, Color.TRANSPARENT) == 0;

        ValueAnimator buttonBackgroundAnimator = createColorAnimator(
                currentTheme.colorPrimary.color, newTheme.colorPrimary.color, new ColorCallable() {
                    @Override
                    public Boolean call() {
                        if (!transparentButton) {
                            button.setBackgroundTintList(ColorStateList.valueOf(color));
                        }
                        button.setStrokeColor(ColorStateList.valueOf(color));
                        return true;
                    }
                });

        ValueAnimator buttonTextAnimator = createColorAnimator(
                transparentButton ? currentTheme.colorPrimary.color
                        : currentTheme.colorPrimary.colorOn,
                transparentButton ? newTheme.colorPrimary.color : newTheme.colorPrimary.colorOn,
                new ColorCallable() {
                    @Override
                    public Boolean call() {
                        button.setTextColor(ColorStateList.valueOf(color));

                        // TODO: Might have to add alpha here
                        button.setRippleColor(ColorStateList.valueOf(color));
                        return true;
                    }
                });

        ValueAnimator buttonCornerRadiusAnimator = ValueAnimator.ofFloat(
                currentTheme.smallComponentShapeAppearance.getTopLeftCorner().getCornerSize(),
                newTheme.smallComponentShapeAppearance.getTopLeftCorner().getCornerSize());
        buttonCornerRadiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                button.setCornerRadius(Math.round((Float) valueAnimator.getAnimatedValue()));
            }
        });
        buttonCornerRadiusAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                button.setShapeAppearanceModel(newTheme.smallComponentShapeAppearance);
            }
        });

        ValueAnimator buttonElevationAnimator = ValueAnimator.ofFloat(currentTheme.elevation,
                newTheme.elevation);
        buttonElevationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!transparentButton) {
                    button.setElevation((Float) valueAnimator.getAnimatedValue());
                }
            }
        });

        ValueAnimator buttonLetterSpacingAnimator = ValueAnimator.ofFloat(
                currentTheme.letterSpacingSmall, newTheme.letterSpacingSmall);
        buttonLetterSpacingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                button.setLetterSpacing((Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator buttonTextSizeAnimator = ValueAnimator.ofFloat(currentTheme.smallTextSize,
                newTheme.smallTextSize);
        buttonTextSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                button.setTextSize(
                        TypedValue.COMPLEX_UNIT_PX, (Float) valueAnimator.getAnimatedValue());
            }
        });
        buttonTextSizeAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                button.setAllCaps(newTheme.smallTextAllCaps);
            }
        });

        ValueAnimator buttonPaddingAnimator = ValueAnimator.ofFloat(currentTheme.smallPadding,
                newTheme.smallPadding);
        buttonPaddingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                button.setPaddingRelative(Math.round((Float) valueAnimator.getAnimatedValue()),
                        button.getPaddingTop(),
                        Math.round((Float) valueAnimator.getAnimatedValue()),
                        button.getPaddingBottom());
            }
        });

        return Arrays.asList(buttonTextSizeAnimator, buttonPaddingAnimator,
                buttonLetterSpacingAnimator, buttonBackgroundAnimator, buttonTextAnimator,
                buttonCornerRadiusAnimator, buttonElevationAnimator);
    }

    private List<ValueAnimator> createCardAnimations(final MaterialCardView card,
            MaterialTheme currentTheme, MaterialTheme newTheme) {
        ValueAnimator cardBackgroundAnimator = createColorAnimator(currentTheme.colorSurface,
                newTheme.colorSurface, new ColorCallable() {
                    @Override
                    public Boolean call() {
                        card.setCardBackgroundColor(ColorStateList.valueOf(color));
                        return true;
                    }
                });

        ValueAnimator cardCornerRadiusAnimator = ValueAnimator.ofFloat(
                currentTheme.mediumComponentShapeAppearance.getTopLeftCorner().getCornerSize(),
                newTheme.mediumComponentShapeAppearance.getTopLeftCorner().getCornerSize());
        cardCornerRadiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                card.setRadius((Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator cardElevationAnimator = ValueAnimator.ofFloat(currentTheme.elevation,
                newTheme.elevation);
        cardElevationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                card.setCardElevation((Float) valueAnimator.getAnimatedValue());
            }
        });

        return Arrays.asList(cardBackgroundAnimator, cardCornerRadiusAnimator,
                cardElevationAnimator);
    }

    private ValueAnimator createColorAnimator(int startColor, int endColor,
            final ColorCallable callable) {
        final float[] from = new float[3], to = new float[3];

        Color.colorToHSV(startColor, from);
        Color.colorToHSV(endColor, to);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        final float[] hsv = new float[3];

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Transition along each axis of HSV (hue, saturation, value)
                hsv[0] = from[0] + (to[0] - from[0]) * animation.getAnimatedFraction();
                hsv[1] = from[1] + (to[1] - from[1]) * animation.getAnimatedFraction();
                hsv[2] = from[2] + (to[2] - from[2]) * animation.getAnimatedFraction();

                callable.color = Color.HSVToColor(hsv);
                callable.call();
            }
        });

        return anim;
    }

    private MaterialTheme generateRandomTheme() {
        boolean isDarkTheme = ((Math.random() * 10)) < 3;
        MaterialTheme theme = new MaterialTheme();

        theme.colorSurface =
                isDarkTheme ? darkSurfaceColors[((int) (Math.random() * darkSurfaceColors.length))]
                        : lightSurfaceColors[((int) (Math.random() * lightSurfaceColors.length))];
        theme.colorOnSurface = getResources().getColor(
                isDarkTheme ? R.color.design_dark_default_color_on_surface
                        : R.color.design_default_color_on_surface);

        theme.colorBackground =
                isDarkTheme ? darkSurfaceColors[((int) (Math.random() * darkSurfaceColors.length))]
                        : lightSurfaceColors[((int) (Math.random() * lightSurfaceColors.length))];
        theme.colorOnBackground = getResources().getColor(
                isDarkTheme ? R.color.design_dark_default_color_on_background
                        : R.color.design_default_color_on_background);

        theme.colorError = getResources().getColor(
                isDarkTheme ? R.color.design_dark_default_color_error
                        : R.color.design_default_color_error);
        theme.colorOnError = getResources().getColor(
                isDarkTheme ? R.color.design_dark_default_color_on_error
                        : R.color.design_default_color_on_error);

        theme.colorPrimary = isDarkTheme ? lightColors[((int) (Math.random() * lightColors.length))]
                : darkColors[((int) (Math.random() * darkColors.length))];
        theme.colorSecondary =
                isDarkTheme ? lightColors[((int) (Math.random() * lightColors.length))]
                        : darkColors[((int) (Math.random() * darkColors.length))];

        boolean useCutCorners = Math.random() * 4 < 1;
        theme.smallComponentShapeAppearance = new ShapeAppearanceModel();
        theme.smallComponentShapeAppearance.setAllCorners(
                useCutCorners ? CornerFamily.CUT : CornerFamily.ROUNDED,
                (int) (Math.random() * smallCornerRadiusVariance));

        theme.mediumComponentShapeAppearance = new ShapeAppearanceModel();
        theme.mediumComponentShapeAppearance.setAllCorners(
                useCutCorners ? CornerFamily.CUT : CornerFamily.ROUNDED,
                (int) ((float) (Math.random() * (mediumCornerRadiusVarianceMax
                        - mediumCornerRadiusVarianceMin)) + mediumCornerRadiusVarianceMin));

        theme.elevation = (float) ((Math.random() * (maxElevation - minElevation)) + minElevation);
        theme.letterSpacingSmall = (float) (Math.random() * .17f);

        theme.smallTextSize = (float) ((Math.random() * (getResources().getDimension(
                R.dimen.max_small_text_size) - getResources().getDimension(
                R.dimen.min_small_text_size))) + getResources().getDimension(
                R.dimen.min_small_text_size));
        theme.smallPadding = (float) ((Math.random() * (getResources().getDimension(
                R.dimen.max_small_padding) - getResources().getDimension(
                R.dimen.min_small_padding))) + getResources().getDimension(
                R.dimen.min_small_padding));
        theme.smallTextAllCaps = Math.random() * 2 < 1;

        theme.mediumPadding = (float) ((Math.random() * (getResources().getDimension(
                R.dimen.max_medium_padding) - getResources().getDimension(
                R.dimen.min_medium_padding))) + getResources().getDimension(
                R.dimen.min_medium_padding));
        theme.xSmallPadding = (float) ((Math.random() * (getResources().getDimension(
                R.dimen.max_xsmall_padding) - getResources().getDimension(
                R.dimen.min_xsmall_padding))) + getResources().getDimension(
                R.dimen.min_xsmall_padding));

        theme.isOutlinedTextField = !isDarkTheme;

        int appBarVisibility = (int) (Math.random() * 10);
        if (appBarVisibility < 3) {
            theme.appBarVisibility = 0;
        } else if (appBarVisibility < 6) {
            theme.appBarVisibility = 1;
        } else {
            theme.appBarVisibility = 2;
        }

        return theme;
    }
}
