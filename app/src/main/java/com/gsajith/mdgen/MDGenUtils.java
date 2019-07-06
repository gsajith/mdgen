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

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.ArrayList;
import java.util.List;

class MDGenUtils {

    static ColorSet[] generateBaseLightColors() {
        return new ColorSet[]{
                // Yellow
                new ColorSet(Color.parseColor("#fff9c4"), Color.parseColor("#cbc693"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#fff59d"), Color.parseColor("#cbc26d"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#fff176"), Color.parseColor("#cabf45"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ffee58"), Color.parseColor("#c9bc1f"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ffeb3b"), Color.parseColor("#c8b900"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ffee58"), Color.parseColor("#c9bc1f"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ffeb3b"), Color.parseColor("#c8b900"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#fdd835"), Color.parseColor("#c6a700"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#fbc02d"), Color.parseColor("#c49000"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#f9a825"), Color.parseColor("#c17900"),
                        Color.parseColor("#000000")),

                // Red
                new ColorSet(Color.parseColor("#ffebee"), Color.parseColor("#ccb9bc"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ffcdd2"), Color.parseColor("#cb9ca1"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ef9a9a"), Color.parseColor("#ba6b6c"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#e57373"), Color.parseColor("#af4448"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ef5350"), Color.parseColor("#b61827"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#f44336"), Color.parseColor("#ba000d"),
                        Color.parseColor("#000000")),

                // Purple
                new ColorSet(Color.parseColor("#f3e5f5"), Color.parseColor("#c0b3c2"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#e1bee7"), Color.parseColor("#af8eb5"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ce93d8"), Color.parseColor("#9c64a6"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ba68c8"), Color.parseColor("#883997"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ab47bc"), Color.parseColor("#790e8b"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#9c27b0"), Color.parseColor("#6a0080"),
                        Color.parseColor("#FFFFFF")),

                // Indigo
                new ColorSet(Color.parseColor("#7986cb"), Color.parseColor("#49599a"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#9fa8da"), Color.parseColor("#6f79a8"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#c5cae9"), Color.parseColor("#9499b7"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#e8eaf6"), Color.parseColor("#b6b8c3"),
                        Color.parseColor("#000000")),

                // Light Blue
                new ColorSet(Color.parseColor("#e1f5fe"), Color.parseColor("#afc2cb"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#b3e5fc"), Color.parseColor("#82b3c9"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#81d4fa"), Color.parseColor("#4ba3c7"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#4fc3f7"), Color.parseColor("#0093c4"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#29b6f6"), Color.parseColor("#0086c3"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#03a9f4"), Color.parseColor("#007ac1"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#039be5"), Color.parseColor("#006db3"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#0288d1"), Color.parseColor("#005b9f"),
                        Color.parseColor("#FFFFFF")),

                // Green
                new ColorSet(Color.parseColor("#e8f5e9"), Color.parseColor("#b6c2b7"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#c8e6c9"), Color.parseColor("#97b498"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#a5d6a7"), Color.parseColor("#75a478"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#81c784"), Color.parseColor("#519657"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#66bb6a"), Color.parseColor("#338a3e"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#4caf50"), Color.parseColor("#087f23"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#43a047"), Color.parseColor("#00701a"),
                        Color.parseColor("#000000")),

                // Deep Orange
                new ColorSet(Color.parseColor("#fbe9e7"), Color.parseColor("#c8b7b5"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ffccbc"), Color.parseColor("#cb9b8c"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ffab91"), Color.parseColor("#c97b63"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ff8a65"), Color.parseColor("#c75b39"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ff7043"), Color.parseColor("#c63f17"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#ff5722"), Color.parseColor("#c41c00"),
                        Color.parseColor("#000000")),

                // Brown
                new ColorSet(Color.parseColor("#efebe9"), Color.parseColor("#bdb9b7"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#d7ccc8"), Color.parseColor("#a69b97"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#bcaaa4"), Color.parseColor("#8c7b75"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#a1887f"), Color.parseColor("#725b53"),
                        Color.parseColor("#000000")),

                // Blue Grey
                new ColorSet(Color.parseColor("#eceff1"), Color.parseColor("#babdbe"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#cfd8dc"), Color.parseColor("#9ea7aa"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#b0bec5"), Color.parseColor("#808e95"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#90a4ae"), Color.parseColor("#62757f"),
                        Color.parseColor("#000000")),
        };
    }

    public static ColorSet[] generateBaseDarkColors() {
        ColorSet[] colors = new ColorSet[]{
                // Yellow
                new ColorSet(Color.parseColor("#f57f17"), Color.parseColor("#bc5100"),
                        Color.parseColor("#000000")),

                // Red
                new ColorSet(Color.parseColor("#ef5350"), Color.parseColor("#b61827"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#f44336"), Color.parseColor("#ba000d"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#e53935"), Color.parseColor("#ab000d"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#d32f2f"), Color.parseColor("#9a0007"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#c62828"), Color.parseColor("#8e0000"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#b71c1c"), Color.parseColor("#7f0000"),
                        Color.parseColor("#FFFFFF")),

                // Purple
                new ColorSet(Color.parseColor("#ab47bc"), Color.parseColor("#790e8b"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#9c27b0"), Color.parseColor("#6a0080"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#8e24aa"), Color.parseColor("#5c007a"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#7b1fa2"), Color.parseColor("#4a0072"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#6a1b9a"), Color.parseColor("#38006b"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#4a148c"), Color.parseColor("#12005e"),
                        Color.parseColor("#FFFFFF")),

                // Indigo
                new ColorSet(Color.parseColor("#5c6bc0"), Color.parseColor("#26418f"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#3f51b5"), Color.parseColor("#002984"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#3949ab"), Color.parseColor("#00227b"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#303f9f"), Color.parseColor("#001970"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#283593"), Color.parseColor("#001064"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#1a237e"), Color.parseColor("#000051"),
                        Color.parseColor("#FFFFFF")),

                // Light Blue
                new ColorSet(Color.parseColor("#0277bd"), Color.parseColor("#004c8c"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#01579b"), Color.parseColor("#002f6c"),
                        Color.parseColor("#FFFFFF")),

                // Green
                new ColorSet(Color.parseColor("#388e3c"), Color.parseColor("#00600f"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#2e7d32"), Color.parseColor("#005005"),
                        Color.parseColor("#ffffff")),
                new ColorSet(Color.parseColor("#1b5e20"), Color.parseColor("#003300"),
                        Color.parseColor("#ffffff")),

                // Deep Orange
                new ColorSet(Color.parseColor("#f4511e"), Color.parseColor("#b91400"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#e64a19"), Color.parseColor("#ac0800"),
                        Color.parseColor("#ffffff")),
                new ColorSet(Color.parseColor("#d84315"), Color.parseColor("#9f0000"),
                        Color.parseColor("#ffffff")),
                new ColorSet(Color.parseColor("#bf360c"), Color.parseColor("#870000"),
                        Color.parseColor("#ffffff")),

                // Brown
                new ColorSet(Color.parseColor("#8d6e63"), Color.parseColor("#5f4339"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#795548"), Color.parseColor("#4b2c20"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#6d4c41"), Color.parseColor("#40241a"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#5d4037"), Color.parseColor("#321911"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#4e342e"), Color.parseColor("#260e04"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#3e2723"), Color.parseColor("#1b0000"),
                        Color.parseColor("#FFFFFF")),

                // Blue Grey
                new ColorSet(Color.parseColor("#78909c"), Color.parseColor("#4b636e"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#607d8b"), Color.parseColor("#34515e"),
                        Color.parseColor("#000000")),
                new ColorSet(Color.parseColor("#546e7a"), Color.parseColor("#29434e"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#455a64"), Color.parseColor("#1c313a"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#37474f"), Color.parseColor("#102027"),
                        Color.parseColor("#FFFFFF")),
                new ColorSet(Color.parseColor("#263238"), Color.parseColor("#000a12"),
                        Color.parseColor("#FFFFFF")),
        };

        return colors;
    }

    static int[] generateLightSurfaceColors(Resources resources) {
        return new int[]{resources.getColor(R.color.design_default_color_surface),
                resources.getColor(R.color.design_default_color_surface),
                resources.getColor(R.color.design_default_color_surface),
                resources.getColor(R.color.design_default_color_surface),
                resources.getColor(R.color.design_default_color_surface),
                resources.getColor(R.color.design_default_color_surface),
                resources.getColor(R.color.design_default_color_surface),
                resources.getColor(R.color.design_default_color_background),
                Color.parseColor("#f4f4f4"),
                Color.parseColor("#f2f4f6"),
                Color.parseColor("#f4f4f4"),
                Color.parseColor("#f2f4f6"),
                Color.parseColor("#f4f4f4"),
                Color.parseColor("#e3f2fd"),
                Color.parseColor("#f9fbe7"),
                Color.parseColor("#ffebee"),
                Color.parseColor("#efebe9"),
                Color.parseColor("#eceff1"),
                Color.parseColor("#e8f5e9"),
                Color.parseColor("#ede7f6")};
    }

    static int[] generateDarkSurfaceColors(Resources resources) {
        return new int[]{resources.getColor(R.color.design_dark_default_color_surface),
                resources.getColor(R.color.design_dark_default_color_background),
                Color.parseColor("#1a1e32"),
                Color.parseColor("#1e272a"),
                Color.parseColor("#242424"),
                Color.parseColor("#293842"),
                Color.parseColor("#2d393e")};
    }

    static <T extends View> List<T> findViewsWithType(View root, Class<T> type) {
        List<T> views = new ArrayList<>();
        findViewsWithType(root, type, views);
        return views;
    }

    static boolean hasRoundedCorners(ShapeAppearanceModel shapeAppearanceModel) {
        return shapeAppearanceModel.getTopLeftCorner() instanceof RoundedCornerTreatment &&
                shapeAppearanceModel.getTopRightCorner() instanceof RoundedCornerTreatment &&
                shapeAppearanceModel.getBottomLeftCorner() instanceof RoundedCornerTreatment &&
                shapeAppearanceModel.getBottomRightCorner() instanceof RoundedCornerTreatment;
    }

    private static <T extends View> void findViewsWithType(View view, Class<T> type,
            List<T> views) {
        if (type.isInstance(view)) {
            views.add(type.cast(view));
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsWithType(viewGroup.getChildAt(i), type, views);
            }
        }
    }
}
