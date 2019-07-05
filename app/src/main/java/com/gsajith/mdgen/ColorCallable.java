package com.gsajith.mdgen;

import java.util.concurrent.Callable;

public abstract class ColorCallable implements Callable {
    int color;

    @Override
    public abstract Object call();
}
