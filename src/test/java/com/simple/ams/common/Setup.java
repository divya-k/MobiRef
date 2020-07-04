package com.simple.ams.common;

import com.simple.ams.core.android.AndroidCore;
import cucumber.api.java.After;

public class Setup {

    private AndroidCore core;

    public Setup(AndroidCore core) {
        this.core = core;
    }

    @After
    public void teardown(){
        core.quit();
    }

}
